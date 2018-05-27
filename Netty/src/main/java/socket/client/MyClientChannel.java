package socket.client;



import com.fasterxml.jackson.core.JsonProcessingException;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import message.MessageManager;
import resnponse.testResponse;


public class MyClientChannel extends SimpleChannelInboundHandler<ByteBuf> {
	private int num;

	public MyClientChannel(int num) {
		this.num=num;
	}

	@Override
    public void channelActive(ChannelHandlerContext context) throws IllegalArgumentException, IllegalAccessException, JsonProcessingException {
		testResponse rsp = new testResponse();
		rsp.setSize(5);
		rsp.setSend("hello");
		rsp.setMachineId(num);
		sendResponse(1, num, rsp, context);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
    	System.out.println("++++");
        System.out.println("Client received: " + byteBuf.toString(CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext context, Throwable cause) {
        cause.printStackTrace();
        context.close();
    }

    //向某一已连接的主机发送消息
  	public void sendResponse(int cmd, int machineId, Object rsp,ChannelHandlerContext ctx) throws IllegalArgumentException, IllegalAccessException, JsonProcessingException{
  		MessageManager messageManager =new MessageManager();
  		ByteBuf buf = messageManager.generateCommandBuf(cmd, machineId, rsp);
  		ctx.writeAndFlush(buf);
  	}


	
	

}
