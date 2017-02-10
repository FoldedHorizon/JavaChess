package Utils;

import Pieces.*;

public class Move
{
	public Piece who;
	public int where;
	
	public Move(Piece who, int where)
	{
		this.who = who;
		this.where = where;
	}
	
	@Override
	public String toString()
	{
		return new String("Ruch " + who + " z " + positionToString(who.getPosition()) + " na " + positionToString(where));
	}
	
	private String positionToString(int pos)
	{
		String out = Character.toString((char)(65 + (pos%8)));
		//String out = Character.toString((char)('A' + (pos%8)));
		
		out += 8 - (pos/8);
		return out;
	}
	
	@Override
	public boolean equals(Object other)
	{
		if(other.getClass() != getClass())
			return false;
		
		Move mv = (Move)other;
		if(mv.who == who && mv.where == where)
			return true;
		
		return false;
	}
}