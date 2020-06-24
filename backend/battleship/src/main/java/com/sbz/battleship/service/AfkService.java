package com.sbz.battleship.service;

import java.util.UUID;

public interface AfkService {

    void warnFrontend(UUID gameId);
    void changeFrontend(UUID gameId);
}
