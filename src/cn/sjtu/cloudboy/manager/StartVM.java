package cn.sjtu.cloudboy.manager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


import cn.sjtu.cloudboy.analysis.AnalysisManager;
import cn.sjtu.cloudboy.entity.XenServer;
import cn.sjtu.cloudboy.entity.XenVM;
import cn.sjtu.cloudboy.monitor.ResponseGenerator;
import cn.sjtu.cloudboy.util.JSchUtil;

public class StartVM implements Runnable{

	private volatile Thread blinker;
	private XenServer server;
	private XenVM vm;
	private final String dbCmd = "cd /usr/glassfish3/javadb/bin; ./startNetworkServer '&'";
	private final String serverCmd = "cd /usr/glassfish3/bin; asadmin start-domain domain1";
	private final String appCmd = "cd /root/appTest/javapetstore-2.0-ea5; ant -f setup/setup.xml setup; ant run;";
	
	public StartVM(XenServer server, XenVM vm){
		this.server = server;
		this.vm = vm;
	}
	
	private void startVM(){
		String command = createCommand(vm);
		System.out.println("command = " + command);
		JSchUtil.executeCommand(server.getUserName(), server.getPasswd(), server.getHostIp(), server.getSshPort(), command);
		//System.out.println("command done " + server.getHostIp());
		try {
			Thread.sleep(140000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		StartApp sa = new StartApp(vm);
		sa.start();
		sa.join();
		for(int i=0; i<10; i++){
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			doHttperf(vm.getHostIp());
		}

	   /* System.out.println("wait app for 10s");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		ResponseGenerator.getInstance().addIp(vm.getHostIp());
		vm.setActice(true);
		server.getActiveVMs().add(vm);
	//	AnalysisManager.getInstance().setAdding(false);
		System.out.println("startVM successfully " + vm.getHostIp());
	}

	private String createCommand(XenVM xm){
		System.out.println("createCommand");
		String command;
		command = "xm create /OVS/test/" + xm.getHostName() + "/vm.cfg";
		return command;
	}
	
	@Override
	public void run() {
		Thread thisThread = Thread.currentThread();
		if (this.blinker == thisThread) {
			try {
			Thread.sleep(50000);
				startVM();			
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
		}
	}
	
	public void start() {
		System.out.println("StartVM start " + vm.getHostIp());
		blinker = new Thread(this, "StartVM");
		blinker.start();
	}

	public void stop() {
		System.out.println("StartVM stop");
		Thread moribund = this.blinker;
		this.blinker = null;
		if (moribund != null) {
			moribund.interrupt();
		}
	}
	
	public void join() {
		System.out.println("StartVM join " + vm.getHostIp());
		try {
			this.blinker.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void doHttperf(String ip){
		//String req = calReqRate();
		//System.out.println("reqRate = " + req);
		String req = "100";
		String cmd = "perl "+"/home/msx/test/scripts/genReq " + ip +" " +req;
		System.out.println(cmd);
		StringBuffer resultStringBuffer = new StringBuffer();

		String lineToRead = "";
		int exitValue = 0;
		try {
			Process proc = Runtime.getRuntime().exec(cmd);
			InputStream inputStream = proc.getInputStream();
			BufferedReader bufferedRreader = new BufferedReader(new InputStreamReader(inputStream));

			// save first line
			if ((lineToRead = bufferedRreader.readLine()) != null) {
				resultStringBuffer.append(lineToRead);
			}

			// save next lines
			while ((lineToRead = bufferedRreader.readLine()) != null) {
				resultStringBuffer.append("\r\n");
				resultStringBuffer.append(lineToRead);
			}

			// Always reading STDOUT first, then STDERR, exitValue last
			proc.waitFor(); // wait for reading STDOUT and STDERR over
			exitValue = proc.exitValue();
		} catch (IOException e) {
			e.printStackTrace();
			resultStringBuffer = new StringBuffer("");
			exitValue = 2;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public XenVM getVm() {
		return vm;
	}

	public void setVm(XenVM vm) {
		this.vm = vm;
	}
	
}
