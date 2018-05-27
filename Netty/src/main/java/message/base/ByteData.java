package message.base;

public class ByteData {
	private int length = 0;
	private byte[] buf = null;
	
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public byte[] getBuf() {
		return buf;
	}
	public void setBuf(byte[] buf) {
		
		this.buf = buf;
	}
	
	public void initBytes(int length){
		buf = new byte[length];
	}
}