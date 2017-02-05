
package chess;

import java.util.*;

public abstract class Piece
{
	public static final int SIZE = 8;
	protected boolean white;
	protected char sign;
	protected int pos;
	
	public boolean isWhite()
	{
		return white;
	}

	public char getSign()
	{
		return sign;
	}
	
	public int getPos()
	{
		return pos;
	}
	
	public abstract List <Pair<Integer,Float>> MovesAndTheirValues(Chessboard board);
	
}