
package chess;

import java.util.*;

public class Knight extends Piece
{
	private static final int translations[] = { -(2 * SIZE) + 1, -SIZE + 2,SIZE + 2,2 * SIZE + 1,2 * SIZE - 1,SIZE - 2,-SIZE - 2,-(2 * SIZE) - 1 };

	Knight(int pos, boolean white)
	{
		this.pos = pos;
		this.white = white;
		
		if(isWhite())
			this.sign = '♞';
		else
			this.sign = '♘';
	}
	
	@Override
	public List<Pair<Integer, Float>> MovesAndTheirValues(Chessboard board) {
		List <Pair<Integer,Float>> results = new ArrayList <Pair<Integer,Float>>();
		for( int i : translations )
		{
			int newPos = pos + i;
			if (Math.abs(pos%SIZE - newPos%SIZE) < 3 && Math.abs(pos - newPos) < 3 * SIZE)
			{
				if (board.canMove(this, newPos))
				{
					Float value;
					if(board.getPiece(newPos)== null)
						value = new Float(0.5);
					else
						value = new Float(1);
					results.add(new Pair<Integer,Float>());
				}
			}
		}
		
		return results;
	}
}

/*#in1clude <iostream>

using namespace std;

/*A friend of you is doing research on the Traveling Knight Problem (TKP) where you are to find the shortest closed tour of knight moves that visits each square of a given set of n squares on a chessboard exactly once. He thinks that the most difficult part of the problem is determining the smallest number of knight moves between two given squares and that, once you have accomplished this, finding the tour would be easy.
Of course you know that it is vice versa. So you offer him to write a program that solves the "difficult" part.
Your job is to write a program that takes two squares a and b as input and then determines the number of knight moves on a shortest route from a to b.

Input Specification
The input file will contain one or more test cases. Each test case consists of one line containing two squares separated by one space. A square is a string consisting of a letter (a-h) representing the column and a digit (1-8) representing the row on the chessboard.

Output Specification
For each test case, print one line saying "To get from xx to yy takes n knight moves.".

Sample Input
e2 e4
a1 b2
b2 c3
a1 h8
a1 h7
h8 a1
b1 c3
f6 f6

Sample Output
To get from e2 to e4 takes 2 knight moves.
To get from a1 to b2 takes 4 knight moves.
To get from b2 to c3 takes 2 knight moves.
To get from a1 to h8 takes 6 knight moves.
To get from a1 to h7 takes 5 knight moves.
To get from h8 to a1 takes 6 knight moves.
To get from b1 to c3 takes 1 knight moves.
To get from f6 to f6 takes 0 knight moves.

const int SIZE = 8;

struct Node {
	int pos;
	bool visited = false;
	int moves = 0;
};

int knight(int start, int target)
{
	Node graph[SIZE*SIZE];
	for (int i = 0;i < SIZE*SIZE;i++)
		graph[i].pos = i;
	Node *fringe[SIZE*SIZE];
	int head = 0, tail = 0;
	fringe[head] = &graph[start];
	fringe[head]->visited = true;
	tail++;
	while (fringe[head]->pos != target)
	{
		Node *current = fringe[head];
		const int translations[8] = { -(2 * SIZE) + 1, -SIZE + 2,SIZE + 2,2 * SIZE + 1,2 * SIZE - 1,SIZE - 2,-SIZE - 2,-(2 * SIZE) - 1 };
		for (int i = 0;i < 8;i++)
		{
			int newPos = current->pos + translations[i];
			if (abs(current->pos%SIZE - newPos%SIZE) < 3 && abs(current->pos - newPos) < 3 * SIZE)
			{
				if ((newPos < SIZE*SIZE) && (newPos >= 0))
				{
					if (!graph[newPos].visited)
					{
						fringe[tail] = &graph[newPos];
						fringe[tail]->visited = true;
						fringe[tail]->moves = current->moves + 1;
						tail++;
					}
				}
			}
		}
		head++;
	}
	return fringe[head]->moves;
}

int main()
{
	int first, sec, start, target;
	char firstChar, secChar;
	while (cin >> firstChar >> first >> secChar >> sec)
	{
		start = (int(firstChar) - 'a') + (SIZE*(first - 1));
		target = (int(secChar) - 'a') + (SIZE*(sec - 1));
		///cout << start << " " << target << endl;
		cout << "To get from " << firstChar << first << " to " << secChar << sec << " takes " << knight(start, target) << " knight moves." << endl;
	}
} */