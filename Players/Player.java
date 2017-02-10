package Players;

import Utils.*;

import java.util.*;

import Pieces.Piece;
import Utils.ChessGame;

public abstract class Player {

	//private List <Piece> myPieces;
	protected ChessGame game;
	protected String name;
	
	protected List <Piece> getMyPieces()
	{
		//get coressponding pieces
		return game.getChessboard().getPieces(game.getWhitePlayer()==this);
	}
	
	protected abstract Move chooseMove(List <Move> moves);
	
	public String getName()
	{
		return new String(name);
	}
	
	public void pieceCaptured(Piece piece)
	{
		System.out.println(piece + " captured!");
	}
	
	public Move getMove()
	{
		//Gather all possible moves.
		List <Move> all = new ArrayList <Move>();
		for(Piece i : getMyPieces())
		{
			all.addAll(i.getMoves());
		}
		
		//Run evaluating function and return its result.
		return chooseMove(all);
	}
	
	protected Player(String name)
	{

		this.name = name;
	}
	
	public void init(ChessGame game)
	{
		this.game = game;
	}
}
