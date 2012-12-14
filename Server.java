package lab5;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;

public class Server extends Network implements Runnable {

	private BufferedReader in;
	private OutputStream out;
	private String filepath;

	private File filep;

	public Server() {
		System.out.println("Opening Server");

	}

	public void run() {

		try {
			ServerSocket servsock = new ServerSocket(8000);
			System.out.println("Server: Waiting for Connection");
			socket = servsock.accept();
			System.out.println("Server: Connected to Client");
			in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			out = socket.getOutputStream();;

			System.out.println("Server: waiting for path");
			filepath = in.readLine();
			System.out.println("Server: Got Filepath: " + filepath);

			filep = HandleFile.getFile(filepath);
			System.out.println("Server: Filed saved to: " + filep.getAbsolutePath());
			byte[] mybytearray = new byte[(int) filep.length()];
			BufferedInputStream bis = new BufferedInputStream(
					new FileInputStream(filep));
			bis.read(mybytearray, 0, mybytearray.length);
			out.write(mybytearray, 0, mybytearray.length);
			out.flush();
			out.close();
			bis.close();
			servsock.close();
			socket.close();
			System.out.println("Server: File sent");
		

		} catch (IOException ex) {
			System.out.println("Server");
			System.err.println(ex);
		}
		
		this.setFinished(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
