
import chess.*;

public class Szachy
{
	public static void main(String args[])
	{
		Game game = new Game();
		System.out.println("Szachownica: ");
		System.out.println(game.chessboard());
		System.out.println("Białe: " + game.white() + ", czarne " + game.black());

		for(int i = 0; i < 5; i++)
		{
			System.out.println("Tura " + (i + 1));
			
			//white moves
			game.nextMove();
			System.out.println(game.chessboard());
			
			//black moves
			game.nextMove();
			System.out.println(game.chessboard());
		}
	}
	
}
