package com.sbz.battleship.web.controller;

import com.sbz.battleship.domain.exception.BadRequest;
import com.sbz.battleship.domain.exception.NotFound;
import com.sbz.battleship.domain.model.Move;
import com.sbz.battleship.domain.model.Ship;
import com.sbz.battleship.domain.model.Tuple;
import com.sbz.battleship.domain.model.enums.Region;
import com.sbz.battleship.domain.model.enums.Strategy;
import com.sbz.battleship.service.GameService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "api/games")
public class GameController {

    private final GameService gameService;

    public GameController( GameService gameService ) {
        this.gameService = gameService;
    }

    @RequestMapping(
            path= "",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(this.gameService.getAll());
    }

    @RequestMapping(
            path= "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getById(
            @PathVariable String id
    ) throws NotFound, BadRequest {
        return ResponseEntity.ok(this.gameService.getById(id));
    }

    @RequestMapping(
            path= "/{id}/set_ships",
            method = RequestMethod.PUT,
            produces = MediaType.TEXT_PLAIN_VALUE
    )
    public ResponseEntity<?> addPlayerShips(
            @PathVariable String id,
            @RequestBody List<Ship> ships
    ) throws NotFound, BadRequest {
        this.gameService.addPlayerShips(id, ships);
        return ResponseEntity.ok("");
    }

    @RequestMapping(
            path= "/{id}/moves",
            method = RequestMethod.PUT,
            produces = MediaType.TEXT_PLAIN_VALUE
    )
    public ResponseEntity<?> addPlayerMove(
            @PathVariable String id,
            @RequestBody List<Move> moves
    ) throws NotFound, BadRequest {
        this.gameService.addPlayerMoves(id, moves);
        return ResponseEntity.ok("");
    }


    @RequestMapping(
            path= "/{id}/computer",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> computerPlay(
            @PathVariable String id
    ) throws NotFound, BadRequest {
        Move move = new Move(new Tuple(1, 1), false, Strategy.INITIAL, Region.INITIAL);
        return ResponseEntity.ok(move);
    }



}
