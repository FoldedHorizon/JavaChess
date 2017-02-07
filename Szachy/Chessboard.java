package Szachy;

import java.util.ArrayList;
import java.util.List;

public class Chessboard {

	private Piece [] board;
	
	Chessboard()
	{
		board = new Piece [64];
	}
	
	protected Piece getPiece(int pos)
	{
		if(pos < 0 || pos >= 64)
			return null;
		return board[pos];
	}
	
	protected int getPosition(char col, int row)
	{
		int pos = (int)(Character.toUpperCase(col)) - 65;
		pos += (ChessGame.SIZE - row) * ChessGame.SIZE;
		
		return pos;
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
	
	
	protected List <Piece> spawnWhiteSet(ChessGame game)
	{
		int j = 0;
		//white ♜♞♝♛♚♝♞♜♟
		board[j++] = new Rook(game, true);
		board[j++] = new Knight(game, true);
		board[j++] = new Bishop(game, true);
		board[j++] = new Queen(game, true);
		board[j++] = new King(game, true);
		board[j++] = new Bishop(game, true);
		board[j++] = new Knight(game, true);
		board[j++] = new Rook(game, true);
		for (int i = 0; i < 8; i++)
			board[j++] = new Pawn(game, true);

		List <Piece> out = new ArrayList <Piece>();
		for (int i = 0; i < 16; i++)
			out.add(board[i]);
		
		return out;
	}
	
	protected List <Piece> spawnBlackSet(ChessGame game)
	{
		int j = 64 - 16;
		//black ♖♘♗♕♔♗♘♖♙
		for (int i = 0; i < 8; i++)
			board[j++] = new Pawn(game, false);
		board[j++] = new Rook(game, false);
		board[j++] = new Knight(game, false);
		board[j++] = new Bishop(game, false);
		board[j++] = new Queen(game, false);
		board[j++] = new King(game, false);
		board[j++] = new Bishop(game, false);
		board[j++] = new Knight(game, false);
		board[j++] = new Rook(game, false);
		
		List <Piece> out = new ArrayList <Piece>();
		for (int i = 64 - 16; i < 64; i++)
			out.add(board[i]);
		
		return out;
	}
	
	protected void execute(Move move) throws KingDied
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
}
