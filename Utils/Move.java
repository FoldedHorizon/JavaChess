package Utils;

public class Move
{
	public final int from;
	public final int where;
	public int score;
	
	public Move(int from, int where)
	{
		this.from = from;
		this.where = where;
	}
	
	@Override
	public String toString()
	{
		return new String("Ruch " + ChessGame.getInstance().getChessboard().getPiece(from) + " z " + positionToString(from) + " na " + positionToString(where));
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
		if(mv.from == from && mv.where == where)
			return true;
		
		return false;
	}
}