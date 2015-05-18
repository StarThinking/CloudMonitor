package cn.sjtu.cloudboy.entity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import cn.sjtu.cloudboy.config.AppContextListener;

public class Cloud {

	private final List<XenServer> serverList = new ArrayList<XenServer>();
	private final String hostname = "CloudBoy";
	private static final Cloud instance;
	private Properties properties;
	
	private static final String XEN_VM_NUMBER = "XEN_VM_NUMBER";
	private static final String XEN_VM_USERNAME = "XEN_VM_USERNAME";
	private static final String XEN_VM_PASSWORD = "XEN_VM_PASSWORD";
	private static final String XEN_VM_SSHPORT = "XEN_VM_SSHPORT";
	private static final String XEN_SERVER_HOSTIPS = "XEN_SERVER_HOSTIPS";
	private static final String XEN_SERVER_1_VMHOSTIPS = "XEN_SERVER_1_VMHOSTIPS";
	private static final String XEN_SERVER_1_VMHOSTNAMES = "XEN_SERVER_1_VMHOSTNAMES";
	private static final String XEN_SERVER_1_VM_NUMBER = "XEN_SERVER_1_VM_NUMBER";
	private static final String XEN_SERVER_2_VMHOSTIPS = "XEN_SERVER_2_VMHOSTIPS";
	private static final String XEN_SERVER_2_VMHOSTNAMES = "XEN_SERVER_2_VMHOSTNAMES";
	private static final String XEN_SERVER_2_VM_NUMBER = "XEN_SERVER_2_VM_NUMBER";
	private static final String XEN_SERVER_1_ACTIVE_VM_NUMBER = "XEN_SERVER_1_ACTIVE_VM_NUMBER";
	
    static{
    	instance = new Cloud();
    }

	public static Cloud getInstance() {
		return instance;
	}
	
    public Cloud(){
    	properties = AppContextListener.getVmProperties();
    }
	
	public void setupCloud(String xenServerHostNames, String xenServerHostIPs, String xenServerUserName, 
			String xenServerPassword, String xenServerSshPort, String xenServerNumber){		
		System.out.println("setupCloud");
		List<String> serverHostIpList = Arrays.asList(xenServerHostIPs.split("&"));
		List<String> serverHostNameList = Arrays.asList(xenServerHostNames.split("&"));	
		for(int i=0; i<Integer.valueOf(xenServerNumber); i++){		
			XenServer server = new XenServer(serverHostNameList.get(i), serverHostIpList.get(i), Integer.valueOf(xenServerSshPort), 
					xenServerUserName, xenServerPassword);
			this.serverList.add(server);
		}		

		for(int i=0; i<Integer.valueOf(xenServerNumber); i++){	
			int index = Integer.parseInt(serverHostIpList.get(i).substring(serverHostIpList.get(i).length()-1));
			String vmHostIps = getParameter("XEN_SERVER_" + index + "_VMHOSTIPS");
			String vmHostNames = getParameter("XEN_SERVER_" +  index +"_VMHOSTNAMES");
			String xenVmNumber = getParameter(XEN_VM_NUMBER);
			String xenServerActiveVmNumber = getParameter("XEN_SERVER_" + index +"_ACTIVE_VM_NUMBER");
			String xenVmSshPort = getParameter(XEN_VM_SSHPORT);
			String xenVmUserName = getParameter(XEN_VM_USERNAME);
			String xenVmPassword = getParameter(XEN_VM_PASSWORD);
			this.serverList.get(i).setupXenServer(vmHostNames, vmHostIps, xenVmUserName, xenVmPassword, xenVmSshPort, xenVmNumber, xenServerActiveVmNumber);
		}		
		
		for(int i=0; i<Integer.valueOf(xenServerNumber); i++){	
			XenServer server = this.serverList.get(i);
			server.startServer();
		}
		
		for(int i=0; i<Integer.valueOf(xenServerNumber); i++){	
			System.out.println("Join Server");
			XenServer server = this.serverList.get(i);
			server.joinServer();
		}
	}
	
	public List<XenServer> getServerlist() {
		return serverList;
	}

	public XenServer getServerByIp(String ip){
		for(int i=0; i<serverList.size(); i++){
			if(serverList.get(i).getHostIp().equals(ip)){
				return serverList.get(i);
			}
		}
		return null;
	}
	
	public String getHostname() {
		return hostname;
	}

	public String getParameter(String key) {
		if (this.properties == null)
			return null;
		return this.properties.getProperty(key);
	}

}
