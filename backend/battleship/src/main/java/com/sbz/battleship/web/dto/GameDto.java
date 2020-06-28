package com.sbz.battleship.web.dto;

import java.util.List;
import java.util.UUID;

import com.sbz.battleship.domain.model.Move;
import com.sbz.battleship.domain.model.Ships;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private Boolean winner; // true computer, false player

}
