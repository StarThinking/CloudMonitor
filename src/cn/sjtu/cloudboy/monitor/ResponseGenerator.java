package cn.sjtu.cloudboy.monitor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import cn.sjtu.cloudboy.entity.Cloud;
import cn.sjtu.cloudboy.entity.ReqDuration;
import cn.sjtu.cloudboy.entity.XenServer;
import cn.sjtu.cloudboy.entity.XenVM;
import cn.sjtu.cloudboy.manager.CloudManager;


public class ResponseGenerator implements Runnable{

	private static ResponseGenerator instance;
	private List<String> ipList;
	private volatile Thread blinker;
	private List<ReqDuration> perfPlan;
	private int currentTimes = 0;
	private Queue<Integer> loadQueue = new LinkedList<Integer>();
	private Integer lastLoad = 0;
	
	static {
		instance = new ResponseGenerator();	
	}

	public static ResponseGenerator getInstance() {
		return instance;
	}
	
	public ResponseGenerator(){
		ipList = new ArrayList<String>();
		blinker = new Thread(this, "ResponseGenerator");
		perfPlan = new ArrayList<ReqDuration>();
		//perfPlan.add(new ReqDuration("2000", 30));
		perfPlan.add(new ReqDuration("180", 60));
		perfPlan.add(new ReqDuration("200", 60));
		perfPlan.add(new ReqDuration("240", 60));
		perfPlan.add(new ReqDuration("280", 60));
		perfPlan.add(new ReqDuration("320", 60));
		perfPlan.add(new ReqDuration("360", 60));
		perfPlan.add(new ReqDuration("220", 150));
		perfPlan.add(new ReqDuration("220", 150));
		perfPlan.add(new ReqDuration("240", 30));
		perfPlan.add(new ReqDuration("200", 30));
		perfPlan.add(new ReqDuration("160", 30));
		perfPlan.add(new ReqDuration("120", 30));
		perfPlan.add(new ReqDuration("100", 30));

		//perfPlan.add(new ReqDuration("500", 20));
		//perfPlan.add(new ReqDuration("600", 20));
	}

	public void addIps(Cloud cloud) {
		ResponseStore responseStore = ResponseStore.getInstance();
		List<XenServer> serverList = cloud.getServerlist();
		Iterator<XenServer> it = serverList.iterator();
		while(it.hasNext()){
			XenServer server = it.next();
			List<XenVM> activeVMs = server.getActiveVMs();
			Iterator<XenVM> it_vm = activeVMs.iterator();
			while(it_vm.hasNext()){
				XenVM vm = it_vm.next();
				String ip = vm.getHostIp();
				System.out.println("Add active VM ip = " + ip);
				responseStore.addIp(ip);
				ipList.add(ip);
			}
		}
	}
	
	public void addIp(String ip) {
		ResponseStore responseStore = ResponseStore.getInstance();
		System.out.println("Add active VM ip = " + ip);
		responseStore.addIp(ip);
		ipList.add(ip);
	}

	public void removeIp(String ip) {
		ResponseStore responseStore = ResponseStore.getInstance();
		System.out.println("Remove active VM ip = " + ip);
		responseStore.removeIp(ip);
		ipList.remove(ip);
	}
	
	@Override
	public void run() {
		Thread thisThread = Thread.currentThread();
		boolean isFirst = true;
		ResponseStore responseStore = ResponseStore.getInstance();
	
		while (this.blinker == thisThread) {
			int sleepTime = 5000;
			try {
				for(int index=0; index<ipList.size(); index++){
					Httperf hp = new Httperf(this.perfPlan, this.ipList.size(), this.currentTimes, ipList.get(index));
					hp.start();
				}		
				if(isFirst){
					isFirst = false;
					//System.out.println("wait app for 5s");
				    Thread.sleep(5000);
					responseStore.start();
				}
				currentTimes++;
				//sleepTime = sleepTime - (ipList.size()-1)*100;
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();		
			} 
		}
	}

	public void start() {
		System.out.println("ResponseGenerator start");
		blinker.start();
	}

	public void stop() {
		System.out.println("ResponseGenerator stop");
		Thread moribund = this.blinker;
		this.blinker = null;
		if (moribund != null) {
			moribund.interrupt();
			ResponseStore.getInstance().stop();	
		}
	}
	
	public List<String> getIpList() {
		return ipList;
	}

	/*public static void main(String[] args) {
		doHttperf("172.16.1.15");
	}*/
}
