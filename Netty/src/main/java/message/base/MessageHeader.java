package message.base;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;


public class MessageHeader{
	private int length = -1;
	private int command = -1;
	private int machineID = -1;
	
	public boolean decode(ByteBuf buf){
		
		this.setCommand(buf.readInt());
		this.setMachineID(buf.readInt());
		return true;
	}
	//堆缓冲
	public ByteBuf encode(){
		ByteBuf buf = Unpooled.buffer();
		buf.writeInt(this.length);
		buf.writeInt(this.command);
		buf.writeInt(this.machineID);
		return buf;
	}
	
	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getCommand() {
		return command;
	}

	public void setCommand(int command) {
		this.command = command;
	}

	public int getMachineID() {
		return machineID;
	}

	public void setMachineID(int machineID) {
		this.machineID = machineID;
	}
	public int getClassLength(){
		return 12;
	}
}
