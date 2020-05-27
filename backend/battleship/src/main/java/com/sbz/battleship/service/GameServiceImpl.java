package com.sbz.battleship.service;

import com.sbz.battleship.domain.exception.BadRequest;
import com.sbz.battleship.domain.exception.NotFound;
import com.sbz.battleship.domain.model.*;
import com.sbz.battleship.domain.model.decisions.AgendaGroupDecision;
import com.sbz.battleship.domain.model.decisions.MoveDecision;
import com.sbz.battleship.domain.model.enums.Formation;
import com.sbz.battleship.domain.model.enums.Region;
import com.sbz.battleship.domain.model.enums.Strategy;
import com.sbz.battleship.repository.GameRepository;
import com.sbz.battleship.repository.PlayerRepository;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GameServiceImpl implements GameService {

    public final int X = 14;
    public final int Y = 14;

    private final PlayerRepository playerRepository;
    private final GameRepository gameRepository;
    private final KieContainer kContainer;


    public GameServiceImpl(
            PlayerRepository playerRepository,
            GameRepository gameRepository,
            KieContainer kieContainer

    ) {
        this.playerRepository = playerRepository;
        this.gameRepository = gameRepository;
        this.kContainer = kieContainer;
    }

    @Override
    public Iterable<Game> getAll() {
        return this.gameRepository.findAll();
    }

    @Override
    public Game getById(String id) throws NotFound, BadRequest {
        return this.getByIdFromRepo(id);
    }

    @Override
    public Move addComputerMove(String id) throws NotFound, BadRequest {
        Game game = this.getByIdFromRepo(id);
        Player player = this.getPlayerByIdFromRepo(game.getPlayerId().toString());

        Move move = this.resoner(game, player);


        move.setHit(this.isShipHit(move.getPosition(), game.getPlayerShips()));
        game.getComputerMoves().add(move);
        this.gameRepository.save(game);
        return move;
    }

    private Move resoner(Game game, Player player) {

        MoveDecision moveDecision = new MoveDecision();
        moveDecision.setMostCommonShipPosition(player.getMostCommonShipPosition());
        moveDecision.setLastPlayShipsPositions(player.getLastPlayShipsPositions());
        moveDecision.setMoves(game.getComputerMoves()); // played moves in this game
        moveDecision.setRegions(null); // use rules to decide which region you should use
        moveDecision.setStrategies(null); // use rules to decide which strategies you should use
        moveDecision.setReadyForDecision(false);
        moveDecision.setDecision(null);

        AgendaGroupDecision agendaGroupDecision = new AgendaGroupDecision(
                player.getLastPlayShipsPositions(),
                player.getMostCommonShipPosition(),
                game.getComputerMoves(),
                null,
                false
        );
        KieSession kieSession = this.kContainer.newKieSession("session");
        kieSession.setGlobal("forRecheckMoves", new ArrayList<Move>());
        kieSession.setGlobal("availableShips",  new ArrayList<Ship>());


        kieSession.insert(agendaGroupDecision);
        kieSession.fireAllRules();


        System.out.println(agendaGroupDecision.getDecision());

        kieSession.insert(moveDecision);
        switch(agendaGroupDecision.getDecision()) {
            case AFTER_HIT:
                // kieSession.getAgenda().getAgendaGroup("after_hit").setFocus();
                moveDecision.setDecision( Strategy.generateMove(Strategy.AFTER_HIT, Region.FREE, game.getComputerMoves()) ); //DELETE
                System.out.println("Random generated - todo");
                break;
            case LAST_POSITIONS:
                kieSession.getAgenda().getAgendaGroup("last_positions").setFocus();
                break;
            case COMMON_POSITIONS:
                // kieSession.getAgenda().getAgendaGroup("common_positions").setFocus();
                moveDecision.setDecision( Strategy.generateMove(Strategy.RANDOM, Region.FREE, game.getComputerMoves()) ); //DELETE
                System.out.println("Random generated - todo");
                break;
            default: // FINDING_ENEMY
                // kieSession.getAgenda().getAgendaGroup("finding_enemy").setFocus();
                moveDecision.setDecision( Strategy.generateMove(Strategy.RANDOM, Region.FREE, game.getComputerMoves()) ); //DELETE
                System.out.println("Random generated - todo");
                break;
        }
        kieSession.fireAllRules();



        return moveDecision.getDecision();
    }

    @Override
    public void addComputerShips(String id) throws NotFound, BadRequest {
        Game game = this.getByIdFromRepo(id);
        // game.setComputerShips(ships);
        this.gameRepository.save(game);
    }


    @Override
    public void addPlayerMoves(String id, List<Move> moves) throws NotFound, BadRequest {
        Game game = this.getByIdFromRepo(id);
        // TODO VALIDATE DATA
        game.getPlayerMoves().addAll(moves);
        this.gameRepository.save(game);
    }

    @Override
    public void endGame(String id, Boolean victory) throws NotFound, BadRequest {
        Game game = this.getByIdFromRepo(id);
        Player player = this.getPlayerByIdFromRepo(game.getPlayerId().toString());

        player.setLastFirst5Strikes(
                Arrays.asList(
                    game.getPlayerMoves().get(0).getPosition(),
                    game.getPlayerMoves().get(1).getPosition(),
                    game.getPlayerMoves().get(2).getPosition(),
                    game.getPlayerMoves().get(3).getPosition(),
                    game.getPlayerMoves().get(4).getPosition()
                )
        );
        player.setLastPlayShipsPositions(game.getPlayerShips().getShips());
        player.setLastGameVictory(victory);


        player.setComputerLastUsedFormation(game.getComputerShips().getFormation());

        List<Game> games = player.getGames();
        player.setCommonFirst5Strikes(this.commonFirst5Strikes(games));  //or maybe 10
        player.setComputerMostUsedFormations(this.extractCommonComputerFormations(games));



        player.setMostCommonShipPosition(this.extractMostCommonShipsPositions(games));

        // TODO VALIDATE DATA
        game.setWinner(victory);
        this.playerRepository.save(player);
        this.gameRepository.save(game);
    }


    @Override
    public void addPlayerShips(String id, List<Ship> ships) throws NotFound, BadRequest {
        Game game = this.getByIdFromRepo(id);
        game.setPlayerShips(new Ships(Formation.PLAYER, ships));
        this.gameRepository.save(game);
    }



    private Game getByIdFromRepo(String sid) throws NotFound, BadRequest {
        UUID id = null;
        try {
            id = UUID.fromString(sid);
        } catch ( IllegalArgumentException ex ) {
            throw new BadRequest(ex);
        }

        Optional<Game> gameMyb = this.gameRepository.findById(id);
        if(!gameMyb.isPresent())
            throw new NotFound("Game with given id is not found!");
        return gameMyb.get();
    }

    private Player getPlayerByIdFromRepo(String sid) throws NotFound, BadRequest {
        UUID id = null;
        try {
            id = UUID.fromString(sid);
        } catch ( IllegalArgumentException ex ) {
            throw new BadRequest(ex);
        }

        Optional<Player> playerMyb = this.playerRepository.findById(id);
        if(!playerMyb.isPresent())
            throw new NotFound("Player with given id is not found!");
        return playerMyb.get();
    }

    private boolean isOnBoard(Tuple tuple, int x, int y ) throws BadRequest {
        if(tuple == null)
            throw new BadRequest("Tuple must be given");

        int inp_x = tuple.getX();
        int inp_y = tuple.getY();
        if( inp_x < 0 || inp_x >= x || inp_y < 0  || inp_y >= y)
            throw new BadRequest("Chosen tuple is not on a board");

        return true;
    }

    private boolean isShipHit(Tuple tuple, Ships ships) {
        List<Ship> shipList = ships.getShips();
        for( Ship ship : shipList ) {
             List<Tuple> positions = ship.getPositions();
             for(Tuple position : positions) {
                 if( position.getX() == tuple.getX() && position.getY() == tuple.getY())
                     return true;
             }
        }
        return false;
    }

    private Set<Formation> extractCommonComputerFormations(List<Game> games) {
        Map<Formation, Integer> formations = new HashMap<>();

        for(Game game : games) {
            if(game.getWinner() != null) {
                Formation computerFormation = game.getComputerShips().getFormation();
                if(formations.containsKey(computerFormation)){
                    formations.put(computerFormation, formations.get(computerFormation) + 1);
                }
                else {
                    formations.put(computerFormation, 1);
                }
            }
        }
        Set<Formation> top = new HashSet<>();
        formations = this.sortByValueFormations(formations);
        Iterator<Map.Entry<Formation, Integer>> iterator = formations.entrySet().iterator();

        if(formations.size() > 3 ) {
            for(int i = 0; i < 3; i++) {
                top.add(iterator.next().getKey());
            }
        }
//        TODO: TEST
//        for(Formation formation : formations.keySet()) {
//            System.out.println(formation + " " + formations.get(formation));
//        }

        return top;
    }

    private List<Tuple> commonFirst5Strikes(List<Game> games) {
        Map<Tuple, Integer> tuples = new HashMap<>();

        for(Game game : games) {
            if(game.getWinner() != null) {
                List<Move> moves = game.getPlayerMoves();
                int end = moves.size() < 5 ? moves.size() : 5;
                for (int i = 0; i < end; i++) {
                    Tuple tuple = moves.get(i).getPosition();
                    Set<Tuple> keys = tuples.keySet();
                    boolean tupleInKeys = false;
                    for (Tuple key : keys) {
                        if (key.equals(tuple)) {
                            tuples.put(key, tuples.get(key) + 1);
                            tupleInKeys = true;
                        }
                    }
                    if (!tupleInKeys) {
                        tuples.put(tuple, 1);
                    }
                }
            }
        }
        List<Tuple> top5 = new ArrayList<>();
        tuples = this.sortByValue(tuples);
        Iterator<Map.Entry<Tuple, Integer>> iterator = tuples.entrySet().iterator();

        int size = tuples.size() < 5 ? tuples.size() : 5;
        for(int i = 0; i < size; i++) {
            top5.add(iterator.next().getKey());
        }
        return top5;
    }

    public List<Tuple> extractMostCommonShipsPositions(List<Game> games) {
        Map<Tuple, Integer> tuples = new HashMap<>();
        for(Game game : games) {
            if(game.getWinner() != null) {
                List<Ship> ships = game.getPlayerShips().getShips();

                for (Ship s : ships) {
                    for (Tuple t : s.getPositions()) {
                        Set<Tuple> keys = tuples.keySet();
                        boolean tupleInKeys = false;
                        for (Tuple key : keys) {
                            if (key.equals(t)) {
                                tuples.put(key, tuples.get(key) + 1);
                                tupleInKeys = true;
                            }
                        }
                        if (!tupleInKeys) {
                            tuples.put(t, 1);
                        }
                    }
                }
            }
        }
        List<Tuple> top5 = new ArrayList<>();
        tuples = this.sortByValue(tuples);
        Iterator<Map.Entry<Tuple, Integer>> iterator = tuples.entrySet().iterator();

        int size = tuples.size() < 5 ? tuples.size() : 5;
        for(int i = 0; i < size; i++) {
            top5.add(iterator.next().getKey());
        }
        return top5;
    }

    public static HashMap<Tuple, Integer> sortByValue(Map<Tuple, Integer> hm)
    {
        // Create a list from elements of HashMap
        List<Map.Entry<Tuple, Integer> > list =
                new LinkedList<Map.Entry<Tuple, Integer> >(hm.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<Tuple, Integer> >() {
            public int compare(Map.Entry<Tuple, Integer> o1,
                               Map.Entry<Tuple, Integer> o2)
            {
                return -(o1.getValue()).compareTo(o2.getValue());
            }
        });

        // put data from sorted list to hashmap
        HashMap<Tuple, Integer> temp = new LinkedHashMap<Tuple, Integer>();
        for (Map.Entry<Tuple, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

    public static HashMap<Formation, Integer> sortByValueFormations(Map<Formation, Integer> hm)
    {
        // Create a list from elements of HashMap
        List<Map.Entry<Formation, Integer> > list =
                new LinkedList<Map.Entry<Formation, Integer> >(hm.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<Formation, Integer> >() {
            public int compare(Map.Entry<Formation, Integer> o1,
                               Map.Entry<Formation, Integer> o2)
            {
                return -(o1.getValue()).compareTo(o2.getValue());
            }
        });

        // put data from sorted list to hashmap
        HashMap<Formation, Integer> temp = new LinkedHashMap<Formation, Integer>();
        for (Map.Entry<Formation, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }





}
