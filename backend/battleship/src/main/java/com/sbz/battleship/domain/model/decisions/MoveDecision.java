package com.sbz.battleship.domain.model.decisions;

import com.sbz.battleship.domain.model.Move;
import com.sbz.battleship.domain.model.Ship;
import com.sbz.battleship.domain.model.Tuple;
import com.sbz.battleship.domain.model.enums.Region;
import com.sbz.battleship.domain.model.enums.Strategy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MoveDecision {

    // For strategy
    // For region

    private List<Ship> lastPlayShipsPositions;
    private List<Tuple> mostCommonShipPosition;

    private List<Move> moves; // played moves in this game

    private Set<Region> regions;
    private Set<Strategy> strategies;

    private boolean readyForDecision = false;
    private Move decision;
}
