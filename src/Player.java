import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Player extends Application{
	public static void main(String[] args) throws UnknownHostException, IOException {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		try
		{
		Parent root = FXMLLoader.load(getClass().getResource("TTTfxml.fxml"));
		stage.setTitle("Tic Tac Toe");
		stage.setScene(new Scene(root,500,800));
		stage.show();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		
		
	}
}