package com.sbz.battleship.service;

import com.sbz.battleship.repository.GameRepository;
import com.sbz.battleship.repository.PlayerRepository;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;

    public GameServiceImpl(
            GameRepository gameRepository,
            PlayerRepository playerRepository
    ) {
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
    }



}
