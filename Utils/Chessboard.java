package Utils;


import java.util.ArrayList;
import java.util.List;

import Pieces.*;

public class Chessboard {

	private Piece [] board;
	
	public Chessboard(ChessGame game)
	{
		board = new Piece [64];

		spawnWhiteSet(game);
		spawnBlackSet(game);
	}
	
	public Chessboard(Chessboard chessboard) 
	{
		board = new Piece [ ChessGame.SIZE * ChessGame.SIZE ];

		for(int i = 0; i < ChessGame.SIZE * ChessGame.SIZE; i++)
		{
                    if(chessboard.board[i] != null)
                        board[i] = chessboard.board[i].copy(this);
                    
		}
		
	}

	public Piece getPiece(int pos)
	{
		if(pos < 0 || pos >= 64)
			return null;
		return board[pos];
	}
	
	public int getPosition(char col, int row)
	{
		int pos = (int)(Character.toUpperCase(col)) - 65;
		pos += (ChessGame.SIZE - row) * ChessGame.SIZE;
		
		return pos;
	}
	
	public int getPosition(Piece piece)
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
	
	
	private void spawnWhiteSet(ChessGame game)
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
	}
	
	private void spawnBlackSet(ChessGame game)
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
		
	}
	
	public void execute(Move move) throws KingDied
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

	public List<Piece> getPieces(boolean wantWhite) 
	{
		List <Piece> out = new ArrayList <Piece> ();
		
		for(Piece i : board)
		{
			if(i != null && i.isWhite() == wantWhite)
				out.add(i);
		}
		
		return out;
	}

	public List <Move> gatherAllMoves(boolean forWhite)
	{
        List <Move> all = new ArrayList <Move>();
        for(Piece i : getPieces(forWhite))
        {
                all.addAll(i.getMoves());
        }
        return all;
	}
}
