/**
 * The RandomPlayer class implements an AI that just plays randomly, selecting
 * a move at random from all of the moves available to it.
 */

import java.util.*;

class ThinkingPlayer implements OthelloPlayer
{
    OthelloBoard board;
    OthelloSide side;

    public ThinkingPlayer()
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

        if( ! board.hasMoves(side))
        {
            return null;
        }

        Move maxMove = null;
        int maxScore = 0;

        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                Move m = new Move(i, j);
                if(board.checkMove(m, side)) {

                    int score = 0;

                    if(maxMove == null || score > maxScore) {
                        maxScore = score;
                        maxMove = m;
                    }
                }
            }
        }

        board.move(maxMove, side);
        return maxMove;
    }
}