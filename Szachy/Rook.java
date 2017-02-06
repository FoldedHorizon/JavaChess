package Szachy;

import java.util.*;

public class Rook extends Piece 
{

	protected static int translations [] = { -Chessboard.SIZE, 1, Chessboard.SIZE, -1 };
	
	public Rook(Chessboard board, boolean white) 
	{
		super(board,white);
		if(white)
			sign = '♜';
		else
			sign = '♖';
		
		value = 5;
		
	}

	
	@Override
	protected List <Move> getMoves()
	{
		List <Move> out = new ArrayList <Move>();
		
		for(int j = 0; j < translations.length; j++)
		{
			for(int i = 1; i < 8; i++)
			{
				Move mv = new Move(this, getPosition() + i * translations[j]);
				
				if(isLegal(mv))
					out.add(mv);
				
				//cannot jump over other pieces
				if(board.getPiece(mv.where) != null)
					break;
			}
		}
		
		return out;
	}
	

}
