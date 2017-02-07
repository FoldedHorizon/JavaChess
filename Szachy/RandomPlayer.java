package Szachy;

import java.util.*;

public class RandomPlayer extends Player {
	
	Random r = new Random();
	
	public RandomPlayer(ChessGame b, List<Piece> p, String name) 
	{
		super(b, p, name);
	}

	
	@Override
	protected Move chooseMove(List <Move> moves)
	{	
		return moves.get(r.nextInt(moves.size()));
	}

}
