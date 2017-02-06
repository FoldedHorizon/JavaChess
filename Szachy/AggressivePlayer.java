package Szachy;

import java.util.*;

public class AggressivePlayer extends Player {

	Random r = new Random();
	
	public AggressivePlayer(Chessboard b, List<Piece> p, String name) 
	{
		super(b, p, name);
	}

	@Override
	protected float evaluate(Move move) 
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected Move chooseMove(List <Move> moves) 
	{
		filterForCaptures(moves);
		
		return moves.get(r.nextInt(moves.size()));	
	}
	
	private void filterForCaptures(List <Move> moves)
	{
		List <Move> safeCopy = new ArrayList <Move>();
		Collections.copy(safeCopy, moves);

		//removing non-aggressive moves
		for(int i = 0; i < moves.size(); i++)
		{
			if(board.getPiece(moves.get(i).where) == null)
				moves.remove(i);
		}
		
		//in case there is no moves that capture any piece
		if(moves.size() == 0)
			Collections.copy(moves, safeCopy);
	}

}
