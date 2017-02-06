package Szachy;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {

	protected static int translations [] = 
		{ -Chessboard.SIZE, 
		-Chessboard.SIZE + 1, 
		1, 
		1 + Chessboard.SIZE, 
		Chessboard.SIZE, 
		Chessboard.SIZE - 1, 
		-1,
		-Chessboard.SIZE -1 };
	
	public Queen(Chessboard board, boolean white) 
	{
		super(board, white);		
		
		if(white)
			sign = '♛';
		else
			sign = '♕';
		
		value = 10;
	}

	@Override
	protected boolean isLegal(Move move)
	{
		if(!super.isLegal(move))
			return false;
		
		//checking if no border was crossed.
		int pos = getPosition();
		if ( (Math.abs(pos%Chessboard.SIZE - move.where%Chessboard.SIZE) 
				!= Math.abs(pos/Chessboard.SIZE - move.where/Chessboard.SIZE)) 
				&& 
				( Math.abs(pos%Chessboard.SIZE - move.where%Chessboard.SIZE) >= 1 
						&& Math.abs(pos/Chessboard.SIZE - move.where/Chessboard.SIZE) >= 1) )
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
