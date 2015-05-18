package cn.sjtu.cloudboy.manager;

import java.util.List;

import cn.sjtu.cloudboy.analysis.AnalysisManager;
import cn.sjtu.cloudboy.entity.Cloud;
import cn.sjtu.cloudboy.entity.XenServer;
import cn.sjtu.cloudboy.monitor.ResponseGenerator;

public class StopCloud implements Runnable {

	private volatile Thread blinker;
	private static StopCloud instance;

	static {
		instance = new StopCloud();
	}

	public static StopCloud getInstance() {
		return instance;
	}

	public StopCloud() {

		blinker = new Thread(this, "StopCloud");
		// properties = AppContextListener.getProperties();
		// System.out.println("properties = " + properties);
	}

	@Override
	public void run() {
		Thread thisThread = Thread.currentThread();
		if (this.blinker == thisThread) {
			List<XenServer> serverList = Cloud.getInstance().getServerlist();
			for(int i=0; i<serverList.size(); i++)
				serverList.get(i).shutdownServer();
			AnalysisManager analysisManager = AnalysisManager.getInstance();
			analysisManager.stop();
			ResponseGenerator responseGenerator = ResponseGenerator.getInstance();
			responseGenerator.stop();		
		}
	}

	public void start() {
		System.out.println("StopCloud start");
		blinker.start();
	}

	public void stop() {
		System.out.println("StopCloud stop");
		Thread moribund = this.blinker;
		this.blinker = null;
		if (moribund != null) {
			moribund.interrupt();
		}
	}
	
	public static void main(String[] args) {
		StopCloud sc = StopCloud.getInstance();
		sc.start();
	}

}
