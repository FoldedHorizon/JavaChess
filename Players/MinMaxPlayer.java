package Players;

import Utils.*;
import java.util.List;


public class MinMaxPlayer extends Player
{

	public MinMaxPlayer(String name)
	{
		super(name);
	}
	
	public int evaluate(Chessboard board, boolean isWhite)
	{
		if(isWhite)
			return board.calcMaterialScore() 
				- board.calcBlokedPawns() 
				+ board.calcMobility();
		
		else
			return -( board.calcMaterialScore() 
					- board.calcBlokedPawns() 
					+ board.calcMobility() );
	}

	int negaMax( int depth, Chessboard board, boolean isWhite) 
	{
		
		if ( depth == 0 ) 
			return evaluate( board, isWhite );
		
		try
		{
			board.execute(move);	
		}
		catch(KingDied e)
		{
			return ChessGame.INFINITY;
		}
		
		int max = -ChessGame.INFINITY;
		List<Move> a = board.gatherAllMoves(this.isWhite());
		
		for ( int i = 0; i <  a.size(); i++ )
		{
			Chessboard clonedBoard = new Chessboard(board);

			// make move
			i.score = - negaMax( depth - 1, clonedBoard );
			// unmake move
			if( i.score > max.score )
				max = i;
		}
		return max;
	}

	@Override
	protected Move chooseMove(List<Move> moves)
	{
		Chessboard c = new Chessboard(game.getChessboard());
		Move bestMove = moves.get(0);
		for (Move i : moves)
		{

		}
		return null;
		// return moves.get(r.nextInt(moves.size()));

	}

}
