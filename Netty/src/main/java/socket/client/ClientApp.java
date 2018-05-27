package socket.client;

public class ClientApp {
	public static void main(String [] args) {
		for(int x=0;x<1024;x++) {
			StartClient sClient = new StartClient(x);
			sClient.start();
			//System.out.println("Client is start");
		}
		
	}

}
