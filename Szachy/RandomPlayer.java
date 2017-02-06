package Szachy;

import java.util.*;

public class RandomPlayer extends Player {
	
	Random r = new Random();
	
	public RandomPlayer(Chessboard b, List<Piece> p, String name) 
	{
		super(b, p, name);
	}

	@Override
	protected float evaluate(Move move)
	{
		//This function shall never be used by this player! He is not smart enough to evaluate any moves.
		assert false;

		return -1;
	}

	@Override
	protected Move chooseMove(List <Move> moves)
	{	
		return moves.get(r.nextInt(moves.size()));
	}

}
