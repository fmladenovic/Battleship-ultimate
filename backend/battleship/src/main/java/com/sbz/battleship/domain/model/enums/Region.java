package com.sbz.battleship.domain.model.enums;

import com.sbz.battleship.domain.model.Tuple;

import java.util.ArrayList;
import java.util.List;

public enum Region {
    PLAYER, // Player moves

    LEFT_TOP_LEFT_TOP,
    LEFT_TOP_RIGHT_TOP,
    LEFT_TOP_LEFT_BOTTOM,
    LEFT_TOP_RIGHT_BOTTOM,
    LEFT_TOP_MIDDLE,

    RIGHT_TOP_LEFT_TOP,
    RIGHT_TOP_RIGHT_TOP,
    RIGHT_TOP_LEFT_BOTTOM,
    RIGHT_TOP_RIGHT_BOTTOM,
    RIGHT_TOP_MIDDLE,

    LEFT_BOTTOM_LEFT_TOP,
    LEFT_BOTTOM_RIGHT_TOP,
    LEFT_BOTTOM_LEFT_BOTTOM,
    LEFT_BOTTOM_RIGHT_BOTTOM,
    LEFT_BOTTOM_MIDDLE,

    RIGHT_BOTTOM_LEFT_TOP,
    RIGHT_BOTTOM_RIGHT_TOP,
    RIGHT_BOTTOM_LEFT_BOTTOM,
    RIGHT_BOTTOM_RIGHT_BOTTOM,
    RIGHT_BOTTOM_MIDDLE,

    FREE; // Full table

    public static List<Tuple> positions( Region region ) {
        switch(region) {
            case LEFT_TOP_LEFT_TOP:
                return generateCubePositions(0, 0);
            case LEFT_TOP_RIGHT_TOP:
                return generateCubePositions(0, 4);
            case LEFT_TOP_LEFT_BOTTOM:
                return generateCubePositions(4, 0);
            case LEFT_TOP_RIGHT_BOTTOM:
                return generateCubePositions(4, 4);
            case LEFT_TOP_MIDDLE:
                return generatePlusPositions(3, 0);

            case RIGHT_TOP_LEFT_TOP:
                return generateCubePositions(0, 7);
            case RIGHT_TOP_RIGHT_TOP:
                return generateCubePositions(0, 11);
            case RIGHT_TOP_LEFT_BOTTOM:
                return generateCubePositions(4, 7);
            case RIGHT_TOP_RIGHT_BOTTOM:
                return generateCubePositions(4, 11);
            case RIGHT_TOP_MIDDLE:
                return generatePlusPositions(3, 7);

            case LEFT_BOTTOM_LEFT_TOP:
                return generateCubePositions(7, 0);
            case LEFT_BOTTOM_RIGHT_TOP:
                return generateCubePositions(7, 4);
            case LEFT_BOTTOM_LEFT_BOTTOM:
                return generateCubePositions(11, 0);
            case LEFT_BOTTOM_RIGHT_BOTTOM:
                return generateCubePositions(11, 4);
            case LEFT_BOTTOM_MIDDLE:
                return generatePlusPositions(10, 0);

            case RIGHT_BOTTOM_LEFT_TOP:
                return generateCubePositions(7, 7);
            case RIGHT_BOTTOM_RIGHT_TOP:
                return generateCubePositions(7, 11);
            case RIGHT_BOTTOM_LEFT_BOTTOM:
                return generateCubePositions(11, 7);
            case RIGHT_BOTTOM_RIGHT_BOTTOM:
                return generateCubePositions(11, 11);
            case RIGHT_BOTTOM_MIDDLE:
                return generatePlusPositions(10, 7);

            case FREE:
                return generateTable();

            default:
                return null;
        }
    }

    static List<Tuple> generateCubePositions(int x, int y) {
        List<Tuple> tuples = new ArrayList<>();
        for(int i = x; i < x + 3; i++) {
            for(int j = y; j < y + 3; j++) {
                tuples.add(new Tuple(i, j));
            }
        }
        return tuples;
    }

    static List<Tuple> generatePlusPositions(int x1, int y1) {
        int x2 = x1 - 3;
        int y2 = y1 + 3;

        List<Tuple> tuples = new ArrayList<>();

        for( int j = y1; j < y2; j++) {
            tuples.add(new Tuple( x1, j));
        }
        for( int i = x2; i < x1; i++ ) {
            tuples.add(new Tuple( i, y2));
        }
        tuples.add(new Tuple( x1, y2));
        for( int j = y2+1; j < y2+4; j++) {
            tuples.add(new Tuple( x1, j));
        }
        for( int i = x1+1; i < x1+4; i++) {
            tuples.add(new Tuple( i, y2));
        }

        return tuples;
    }


    static List<Tuple> generateTable() {
        List<Tuple> tuples = new ArrayList<>();
        for(int i = 0; i < 14; i++) {
            for(int j = 0; j < 14; j++) {
                tuples.add(new Tuple(i, j));
            }
        }
        return tuples;
    }

}
