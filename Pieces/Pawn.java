package Pieces;

import Utils.*;

import java.util.ArrayList;
import java.util.List;

import Utils.ChessGame;


public class Pawn extends Piece 
{

	private final static int value = 1;

	public Pawn(Chessboard board, boolean white) {
		super(board, white);
		
		if(white)
			sign = '♟';
		else
			sign = '♙';
	}
        
    @Override
    public Piece copy(Chessboard board)
    {
    	return new Pawn(board,isWhite());
    }
        
	@Override
	protected boolean isLegal(Move move)
	{
		if(!super.isLegal(move))
			return false;
		
		//checking if no border was crossed.
		int pos = getPosition();
		if ( Math.abs(pos%ChessGame.SIZE - move.where%ChessGame.SIZE) >= 2 
				|| Math.abs(pos/ChessGame.SIZE - move.where/ChessGame.SIZE ) >= 2)
		{
			return false;
		}
		
		return true;
	}
	
	@Override
	public List<Move> getMoves() 
	{
		List <Move> out = new ArrayList <Move>();
		
		int translation;
		
		if(!isWhite())
			translation = -ChessGame.SIZE;
		else
			translation = ChessGame.SIZE;	
		
		//forward	
		Move mv = new Move(this.getPosition(), getPosition() + translation);
		if(isLegal(mv) && (board.getPiece(mv.where) == null))
			out.add(mv);
		
		//left
		translation += -1;
		mv = new Move(this.getPosition(), getPosition() + translation);
		if(isLegal(mv) && (board.getPiece(mv.where) != null))
			out.add(mv);
		//right
		translation += 2;
		mv = new Move(this.getPosition(), getPosition() + translation);
		if(isLegal(mv) && (board.getPiece(mv.where) != null))
			out.add(mv);
		
		//I know it's ugly copy-paste. Maybe I will come back and rewrite it later.
		
		return out;
		
	}

	@Override
	public int getValue()
	{
		return value * ChessGame.MATERIALMULTIPLIER;
	}

}
