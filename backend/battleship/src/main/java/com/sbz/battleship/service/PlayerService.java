package com.sbz.battleship.service;

import com.sbz.battleship.domain.exception.BadRequest;
import com.sbz.battleship.domain.exception.NotFound;
import com.sbz.battleship.domain.model.Player;
import com.sbz.battleship.web.dto.GameDto;
import com.sbz.battleship.web.dto.SignInRequest;
import com.sbz.battleship.web.dto.SignUpRequest;

public interface PlayerService {

    Player create(SignUpRequest request) throws BadRequest;
    GameDto signIn(SignInRequest request) throws BadRequest, NotFound;

    Iterable<Player> getAll();
    Player getById(String id) throws NotFound, BadRequest;

}
