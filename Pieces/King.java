package Pieces;

import Utils.*;

import java.util.*;

import Utils.ChessGame;
import Utils.KingDied;

public class King extends Piece 
{
	private final static int value = 100;

	protected static int translations [] = 
		{ -ChessGame.SIZE, 
		-ChessGame.SIZE + 1, 
		1, 
		1 + ChessGame.SIZE, 
		ChessGame.SIZE, 
		ChessGame.SIZE - 1, 
		-1,
		-ChessGame.SIZE -1 };
	
	public King(Chessboard board, boolean white) 
	{
		super(board, white);
		
		if(white)
			sign = '♚';
		else
			sign = '♔';
		
	}
	
    @Override
    public Piece copy(Chessboard board)
    {
        return new King(board,isWhite());
    }
    
	@Override
	protected boolean isLegal(Move move)
	{
		if(!super.isLegal(move))
			return false;
		
		//checking if no border was crossed.
		int pos = getPosition();
		if ( Math.abs(pos%ChessGame.SIZE - move.where%ChessGame.SIZE) >= 2 
			|| Math.abs(pos/ChessGame.SIZE - move.where/ChessGame.SIZE ) >= 2 )
		{
			return false;
		}
		
		return true;
	}

	@Override
	public List<Move> getMoves() 
	{
		List <Move> out = new ArrayList <Move>();
		
		for(int j = 0; j < translations.length; j++)
		{
			Move mv = new Move(this.getPosition(), getPosition() + translations[j]);
			if(isLegal(mv))
			{
				out.add(mv);
			}
		}
		
		return out;
	}
	
	@Override
	public String destroy() throws KingDied
	{
		throw new KingDied();
	}
	
	@Override
	public int getValue()
	{
		return value * ChessGame.MATERIALMULTIPLIER;
	}

}
