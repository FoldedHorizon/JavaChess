package Szachy;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

	public Pawn(Chessboard board, boolean white) {
		super(board, white);
		
		if(white)
			sign = '♟';
		else
			sign = '♙';
		
		value = 1;
	}
	
	@Override
	protected List<Move> getMoves() {
		List <Move> out = new ArrayList <Move>();
		
		int translation;
		
		if(!isWhite())
			translation = -Chessboard.SIZE;
		else
			translation = Chessboard.SIZE;	
		
		//forward	
		Move mv = new Move(this, getPosition() + translation);
		if(isLegal(mv) && (board.getPiece(mv.where) == null))
			out.add(mv);
		
		//left
		translation += -1;
		mv = new Move(this, getPosition() + translation);
		if(isLegal(mv) && (board.getPiece(mv.where) != null))
			out.add(mv);
		//right
		translation += 2;
		mv = new Move(this, getPosition() + translation);
		if(isLegal(mv) && (board.getPiece(mv.where) != null))
			out.add(mv);
		
		//I know it's ugly copy-paste. Maybe I will come back and rewrite it later.
		
		return out;
		
	}

}
