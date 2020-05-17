package com.sbz.battleship.domain.model.enums;

import com.sbz.battleship.domain.model.Move;
import com.sbz.battleship.domain.model.Ship;
import com.sbz.battleship.domain.model.Ships;
import com.sbz.battleship.domain.model.Tuple;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public enum Formation {
    PLAYER,
    INITIAL,

    FORMATION1,
    FORMATION2,
    FORMATION3,
    FORMATION4,
    FORMATION5,
    FORMATION6,
    FORMATION7,
    FORMATION8,
    FORMATION9,
    FORMATION10;


    public static Ships generateShips(Formation formation) {
        System.out.println(formation);

        switch(formation) {
            case FORMATION1:
                return initialFormation1();
            case FORMATION2:
                return initialFormation2();
            case FORMATION3:
                return initialFormation3();
            case FORMATION4:
                return initialFormation4();
            case FORMATION5:
                return initialFormation5();
            case FORMATION6:
                return initialFormation6();
            case FORMATION7:
                return initialFormation7();
            case FORMATION8:
                return initialFormation8();
            case FORMATION9:
                return initialFormation9();
            case FORMATION10:
                return initialFormation10();
            default:
                return test(INITIAL);
        }
    }



    public static Ships initialFormation1() {

        Ship ship1 = new Ship(
                Arrays.asList(
                        new Tuple(1, 0),
                        new Tuple(1, 1),
                        new Tuple(1, 2),
                        new Tuple(1, 3),
                        new Tuple(1, 4)
                ), 5);
        Ship ship2 = new Ship(
                Arrays.asList(
                        new Tuple(2, 1),
                        new Tuple(3, 1),
                        new Tuple(4, 1),
                        new Tuple(5, 1)
                ), 4);
        Ship ship3 = new Ship(
                Arrays.asList(
                        new Tuple(6, 0),
                        new Tuple(6, 1),
                        new Tuple(6, 2)
                ), 3);
        Ship ship4 = new Ship(
                Arrays.asList(
                        new Tuple(10, 0),
                        new Tuple(10, 1),
                        new Tuple(10, 2)
                ), 3);
        Ship ship5 = new Ship(
                Arrays.asList(
                        new Tuple(2, 2),
                        new Tuple(2, 3)
                ), 2);

        return new Ships(Formation.FORMATION1, Arrays.asList(ship1, ship2, ship3, ship4, ship5));
    }

    public static Ships initialFormation2() {

        Ship ship1 = new Ship(
                Arrays.asList(
                        new Tuple(1, 0),
                        new Tuple(1, 1),
                        new Tuple(1, 2),
                        new Tuple(1, 3),
                        new Tuple(1, 4)
                ), 5);
        Ship ship2 = new Ship(
                Arrays.asList(
                        new Tuple(2, 1),
                        new Tuple(3, 1),
                        new Tuple(4, 1),
                        new Tuple(5, 1)
                ), 4);
        Ship ship3 = new Ship(
                Arrays.asList(
                        new Tuple(6, 0),
                        new Tuple(6, 1),
                        new Tuple(6, 2)
                ), 3);
        Ship ship4 = new Ship(
                Arrays.asList(
                        new Tuple(10, 0),
                        new Tuple(10, 1),
                        new Tuple(10, 2)
                ), 3);
        Ship ship5 = new Ship(
                Arrays.asList(
                        new Tuple(2, 2),
                        new Tuple(2, 3)
                ), 2);

        return new Ships(Formation.FORMATION2, Arrays.asList(ship1, ship2, ship3, ship4, ship5));
    }

    public static Ships initialFormation3() {
        return test(FORMATION3);
    }
    public static Ships initialFormation4() {
        return test(FORMATION4);
    }
    public static Ships initialFormation5() {
        return test(FORMATION5);
    }
    public static Ships initialFormation6() {
        return test(FORMATION6);
    }
    public static Ships initialFormation7() {
        return test(FORMATION7);
    }
    public static Ships initialFormation8() {
        return test(FORMATION8);
    }
    public static Ships initialFormation9() {
        return test(FORMATION9);
    }
    public static Ships initialFormation10() {
        return test(FORMATION10);
    }


    public static Ships test(Formation formation) {

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

        return new Ships(formation, Arrays.asList(ship1, ship2, ship3, ship4, ship5));
    }

    public static Set<Formation> computerFormations() {
        Set<Formation> formations = new HashSet<>();

        formations.add( FORMATION1 );
        formations.add( FORMATION2 );
        formations.add( FORMATION3 );
        formations.add( FORMATION4 );
        formations.add( FORMATION5 );
        formations.add( FORMATION6 );
        formations.add( FORMATION7 );
        formations.add( FORMATION8 );
        formations.add( FORMATION9 );
        formations.add( FORMATION10 );
        formations.add( INITIAL ); // test
        return formations;
    }


}
