package socket.server;

public class StartServer extends Thread {
	private SocketStart socketStart = new SocketStart();
	public void run() {
		try {
			socketStart.startServer("127.0.0.1", 8081);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
