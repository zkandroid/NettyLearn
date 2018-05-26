package socket.server;



import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

public class MySocketChannel extends ChannelInboundHandlerAdapter {

	
	@Override
    public void channelRead(ChannelHandlerContext context, Object msg) {
		
        
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
    	ctx.writeAndFlush(Unpooled.copiedBuffer("Server send", CharsetUtil.UTF_8));
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext context, Throwable couse) {
        couse.printStackTrace();
        context.close();
    }


	

}
