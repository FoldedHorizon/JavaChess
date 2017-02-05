
package chess;


import java.util.*;


public class Chessboard
{

	//char [] board;
	public static final int SIZE = 8;
	//protected Piece [] chessboard;
	protected List <Piece> piecesOnBoard;
	
	public Chessboard(List <Piece> pieces)
	{
		Collections.copy(piecesOnBoard,pieces);
		//board = new String("♜♞♝♛♚♝♞♜♟♟♟♟♟♟♟♟................................♙♙♙♙♙♙♙♙♖♘♗♕♔♗♘♖").toCharArray();

		//System.arraycopy(pieces, 0, chessboard, 0, 16);
		//System.arraycopy(pieces, 16, chessboard, (8*8)-16, 16);
		
	}
	
	public Piece [] getChessboard()
	{
		
		Piece [] chessboard = new Piece[SIZE*SIZE];
		
		for(Piece i : piecesOnBoard)
		{
			chessboard[i.getPos()]=i;
		}
		
		return chessboard;
	}
	
	public void move(Piece who, int x, int y)
	{
		System.out.print("Ruch " + who.sign + " z " + positionToString(who.pos) + " na ");
		
	}
	
	private String positionToString(int pos)
	{
		String out = Character.toString((char)(65 + (pos%8)));
		//String out = Character.toString((char)(A + (pos%8)));
		
		out+=8-(pos/8);
		return out;
		
	}
	
	public String toString()
	{
		String out;
		Piece [] chessboard = getChessboard();
		out = ("   ABCDEFGH\n");
		for(int i = 0; i < 8; i++)
		{
			out += ( (8 - i) + ": ");
			for(int j = 0; j < 8; j++)
			{
				Piece chess = chessboard[(8 * i) + j];
				if(chess == null)
					out += (".");
				else
					out += chess.sign;
			}
			out += ( " " +  (8 - i) + "\n");
		}
		out += ("   ABCDEFGH\n");
		return out;
	}
	
	public Piece getPiece(int pos)
	{
		return getChessboard()[pos];
	}
	


	public boolean canMove(Piece piece, int newPos) {
		if((newPos >= SIZE*SIZE) || (newPos < 0))
			return false;
		Piece other = getPiece(newPos);
		if(other!=null)
		{
			if( other.isWhite() == piece.isWhite() )
				return false;
		}
		
		return true;
	}
	
	public static void main(String args[])
	{
		//Chessboard test = new Chessboard();
		//System.out.println(test);
	}
	
}
