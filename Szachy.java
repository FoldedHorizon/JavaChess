
import Szachy.*;

public class Szachy
{
	public static void main(String args[])
	{
		//Here you can choose which kind of players you want to spawn. Currently supported: Random, Aggressive and Human. Work in Progress on min-max.
		Player white = new AggressivePlayer("Alan");
		
		//You can play by yourself ;)
		Player black = new HumanPlayer();
		
		//Or let a random be the prey.
		//black = new RandomPlayer(this,chessboard.spawnBlackSet(this),"Random");
				
		ChessGame game = new ChessGame(white, black); 
		game.play(70);
	}
	
}
