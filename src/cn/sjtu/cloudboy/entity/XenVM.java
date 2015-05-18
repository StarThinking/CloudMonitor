package cn.sjtu.cloudboy.entity;

public class XenVM extends Host{

	private boolean isActice;
	
	public XenVM(String hostName, String hostIp, int sshPort, String userName, String passwd) {
		super(hostName, hostIp, sshPort, userName, passwd);
	}
	
	public boolean isActice() {
		return isActice;
	}

	public void setActice(boolean isActice) {
		this.isActice = isActice;
	}
	
	public String toString(){
		return "hostName = " + hostName + " hostIp = " + hostIp;
	}

}
