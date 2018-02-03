import java.io.Serializable;

public class Board implements Serializable{
	private int[][] boardArray;
	public int[][] getBoardArray() {
		return boardArray;
	}
	
	public void setBoardArray(int[][] boardArray) {
		this.boardArray = boardArray;
	}
	public Board() {
		setBoardArray(new int[3][3]);
	}
	public Board(Board b) {
		setBoardArray(b.getBoardArray());
	}
	public boolean makeMove(int space, boolean player)
	{
		System.out.println("Attempting move");
		if(getCell(space) == 1 || getCell(space) == 2)
		{
			System.out.println("move failed");
			return false;
		}
		setCell(space,player);
		System.out.println("move succeeded");
		return true;
	}
	public int getCell(int space)
	{
		space -= 1;
		int x = (space%3);
		int y = space/3;
		return boardArray[x][y];
	}
	public void setCell(int space,boolean player)
	{
		space -= 1;
		int x = (space%3);
		int y = space/3;
		if(player)
			boardArray[x][y] = 1;
		else
			boardArray[x][y] = 2;
	}
	public boolean isBoardFull()
	{
		System.out.println("checking if game board is full");
		for(int[] x: boardArray)
		{
			for(int y: x)
			{
				if(y == 0)
					return false;
			}
		}
		return true;
	}
	public boolean isOver(boolean player)
	{
		System.out.println("checking game board for winner");
		int x;
		if(player)
			x = 1;
		else
			x = 2; 
		if(getCell(1)== x)
		{
			System.out.println(getCell(1));
			if(getCell(2) == x)
			{
				if(getCell(3) == x)
				{
					System.out.println("we have a winner");
					return true;
				}
			}
			if(getCell(5) == x)
			{
				if(getCell(9) == x)
				{
					System.out.println("we have a winner");
					return true;
				}
			}
			if(getCell(4)== x)
			{
				if(getCell(7)== x)
				{
					System.out.println("we have a winner");
					return true;
				}
			}
		}
		if(getCell(6)== x)
		{
			if(getCell(5)== x)
			{
				if(getCell(4)== x)
				{
					System.out.println("we have a winner");
					return true;
				}
			}
			if(getCell(3)== x)
			{
				if(getCell(9)== x)
				{
					System.out.println("we have a winner");
					return true;
				}
			}
		}
		if(getCell(7)== x)
		{
			if(getCell(5)== x)
			{
				if(getCell(3)== x)
				{
					System.out.println("we have a winner");
					return true;
				}
			}
			if(getCell(8)== x)
			{
				if(getCell(9)== x)
				{
					System.out.println("we have a winner");
					return true;
				}
			}	
		}
		if(getCell(2)== x)
		{
			if(getCell(5)== x)
			{
				if(getCell(8)== x)
				{
					System.out.println("we have a winner");
					return true;
				}
			}
		}
		System.out.println("we dont have a winner");
		return false;
	}
	public String toString()
	{
		char c = ' ';
		String s= "";
		for(int y = 0; y < 3; y++)
		{
			for(int x = 0;x < 3; x++ )
			{
				switch(boardArray[x][y])
				{
				case 0:
					c = ' ';
					break;
				case 1:
					c = 'X';
					break;
				case 2:
					c = '0';
					break;	
				}
				if(x == 2)
					s += c; 
				else
				{
					String z = "" + c + "|";
					s += z;
				}
			}
			if(y != 2)
			 s += "\n _____\n";	
		}	
		return s;
	}
}