package Szachy;

import java.util.*;

public abstract class Player {

	private List <Piece> myPieces;
	protected Chessboard board;
	private String name;
	
	
	protected abstract float evaluate(Move move);
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
	
	protected Player(Chessboard b, List <Piece> p, String name)
	{
		board = b;
		myPieces = new ArrayList <Piece>();
		myPieces.addAll(p);
		this.name = name;
	}
}
