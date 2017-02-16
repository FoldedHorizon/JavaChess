package Players;

import Utils.*;

import java.util.*;

import Pieces.Piece;
import Utils.ChessGame;

public abstract class Player {

	protected ChessGame game;
	protected String name;
	
/*	protected List <Piece> getMyPieces()
	{
		//get coressponding pieces
		return game.getChessboard().getPieces(game.getWhitePlayer()==this);
	}
*/	
	protected abstract Move chooseMove(List <Move> moves);
	
	public String getName()
	{
		return new String(name);
	}
	
	public void pieceCaptured(Piece piece)
	{
		System.out.println(piece + " captured!");
	}
	
	public Move getMove() throws NoValidMove
	{
		//Run evaluating function and return its result.
		Move result = chooseMove(game.getChessboard().gatherAllMoves(this.isWhite())); 
		if(result == null)
            throw new NoValidMove();
        return result;
	}
	        
	protected Player(String name)
	{
		this.name = name;
	}
	
	public boolean isWhite()
	{
		return game.isWhite(this);
	}
	
	public void init(ChessGame game)
	{
		this.game = game;
	}
}
