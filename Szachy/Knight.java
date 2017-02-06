package Szachy;

import java.util.*;

public class Knight extends Piece {

	protected static int translations [] = 
		{ -(2 * Chessboard.SIZE) + 1, 
		-Chessboard.SIZE + 2, 
		Chessboard.SIZE + 2, 
		2 * Chessboard.SIZE + 1, 
		2 * Chessboard.SIZE - 1, 
		Chessboard.SIZE - 2, 
		-Chessboard.SIZE - 2, 
		-(2 * Chessboard.SIZE) - 1 };

	public Knight(Chessboard board, boolean white) {
		super(board, white);
		
		if(white)
			sign = '♞';
		else
			sign = '♘';
		
		value = 5;
	}
	
	@Override
	protected List <Move> getMoves()
	{
		List <Move> out = new ArrayList <Move>();
		
		for(int i = 0; i < translations.length; i++)
		{
			Move mv = new Move(this, getPosition() + translations[i]);
			if(isLegal(mv))
			{
				out.add(mv);
			}
		}
				
		return out;
	}
	

}
