package Szachy;

import java.util.List;
import java.util.Scanner;

public class HumanPlayer extends Player {

	Scanner reader;
	
	public HumanPlayer() {
		super("Ola");
		reader = new Scanner(System.in);
		System.out.println("Enter your name: ");
		try
		{
			name = reader.next();		
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			System.out.println("Your name is Ola, then.");
		}
	}

	private int getPlayerInput()
	{
		String s = reader.next();
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
		System.out.println("I want to move a piece from: ");
		Piece who = game.getChessboard().getPiece(getPlayerInput());
		while( who == null || (who.isWhite() != game.isWhite(this)) )
		{
			System.out.println("There is no piece of your color on this field!");
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
