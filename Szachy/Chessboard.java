
package Szachy;

import java.util.*;

public class Chessboard {
//---------------------------------------------------------------------
	protected final static int SIZE = 8;


//---------------------------------------------------------------------
	private Piece [] board;
	private final Player white;
	private final Player black;
	private Player current;
	private Player winner = null;
	
//---------------------------------------------------------------------
	private List <Piece> spawnWhiteSet()
	{
		int j = 0;
		//white ♜♞♝♛♚♝♞♜♟
		board[j++] = new Rook(this, true);
		board[j++] = new Knight(this, true);
		board[j++] = new Bishop(this, true);
		board[j++] = new Queen(this, true);
		board[j++] = new King(this, true);
		board[j++] = new Bishop(this, true);
		board[j++] = new Knight(this, true);
		board[j++] = new Rook(this, true);
		for (int i = 0; i < 8; i++)
			board[j++] = new Pawn(this, true);

		List <Piece> out = new ArrayList <Piece>();
		for (int i = 0; i < 16; i++)
			out.add(board[i]);
		
		return out;
	}
	
	private List <Piece> spawnBlackSet()
	{
		int j = 64 - 16;
		//black ♖♘♗♕♔♗♘♖♙
		for (int i = 0; i < 8; i++)
			board[j++] = new Pawn(this, false);
		board[j++] = new Rook(this, false);
		board[j++] = new Knight(this, false);
		board[j++] = new Bishop(this, false);
		board[j++] = new Queen(this, false);
		board[j++] = new King(this, false);
		board[j++] = new Bishop(this, false);
		board[j++] = new Knight(this, false);
		board[j++] = new Rook(this, false);
		
		List <Piece> out = new ArrayList <Piece>();
		for (int i = 64 - 16; i < 64; i++)
			out.add(board[i]);
		
		return out;
	}
	
//---------------------------------------------------------------------
	protected Piece getPiece(int pos)
	{
		if(pos < 0 || pos >= 64)
			return null;
		return board[pos];
	}
	
	protected int getPosition(Piece piece)
	{
		for(int i = 0; i < 64; i++)
		{
			if(board[i] == piece)
			{
				return i;
			}
		}
		
		//Well, there is no such piece on the board. That's no good.
		assert false;
		
		return -1;
	}
	
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
				Piece chess = board[(8 * i) + j];
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
		//Clear old position
		for(int i = 0; i < 64; i++)
		{
			if(board[i] == move.who)
			{
				board[i] = null;
				break;
			}
		}
		
		//Inform piece being captured of its fate.
		if(board[move.where] != null)
			board[move.where].destroy();
		
		//Sets new position
		board[move.where] = move.who;
	}
	
	public Chessboard()
	{
		board = new Piece [64];
		
		//Here you can choose which kind of players you want to spawn. Currently only Random works. Work in Progress on agressive, mini-max and Human ones.
		white = new AggressivePlayer(this, spawnWhiteSet(), "Alan");
		black = new RandomPlayer(this, spawnBlackSet(), "Marlena");
		
	}
	
//---------------------------------------------------------------------	
	public static void main(String[] args) 
	{

	}

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
	
}