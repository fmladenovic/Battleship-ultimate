package com.sbz.battleship.web.controller;

import com.sbz.battleship.domain.exception.BadRequest;
import com.sbz.battleship.domain.exception.NotFound;
import com.sbz.battleship.domain.model.Player;
import com.sbz.battleship.service.PlayerService;
import com.sbz.battleship.web.dto.GameDto;
import com.sbz.battleship.web.dto.SignInRequest;
import com.sbz.battleship.web.dto.SignUpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(value = "api/player")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController( PlayerService playerService ) {
        this.playerService = playerService;
    }

    @RequestMapping(
            path= "",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(this.playerService.getAll());
    }

    @RequestMapping(
            path= "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getById(
            @PathVariable String id
    ) throws NotFound, BadRequest {
        return ResponseEntity.ok(this.playerService.getById(id));
    }

    @RequestMapping(
            path= "",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> create(
            @RequestBody SignUpRequest request
    ) throws BadRequest {
        Player player = this.playerService.create(request);
        UriComponents url = UriComponentsBuilder.newInstance().path("/api/player/{id}").buildAndExpand(player.getId());
        return ResponseEntity.created(url.toUri()).build();
    }

    @RequestMapping(
            path= "",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> signIn(
            @RequestBody SignInRequest request
    ) throws BadRequest, NotFound {
        GameDto game = this.playerService.signIn(request);
        return ResponseEntity.ok(game);
    }



}
