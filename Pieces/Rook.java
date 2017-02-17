package Pieces;

import Utils.*;

import java.util.*;



public class Rook extends Piece 
{
	private final static int value = 5;
	
	protected static int translations [] = { -ChessGame.SIZE, 1, ChessGame.SIZE, -1 };
	
	public Rook(Chessboard board, boolean white) 
	{
		super(board,white);
		if(white)
			sign = '♜';
		else
			sign = '♖';
				
	}

    @Override
    public Piece copy(Chessboard newBoard)
    {
    	assert newBoard != null;
        Piece out = new Rook(newBoard, isWhite());
        return out;
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
			for(int i = 1; i < ChessGame.SIZE; i++)
			{
				int myPos = getPosition();
				Move mv = new Move(myPos, myPos + i * translations[j]);
				
				if(isLegal(mv))
					out.add(mv);
				
				//cannot jump over other pieces
				if(board.getPiece(mv.where) != null)
					break;
			}
		}
		
		return out;
	}
	
	@Override
	public int getValue()
	{
		return value * ChessGame.MATERIALMULTIPLIER;
	}

}
