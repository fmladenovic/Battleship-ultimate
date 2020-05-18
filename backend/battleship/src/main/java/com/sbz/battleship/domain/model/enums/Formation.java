package com.sbz.battleship.domain.model.enums;

import com.sbz.battleship.domain.model.Ship;
import com.sbz.battleship.domain.model.Ships;
import com.sbz.battleship.domain.model.Tuple;

import java.util.Arrays;
import java.util.HashSet;
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
                return formation1();
            case FORMATION2:
                return formation2();
            case FORMATION3:
                return formation3();
            case FORMATION4:
                return formation4();
            case FORMATION5:
                return formation5();
            case FORMATION6:
                return formation6();
            case FORMATION7:
                return formation7();
            case FORMATION8:
                return formation8();
            case FORMATION9:
                return formation9();
            case FORMATION10:
                return formation10();
            default:
                return test(INITIAL);
        }
    }



    public static Ships formation1() {
        Ship ship1 = new Ship(Arrays.asList(new Tuple(1, 0), new Tuple(1, 1), new Tuple(1, 2), new Tuple(1, 3), new Tuple(1, 4)), 5);
        Ship ship2 = new Ship(Arrays.asList(new Tuple(2, 1), new Tuple(3, 1), new Tuple(4, 1), new Tuple(5, 1)), 4);
        Ship ship3 = new Ship(Arrays.asList(new Tuple(6, 0), new Tuple(6, 1), new Tuple(6, 2)), 3);
        Ship ship4 = new Ship(Arrays.asList(new Tuple(10, 0), new Tuple(10, 1), new Tuple(10, 2)), 3);
        Ship ship5 = new Ship(Arrays.asList(new Tuple(2, 2), new Tuple(2, 3)), 2);
        return new Ships(Formation.FORMATION1, Arrays.asList(ship1, ship2, ship3, ship4, ship5));
    }

    public static Ships formation2() {
        Ship ship1 = new Ship(Arrays.asList( new Tuple(11, 9), new Tuple(11, 10), new Tuple(11, 11), new Tuple(11, 12), new Tuple(11, 13)), 5);
        Ship ship2 = new Ship(Arrays.asList( new Tuple(4, 10), new Tuple(4, 11), new Tuple(4, 12), new Tuple(4, 13)), 4);
        Ship ship3 = new Ship(Arrays.asList( new Tuple(7, 11), new Tuple(7, 12), new Tuple(7, 13)), 3);
        Ship ship4 = new Ship(Arrays.asList( new Tuple(9, 11), new Tuple(9, 12), new Tuple(9, 13)), 3);
        Ship ship5 = new Ship(Arrays.asList( new Tuple(8, 7), new Tuple(8, 8)), 2);
        return new Ships(Formation.FORMATION2, Arrays.asList(ship1, ship2, ship3, ship4, ship5));
    }

    public static Ships formation3() {
        Ship ship1 = new Ship(Arrays.asList(new Tuple(0, 4), new Tuple(0, 3), new Tuple(0, 2), new Tuple(0, 1), new Tuple(0, 0)), 5);
        Ship ship2 = new Ship(Arrays.asList(new Tuple(0, 13), new Tuple(1, 13), new Tuple(2, 13), new Tuple(3, 13)), 4);
        Ship ship3 = new Ship(Arrays.asList(new Tuple(13, 13), new Tuple(13, 12), new Tuple(13, 11)), 3);
        Ship ship4 = new Ship(Arrays.asList(new Tuple(13, 0), new Tuple(12, 0), new Tuple(11, 0)), 3);
        Ship ship5 = new Ship(Arrays.asList(new Tuple(7, 7), new Tuple(6, 7)), 2);
        return new Ships(Formation.FORMATION3, Arrays.asList(ship1, ship2, ship3, ship4, ship5));
    }

    public static Ships formation4() {
        Ship ship1 = new Ship(Arrays.asList(new Tuple(6, 8), new Tuple(6, 7), new Tuple(6, 6), new Tuple(6, 5), new Tuple(6, 4)), 5);
        Ship ship2 = new Ship(Arrays.asList(new Tuple(9, 7), new Tuple(9, 6), new Tuple(9, 5), new Tuple(9, 4)), 4);
        Ship ship3 = new Ship(Arrays.asList(new Tuple(8, 3), new Tuple(7, 3), new Tuple(6, 3)),3);
        Ship ship4 = new Ship(Arrays.asList(new Tuple(9, 8), new Tuple(8, 8), new Tuple(7, 8)), 3);
        Ship ship5 = new Ship(Arrays.asList(new Tuple(8, 6), new Tuple(7, 6)), 2);
        return new Ships(Formation.FORMATION4, Arrays.asList(ship1, ship2, ship3, ship4, ship5));
    }

    public static Ships formation5() {
        Ship ship1 = new Ship(Arrays.asList( new Tuple(13, 8), new Tuple(13, 7), new Tuple(13, 6), new Tuple(13, 5), new Tuple(13, 4)), 5);
        Ship ship2 = new Ship(Arrays.asList( new Tuple(8, 13), new Tuple(7, 13), new Tuple(6, 13), new Tuple(5, 13)), 4);
        Ship ship3 = new Ship(Arrays.asList( new Tuple(7, 0), new Tuple(6, 0), new Tuple(5, 0)), 3);
        Ship ship4 = new Ship(Arrays.asList( new Tuple(0, 8), new Tuple(0, 7), new Tuple(0, 6)), 3);
        Ship ship5 = new Ship(Arrays.asList( new Tuple(0, 10), new Tuple(0, 9)), 2);
        return new Ships(Formation.FORMATION5, Arrays.asList(ship1, ship2, ship3, ship4, ship5));
    }

    public static Ships formation6() {
        Ship ship1 = new Ship(Arrays.asList( new Tuple(4, 9), new Tuple(4, 8), new Tuple(4, 7), new Tuple(4, 6), new Tuple(4, 5)), 5);
        Ship ship2 = new Ship(Arrays.asList( new Tuple(6, 9), new Tuple(6, 8), new Tuple(6, 7), new Tuple(6, 6)), 4);
        Ship ship3 = new Ship(Arrays.asList( new Tuple(8, 9), new Tuple(8, 8), new Tuple(8, 7)), 3);
        Ship ship4 = new Ship(Arrays.asList( new Tuple(10, 9), new Tuple(10, 8), new Tuple(10, 7)), 3);
        Ship ship5 = new Ship(Arrays.asList( new Tuple(10, 5), new Tuple(10, 4)), 2);
        return new Ships(Formation.FORMATION6, Arrays.asList(ship1, ship2, ship3, ship4, ship5));
    }

    public static Ships formation7() {
        Ship ship1 = new Ship(Arrays.asList( new Tuple(8, 1), new Tuple(7, 1), new Tuple(6, 1), new Tuple(5, 1), new Tuple(4, 1)), 5);
        Ship ship2 = new Ship(Arrays.asList( new Tuple(3, 13), new Tuple(2, 13), new Tuple(1, 13), new Tuple(0, 13)), 4);
        Ship ship3 = new Ship(Arrays.asList( new Tuple(7, 11), new Tuple(7, 12), new Tuple(7, 13)), 3);
        Ship ship4 = new Ship(Arrays.asList( new Tuple(12, 0), new Tuple(12, 1), new Tuple(12, 2)), 3);
        Ship ship5 = new Ship(Arrays.asList( new Tuple(0, 6), new Tuple(0, 7)), 2);
        return new Ships(Formation.FORMATION7, Arrays.asList(ship1, ship2, ship3, ship4, ship5));
    }

    public static Ships formation8() {
        Ship ship1 = new Ship(Arrays.asList( new Tuple(6, 4), new Tuple(6, 3), new Tuple(6, 2), new Tuple(6, 1), new Tuple(6, 0)), 5);
        Ship ship2= new Ship(Arrays.asList( new Tuple(6, 13), new Tuple(6, 12), new Tuple(6, 11), new Tuple(6, 10)), 4);
        Ship ship3 = new Ship(Arrays.asList( new Tuple(0, 5), new Tuple(1, 5), new Tuple(2, 5)), 3);
        Ship ship4 = new Ship(Arrays.asList( new Tuple(0, 9), new Tuple(1, 9), new Tuple(2, 9)), 3);
        Ship ship5 = new Ship(Arrays.asList( new Tuple(13, 7), new Tuple(12, 7)), 2);
        return new Ships(Formation.FORMATION8, Arrays.asList(ship1, ship2, ship3, ship4, ship5));
    }

    public static Ships formation9() {
        Ship ship1 = new Ship(Arrays.asList( new Tuple(12, 13), new Tuple(12, 12), new Tuple(12, 11), new Tuple(12, 10), new Tuple(12, 9)), 5);
        Ship ship2 = new Ship(Arrays.asList( new Tuple(11, 6), new Tuple(10, 6), new Tuple(9, 6), new Tuple(8, 6)), 4);
        Ship ship3 = new Ship(Arrays.asList( new Tuple(7, 5), new Tuple(7, 4), new Tuple(7, 3)), 3);
        Ship ship4 = new Ship(Arrays.asList( new Tuple(6, 2), new Tuple(5, 2), new Tuple(4, 2)), 3);
        Ship ship5 = new Ship(Arrays.asList( new Tuple(12, 8), new Tuple(12, 7)), 2);
        return new Ships(Formation.FORMATION9, Arrays.asList(ship1, ship2, ship3, ship4, ship5));
    }

    public static Ships formation10() {
        Ship ship1 = new Ship(Arrays.asList( new Tuple(2, 8), new Tuple(2, 7), new Tuple(2, 6), new Tuple(2, 5), new Tuple(2, 4)), 5);
        Ship ship2 = new Ship(Arrays.asList( new Tuple(6, 6), new Tuple(5, 6), new Tuple(4, 6), new Tuple(3, 6)), 4);
        Ship ship3 = new Ship(Arrays.asList( new Tuple(7, 5), new Tuple(7, 4), new Tuple(7, 3)), 3);
        Ship ship4 = new Ship(Arrays.asList( new Tuple(7, 9), new Tuple(7, 8), new Tuple(7, 7)), 3);
        Ship ship5 = new Ship(Arrays.asList( new Tuple(8, 6), new Tuple(9, 6)), 2);
        return new Ships(Formation.FORMATION10, Arrays.asList(ship1, ship2, ship3, ship4, ship5));
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
