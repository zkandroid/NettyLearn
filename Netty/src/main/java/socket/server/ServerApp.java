package socket.server;

public class ServerApp {
	
	public static void main(String [] args) {
		StartServer server = new StartServer();
		server.start();
		System.out.println("Server is start");
	}

}
