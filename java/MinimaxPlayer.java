/**
 * The RandomPlayer class implements an AI that just plays randomly, selecting
 * a move at random from all of the moves available to it.
 */

import java.util.*;

class MinimaxPlayer implements OthelloPlayer
{
    OthelloBoard board;
    OthelloSide side;
    final int DEPTH = 2;

    public MinimaxPlayer()
    {
        side = null;
        board = new OthelloBoard();
    }

    @Override
    public void init(OthelloSide s)
    {
        side = s;
    }

    @Override
    public Move doMove(Move opponentsMove, long millisLeft) {

        board.move(opponentsMove, side.opposite());

        if (! board.hasMoves(side)) {
            return null;
        }

        MoveScore best = minMaxMove(board, DEPTH);

        board.move(best.move, side);
        return best.move;
    }

    private MoveScore minMaxMove(OthelloBoard b, int depth) {

        if(depth == 0) {
            return new MoveScore(new Move(0, 0), computeScore(b));
        }

        ArrayList<Move> moves = getValidMoves(b, side);
        MoveScore max = new MoveScore(new Move(0, 0), -Integer.MAX_VALUE / 2);

        for(Move m : moves) {

            int i = m.getX();
            int j = m.getY();

            int min = Integer.MAX_VALUE;
            OthelloBoard cpy1 = b.copy();
            cpy1.move(m, side);
            ArrayList<Move> oMoves = getValidMoves(cpy1, side.opposite());

            for(Move o : oMoves) {
                OthelloBoard cpy2 = cpy1.copy();
                cpy2.move(o, side.opposite());

                MoveScore next = minMaxMove(cpy2, depth-1);
                min = Math.min(next.score, min);
            }

            if(min >= max.score) {
                max.move = m;
                max.score = min;
            }
        }
        return max;
    }

    private int computeScore(OthelloBoard b) {
        int score = b.countWhite() - b.countBlack();
        if(side == OthelloSide.BLACK)
            score *= -1;
        return score;
    }

    private ArrayList<Move> getValidMoves(OthelloBoard b, OthelloSide s) {
        ArrayList<Move> moves = new ArrayList<Move>();

        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                Move m = new Move(i, j);
                if(b.checkMove(m, s)) {
                    moves.add(m);
                }
            }
        }

        return moves;
    }

    /**
     * Serves the function of a struct that contains a move and its associated minimax score
     */
    private class MoveScore {
        public Move move;
        public int score;

        public MoveScore(Move m, int s) {
            move = m;
            score = s;
        }
    }
}