import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
//import java.util.concurrent.TimeUnit;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class guiController extends Thread{
	 
	@FXML Button btn1;
	@FXML Button btn2;
	@FXML Button btn3;
	@FXML Button btn4;
	@FXML Button btn5;
	@FXML Button btn6;
	@FXML Button btn7;
	@FXML Button btn8;
	@FXML Button btn9;
	@FXML Label turn;
	@FXML GridPane grid;
	@FXML VBox box;
	 
	 
	public  int move;
	public int PORT_NUM;
	public guiController thread = null;
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
	public void run()
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
		

		
	}
	public void running() throws ClassNotFoundException, IOException, InterruptedException
	{
		
		try {
			thread = new guiController();
		} catch (ClassNotFoundException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		thread.start();
		
	}

	public Board recieveBoard() throws InterruptedException, ClassNotFoundException, IOException {
		System.out.println("Running");

		Board board = (Board) thread.objectIn.readObject();

		
		return board;

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
	public void btn1action() throws ClassNotFoundException, InterruptedException, IOException {
		thread.os.println(1);
		turn.setText("Its not Turn!");
		updateBoard(recieveBoard());
	}
	public void btn2action() throws ClassNotFoundException, InterruptedException, IOException {
		thread.os.println(2);
		turn.setText("Its not Turn!");
		updateBoard(recieveBoard());
	}
	public void btn3action() throws ClassNotFoundException, InterruptedException, IOException {
		thread.os.println(3);
		turn.setText("Its not Turn!");
		updateBoard(recieveBoard());
	}
	public void btn4action() throws ClassNotFoundException, InterruptedException, IOException {
		thread.os.println(4);
		turn.setText("Its not Turn!");
		updateBoard(recieveBoard());
	}
	public void btn5action() throws ClassNotFoundException, InterruptedException, IOException {
		thread.os.println(5);
		turn.setText("Its not Turn!");
		updateBoard(recieveBoard());
	}
	public void btn6action() throws ClassNotFoundException, InterruptedException, IOException {
		thread.os.println(6);
		turn.setText("Its not Turn!");
		updateBoard(recieveBoard());
	}
	public void btn7action() throws ClassNotFoundException, InterruptedException, IOException {
		thread.os.println(7);
		turn.setText("Its not Turn!");
		updateBoard(recieveBoard());
	}
	public void btn8action() throws ClassNotFoundException, InterruptedException, IOException {
		thread.os.println(8);
		turn.setText("Its not Turn!");
		updateBoard(recieveBoard());
	}
	public void btn9action() throws ClassNotFoundException, InterruptedException, IOException {
		thread.os.println(9);
		turn.setText("Its not Turn!");
		updateBoard(recieveBoard());
	}
}
