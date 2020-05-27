package com.sbz.battleship.domain.model.decisions;

import com.sbz.battleship.domain.model.Move;
import com.sbz.battleship.domain.model.Ship;
import com.sbz.battleship.domain.model.Tuple;
import com.sbz.battleship.domain.model.enums.AgendaGroup;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.drools.core.factmodel.traits.Traitable;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Traitable
public class AgendaGroupDecision {

    private List<Ship> lastPlayShipsPositions;
    private List<Tuple> mostCommonShipPosition;

    private List<Move> moves;

    private AgendaGroup decision;

    Boolean checkLastPositions;
}
