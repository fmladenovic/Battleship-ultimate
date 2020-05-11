package com.sbz.battleship.service;

import com.sbz.battleship.domain.exception.BadRequest;
import com.sbz.battleship.domain.exception.NotFound;
import com.sbz.battleship.domain.model.*;
import com.sbz.battleship.domain.model.enums.Formation;
import com.sbz.battleship.domain.model.enums.Region;
import com.sbz.battleship.domain.model.enums.Strategy;
import com.sbz.battleship.repository.GameRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GameServiceImpl implements GameService {

    public final int X = 14;
    public final int Y = 14;

    private final GameRepository gameRepository;

    public GameServiceImpl(
            GameRepository gameRepository
    ) {
        this.gameRepository = gameRepository;
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
    public void addComputerMove(String id) throws NotFound, BadRequest {
        Game game = this.getByIdFromRepo(id);
        // game.getComputerMoves().add(move);
        this.gameRepository.save(game);
    }

    @Override
    public void addComputerShips(String id) throws NotFound, BadRequest {
        Game game = this.getByIdFromRepo(id);
        // game.setComputerShips(ships);
        this.gameRepository.save(game);
    }


    // TODO CHANGE METHOD - FRONTEND VS BACKEND
    @Override
    public void addPlayerMove(String id, Tuple tuple) throws NotFound, BadRequest {
        Game game = this.getByIdFromRepo(id);
        this.isOnBoard(tuple, X, Y);

        Move move = new Move(tuple, false, Strategy.PLAYER, Region.PLAYER);
        boolean hit = this.isBoatHit(tuple, game.getComputerShips());
        move.setHit(hit);

        if(hit) {
            System.out.println("Player move");
        } else {
            System.out.println("Call computer move"); //TODO: REZONER!
        }

        game.getPlayerMoves().add(move);
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

    private boolean isOnBoard(Tuple tuple, int x, int y ) throws BadRequest {
        if(tuple == null)
            throw new BadRequest("Tuple must be given");

        int inp_x = tuple.getX();
        int inp_y = tuple.getY();
        if( inp_x < 0 || inp_x >= x || inp_y < 0  || inp_y >= y)
            throw new BadRequest("Chosen tuple is not on a board");

        return true;
    }

    private boolean isBoatHit(Tuple tuple, Ships ships) {
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

}
