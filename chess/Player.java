
package chess;

import java.util.*;

public abstract class Player
{
	
	String name_;
	List <Piece> myPieces;

	public Player(String name, List <Piece> pieces)
	{
		name_ = new String(name);
		myPieces = pieces;
	}

	public String toString()
	{
		return new String(name_);	
	}
	
	public void move()
	{
		for(Piece i : myPieces)
		{
			
		}
	}
	
	public abstract Float evaluateMove(Pair <Piece, Integer> move);
	
}
