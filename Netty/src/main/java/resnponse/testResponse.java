package resnponse;

import request.IRequest;

public class testResponse extends IRequest {
	
	private int size =0;
	private String send="";
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getSend() {
		return send;
	}
	public void setSend(String send) {
		this.send = send;
	}
	

}
