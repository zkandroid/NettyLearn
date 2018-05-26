package socket.client;

import javax.sound.sampled.Port;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import socket.server.MySocketChannel;

public class ClientStart {
	
	
	public void connectServer(String host,int port) throws InterruptedException {
		
		EventLoopGroup group = new NioEventLoopGroup();//默认是cpu内核数的2倍
		try {
			Bootstrap b = new Bootstrap();//1,创建客户端连接实例
			b.group(group)
			.channel(NioSocketChannel.class)
			.handler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast("socket-decoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, -4, 4));
					//ch.pipeline().addLast( new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 4, 1, 0, 0));
					 ch.pipeline().addLast(new MyClientChannel());
					
					
				}
			}).option(ChannelOption.SO_KEEPALIVE, true)
			.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000);//连接超时
			
			ChannelFuture f = b.connect(host, port).sync();
			f.channel().closeFuture().sync();
			
		} finally {
			group.shutdownGracefully();
		}
		
	}

}
