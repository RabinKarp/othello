/**
 * The RandomPlayer class implements an AI that just plays randomly, selecting
 * a move at random from all of the moves available to it.
 */

import java.util.*;


class RandomPlayer implements OthelloPlayer
{
    OthelloBoard board;
    OthelloSide side;

    public RandomPlayer()
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
            return new Move(-1, -1);


        ArrayList<Move> validMoves = new ArrayList<Move>();

        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                Move m = new Move(i, j);
                if(board.checkMove(m, side))
                    validMoves.add(m);
            }
        }

        int index = (int) (Math.random() * validMoves.size());
        board.move(validMoves.get(index), side);
        return validMoves.get(index);
    }
}