package lab5;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;



public class Client extends Network implements Runnable {

	private String filepath;
	private PrintWriter out;
	

	public Client() {
		socket = null;
		System.out.println("Opening Client");

	}

	public void run()  {
		try{
			Thread.sleep(2000);	
		}
		catch (Exception e)
		{
			
		}
		
		try {
			socket = new Socket("127.0.0.1", 8000);
			out = new PrintWriter(socket.getOutputStream(), true);
			out.flush();
			out.println(filepath);
			InputStream is =  socket.getInputStream();
			byte[] bytearray = new byte[1024];
			FileOutputStream fos = new FileOutputStream("filesaved.xml");
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			System.out.println("Client: Filepath sent, waiting for File");
			int bytesRead = 0;
			while ((bytesRead = is.read(bytearray, 0, bytearray.length)) != -1)
			{
				bos.write(bytearray, 0, bytesRead);
				bos.flush();
				System.out.println("Client: Saving file: " + bytesRead + "bytes");
				if (bytesRead < 1024)
				{
					System.out.println("Client: Finished file read: exiting");
					System.exit(0);
				}
			}
			bos.close();
			out.close();
			is.close();
			socket.close();
			this.setFinished(true);
			

		} catch (IOException ex) {
			System.out.println("Client");
			System.err.println(ex);

		}
		
		
		
		
	}

	protected Client(String filepath) {
		this.filepath = filepath;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	protected String getFilepath() {
		return filepath;
	}

	protected void setFilepath(String filepath) {
		this.filepath = filepath;
	}

}
