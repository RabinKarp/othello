import java.util.*;

class AlphaBetaPlayer implements OthelloPlayer {
    OthelloBoard board;
    OthelloSide side;
    final int DEPTH = 4;

    public AlphaBetaPlayer() {
        side = null;
        board = new OthelloBoard();
    }

    @Override
    public void init(OthelloSide s) {
        side = s;
    }

    @Override
    public Move doMove(Move opponentsMove, long millisLeft) {

        board.move(opponentsMove, side.opposite());

        if (!board.hasMoves(side)) {
            return null;
        }

        MoveScore best = alphaBetaMove(board, DEPTH, -Integer.MAX_VALUE, Integer.MAX_VALUE);

        board.move(best.move, side);
        return best.move;
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

    private MoveScore alphaBetaMove(OthelloBoard board, int depth, int a, int b) {

        if (depth == 0) {
            return new MoveScore(new Move(0, 0), computeScore(board));
        }

        ArrayList<Move> moves = getValidMoves(board, side);
        MoveScore max = new MoveScore(new Move(0, 0), -Integer.MAX_VALUE / 2);

        for (Move m : moves) {
            int beta = b;
            int i = m.getX();
            int j = m.getY();

            int min = Integer.MAX_VALUE;
            OthelloBoard cpy1 = board.copy();
            cpy1.move(m, side);
            ArrayList<Move> oMoves = getValidMoves(cpy1, side.opposite());

            for (Move o : oMoves) {
                OthelloBoard cpy2 = cpy1.copy();
                cpy2.move(o, side.opposite());

                MoveScore next = alphaBetaMove(cpy2, depth - 1, a, beta);
                min = Math.min(next.score, min);
                beta = Math.min(beta, next.score);
                if (beta <= a)
                    break;
            }

            if (min >= max.score) {
                max.move = m;
                max.score = min;
                a = Math.max(a, min);
            }
            if (b <= a)
                break;
        }
        return max;
    }

    private int computeScore(OthelloBoard b) {

        int score = b.countWhite() - b.countBlack();
        if (side == OthelloSide.BLACK)
            score *= -1;
        return score;
    }

    private ArrayList<Move> getValidMoves(OthelloBoard b, OthelloSide s) {
        ArrayList<Move> moves = new ArrayList<Move>();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Move m = new Move(i, j);
                if (b.checkMove(m, s)) {
                    moves.add(m);
                }
            }
        }

        return moves;
    }
}