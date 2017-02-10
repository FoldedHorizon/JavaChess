package Players;

import Utils.*;

import java.util.List;

import Utils.Chessboard;

public class MinMaxPlayer extends Player {

	public MinMaxPlayer(String name) 
	{
		super(name);
	}	



	@Override
	protected Move chooseMove(List<Move> moves) {
		Chessboard c = new Chessboard(game.getChessboard());
		Move bestMove = moves.get(0);
		for(Move i : moves)
		{
			
		}
		return null;
		//return moves.get(r.nextInt(moves.size()));	
	
	}

}
