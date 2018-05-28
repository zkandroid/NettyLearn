package request;

public class MachineTimeVerificationRequest {
	private int result = -1;
	private long machineTime = -1;
	private long serverTime = -1;
	
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public long getMachineTime() {
		return machineTime;
	}
	public void setMachineTime(long machineTime) {
		this.machineTime = machineTime;
	}
	public long getServerTime() {
		return serverTime;
	}
	public void setServerTime(long serverTime) {
		this.serverTime = serverTime;
	}
}
