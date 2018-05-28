package socket.server;



import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import message.MessageManager;
import message.base.IMessageBody;
import message.base.MessageHeader;
import request.IRequest;


public class MySocketChannel extends ChannelInboundHandlerAdapter {

	private MessageManager messageManager =new MessageManager();
	@Override
    public void channelRead(ChannelHandlerContext context, Object msg) throws IllegalArgumentException, IllegalAccessException, JsonParseException, JsonMappingException, IOException {

		ByteBuf buf = null;
		System.out.println("----------");
			buf = (ByteBuf)msg;
			System.out.println("buf ref count: " + buf.refCnt());
			//System.out.println("get connection: " + msg.channel().remoteAddress());
			MessageHeader header =new MessageHeader();
			header.decode(buf);
			//MessageCenter mc = MyFactory.context.getBean("MessageCenter", MessageCenter.class);
			System.out.println("get command: " + header.getCommand() + " from matchine: " + header.getMachineID());	
			IMessageBody body = messageManager.chooseMessageBody(header);
			
			body.decode(buf);
			IRequest request = messageManager.translateToRequest(header, body);
			

		
        
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
    	//ctx.writeAndFlush(Unpooled.copiedBuffer("Server send", CharsetUtil.UTF_8));
        //ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext context, Throwable couse) {
        couse.printStackTrace();
        context.close();
    }
    
  //向某一已连接的主机发送消息
  	public void sendResponse(int cmd, int machineId, Object rsp,ChannelHandlerContext ctx) throws IllegalArgumentException, IllegalAccessException, JsonProcessingException{
  		MessageManager messageManager =new MessageManager();
  		ByteBuf buf = messageManager.generateCommandBuf(cmd, machineId, rsp);
  		ctx.writeAndFlush(buf);
  	}


	

}
