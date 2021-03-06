package Players;

import Utils.*;

import java.util.*;


public class AggressivePlayer extends Player 
{
	Random r = new Random();
	
	public AggressivePlayer(String name) 
	{
		super(name);
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
		safeCopy.addAll(moves);

		//removing non-aggressive moves
		for(int i = 0; i < moves.size(); i++)
		{
			if(game.getChessboard().getPiece(moves.get(i).where) == null)
			{
				moves.remove(i);
				i--;
			}
		}
		
		//in case there is no moves that capture any piece
		if(moves.size() == 0)
			moves.addAll(safeCopy);	
	
	}

}
