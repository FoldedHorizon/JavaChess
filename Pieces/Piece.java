package Pieces;


import java.util.*;

import Utils.*;

public abstract class Piece {

	protected static int translations [];
	//protected ChessGame game;
    protected Chessboard board;
	protected char sign;//wtf? there is no access specifier that lets only subclasses have access to a field? Seriously? There is only possibility for whole package to see a field!?
	private boolean white;
	
	public abstract List <Move> getMoves();
	
    public abstract Piece copy(Chessboard board);
        
	public int getPosition()
	{
    	assert board != null;
		return board.getPosition(this);
	}
	
	public boolean isWhite()
	{
		return white;
	}
	
	public String destroy() throws KingDied
	{
		return new String(sign + " captured!");
	}
		
	public abstract int getValue();

	////Method to be overridden, but should be called from children (super.isLegal() && child output). //Not true. Changed it. And commented the comment in case I would like it to be back the old way.
	protected boolean isLegal(Move move)
	{
		//Out of board
		if( ( move.where >= ChessGame.SIZE * ChessGame.SIZE ) || ( move.where < 0 ) )
			return false;

                //Check if tile to bo moved to isn't being occupied by piece from the same team.
		Piece other = board.getPiece(move.where);
		if( other != null )
		{
			if( other.isWhite() == this.isWhite() )
				return false;
		}
		
		return true;
	}
	
	protected Piece(Chessboard board, boolean white)
	{
		this.white = white;
		this.board = board;
		assert board != null;
	}
	
	@Override
	public String toString()
	{
		return Character.toString(sign);
	}
}
