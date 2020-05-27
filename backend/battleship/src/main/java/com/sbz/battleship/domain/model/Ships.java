package com.sbz.battleship.domain.model;

import com.sbz.battleship.domain.model.enums.Formation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Ships {

    private Formation formation;
    private List<Ship> ships;


    public boolean isTupleInAnyShip(Tuple tuple) {
        for( Ship ship : ships ) {
            if(ship.isTupleInShip(tuple)) {
                return true;
            }
        }
        return false;
    }
}
