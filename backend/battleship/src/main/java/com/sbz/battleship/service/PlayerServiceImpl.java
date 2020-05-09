package com.sbz.battleship.service;

import com.sbz.battleship.repository.PlayerRepository;
import org.springframework.stereotype.Service;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerServiceImpl(
            PlayerRepository playerRepository
    ) {
        this.playerRepository = playerRepository;
    }



}
