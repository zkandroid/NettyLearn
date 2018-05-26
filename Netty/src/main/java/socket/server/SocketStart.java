package socket.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;


public class SocketStart {
	
	public void startServer(String host,int port) throws InterruptedException {
		/*EventLoopGroup实际上就是EventLoop的数组，EventLoop的职责是处理所有注册到本地
		 * 线程多路复用器Selector上的Channel
		 */
		EventLoopGroup bossGroup = new NioEventLoopGroup(1);//处理tcp连接请求
		EventLoopGroup workerGrop = new NioEventLoopGroup(1);//处理网络io
		//7，Selector轮询。由Reactor线程NioEventLoop负责调度和执行Selector轮询操作，选择准备就绪的Channel集合
		//8，当轮询到装备就绪的channel,就由由Reactor线程NioEventLoop执行ChannelPipeline相应的方法，最终调度并执行ChannelHandler
		//9,执行Netty系统的ChannelHandler和用户添加定制的ChannelHandler。ChannelPipeline根据网络事件类型。调度并执行ChannelHandler
		try {
			ServerBootstrap b = new ServerBootstrap();//1，创建Netty的服务端启动辅助类
			b.group(bossGroup, workerGrop)//2，设置并绑定Reactor线程池
			.channel(NioServerSocketChannel.class)//3，处理并绑定服务端Channel，用它来建立新accept的连接，用于构造serversocketchannel的工厂类 
			.childHandler(new ChannelInitializer<SocketChannel>() {//5,添加并设置childHandler，为accept channel的pipeline预添加的inboundhandler  
				

				@Override
				//当新连接accept的时候，这个方法会调用 
				protected void initChannel(SocketChannel ch) throws Exception {
					//4,链路创建的时候创建并初始化ChannelPipeline
					//ch.pipeline().addLast("socket-decoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, -4, 4));//http://www.voidcn.com/article/p-gzqzzsyz-bqy.html
					ch.pipeline().addLast( new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 4, 1, 0, 0));
					ch.pipeline().addLast(new MySocketChannel());
	     	
				}		
			}).option(ChannelOption.SO_BACKLOG, 1024) //指定了内核为此套接字接口排队的最大连接数，Socket参数，服务端接受连接的队列长度，如果队列已满，客户端连接将被拒绝。
	        .childOption(ChannelOption.SO_KEEPALIVE, true);
	    ChannelFuture f = b.bind(host,port).sync();//6， 绑定并启动监听端口
	    /*
	     * 绑定监听端口这里，会从bossGroup线程池中获取一个NioEventLoop用于监听和接收客户端连接的Reator线程
	     */
	    f.channel().closeFuture().sync();
			
		} finally {
			workerGrop.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}
		
	}

}
