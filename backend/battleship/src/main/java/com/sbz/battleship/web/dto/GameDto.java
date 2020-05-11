package com.sbz.battleship.web.dto;

import com.sbz.battleship.domain.model.Move;
import com.sbz.battleship.domain.model.Player;
import com.sbz.battleship.domain.model.Ships;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GameDto {
    private UUID id;
    private PlayerDto player;
    private List<Move> playerMoves;
    private Ships playerShips;
    private List<Move> computerMoves;
    private Ships computerShips;
}
