
package Szachy;


public class ChessGame {
//---------------------------------------------------------------------
	protected final static int SIZE = 8;


//---------------------------------------------------------------------

	private Chessboard chessboard;
	private final Player white;
	private final Player black;
	private Player current;
	private Player winner = null;
	
//---------------------------------------------------------------------
	
	protected Chessboard getChessboard()
	{
		return chessboard;
	}
	
//---------------------------------------------------------------------
	
	protected Player getWhitePlayer() 
	{
		return white;
	}
	
	protected Player getBlackPlayer() 
	{
		return black;
	}

	@Override
	public String toString()
	{
		String out;
		out = ("   ABCDEFGH\n");
		for(int i = 0; i < 8; i++)
		{
			out += ( (8 - i) + ": ");
			for(int j = 0; j < 8; j++)
			{
				Piece chess = chessboard.getPiece((8 * i) + j);// board[(8 * i) + j];
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
	
//---------------------------------------------------------------------
	public void play(int nTurns)
	{
		System.out.println("Szachownica: ");
		System.out.println(this);
		System.out.println("Białe: " + white.getName() + ", czarne: " + black.getName());
		
		for (int i = 0; i < 2 * nTurns; i++)
		{
			if(i%2 == 0)
				System.out.println("Tura " + (i/2 + 1));
			
			//Sets first player to be the white one and swaps players each round.
			if(current != white)
				current = white;
			else
				current = black;
			
			//Asks a player for a move and executes it.
			Move mv = current.getMove();
			System.out.println(mv);
			try
			{
				execute(mv);
			}
			catch(KingDied e)
			{
				winner = current;
				break;
			}
			finally
			{
				System.out.println(this);
			}
		}
		
		System.out.print("Koniec gry! ");
		if (winner == white)
			System.out.println("Król został zabity! Zwyciężył " + white.getName() + " (białe).");
		else if (winner == black)
			System.out.println("Król został zabity! Zwyciężył " + black.getName() + " (czarne).");
		else
			System.out.println("Remis.");
		
	}
	
	private void execute(Move move) throws KingDied
	{
		chessboard.execute(move);
	}
	
	public ChessGame()
	{
		
		chessboard = new Chessboard();
		//Here you can choose which kind of players you want to spawn. Currently only Random and Aggressive works. Work in Progress on mini-max and Human ones.
		white = new AggressivePlayer(this, chessboard.spawnWhiteSet(this), "Alan");
		black = new HumanPlayer(this, chessboard.spawnBlackSet(this));

		
	}
	
//---------------------------------------------------------------------	


}

class Move
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
		return new String("Ruch " + who.sign + " z " + positionToString(who.getPosition()) + " na " + positionToString(where));
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