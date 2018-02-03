import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class guiController {
	 @FXML
	 Button btn1;
	 @FXML
	 Button btn2;
	 @FXML
	 Button btn3;
	 @FXML
	 Button btn4;
	 @FXML
	 Button btn5;
	 @FXML
	 Button btn6;
	 @FXML
	 Button btn7;
	 @FXML
	 Button btn8;
	 @FXML
	 Button btn9;
	 @FXML
	 Label turn;
	 @FXML
	 GridPane grid;
	 @FXML
	 VBox box;
	 
	 
	public  int move;
	public int PORT_NUM;
	public  Socket myClient = null;
	public  PrintWriter os = null;
	public  ObjectInputStream objectIn= null;
	public  BufferedReader is = null;
	public Scanner sc = null;
	public guiController() throws IOException, ClassNotFoundException
	{
		PORT_NUM = 8027;
		move = -1;
	}
	public void connect() throws ClassNotFoundException, IOException, InterruptedException
	{
		System.out.println("Attempting to connect");
		try {
			myClient = new Socket("localhost", PORT_NUM);
			objectIn = new ObjectInputStream(myClient.getInputStream());
			sc = new Scanner(System.in);
			System.out.println("Connected to host: " + PORT_NUM);
			os = new PrintWriter(myClient.getOutputStream(), true);
			System.out.println("Created output Stream");
			is = new BufferedReader(new InputStreamReader(myClient.getInputStream()));
			System.out.print("Created input stream");
			// is = new DataInputStream(myClient.getInputStream());
		} catch (IOException e) {
			System.out.println(e);
		}
		System.out.println("Connected");
		
		running();
	}
	public void running() throws ClassNotFoundException, IOException, InterruptedException
	{
		System.out.println("Running");
		String serverLine;
		//Board objectline;
		Board board; //= (Board) objectIn.readObject();
		
		if (myClient != null && os != null && is != null) {
			while (myClient.isConnected()) {
				while (/*(serverLine = is.readLine())*/(board = (Board) objectIn.readObject()) != null) {
					//if (serverLine.equals("m"))//m is my flag that server wants input
				//	{
					System.out.println("Received Board \n" + board);
						move = -1;
						//turn.setText("Its Your Turn!");
						updateBoard(board);
						
						//int x = 4;
						do
						{
							System.out.print(".");
						}while(move==-1);
						os.println(move);
						System.out.println("Sent Move");
						//turn.setText("Its Not Your Turn!");
					//}
					//else
					//System.out.println(serverLine);
				}	
			}
		}
	}
	public String cellToMark(int x)
	{
		switch(x)
		{
		case 1:
			return "X";
		case 2:
			return "O";
		default:
			return " ";
		}
	}
	public void updateBoard(Board b) throws InterruptedException {
		System.out.println("Updating Board");
		btn1.setText(cellToMark(b.getCell(1)));
		btn2.setText(cellToMark(b.getCell(2)));
		btn3.setText(cellToMark(b.getCell(3)));
		btn4.setText(cellToMark(b.getCell(4)));
		btn5.setText(cellToMark(b.getCell(5)));
		btn6.setText(cellToMark(b.getCell(6)));
		btn7.setText(cellToMark(b.getCell(7)));
		btn8.setText(cellToMark(b.getCell(8)));
		btn9.setText(cellToMark(b.getCell(9)));
		System.out.println("Board Updated");
		
	}
	public void btn1action() {
		move = 1;
	}
	public void btn2action() {
		move = 2;
	}
	public void btn3action() {
		move = 3;
	}
	public void btn4action() {
		move = 4;
	}
	public void btn5action() {
		move = 5;
	}
	public void btn6action() {
		move = 6;
	}
	public void btn7action() {
		move = 7;
	}
	public void btn8action() {
		move = 8;
	}
	public void btn9action() {
		move = 9;
	}
}
