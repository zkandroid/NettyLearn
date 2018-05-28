package request;

public class MachineLoginRequest {
	private int result = 0;
	private String publicIp = "";
	private int publicPort = 0;
	private String qrcodeUrl = "";
	
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	

	public String getPublicIp() {
		return publicIp;
	}
	public void setPublicIp(String publicIp) {
		this.publicIp = publicIp;
	}
	public int getPublicPort() {
		return publicPort;
	}
	public void setPublicPort(int publicPort) {
		this.publicPort = publicPort;
	}
	
	public String getQrcodeUrl() {
		return qrcodeUrl;
	}
	public void setQrcodeUrl(String qrcodeUrl) {
		this.qrcodeUrl = qrcodeUrl;
	}
}
