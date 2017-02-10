package Pieces;


import java.util.*;

import Players.Player;
import Utils.*;

public abstract class Piece {

	protected static int translations [];
	protected int value;
	protected ChessGame game;
	protected char sign;//wtf? there is no access specifier that lets only subclasses have access to a field? Seriously? There is only possibility for whole package to see a field!?
	private boolean white;
	
	public abstract List <Move> getMoves();
	
	public int getPosition()
	{
		return game.getChessboard().getPosition(this);
	}
	
	public boolean isWhite()
	{
		return white;
	}
	
	public void destroy() throws KingDied
	{
		getOwner().pieceCaptured(this);
	}
	
	protected Player getOwner()
	{
		if(white)
			return game.getWhitePlayer();
		else
			return game.getBlackPlayer();
	}
	
	protected int getValue()
	{
		return value;
	}

	////Method to be overridden, but should be called from children (super.isLegal() && child output). //Not true. Changed it. And commented the comment in case I would like it to be back the old way.
	protected boolean isLegal(Move move)
	{
		//Out of board
		if((move.where >= ChessGame.SIZE * ChessGame.SIZE) || (move.where < 0))
			return false;
		
		Piece other = game.getChessboard().getPiece(move.where);
		if(other != null)
		{
			if( other.isWhite() == this.isWhite() )
				return false;
		}
		
		return true;
	}
	
	protected Piece(ChessGame game, boolean white)
	{
		this.game = game;
		this.white = white;
	}
	
	@Override
	public String toString()
	{
		return Character.toString(sign);
	}
}