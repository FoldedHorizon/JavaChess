package Utils;

import Pieces.Piece;
import Players.Player;

public class ChessGame
{
	// ---------------------------------------------------------------------
	public final static int SIZE = 8;
	public final static int INFINITY = 999999999;
	public static final int MATERIALMULTIPLIER = 100;
	
	private static ChessGame instance;
	
	// ---------------------------------------------------------------------

	private Chessboard chessboard;
	private final Player white;
	private final Player black;
	private Player current;
	private Player winner = null;

	// ---------------------------------------------------------------------

	public Chessboard getChessboard()
	{
		return chessboard;
	}

	// ---------------------------------------------------------------------

	public Player getWhitePlayer()
	{
		return white;
	}

	public Player getBlackPlayer()
	{
		return black;
	}

	public boolean isWhite(Player player)
	{
		if (player == white) return true;
		return false;
	}

	@Override
	public String toString()
	{
		String out;
		out = ("   ABCDEFGH\n");
		for (int i = 0; i < ChessGame.SIZE; i++)
		{
			out += ((ChessGame.SIZE - i) + ": ");
			for (int j = 0; j < ChessGame.SIZE; j++)
			{
				Piece chess = chessboard.getPiece((ChessGame.SIZE * i) + j);// board[(8 * i)
				// + j];
				if (chess == null)
					out += (".");
				else
					out += chess;
			}
			out += (" " + (ChessGame.SIZE - i) + "\n");
		}
		out += ("   ABCDEFGH\n");
		return out;
	}

	public void swapPlayers()
	{
		if (current != white)
			current = white;
		else
			current = black;
	}

	// ---------------------------------------------------------------------
	public void play(int nTurns)
	{
		System.out.println("Szachownica: ");
		System.out.println(this);
		System.out.println("Białe: " + white.getName() + ", czarne: "
				+ black.getName());

		for (int i = 0; i < 2 * nTurns; i++)
		{
			if (i % 2 == 0) System.out.println("Tura " + (i / 2 + 1));

			// Sets first player to be the white one and swaps players each round.
			swapPlayers();

			// Asks a player for a move and executes it.
			try
			{
				Move mv = current.getMove();
				System.out.println(mv);
				execute(mv);
			}
			catch (KingDied e)
			{
				System.out.println("Król został zabity!");
				winner = current;
				break;
			}
			catch (NoValidMove e)
			{
				System.out.println(current.getName() + " has no moves!");
				swapPlayers();
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
			System.out.println("Zwyciężył " + white.getName() + " (białe).");
		else if (winner == black)
			System.out.println("Zwyciężył " + black.getName() + " (czarne).");
		else
			System.out.println("Remis.");

	}

	private void execute(Move move) throws KingDied
	{
		chessboard.execute(move);
	}
	
	public static ChessGame getInstance()
	{
		return instance;
	}

	public ChessGame(Player whitePlayer, Player blackPlayer)
	{
		chessboard = new Chessboard(this);
		white = whitePlayer;
		black = blackPlayer;
		white.init(this);
		black.init(this);
		
		instance = this;

	}

	// ---------------------------------------------------------------------

}
