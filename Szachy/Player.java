package Szachy;

import java.util.*;

public abstract class Player {

	private List <Piece> myPieces;
	protected ChessGame game;
	protected String name;
	
	
	protected abstract Move chooseMove(List <Move> moves);
	
	public String getName()
	{
		return new String(name);
	}
	
	protected void pieceCaptured(Piece piece)
	{
		if (!myPieces.remove(piece))
		{
			//Trying to remove pieces too often or from a wrong player, ay?
			assert false;
		}
	}
	
	protected Move getMove()
	{
		//Gather all possible moves.
		List <Move> all = new ArrayList <Move>();
		for(Piece i : myPieces)
		{
			all.addAll(i.getMoves());
		}
		
		//Run evaluating function and return its result.
		return chooseMove(all);
	}
	
	protected Player(ChessGame game, List <Piece> pieces, String name)
	{
		this.game = game;
		myPieces = new ArrayList <Piece>();
		myPieces.addAll(pieces);
		this.name = name;
	}
}
