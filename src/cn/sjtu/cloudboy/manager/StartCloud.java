package cn.sjtu.cloudboy.manager;

import java.util.Properties;
import cn.sjtu.cloudboy.analysis.AnalysisManager;
import cn.sjtu.cloudboy.config.AppContextListener;
import cn.sjtu.cloudboy.entity.Cloud;
import cn.sjtu.cloudboy.monitor.ResponseGenerator;

public class StartCloud implements Runnable {

	private volatile Thread blinker;
	private static StartCloud instance;
	private static final String XEN_SERVER_HOSTIPS = "XEN_SERVER_HOSTIPS";
	private static final String XEN_SERVER_HOSTNAMES = "XEN_SERVER_HOSTNAMES";
	private static final String XEN_SERVER_USERNAME = "XEN_SERVER_USERNAME";
	private static final String XEN_SERVER_PASSWORD = "XEN_SERVER_PASSWORD";
	private static final String XEN_SERVER_SSHPORT = "XEN_SERVER_SSHPORT";
	private static final String XEN_SERVER_NUMBER = "XEN_SERVER_NUMBER";
	private Properties properties;

	static {
		instance = new StartCloud();
	}

	public static StartCloud getInstance() {
		return instance;
	}

	public StartCloud() {
		
		blinker = new Thread(this, "StartCloud");
    	properties = AppContextListener.getProperties();
    	//System.out.println("properties = " + properties);
	}

	@Override
	public void run() {
		Thread thisThread = Thread.currentThread();
		if (this.blinker == thisThread) {
			String xenServerHostIPs = getParameter(XEN_SERVER_HOSTIPS);
			String xenServerHostNames = getParameter(XEN_SERVER_HOSTNAMES);
			String xenServerUserName = getParameter(XEN_SERVER_USERNAME);
			String xenServerPassword = getParameter(XEN_SERVER_PASSWORD);
			String xenServerSshPort = getParameter(XEN_SERVER_SSHPORT);
			String xenServerNumber = getParameter(XEN_SERVER_NUMBER);

			Cloud cloud = Cloud.getInstance();
			cloud.setupCloud(xenServerHostNames, xenServerHostIPs,
					xenServerUserName, xenServerPassword, xenServerSshPort,
					xenServerNumber);

			ResponseGenerator responseGenerator = ResponseGenerator
					.getInstance();
			responseGenerator.start();

			AnalysisManager analysisManager = AnalysisManager.getInstance();
			analysisManager.start();
		}
	}
	
	public void start() {
		System.out.println("StartCloud start");
		blinker.start();
	}

	public void stop() {
		System.out.println("StartCloud stop");
		Thread moribund = this.blinker;
		this.blinker = null;
		if (moribund != null) {
			moribund.interrupt();
		}
	}
	
	public String getParameter(String key) {
		if (this.properties == null)
			return null;
		return this.properties.getProperty(key);
	}
	
	public static void main(String[] args) {
		StartCloud sc = StartCloud.getInstance();
		sc.start();
	}

}
