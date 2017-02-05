
package chess;

import java.util.*;

public class Pawn extends Piece
{
	private static final int translations[] = {SIZE};
	
	Pawn(int pos, boolean white)
	{
		this.pos = pos;
		this.white = white;
		
		if(isWhite())
			this.sign = '♟';
		else
			this.sign = '♙';
	}

	public List <Pair<Integer,Float>> MovesAndTheirValues(Chessboard board)
	{
		int newPos;
		List <Pair<Integer,Float>> out = new ArrayList <Pair<Integer,Float>>();
		//going forward
		if(white)
			newPos = pos - translations[0];
		else
			newPos = pos + translations[0];
		if(board.canMove(this, newPos))
			out.add(new Pair <Integer,Float>(newPos, new Float(0.5)));
		
		
		
			
		
		
		return out;
	}
}