package message;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.netty.buffer.ByteBuf;
import message.base.IMessageBody;
import message.base.MessageBody;
import message.base.MessageHeader;
import request.IRequest;
import request.testRequest;
import resnponse.testResponse;

public class MessageManager {
	
	public ByteBuf generateCommandBuf(int cmd, int machineID, Object bodyMessage) throws IllegalArgumentException, IllegalAccessException, JsonProcessingException{
		MessageHeader header = new MessageHeader();
        header.setCommand(cmd);
		header.setMachineID(machineID);
		MessageBody body = new MessageBody();
		body.setJsonStringByObject(bodyMessage);
		System.out.println("generateCommandBuf-------"+body.getJsonString());
		ByteBuf buf1 = body.encode();
		header.setLength(header.getClassLength() + buf1.readableBytes());
		ByteBuf buf2 = header.encode();
		buf2.writeBytes(buf1);
		return buf2;
	}
	
	public IMessageBody chooseMessageBody(MessageHeader header){
		return new MessageBody();
	}
	
	public IRequest translateToRequest(MessageHeader header, IMessageBody body) throws JsonParseException, JsonMappingException, IOException {
			IRequest request = null;
			ObjectMapper mapper = new ObjectMapper();
		
			request = mapper.readValue(((MessageBody)body).getJsonString(), testResponse.class);
		
		return request;
	}
	//*/
	/*
	public ChannelFuture sendCommand(ChannelHandlerContext ctx, MessageHeader header, IMessageBody body) throws IllegalArgumentException, IllegalAccessException{
		ChannelFuture cf = null;
		ByteBuf buf1 = body.encode();
		header.setLength(header.getClassLength() + buf1.readableBytes());
		ByteBuf buf2 = header.encode();
		buf2.writeBytes(buf1);
		while (true) {
			if(ctx.channel().isWritable()){
				cf = ctx.writeAndFlush(buf2);
				break;
			}
			
		}
		return cf;
	}*/
	/*
	public ChannelFuture sendCommandSimple(ChannelHandlerContext ctx, int cmd, int machineID, ICommand body) throws IllegalArgumentException, IllegalAccessException{
		ChannelFuture cf = null;
		CommandHeader header = new CommandHeader();
        header.setCommand(cmd);
		header.setMachineID(machineID);
		ByteBuf buf1 = body.encode();
		header.setLength(header.getClassLength() + buf1.readableBytes());
		ByteBuf buf2 = header.encode();
		buf2.writeBytes(buf1);
		while (true) {
			if(ctx.channel().isWritable()){
				cf = ctx.writeAndFlush(buf2);
				break;
			}
		}
		return cf;
	}
	*/
	
	
}
