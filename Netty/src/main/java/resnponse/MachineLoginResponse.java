package resnponse;

import java.awt.color.CMMException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.sql.SQLException;


import com.fasterxml.jackson.core.JsonProcessingException;


import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import request.IRequest;


public class MachineLoginResponse extends IRequest {
	private String privateIp = "";
	
	private int privatePort = -1;
	private ChannelHandlerContext ctx = null;

	public String getPrivateIp() {
		return privateIp;
	}
	public void setPrivateIp(String privateIp) {
		this.privateIp = privateIp;
	}
	public int getPrivatePort() {
		return privatePort;
	}
	public void setPrivatePort(int privatePort) {
		this.privatePort = privatePort;
	}
	
	public ChannelHandlerContext getCtx() {
		return ctx;
	}
	public void setCtx(ChannelHandlerContext ctx) {
		this.ctx = ctx;
	}
}
