import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Player {
	public static void main(String[] args) throws UnknownHostException, IOException {
		int PORT_NUM;
		PORT_NUM = 8016;
		Socket myClient = null;
		PrintWriter os = null;
		BufferedReader is = null;
		Scanner sc = null;
		System.out.println("Attempting to connect");
		try {
			myClient = new Socket("localhost", PORT_NUM);
			sc = new Scanner(System.in);
			System.out.println("Connected to host: " + PORT_NUM);
			os = new PrintWriter(myClient.getOutputStream(), true);
			System.out.println("Created output Stream");
			is = new BufferedReader(new InputStreamReader(myClient.getInputStream()));
			System.out.print("Created input stream");
		} catch (IOException e) {
			System.out.println(e);
		}
		System.out.println("Connected");
		String serverLine;
		try {
		if (myClient != null && os != null && is != null) {
			while (myClient.isConnected()) {
				while ((serverLine = is.readLine()) != null) {
					if (serverLine.equals("m"))//m is my flag that server wants input
					{
						System.out.print("Make a move choose a space 1-9 that isnt occupied");
						int x = sc.nextInt();
						os.println(x);
					}
					else
					System.out.println(serverLine);
				}	
			}
		}
		}catch(SocketException e)
		{
			System.out.println("Disconnected");
		}
		
	}
}