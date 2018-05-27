package socket.client;

public class StartClient extends Thread{
	private ClientStart clientStart = new ClientStart();
	private int num;
	
	public StartClient(int num) {
		this.num =num;
	}


	public void run() {
		try {
			clientStart.connectServer("127.0.0.1", 8081,num);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
