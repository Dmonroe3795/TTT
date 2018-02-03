import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Controller
{
    public static void main(String[] args)
    {
        final int PORT_NUM1 = 8016;
        //final int PORT_NUM2 = 8017;	
        ServerSocket player1Socket;
        Socket player1;
        DataInputStream is1;
        PrintWriter os1;
        //ServerSocket player2Socket;
        Socket player2;
        DataInputStream is2;
        PrintWriter os2;
       // String p1Line;
      //  String p2Line;
        System.out.println("Attempting to connect");
        try
        {
            player1Socket = new ServerSocket(PORT_NUM1);
          
            player1 = player1Socket.accept();
            player2 = player1Socket.accept();
            is1 = new DataInputStream(player1.getInputStream());
            os1 = new PrintWriter(player1.getOutputStream(),true);
            is2 = new DataInputStream(player2.getInputStream());
            os2 = new PrintWriter(player2.getOutputStream(),true);
            // As long as we receive data, echo that data back to the client.
            System.out.println("Connected to Client");
            Board gameBoard = new Board();
            PrintWriter out;
            DataInputStream in;
            boolean player = false;
            while(!gameBoard.isOver(player) && !gameBoard.isBoardFull())
            {
            	player = !player;
            	System.out.println("Game is on");
                if(player)
                {
                	in = is1;
                	out = os1;
                	out.println("Player 1 its your turn!");
                }
                else 
                {
                	in = is2;
                	out = os2;
                	out.println("Player 2 its your turn!");
                }
                int move = -1;
                do
                {
                do
                {
                	out.println(gameBoard);
                	out.println("m");
                	move = Integer.parseInt(in.readLine());
                	
                }while(move < 1 && move > 9);
                }while(!gameBoard.makeMove(move, player)); 
            }
            System.out.println("Game is over");
            System.out.println(gameBoard.isOver(player));
            System.out.println(gameBoard.isBoardFull());
            if(gameBoard.isOver(player))
            {
            	if(player)
            	{
            		os1.println("gratz you won");
            		os2.println(gameBoard);
            		os2.println("YOU LOSE!");
            	}
            	else
            	{
            		os2.println("gratz you won");
            		os2.println(gameBoard);
            		os1.println("YOU LOSE!");
            	}
            }
            else 
            {
            	os1.println("its a cats game");
            	os2.println("its a cats game");
            	}
        } catch (IOException e)
        {
            System.out.println(e);
        }
    }
    public static int requestMove(PrintWriter out, DataInputStream in) throws IOException
    {
        int move = 0;
        while(move < 1 || move > 9)
        {
        out.println("Enter a move 1-9");
        move = in.readInt();
        }    
        return move;
    }   
}