package Szachy;

import java.util.List;

public class MinMaxPlayer extends Player {

	public MinMaxPlayer(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}



	@Override
	protected Move chooseMove(List<Move> moves) {
		Move bestMove = moves.get(0);
		for(Move i : moves)
		{
			
		}
		return null;
		//return moves.get(r.nextInt(moves.size()));	
	
	}

}
