package cn.sjtu.cloudboy.manager;



import cn.sjtu.cloudboy.entity.XenVM;
import cn.sjtu.cloudboy.util.JSchUtil;

public class StartApp implements Runnable {
	
	private volatile Thread blinker;
	private XenVM vm;
	private final String dbCmd = "cd /usr/glassfish3/javadb/bin; ./startNetworkServer '&'";
	private final String serverCmd = "cd /usr/glassfish3/bin; asadmin start-domain domain1";
	private final String appCmd = "cd /root/appTest/javapetstore-2.0-ea5; ant -f setup/setup.xml setup; ant run;";
	
	public StartApp(XenVM vm){
		this.vm = vm;
	}
	
	private void startDB(){
		JSchUtil.executeCommandNoResult(vm.getUserName(), vm.getPasswd(), vm.getHostIp(), vm.getSshPort(), dbCmd);
	}
	
	public void startApp(){	
		this.start();
		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.stop();
		JSchUtil.executeCommand(vm.getUserName(), vm.getPasswd(), vm.getHostIp(), vm.getSshPort(), serverCmd);
		System.out.println("serverCmd done " + vm.getHostIp());
		JSchUtil.executeCommand(vm.getUserName(), vm.getPasswd(), vm.getHostIp(), vm.getSshPort(), appCmd);
		System.out.println("appCmd done " + vm.getHostIp());
	}
		
	@Override
	public void run() {
		System.out.println("StartApp run " + vm.getHostIp());
		Thread thisThread = Thread.currentThread();
		if (this.blinker == thisThread) {
			JSchUtil.executeCommandNoResult(vm.getUserName(), vm.getPasswd(), vm.getHostIp(), vm.getSshPort(), dbCmd);
			try {
				//System.out.println("before dbCmd sleep");
				Thread.sleep(30000);
				//System.out.println("after dbCmd sleep");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("dbCmd done " + vm.getHostIp());
			
			JSchUtil.executeCommand(vm.getUserName(), vm.getPasswd(), vm.getHostIp(), vm.getSshPort(), serverCmd);
			System.out.println("serverCmd done " + vm.getHostIp());
			
			JSchUtil.executeCommand(vm.getUserName(), vm.getPasswd(), vm.getHostIp(), vm.getSshPort(), appCmd);
			System.out.println("appCmd done " + vm.getHostIp());
		}
	}
	
	public void start() {
		System.out.println("StartApp start " + vm.getHostIp());
		blinker = new Thread(this, "StartApp");
		blinker.start();
	}

	public void stop() {
		System.out.println("StartApp stop " + vm.getHostIp());
		Thread moribund = this.blinker;
		this.blinker = null;
		if (moribund != null) {
			moribund.interrupt();
		}
	}
	
	public void join() {
		System.out.println("StartApp join " + vm.getHostIp());
		try {
			this.blinker.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		XenVM vm11 = new XenVM("vm11", "172.16.1.11", 22, "root", "welcome");
		//XenVM vm22 = new XenVM("vm22", "172.16.1.22", 22, "root", "welcome");
		//XenVM vm31 = new XenVM("vm31", "172.16.1.31", 22, "root", "welcome");
		//XenVM vm31 = new XenVM("vm31", "172.16.1.31", 22, "root", "welcome");
		//XenVM vm21 = new XenVM("vm21", "172.16.1.21", 22, "root", "welcome");
		StartApp s11 = new StartApp(vm11);
		//StartApp s22 = new StartApp(vm22);
		//StartApp s31 = new StartApp(vm31);
		//StartApp s31 = new StartApp(vm31);
		//StartApp s21 = new StartApp(vm21);
		s11.start();
		//s22.start();
		//s31.start();
		s11.join();
		//s12.join();
		//s31.join();
		//s21.start();
		//s22.join();
	}
	
}
