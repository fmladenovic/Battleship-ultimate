package com.sbz.battleship.domain.model.enums;

import com.sbz.battleship.domain.model.Move;
import com.sbz.battleship.domain.model.Tuple;

import java.lang.reflect.Executable;
import java.util.*;

public enum Strategy {
    PLAYER,

    RANDOM,
    EVERY_SECOND_VERTICAL,
    EVERY_SECOND_HORIZONTAL,
    AFTER_HIT,
    AROUND_SHIP,
    AROUND_LAST_MOVE,
    CHECK_LAST_GAME_POSITIONS,
    CHECK_COMMON_POSITIONS;

    public static Move generateMove(Strategy strategy, Region region, List<Move> executedMoves) {
        switch(strategy) {

            case RANDOM:
                return random(region, executedMoves);

            case EVERY_SECOND_HORIZONTAL:
                return everySecondHorizontal(region, executedMoves);

            case EVERY_SECOND_VERTICAL:
                return everySecondVertical(region, executedMoves);

            case AFTER_HIT:
                return random(region, executedMoves);


            case AROUND_SHIP:
                return null;

            default:
                return null;
        }
    }

    // like my last game with him - gadjaj tamo gde si u prosloj partiji sa njim postavljao brodove


    private static Move random(Region region, List<Move> executedMoves) {
        List<Tuple> tuples = removeUsedFields(region, executedMoves);
        int range = tuples.size();
        Random rand = new Random();
        return prepareMove(tuples.get(rand.nextInt(range)), region, Strategy.RANDOM);
    }

    private static Move everySecondHorizontal(Region region, List<Move> executedMoves) {
        Strategy strategy = Strategy.EVERY_SECOND_HORIZONTAL;
        List<Tuple> tuples = removeUsedFields(region, executedMoves);
        int len = tuples.size();
        if(len == 1)
            return prepareMove(tuples.get(0), region, strategy);
        else if(len == 0)
            return random(Region.FREE, executedMoves);
        int i = countExecutedStrategyForRegion(region, strategy, executedMoves);
        return prepareMove(tuples.get(i%len), region, strategy);
    }

    private static Move everySecondVertical(Region region, List<Move> executedMoves) {
        Strategy strategy = Strategy.EVERY_SECOND_VERTICAL;
        List<Tuple> tuples = removeUsedFields(region, executedMoves);
        sort(tuples);
        int len = tuples.size();
        if(len == 1)
            return prepareMove(tuples.get(0), region, strategy);
        int i = countExecutedStrategyForRegion(region, strategy, executedMoves);
        return prepareMove(tuples.get(i%len), region, strategy);
    }




    public static List<Tuple> removeUsedFields( Region region, List<Move> executedMoves ) {
        List<Tuple> tuples = Region.positions(region);
        tuples.removeIf(tuple -> {
            for(Move move : executedMoves) {
                if(move.getPosition().getX() == tuple.getX() && move.getPosition().getY() == tuple.getY())
                    return true;
            }
            return false;
        });
        return tuples;
    }

    private static Move prepareMove(Tuple tuple, Region region, Strategy strategy) {
        Move move = new Move();
        move.setPosition(tuple);
        move.setStrategy(strategy);
        move.setRegion(region);
        return move;
    }

    private static int countExecutedStrategyForRegion(Region region, Strategy strategy, List<Move> executedMoves) {
        int counter = 0;
        for(Move move : executedMoves) {
            if( move.getRegion().equals(region) && move.getStrategy().equals(strategy) ) {
                counter++;
            }
        }
        return counter;
    }

    @SuppressWarnings("unchecked")
	private static void sort(List<Tuple> tuples) {
        Collections.sort(tuples, new Comparator() {
            public int compare(Object o1, Object o2) {
                Integer y1 = ((Tuple) o1).getY();
                Integer y2 = ((Tuple) o2).getY();
                int sComp = y1.compareTo(y2);
                if (sComp != 0) {
                    return sComp;
                }
                Integer x1 = ((Tuple) o1).getX();
                Integer x2 = ((Tuple) o2).getX();
                return x1.compareTo(x2);
            }});
    }

//    private static void sortX(List<Move> moves) {
//        Collections.sort(moves, new Comparator() {
//            public int compare(Object o1, Object o2) {
//                Integer x1 = ((Move) o1).getPosition().getX();
//                Integer x2 = ((Move) o2).getPosition().getX();
//                return x1.compareTo(x2);
//            }});
//    }
//
//    private static void sortY(List<Move> moves) {
//        Collections.sort(moves, new Comparator() {
//            public int compare(Object o1, Object o2) {
//                Integer y1 = ((Move) o1).getPosition().getY();
//                Integer y2 = ((Move) o2).getPosition().getY();
//                return  y1.compareTo(y2);
//                }
//            });
//    }

    private static List<Move> extractHitsInRow(List<Move> executedMoves) {
        List<Move> moves = new ArrayList<>();
        int size = executedMoves.size();
        for( int i = size - 1; i >= 0; i--) {
            Move move = executedMoves.get(i);
            if(move.isHit())
                moves.add(move);
            else
                return moves;
        }
        return moves;
    }

    private static boolean checkTuple(Tuple tuple, List<Move> executedMoves) {
        int x = tuple.getX();
        int y = tuple.getY();
        if(x < 0 || x > 13 || y < 0 || y > 13)
            return false;
        for( Move move : executedMoves ) {
            if( move.getPosition().equals(tuple) )
                return false;
        }
        return true;
    }

    private static Tuple checkOrientation(List<Move> hitsInRow) {
        Move hit1 = hitsInRow.get(hitsInRow.size()-1);
        Move hit2 = hitsInRow.get(hitsInRow.size()-2);
        if( hit1.getPosition().getX() == hit2.getPosition().getX() ) {
            return new Tuple(0, 1);
        } else if( hit1.getPosition().getY() == hit2.getPosition().getY() ) {
            return new Tuple(1, 0);
        }
        return null;
    }

    
    public static Set<Strategy> getAllStrategyes() {

        Set<Strategy> strategies = new HashSet<>();
        strategies.add( EVERY_SECOND_VERTICAL );
        strategies.add( EVERY_SECOND_HORIZONTAL );

        return strategies;
    }


}
