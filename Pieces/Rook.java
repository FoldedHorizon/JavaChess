package Pieces;

import Utils.*;

import java.util.*;

import Utils.ChessGame;


public class Rook extends Piece 
{
	private final static int value = 5;
	
	protected static int translations [] = { -ChessGame.SIZE, 1, ChessGame.SIZE, -1 };
	
	public Rook(ChessGame game, boolean white) 
	{
		super(game,white);
		if(white)
			sign = '♜';
		else
			sign = '♖';
				
	}

        @Override
        public Piece copy(Chessboard board)
        {
            Piece out = new Rook(game,isWhite());
            out.board = board;
            out.sign = sign;
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
				Move mv = new Move(this.getPosition(), getPosition() + i * translations[j]);
				
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
