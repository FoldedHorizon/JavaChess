
package chess;

import java.util.*;


public class Game
{
	private Player white;
	private Player black;
	private Chessboard board;
	private Player current = null;
	private List<Piece> pieces;
	
	public Game()
	{
		white = new Player("Emanuel Lasker");
		black = new Player("Jose Raul Capablanca");
		pieces = new ArrayList<Piece>();
		createPieces();
		System.arraycopy(pieces, 0, white.pieces, 0, 16);
		System.arraycopy(pieces, 16, black.pieces, 0, 16);
		board = new Chessboard(pieces);
	}
	
	public final Player whitePlayer() { return white; }
	public final Player blackPlayer() { return black; }
	public final Chessboard chessboard() { return board; }
	public void nextMove()
	{
		if (current == white)
			current = black;
		else
			current = white;

		current.move();
	}
	
	private void createPieces()
	{
		int j = 0;
		//white ♜♞♝♛♚♝♞♜♟
		pieces[j++] = new Rook(0,0,'♜');
		pieces[j++] = new Knight(1,0,'♞');
		pieces[j++] = new Bishop(2,0,'♝');
		pieces[j++] = new Queen(3,0,'♛');
		pieces[j++] = new King(4,0,'♚');
		pieces[j++] = new Bishop(5,0,'♝');
		pieces[j++] = new Knight(6,0,'♞');
		pieces[j++] = new Rook(7,0,'♜');
		for (int i = 0; i < 8; i++)
			pieces[j++] = new Pawn(i,1,'♟');
		
		//black ♖♘♗♕♔♗♘♖♙
		for (int i = 0; i < 8; i++)
			pieces[j++] = new Pawn(i,6,false);
		pieces[j++] = new Rook(0,7,'♖');
		pieces[j++] = new Knight(1,7,'♘');
		pieces[j++] = new Bishop(2,7,'♗');
		pieces[j++] = new Queen(3,7,'♕');
		pieces[j++] = new King(4,7,'♔');
		pieces[j++] = new Bishop(5,7,'♗');
		pieces[j++] = new Knight(6,7,'♘');
		pieces[j++] = new Rook(7,7,'♖');
	}
	
	
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
