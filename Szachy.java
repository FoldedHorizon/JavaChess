
import Players.*;
import Utils.*;

public class Szachy
{
	public static void main(String args[])
	{
		//Here you can choose which kind of players you want to spawn. Currently supported: Random, Aggressive, MinMax and Human.
		Player white = new MinMaxPlayer("Alan");
		
		//You can play by yourself ;)
		//Player black = new HumanPlayer();
		
		//Or let a random be the prey.
		Player black = new AggressivePlayer("Martin");
				
		ChessGame game = ChessGame.init(white, black); 
		game.play(70);
	}
	
}