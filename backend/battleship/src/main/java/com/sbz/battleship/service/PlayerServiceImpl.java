package com.sbz.battleship.service;

import com.sbz.battleship.domain.exception.BadRequest;
import com.sbz.battleship.domain.exception.NotFound;
import com.sbz.battleship.domain.model.*;
import com.sbz.battleship.domain.model.enums.Formation;
import com.sbz.battleship.domain.model.enums.Region;
import com.sbz.battleship.domain.model.enums.Strategy;
import com.sbz.battleship.repository.GameRepository;
import com.sbz.battleship.repository.PlayerRepository;
import com.sbz.battleship.utility.Formations;
import com.sbz.battleship.web.dto.GameDto;
import com.sbz.battleship.web.dto.PlayerDto;
import com.sbz.battleship.web.dto.SignInRequest;
import com.sbz.battleship.web.dto.SignUpRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    private final GameRepository gameRepository;


    public PlayerServiceImpl(
            PlayerRepository playerRepository,
            GameRepository gameRepository
            ) {
        this.playerRepository = playerRepository;
        this.gameRepository = gameRepository;
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

        return this.playerRepository.save(player);
    }

    @Override
    public GameDto signIn(SignInRequest request) throws BadRequest, NotFound {
        Player player = this.getByEmailAndPassword(request);

        Game game = new Game();
        UUID id = UUID.randomUUID();
        game.setId(id);
        game.setPlayerShips(new Ships(Formation.PLAYER, new ArrayList<>()));
        game.setPlayerMoves(new ArrayList<>());
        game.setComputerMoves(new ArrayList<>());
        game.setWinner(null); // not ended game
        game.setComputerShips(Formations.initialFormation()); // TODO: FILL WITH REZONER

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
        game.setComputerShips(Formations.initialFormation()); // TODO: FILL WITH REZONER

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
