package socket.client;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.Socket;
import java.net.UnknownHostException;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import message.MessageManager;
import resnponse.MachineLoginResponse;
import resnponse.testResponse;

public class ClientTest {
	private static final int MAX = 1024;
	private static String serverName ="127.0.0.1";
	private static int port =8081;
	private static ClientTest cTest = new ClientTest();
	
	public static void main(String [] args) throws UnknownHostException, IOException {
		int[] sockets = new int[MAX];
		
		for(int i =0;i<1;i++ ) {
			 try
		      {
				
				Socket socket=new Socket("127.0.0.1",8081);
				cTest.send(socket,1);
				cTest.receive(socket);
				
		  		
		      }catch (Exception e) {
				// TODO: handle exception
			}
		
		}
	}
	
	public void send(Socket s,int i) throws IllegalArgumentException, IllegalAccessException, IOException {
		//PrintWriter os=new PrintWriter(socket.getOutputStream());
		MachineLoginResponse rsp = new MachineLoginResponse();
		rsp.setMachineId(i);
		rsp.setPrivateIp("192.168.0.9");
		rsp.setPrivatePort(i);
		

		MessageManager messageManager =new MessageManager();
  		ByteBuf buf = messageManager.generateCommandBuf(2005, i, rsp);
  		OutputStream out = s.getOutputStream();
  		
  	
  		out.write(buf.array());
  		out.flush();
  		
       // out.close();
  		//System.out.println("----");
  		//s.close();
	}
	public void receive(Socket s) throws IOException {
		System.out.println("--");
		
		InputStream inFromServer = s.getInputStream();
		
		//byte[] bs = input2byte(inFromServer);
		
      System.out.println(inFromServer.toString());
		
     
	}
	public  byte[] input2byte(InputStream inStream)  
            throws IOException {  
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();  
        byte[] buff = new byte[100];  
        int rc = 0;  
        while ((rc = inStream.read(buff, 0, 100)) > 0) {  
            swapStream.write(buff, 0, rc);  
        }  
        byte[] in2b = swapStream.toByteArray();  
        return in2b;  
    } 
}

