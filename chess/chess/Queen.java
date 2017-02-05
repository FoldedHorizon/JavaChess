
package chess;

import java.util.List;

public class Queen extends Piece
{
	private static final int translations[] = {-1,SIZE-1,SIZE,SIZE+1,+1,-SIZE+1,-SIZE,-SIZE-1};
	
	Queen(int pos, boolean white)
	{
		this.pos = pos;
		this.white = white;
		
		if(isWhite())
			this.sign = '♛';
		else
			this.sign = '♕';
	}

	@Override
	public List<Pair<Piece, Integer>> possibleMoves() {
		// TODO Auto-generated method stub
		return null;
	}
}