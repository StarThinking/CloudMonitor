package cn.sjtu.cloudboy.manager;


import cn.sjtu.cloudboy.analysis.AnalysisManager;
import cn.sjtu.cloudboy.entity.XenServer;
import cn.sjtu.cloudboy.entity.XenVM;
import cn.sjtu.cloudboy.monitor.ResponseGenerator;
import cn.sjtu.cloudboy.util.JSchUtil;

public class StopVM implements Runnable{
	private volatile Thread blinker;
	private XenServer server;
	private XenVM vm;
	
	public StopVM(XenServer server, XenVM vm){
		this.server = server;
		this.vm = vm;
	}
	
	public void stopVM(){
		String command = createCommand(vm);
		System.out.println("command = " + command);
		JSchUtil.executeCommand(server.getUserName(), server.getPasswd(), server.getHostIp(), server.getSshPort(), command);
		ResponseGenerator.getInstance().removeIp(vm.getHostIp());
		vm.setActice(false);
		server.getUnActiceVMs().add(vm);
		//AnalysisManager.getInstance().setAdding(false);
		System.out.println("stopVM successfully " + vm.getHostIp());
	}
	
	public String createCommand(XenVM xm){
		String command;
		command = "xm shutdown " + xm.getHostName();
		return command;
	}

	@Override
	public void run() {
		Thread thisThread = Thread.currentThread();
		if (this.blinker == thisThread) {
			try {
				Thread.sleep(30000);
				stopVM();
			
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
		}
	}
	
	public void start() {
		System.out.println("StopVM start " + vm.getHostIp());
		blinker = new Thread(this, "StartVM");
		blinker.start();
	}

	public void stop() {
		System.out.println("StopVM stop");
		Thread moribund = this.blinker;
		this.blinker = null;
		if (moribund != null) {
			moribund.interrupt();
		}
	}
	
	public void join() {
		System.out.println("StopVM join " + vm.getHostIp());
		try {
			this.blinker.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
