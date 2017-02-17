package Utils;


import java.util.*;

import Pieces.*;

public class Chessboard {

	private Piece [] piecesOnBoard;
	
	public Chessboard()
	{
		piecesOnBoard = new Piece [ ChessGame.SIZE * ChessGame.SIZE ];
	}
	
	public static Chessboard copy(Chessboard chessboard)
	{
		Chessboard out = new Chessboard();
		
		for(int i = 0; i < ChessGame.SIZE * ChessGame.SIZE; i++)
		{
            if(chessboard.piecesOnBoard[i] != null)
                out.piecesOnBoard[i] = chessboard.piecesOnBoard[i].copy(out);
		}
		
		return out;
	}

	public Piece getPiece(int pos)
	{
		if(pos < 0 || pos >= 64)
			return null;
		return piecesOnBoard[pos];
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
			if(piecesOnBoard[i] == piece)
			{
				return i;
			}
		}
		
		//Well, there is no such piece on the board. That's no good.
		assert false;
		
		return -1;
	}
	
	
	private void spawnWhiteSet()
	{		
		int j = 0;
		//white ♜♞♝♛♚♝♞♜♟
		piecesOnBoard[j++] = new Rook(this, true);
		piecesOnBoard[j++] = new Knight(this, true);
		piecesOnBoard[j++] = new Bishop(this, true);
		piecesOnBoard[j++] = new Queen(this, true);
		piecesOnBoard[j++] = new King(this, true);
		piecesOnBoard[j++] = new Bishop(this, true);
		piecesOnBoard[j++] = new Knight(this, true);
		piecesOnBoard[j++] = new Rook(this, true);
		for (int i = 0; i < 8; i++)
			piecesOnBoard[j++] = new Pawn(this, true);
	}
	
	private void spawnBlackSet()
	{		
		int j = 64 - 16;
		//black ♖♘♗♕♔♗♘♖♙
		for (int i = 0; i < 8; i++)
			piecesOnBoard[j++] = new Pawn(this, false);
		piecesOnBoard[j++] = new Rook(this, false);
		piecesOnBoard[j++] = new Knight(this, false);
		piecesOnBoard[j++] = new Bishop(this, false);
		piecesOnBoard[j++] = new Queen(this, false);
		piecesOnBoard[j++] = new King(this, false);
		piecesOnBoard[j++] = new Bishop(this, false);
		piecesOnBoard[j++] = new Knight(this, false);
		piecesOnBoard[j++] = new Rook(this, false);
		
	}
	
	public String execute(Move move) throws KingDied
	{
		assert move != null;

		String report = "";
		//Clear old position
		Piece who = getPiece(move.from);
		for(int i = 0; i < 64; i++)
		{
			if(piecesOnBoard[i] == who)
			{
				piecesOnBoard[i] = null;
				break;
			}
		}
		
		//Inform piece being captured of its fate.
		if(piecesOnBoard[move.where] != null)
			report = piecesOnBoard[move.where].destroy();
		
		//Sets new position
		piecesOnBoard[move.where] = who;
		
		return report;
	}

	public List <Piece> getPieces(boolean wantWhite) 
	{
		List <Piece> out = new ArrayList <Piece> ();
		
		for(Piece i : piecesOnBoard)
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

	// ---------------------------------------------------------------------
	// All "calc" functions are calculated from a white player point-of-view
	public int calcMaterialScore()
	{
		int score = 0;
		//Add white player's pieces' values
		for (Piece i : getPieces(true))
		{
			score += i.getValue();
		}
		//Add black player's pieces' values
		for (Piece i : getPieces(false))
		{
			score -= i.getValue();
		}	
		
		return score;
	}

	public int calcBlokedPawns()
	{
		// TODO later ;) Not so important.
		return 0;
	}

	public int calcMobility()
	{
		return (ChessGame.MATERIALMULTIPLIER/10) *(gatherAllMoves(true).size() - gatherAllMoves(false).size());
	}

	public void init()
	{
		spawnWhiteSet();
		spawnBlackSet();
		
	}
	
}
