package Pieces;

import Utils.*;

import java.util.*;

import Utils.ChessGame;


public class Rook extends Piece 
{

	protected static int translations [] = { -ChessGame.SIZE, 1, ChessGame.SIZE, -1 };
	
	public Rook(ChessGame board, boolean white) 
	{
		super(board,white);
		if(white)
			sign = '♜';
		else
			sign = '♖';
		
		value = 5;
		
	}

	@Override
	protected boolean isLegal(Move move)
	{
		if(!super.isLegal(move))
			return false;
		
		//checking if no border was crossed.
		int pos = getPosition();
		if ( Math.abs(pos%ChessGame.SIZE - move.where%ChessGame.SIZE) >= 1 
				&& Math.abs(pos/ChessGame.SIZE - move.where/ChessGame.SIZE ) >= 1)
		{
			return false;
		}
		
		return true;
	}
	
	@Override
	public List <Move> getMoves()
	{
		List <Move> out = new ArrayList <Move>();
		
		for(int j = 0; j < translations.length; j++)
		{
			for(int i = 1; i < 8; i++)
			{
				Move mv = new Move(this, getPosition() + i * translations[j]);
				
				if(isLegal(mv))
					out.add(mv);
				
				//cannot jump over other pieces
				if(game.getChessboard().getPiece(mv.where) != null)
					break;
			}
		}
		
		return out;
	}
	

}
