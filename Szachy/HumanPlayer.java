package Szachy;

import java.util.List;
import java.util.Scanner;

public class HumanPlayer extends Player {

	public HumanPlayer(ChessGame game, List<Piece> pieces) {
		super(game, pieces, "");
		Scanner reader = new Scanner(System.in);
		System.out.println("Enter your name: ");
		name = reader.next();
		reader.close();
		
		
	}

	private int getPlayerInput()
	{
		Scanner reader = new Scanner(System.in);
		String s = reader.next();
		reader.close();
		try
		{
			char column = s.charAt(0);
			int row = Character.getNumericValue(s.charAt(1));
			return game.getChessboard().getPosition(column, row);
		}
		catch(Exception e)
		{
			System.out.println("You entered wrong value! Try something like b7 or A5: ");
			return getPlayerInput();
		}

	}
	
	private Move getUserDefinedMove()
	{
		Piece who = null;
		while(who == null)
		{
			System.out.println("I want to move a piece from: ");
			who = game.getChessboard().getPiece(getPlayerInput());
		}

		System.out.println(" to: ");
		int where = getPlayerInput();
		return new Move(who, where);
	}
	
	@Override
	protected Move chooseMove(List<Move> moves) 
	{
		Move mv = getUserDefinedMove();
		while(!moves.contains(mv))
		{
			System.out.println("That is not a valid move! Let's try again.");
			mv = getUserDefinedMove();
		}
		
		return mv;
		
	}

}
