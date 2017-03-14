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
                    int score = 1;
                    if((i == 0 && j == 0)
                        || (i == 0 && j == 7)
                        || (i == 7 && j == 0)
                        || (i == 7 && j == 7))
                    {
                        score *= 10;
                    }
                    else if((i > 1 && i < 6 && j == 0)
                        || (i > 1 && i < 6 && j == 7)
                        || (j > 1 && j < 6 && i == 0)
                        || (j > 1 && j < 6 && j == 7))
                    {
                        score *= 5;
                    }
                    else if((i == 0 && (j == 1 || j == 6))
                            || (i == 7 && (j == 1 || j == 6))
                            || (j == 0 && (i == 1 || i == 6))
                            || (j == 7 && (i == 1 || i == 6)))
                    {
                        score *= -1;
                    }
                    else if((i == 1 && j == 1)
                            || (i == 1 && j == 6)
                            || (i == 6 && j == 1)
                            || (i == 6 && j == 6))
                    {
                        score *= -10;
                    }


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