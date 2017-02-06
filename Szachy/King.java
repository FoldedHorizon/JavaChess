package Szachy;

import java.util.*;

public class King extends Piece 
{
	
	protected static int translations [] = 
		{ -Chessboard.SIZE, 
		-Chessboard.SIZE + 1, 
		1, 
		1 + Chessboard.SIZE, 
		Chessboard.SIZE, 
		Chessboard.SIZE - 1, 
		-1,
		-Chessboard.SIZE -1 };
	
	public King(Chessboard board, boolean white) 
	{
		super(board, white);
		
		if(white)
			sign = '♚';
		else
			sign = '♔';
		
		value = 100;
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
