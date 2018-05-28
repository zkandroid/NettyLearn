package socket.client;

import java.io.IOException;
import java.io.PrintWriter;

import java.net.UnknownHostException;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.unix.Socket;
import message.MessageManager;
import resnponse.testResponse;

public class ClientTest1 {

	private static final int MAX = 1024;
	private static String serverName ="127.0.0.1";
	private static int port =8081;
	
	public static void main(String [] args) throws UnknownHostException, IOException {
		int[] sockets = new int[MAX];
		

	}
}
