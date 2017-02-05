
package chess;

public class Rook extends Piece
{
	Rook(int pos, boolean white)
	{
		this.pos = pos;
		this.white = white;
		
		if(isWhite())
			this.sign = '♜';
		else
			this.sign = '♖';
	}
}