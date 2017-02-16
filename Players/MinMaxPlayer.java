package Players;

import Utils.*;

import java.util.List;

import Utils.Chessboard;

public class MinMaxPlayer extends Player {

	public MinMaxPlayer(String name) 
	{
		super(name);
	}	

	private int getScoreAfterNSteps(int n)
	{
		if( n == 0 )
		{
//			int max =
	//		evaluate(game.getChessboard().getPieces(isWhite()));
		}
		return -1;
	}

        public int evaluate( Chessboard board )
        {
        	//Note! In order for negaMax to work, your Static Evaluation function must 
        	//return a score relative to the side to being evaluated. (e.g. the simplest 
        	//score evaluation could be: 
        	//score = materialWeight * (numWhitePieces - numBlackPieces) * who2move 
        	//where who2move = 1 for white, and who2move = -1 for black).
            return board.calcMaterialScore() - calcBlokedPawns() + calcMobility();
        }
        
        int negaMax( int depth, Chessboard board ) 
        {
            if ( depth == 0 ) 
                return evaluate( board );
            int max = -9999999;
            for ( Move i : board.gatherAllMoves( this )  
            {
                // make move
                Chessboard clonedBoard = new Chessboard(board);
                clonedBoard.execute(i);
                int score = -negaMax( depth - 1, clonedBoard );
                // unmake move
                if( score > max )
                    max = score;
            }
            return max;
        }

	@Override
	protected Move chooseMove(List<Move> moves) 
        {
		Chessboard c = new Chessboard(game.getChessboard());
		Move bestMove = moves.get(0);
		for(Move i : moves)
		{
			
		}
		return null;
		//return moves.get(r.nextInt(moves.size()));	
	
	}

}
