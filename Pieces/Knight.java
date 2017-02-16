package Pieces;

import Utils.*;

import java.util.*;

import Utils.ChessGame;


public class Knight extends Piece 
{
	private final static int value = 3;

	protected static int translations [] = 
		{ -(2 * ChessGame.SIZE) + 1, 
		-ChessGame.SIZE + 2, 
		ChessGame.SIZE + 2, 
		2 * ChessGame.SIZE + 1, 
		2 * ChessGame.SIZE - 1, 
		ChessGame.SIZE - 2, 
		-ChessGame.SIZE - 2, 
		-(2 * ChessGame.SIZE) - 1 };

	public Knight(ChessGame game, boolean white) 
	{
		super(game, white);
		
		if(white)
			sign = '♞';
		else
			sign = '♘';
		
	}
	
    @Override
    public Piece copy(Chessboard board)
    {
        Piece out = new Knight(game,isWhite());
        out.board = board;
        out.sign = sign;
        return out;
    }
        
	@Override
	public List <Move> getMoves()
	{
		List <Move> out = new ArrayList <Move>();
		
		for(int i = 0; i < translations.length; i++)
		{
			Move mv = new Move(this.getPosition(), getPosition() + translations[i]);
			if(isLegal(mv))
			{
				out.add(mv);
			}
		}
				
		return out;
	}
	
	@Override
	protected boolean isLegal(Move move)
	{
		if(!super.isLegal(move))
			return false;
		
		//checking if no border was crossed.
		int pos = getPosition();
		if ( Math.abs(pos%ChessGame.SIZE - move.where%ChessGame.SIZE) >= 3 
			|| Math.abs(pos/ChessGame.SIZE - move.where/ChessGame.SIZE ) >= 3 )
		{
			return false;
		}
		
		return true;
	}

	@Override
	public int getValue()
	{
		return value * ChessGame.MATERIALMULTIPLIER;
	}

}
