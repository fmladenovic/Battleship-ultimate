package com.sbz.battleship.utility;

import com.sbz.battleship.domain.model.Ship;
import com.sbz.battleship.domain.model.Ships;
import com.sbz.battleship.domain.model.Tuple;
import com.sbz.battleship.domain.model.enums.Formation;

import java.util.Arrays;

public class Formations {

    public static Ships initialFormation() {

        Ship ship1 = new Ship(
                Arrays.asList(
                        new Tuple(0, 0),
                        new Tuple(0, 1),
                        new Tuple(0, 2),
                        new Tuple(0, 3),
                        new Tuple(0, 4)
                ), 5);
        Ship ship2 = new Ship(
                Arrays.asList(
                        new Tuple(1, 0),
                        new Tuple(2, 0),
                        new Tuple(3, 0),
                        new Tuple(4, 0)
                ), 4);
        Ship ship3 = new Ship(
                Arrays.asList(
                        new Tuple(9, 0),
                        new Tuple(9, 1),
                        new Tuple(9, 2)
                ), 3);
        Ship ship4 = new Ship(
                Arrays.asList(
                        new Tuple(5, 0),
                        new Tuple(5, 1),
                        new Tuple(5, 2)
                ), 3);
        Ship ship5 = new Ship(
                Arrays.asList(
                        new Tuple(8, 0),
                        new Tuple(8, 1)
                ), 2);

        return new Ships(Formation.INITIAL, Arrays.asList(ship1, ship2, ship3, ship4, ship5));
    }

}
