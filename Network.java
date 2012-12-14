package lab5;

import java.net.Socket;

public class Network implements Runnable {

	protected Socket socket;
	
	protected boolean finished = false;
	public void run() {
		// TODO Auto-generated method stub

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	protected boolean isFinished() {
		return finished;
	}

	protected void setFinished(boolean finished) {
		this.finished = finished;
	}

}
