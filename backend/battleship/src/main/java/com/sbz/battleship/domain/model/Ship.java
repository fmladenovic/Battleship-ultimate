package com.sbz.battleship.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Ship {

    private List<Tuple> positions;
    private int size;

    public boolean isTupleInShip(Tuple tuple) {
        for(Tuple t : this.positions) {
            if(t.equals(tuple)) {
                return true;
            }
        }
        return false;
    }


}
