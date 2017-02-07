package Szachy;

import java.util.*;

public class King extends Piece 
{
	
	protected static int translations [] = 
		{ -ChessGame.SIZE, 
		-ChessGame.SIZE + 1, 
		1, 
		1 + ChessGame.SIZE, 
		ChessGame.SIZE, 
		ChessGame.SIZE - 1, 
		-1,
		-ChessGame.SIZE -1 };
	
	public King(ChessGame board, boolean white) 
	{
		super(board, white);
		
		if(white)
			sign = '♚';
		else
			sign = '♔';
		
		value = 100;
	}
	
	@Override
	protected boolean isLegal(Move move)
	{
		if(!super.isLegal(move))
			return false;
		
		//checking if no border was crossed.
		int pos = getPosition();
		if ( Math.abs(pos%ChessGame.SIZE - move.where%ChessGame.SIZE) >= 2 
				|| Math.abs(pos/ChessGame.SIZE - move.where/ChessGame.SIZE ) >= 2 )
		{
			return false;
		}
		
		return true;
	}

	@Override
	protected List<Move> getMoves() 
	{
		List <Move> out = new ArrayList <Move>();
		
		for(int j = 0; j < translations.length; j++)
		{
			Move mv = new Move(this, getPosition() + translations[j]);
			if(isLegal(mv))
			{
				out.add(mv);
			}
		}
		
		return out;
	}
	
	@Override
	protected void destroy() throws KingDied
	{
		super.destroy();
		throw new KingDied();
	}

}
