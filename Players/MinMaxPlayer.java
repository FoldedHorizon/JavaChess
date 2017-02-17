package Players;

import Utils.*;
import java.util.List;

public class MinMaxPlayer extends Player
{
	public final static int LEVELOFDEPTH = 6;
	
	public MinMaxPlayer(String name)
	{
		super(name);
	}

	public int evaluate(Chessboard board, boolean isWhite)
	{
		if (isWhite)
			return board.calcMaterialScore() - board.calcBlokedPawns()
					+ board.calcMobility();

		else
			return -(board.calcMaterialScore() - board.calcBlokedPawns() 
					+ board.calcMobility());
	}

	int negaMax(int depth, Chessboard board, boolean isWhite, Move move)
	{
		//executing move
		Chessboard clonedBoard = Chessboard.copy(board);
		try
		{
			clonedBoard.execute(move);
		}
		catch (KingDied e)
		{
			return ChessGame.INFINITY;
		}

		//evaluating leaf 
		if (depth == 0)
			return evaluate(clonedBoard, isWhite());
		else
		{
			//running negamax for children moves
			List<Move> childrenMoves = board.gatherAllMoves(this.isWhite());
			
			int max = -ChessGame.INFINITY;
			for (int i = 0; i < childrenMoves.size(); i++)
			{
				int score = -negaMax(depth - 1, clonedBoard, !isWhite(), childrenMoves.get(i));
				if (score > max) 
					max = score;
			}
			return max;
		}
	}

	@Override
	protected Move chooseMove(List<Move> moves)
	{
		//evaluating all possible moves
		for (Move i : moves)
		{
			i.score = negaMax(0, game.getChessboard(), isWhite(), i);
		}
		
		//choosing the best move
		Move bestMove = moves.get(0);
		for(Move i : moves)
		{
			if(i.score > bestMove.score)
				bestMove = i;
		}
		
		return bestMove;
	}

}
