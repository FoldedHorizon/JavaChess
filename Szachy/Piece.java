package Szachy;

import java.util.*;

public abstract class Piece {

	protected static int translations [];
	protected int value;
	protected Chessboard board;
	protected char sign;//wtf? there is no access specifier that lets only subclasses have access to a field? Seriously? There is only possibility for whole package to see a field!?
	private boolean white;
	
	protected abstract List <Move> getMoves();
	
	protected int getPosition()
	{
		return board.getPosition(this);
	}
	
	protected boolean isWhite()
	{
		return white;
	}
	
	protected void destroy() throws KingDied
	{
		getOwner().pieceCaptured(this);
	}
	
	protected Player getOwner()
	{
		if(white)
			return board.getWhitePlayer();
		else
			return board.getBlackPlayer();
	}
	
	protected int getValue()
	{
		return value;
	}

	////Method to be overridden, but should be called from children (super.isLegal() && child output). //Not true. Changed it. And commented the comment in case I would like it to be back the old way.
	protected boolean isLegal(Move move)
	{
		//Out of board
		if((move.where >= Chessboard.SIZE * Chessboard.SIZE) || (move.where < 0))
			return false;
		
		Piece other = board.getPiece(move.where);
		if(other != null)
		{
			if( other.isWhite() == this.isWhite() )
				return false;
		}
		
		//checking if no border was crossed. //But it sucks currently. Does work only for knight and pawn probably.
		int pos = getPosition();
		if ( Math.abs(pos%Chessboard.SIZE - move.where%Chessboard.SIZE) >= 3 
				&& Math.abs(pos - move.where) >= 3 * Chessboard.SIZE )
		{
			return false;
		}
		
		return true;
	}
	
	protected Piece(Chessboard board, boolean white)
	{
		this.board = board;
		this.white = white;
	}
	
}
