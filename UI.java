package lab5;



import java.util.Scanner;

public class UI {

	private Business biz;
	
	public void ServerandClient()
	{
		getFilePath();
		biz.startserver();
		biz.startclient();
		biz.done();
	}
	
	protected UI() {
		this.biz = new Business();
	}

	public void getFilePath()
	{
		System.out.print("Enter File Path: ");
		Scanner in = new Scanner(System.in);
		String text =  in.nextLine();
		biz.setFilepath(text);
		in.close();
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UI ui = new UI();
		ui.ServerandClient();
	}

}
