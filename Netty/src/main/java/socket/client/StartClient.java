package socket.client;

public class StartClient extends Thread{
	private ClientStart clientStart = new ClientStart();
	
	public void run() {
		try {
			clientStart.connectServer("127.0.0.1", 8081);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
