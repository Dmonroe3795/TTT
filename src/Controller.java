import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Controller {
	final static int PORT_NUM1 = 8016;
	static ServerSocket player1Socket;
	static Socket player1;
	static DataInputStream is1;
	static PrintWriter os1;
	static Socket player2;
	static DataInputStream is2;
	static PrintWriter os2;
	public static void main(String[] args) {
		System.out.println("Attempting to connect");
		try {
			setStreams();
			Board gameBoard = new Board();
			PrintWriter out;
			DataInputStream in;
			boolean player = false;
			while (!gameBoard.isOver(player) && !gameBoard.isBoardFull()) {
				player = !player;
				System.out.println("Game is on");
				if (player) {
					in = is1;
					out = os1;
					out.println("Player 1 its your turn!");
				} else {
					in = is2;
					out = os2;
					out.println("Player 2 its your turn!");
				}
				int move = -1;
				do {
					out.println(gameBoard);
					out.println("m");
					move = Integer.parseInt(in.readLine());
				} while (!gameBoard.makeMove(move, player));
				out.println(gameBoard);
			}
			System.out.println("Game is over");
			System.out.println(gameBoard.isOver(player));
			System.out.println(gameBoard.isBoardFull());
			if (gameBoard.isOver(player)) {
				if (player) {
					os1.println(gameBoard);
					os1.println("gratz you won");
					os2.println(gameBoard);
					os2.println("YOU LOSE!");
				} else {
					os1.println(gameBoard);
					os2.println("gratz you won");
					os2.println(gameBoard);
					os1.println("YOU LOSE!");
				}
			} else {
				os1.println(gameBoard);
				os2.println(gameBoard);
				os1.println("its a cats game");
				os2.println("its a cats game");
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	public static void setStreams() throws IOException {
		player1Socket = new ServerSocket(PORT_NUM1);
		player1 = player1Socket.accept();
		player2 = player1Socket.accept();
		is1 = new DataInputStream(player1.getInputStream());
		os1 = new PrintWriter(player1.getOutputStream(), true);
		is2 = new DataInputStream(player2.getInputStream());
		os2 = new PrintWriter(player2.getOutputStream(), true);
		System.out.println("Connected to Client");
		
	}

}