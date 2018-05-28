package socket.client;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.net.UnknownHostException;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import message.MessageManager;
import resnponse.testResponse;

public class StartClient extends Thread{
	private ClientStart clientStart = new ClientStart();
	private int num;
	//private StartClient sClient =new StartClient(0);
	
	public StartClient(int num) {
		this.num =num;
	}


	public void run() {
		for(int x =0 ;x<10;x++) {
			try {
				clientStart.connectServer("127.0.0.1", 8081, x);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public  boolean clientTest(int num) throws UnknownHostException, IOException {
		Socket client = new Socket("127.0.0.1",8081);
		
		return true;
	}
	 //向某一已连接的主机发送消息
  	public void sendResponse(int cmd, int machineId, Object rsp,ChannelHandlerContext ctx) throws IllegalArgumentException, IllegalAccessException, JsonProcessingException{
  		MessageManager messageManager =new MessageManager();
  		ByteBuf buf = messageManager.generateCommandBuf(cmd, machineId, rsp);
  		ctx.writeAndFlush(buf);
  	}
}
