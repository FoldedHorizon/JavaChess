
package chess;

public class King extends Piece
{
	King(int pos, boolean white)
	{
		this.pos = pos;
		this.white = white;
		
		if(isWhite())
			this.sign = '♚';
		else
			this.sign = '♔';
	}
}