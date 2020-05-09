package com.sbz.battleship.service;

import com.sbz.battleship.domain.model.Game;
import com.sbz.battleship.web.dto.GameRequest;

public interface GameService {

    Game creat(GameRequest gameRequest);

}
