package socket.client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import message.MessageManager;
import resnponse.testResponse;

public class ClientTest {
	private static final int MAX = 1024;
	private static String serverName ="127.0.0.1";
	private static int port =8081;
	
	public static void main(String [] args) throws UnknownHostException, IOException {
		int[] sockets = new int[MAX];
		
		//for(int i =0;i<10;i++ ) {
			 try
		      {
				
				Socket socket=new Socket("127.0.0.1",8081);
				//PrintWriter os=new PrintWriter(socket.getOutputStream());
				testResponse rsp = new testResponse();
				rsp.setSize(5);
				rsp.setSend("hello");
				//ChannelHandlerContext CTX = (ChannelHandlerContext)socket.getChannel();
				rsp.setMachineId(1);
				//socket.g
				MessageManager messageManager =new MessageManager();
		  		ByteBuf buf = messageManager.generateCommandBuf(1, 1, rsp);
		  		//while(true) {
		  			//CTX.writeAndFlush(buf);
		  		OutputStream out = socket.getOutputStream();
		        ByteBuf header = Unpooled.buffer(1024);
		        //header.writeBytes();
		        out.write(buf.array());
		        out.flush();
		        out.close();
		  			System.out.println("----");
		  			//os.close();
		  		//}
		      }catch (Exception e) {
				// TODO: handle exception
			}
		//}

	}
}

