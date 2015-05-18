package cn.sjtu.cloudboy.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.sjtu.cloudboy.manager.StartApp;
import cn.sjtu.cloudboy.manager.StartVM;
import cn.sjtu.cloudboy.manager.StopVM;


public class XenServer extends Host {

	private List<XenVM> activeVMs;
	private List<XenVM> unActiceVMs;
	private List<StartVM> startVMList;
	private List<StartApp> startAppList;
	
	
	public XenServer(String hostName, String hostIp, int sshPort, String userName, String passwd) {
		super(hostName, hostIp, sshPort, userName, passwd);
		activeVMs = new ArrayList<XenVM>();
		unActiceVMs = new ArrayList<XenVM>();
		startVMList = new ArrayList<StartVM>();
		startAppList = new ArrayList<StartApp>();
	}

	public void shutdownServer(){
		System.out.println("shutdownServer");
		for(int i=0; i<activeVMs.size(); i++){
			StopVM stop = new StopVM(this, activeVMs.get(i));
			stop.start();
		}
	}
	
	public void setupXenServer(String vmHostNames, String vmHostIps, String xenVmUserName, String xenVmPassword, 
			String xenVmSshPort, String xenVmNumber, String xenServerActiveVmNumber){
		System.out.println("setupXenServer");
		for(int i=0; i<Integer.valueOf(xenVmNumber); i++){
			List<String> vmHostIpList = Arrays.asList(vmHostIps.split("&"));
			List<String> vmHostNameList = Arrays.asList(vmHostNames.split("&"));
			XenVM vm = new XenVM(vmHostNameList.get(i), vmHostIpList.get(i), Integer.valueOf(xenVmSshPort), xenVmUserName, xenVmPassword);
			vm.setActice(false);
			this.getUnActiceVMs().add(vm);
		}

		for(int j=0; j<Integer.valueOf(xenServerActiveVmNumber); j++){
			if(!unActiceVMs.isEmpty()){
				XenVM vm = unActiceVMs.remove(0);
				//vm.setActice(true);
				//activeVMs.add(vm);			
				StartVM startVM = new StartVM(this, vm);
				startVMList.add(startVM);
				StartApp startApp = new StartApp(vm);
				startAppList.add(startApp);
			}				
		}
	}
	
	public void startServer(){
		for(int index=0; index<startVMList.size(); index++){
			startVMList.get(index).start();
		}
	}
	
	public void joinServer(){	
		for(int index=0; index<startVMList.size(); index++){
			startVMList.get(index).join();
			//startVMList.get(index).getVm().setActice(true);
			//activeVMs.add(startVMList.get(index).getVm());	
		}
	}
	
	public void startServerApp(){
		for(int index=0; index<startVMList.size(); index++){
			startAppList.get(index).start();
		}
	}
	
	public void joinServerApp(){	
		for(int index=0; index<startVMList.size(); index++){
			startAppList.get(index).join();
		}
	}	
	
	public List<XenVM> getActiveVMs() {
		return activeVMs;
	}

	public void setActiveVMs(List<XenVM> activeVMs) {
		this.activeVMs = activeVMs;
	}

	public List<XenVM> getUnActiceVMs() {
		return unActiceVMs;
	}

	public void setUnActiceVMs(List<XenVM> unActiceVMs) {
		this.unActiceVMs = unActiceVMs;
	}

}
