package com.sbz.battleship.domain.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Tuple {
    private int x;
    private int y;

    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;
        if( !(obj instanceof Tuple) )
            return false;
        Tuple tuple = (Tuple) obj;
        int x = tuple.getX();
        int y = tuple.getY();

        return this.x == x && this.y == y;
    }
}
