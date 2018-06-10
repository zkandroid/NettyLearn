package NettySourceCode;

import java.nio.ByteBuffer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * netty是一个nio框架，其nio的实现主要是靠bytabuf缓冲区，channel通道，selector选择器
 * bytebuf缓冲区主要解决了io的非阻塞。通过这个缓冲区可以将收到或者发送的数据先缓存起来，就不需因为没有缓冲区一直要等待处理完成一个消息tcp才能处理下一条消息，导致窗口变小。
 * @author zk
 *
 */
public class ByteBufLearn {
	public static void main(String [] args) {
		String testBuffer ="hello";
		//bytebuffertest(testBuffer);
		byteBufTest(testBuffer);
	}
	
	
	
	
	/**
	 * buffer的调用是相当的麻烦的，每次调用之前还有初始化容量，读写之后还需要进行flip操作使其position回到0，读的时候还需要调用remaining来回去buffer中数据的实际长度。
	 * @param rsp
	 */
	public static void bytebuffertest(String rsp) {
		ByteBuffer buffer = ByteBuffer.allocate(10);//首先应该初始化buffer的容量，它不能动态扩容和收缩，所以如果传入的字节大于它，则会发生错误，小于的化会造成内存的浪费
		buffer.put(rsp.getBytes());
		buffer.flip();//buffer只有一个位置指针用于处理读写操作，所以每次写（读）时斗鱼进行flip操作，否则它读（写）的内容将会是position到capacity之间的内容,如果注释掉这一行，则下面输出的就是
		//6-10的内容，然而这里面时空的，所以输出空字符串
		byte [] rspArr = new byte[buffer.remaining()];//Returns the number of elements between the current position and the limit
		
		//byte [] rspArr = new byte[buffer.limit()];
		buffer.get(rspArr);
		String decodeRsp = new String(rspArr);
		System.out.println(decodeRsp+"1");
		
	}
	
	/**
	 * netty的buf是对jdk的一个改善，它会自动扩容，所以不会担心初始化容量问题，如果剩余的空间不足，它会创建一个新的buffer，然后将之前的复制进去（相当于jdk的map），再将老的释放掉
	 * 而且也不用调用filp更新position的位置，因为它有两个记录下标的字段，一个writerIndex用于记录写，一个readerIndex用于记录读的位置
	 * @param rsp
	 */
	private static void byteBufTest(String rsp) {
		
		ByteBuf buf = Unpooled.copiedBuffer(rsp.getBytes());
		int writeIndex = buf.capacity();
		System.out.println(writeIndex);
		String value ="write";
		byte [] date = value.getBytes();
		ByteBuf buf1 = Unpooled.buffer();
		buf1.writeBytes(date);
		byte reader = buf1.readByte();
		System.out.println(reader);
	
	}

}
