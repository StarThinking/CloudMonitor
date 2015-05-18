package cn.sjtu.cloudboy.entity;

public class Host {
	
	protected final String hostName;
	protected final String hostIp;
	protected final int sshPort;
	protected final String userName;
	protected final String passwd;
	
	public Host(String hostName, String hostIp, int sshPort, String userName, String passwd) {
		this.hostName = hostName;
		this.hostIp = hostIp;
		this.sshPort = sshPort;
		this.userName = userName;
		this.passwd = passwd;
	}

	public String getHostName() {
		return hostName;
	}

	public String getHostIp() {
		return hostIp;
	}

	public int getSshPort() {
		return sshPort;
	}

	public String  getUserName() {
		return userName;
	}

	public String getPasswd() {
		return passwd;
	}

}
