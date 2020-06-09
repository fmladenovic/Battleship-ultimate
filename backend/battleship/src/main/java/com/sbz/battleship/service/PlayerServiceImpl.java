package com.sbz.battleship.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

import com.sbz.battleship.domain.exception.BadRequest;
import com.sbz.battleship.domain.exception.NotFound;
import com.sbz.battleship.domain.model.Game;
import com.sbz.battleship.domain.model.Player;
import com.sbz.battleship.domain.model.Ships;
import com.sbz.battleship.domain.model.Tuple;
import com.sbz.battleship.domain.model.decisions.FormationDecision;
import com.sbz.battleship.domain.model.decisions.SignInDecision;
import com.sbz.battleship.domain.model.enums.Formation;
import com.sbz.battleship.repository.GameRepository;
import com.sbz.battleship.repository.PlayerRepository;
import com.sbz.battleship.web.dto.GameDto;
import com.sbz.battleship.web.dto.PlayerDto;
import com.sbz.battleship.web.dto.SignInRequest;
import com.sbz.battleship.web.dto.SignUpRequest;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    private final GameRepository gameRepository;
    private final KieContainer kContainer;
    private final KieSession cepSession;


    public PlayerServiceImpl(
            PlayerRepository playerRepository,
            GameRepository gameRepository,
            KieContainer kieContainer,
            KieSession cepSession
            ) {
        this.playerRepository = playerRepository;
        this.gameRepository = gameRepository;
        this.kContainer = kieContainer;
        this.cepSession = cepSession;
    }


    @Override
    public Player create(SignUpRequest request) throws BadRequest {
        if( request == null )
            throw new BadRequest("Request must be given!");
        String email = request.getEmail();
        String nick = request.getNick();
        String password = request.getPassword();
        if( email == null || nick == null  || password == null )
            throw new BadRequest("Fields are not filled!");

        if(this.playerRepository.findByEmail(email).size() != 0)
            throw new BadRequest("Player with given email exists!");
        if(this.playerRepository.findByNick(nick).size() != 0)
            throw new BadRequest("Player with given nick exists!");

        Player player = new Player();
        player.setId(UUID.randomUUID());
        player.setEmail(email);
        player.setNick(nick);
        player.setPassword(password);
        player.setGames(new ArrayList<>());

        player.setCommonFirst5Strikes(new ArrayList<>());
        player.setLastFirst5Strikes(new ArrayList<>());
        player.setComputerLastUsedFormation(null);
        player.setLastGameVictory(null);
        player.setComputerMostUsedFormations(new HashSet<>());

        player.setLastPlayShipsPositions(new ArrayList<>());
        player.setMostCommonShipPosition(new ArrayList<>());

        return this.playerRepository.save(player);
    }

    @Override
    public GameDto signIn(SignInRequest request) throws BadRequest, NotFound {        
       
        if( request == null )
            throw new BadRequest("Request must be given!");
        
        String email = request.getEmail();
        String password = request.getPassword();
        
        if( email == null  || password == null )
            throw new BadRequest("Fields are not filled!");
        

        
        List<Player> players = this.playerRepository.findByEmail(email);
        
        if(players.size() == 0) 
            throw new BadRequest("Wrong email!");

        Player player = players.get(0);
        boolean success = player.getPassword().equals(password);
        
        SignInDecision sid = new SignInDecision();
        sid.setPlayerId(player.getId());
        sid.setPlayerNick(player.getNick());
        sid.setSuccess(success);
        
        
        this.cepSession.insert(sid);
        this.cepSession.fireAllRules();
        
        if( sid.getForbiden() )
            throw new BadRequest(sid.getReason());
        if(!success)
            throw new BadRequest("Wrong password!");



        Game game = new Game();
        UUID id = UUID.randomUUID();
        game.setId(id);
        game.setPlayerShips(new Ships(Formation.PLAYER, new ArrayList<>()));
        game.setPlayerMoves(new ArrayList<>());
        game.setComputerMoves(new ArrayList<>());
        game.setWinner(null); // not ended game

        List<Tuple> danger = player.getCommonFirst5Strikes();
        List<Tuple> danger1 = player.getLastFirst5Strikes();
        danger.addAll(danger1);

        FormationDecision decision = new FormationDecision(
                Formation.computerFormations(),
                player.getComputerLastUsedFormation(),
                player.getComputerMostUsedFormations(),
                danger,
                false,
                null
        );


        KieSession kieSession = this.kContainer.newKieSession("session");
        kieSession.setGlobal("forRecheck", new ArrayList<>());
        kieSession.insert(decision);
        kieSession.fireAllRules();

        game.setComputerShips(decision.getDecision());

        player.setComputerLastUsedFormation(decision.getDecision().getFormation());


        game.setPlayerId(player.getId());
        this.gameRepository.save(game);

        player.getGames().add(game);
        this.playerRepository.save(player);

        PlayerDto playerDto = new PlayerDto(player.getId(), player.getEmail(), player.getNick());
        GameDto gameDto = new GameDto(id, playerDto, game.getPlayerMoves(), game.getPlayerShips(),
                game.getComputerMoves(), game.getComputerShips(), null);
        return gameDto;
    }

    @Override
    public GameDto playAgain(String playerId) throws BadRequest, NotFound {
        Player player = this.getByIdFromRepo(playerId);
        Game game = new Game();
        UUID id = UUID.randomUUID();
        game.setId(id);
        game.setPlayerShips(new Ships(Formation.PLAYER, new ArrayList<>()));
        game.setPlayerMoves(new ArrayList<>());
        game.setComputerMoves(new ArrayList<>());
        game.setWinner(null); // not ended game

        List<Tuple> danger = player.getCommonFirst5Strikes();
        List<Tuple> danger1 = player.getLastFirst5Strikes();
        danger.addAll(danger1);

        FormationDecision decision = new FormationDecision(
                Formation.computerFormations(),
                player.getComputerLastUsedFormation(),
                player.getComputerMostUsedFormations(),
                danger,
                false,
                null
        );


        KieSession kieSession = this.kContainer.newKieSession("session");
        kieSession.setGlobal("forRecheck", new ArrayList<>());
        kieSession.insert(decision);
        kieSession.fireAllRules();

        game.setComputerShips(decision.getDecision());

        player.setComputerLastUsedFormation(decision.getDecision().getFormation());
        game.setPlayerId(player.getId());
        this.gameRepository.save(game);

        player.getGames().add(game);
        this.playerRepository.save(player);

        PlayerDto playerDto = new PlayerDto(player.getId(), player.getEmail(), player.getNick());
        GameDto gameDto = new GameDto(id, playerDto, game.getPlayerMoves(), game.getPlayerShips(),
                game.getComputerMoves(), game.getComputerShips(), null);
        return gameDto;
    }

    @Override
    public Iterable<Player> getAll() {
        return this.playerRepository.findAll();
    }

    @Override
    public Player getById(String id) throws NotFound, BadRequest {
        return this.getByIdFromRepo(id);
    }






    private Player getByEmailAndPassword(SignInRequest request) throws BadRequest, NotFound {
        if( request == null )
            throw new BadRequest("Request must be given!");
        String email = request.getEmail();
        String password = request.getPassword();
        if( email == null  || password == null )
            throw new BadRequest("Fields are not filled!");

        Optional<Player> playerMyb = this.playerRepository.findByEmailAndPassword(email, password);
        if( !playerMyb.isPresent() )
            throw new NotFound("Player with given credentials doesn't exist!");

        return playerMyb.get();
    }

    private Player getByIdFromRepo(String sid) throws NotFound, BadRequest {
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

}
