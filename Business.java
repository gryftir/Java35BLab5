package lab5;



public class Business {
	private Server server;
	private Client client;
	private String filepath;
	Thread t1, t2;
	
	protected void startserver()
	{
		
		server = new Server();
		t2 = new Thread(server);
		t2.start();
	}
	protected void startclient()
	{
		client = new Client(filepath);
		t1 = new Thread(client);
			t1.start();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public void done()
	{
		while (!server.isFinished() && !client.isFinished())
		{
		}
		System.out.println("Program Ending");
		t1 = null;
		t2 = null;
		System.exit(0);
	}
	protected String getFilepath() {
		return filepath;
	}

	protected void setFilepath(String filepath) {
		this.filepath = filepath;
	}

}
