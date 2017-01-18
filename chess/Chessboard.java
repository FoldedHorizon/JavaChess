
package chess;

public class Chessboard
{

	char [] board;
	public String toString()
	{
		String out;
		out = ("   ABCDEFGH\n");
		for(int i = 0; i < 8; i++)
		{
			out += ( (8 - i) + ": ");
			for(int j = 0; j < 8; j++)
			{
				out += (board[ (8 * i) + j]);
			}
			out += ( " " +  (8 - i) + "\n");
		}
		out += ("   ABCDEFGH\n");
		return out;
	}
	
	public Chessboard()
	{
		board = new String("♜♞♝♛♚♝♞♜♟♟♟♟♟♟♟♟................................♙♙♙♙♙♙♙♙♖♘♗♕♔♗♘♖").toCharArray();

	}
	
	public static void main(String args[])
	{
		Chessboard test = new Chessboard();
		System.out.println(test);
	}
	
}
