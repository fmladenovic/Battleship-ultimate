package com.sbz.battleship.service;

import com.sbz.battleship.domain.exception.BadRequest;
import com.sbz.battleship.domain.exception.NotFound;
import com.sbz.battleship.domain.model.*;

import java.util.List;

public interface GameService {

    Iterable<Game> getAll();
    Game getById(String id) throws NotFound, BadRequest;

    void addComputerMove(String id) throws NotFound, BadRequest;
    void addPlayerMove(String id, Tuple tuple) throws NotFound, BadRequest;

    void addComputerShips(String id) throws NotFound, BadRequest;
    void addPlayerShips(String id, List<Ship> ships) throws NotFound, BadRequest;


}
