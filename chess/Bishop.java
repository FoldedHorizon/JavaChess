
package chess;

public class Bishop extends Piece
{
	Bishop(int pos, boolean white)
	{		
		this.pos = pos;
		this.white = white;
		
		if(isWhite())
			this.sign = '♝';
		else
			this.sign = '♗';
	}
}