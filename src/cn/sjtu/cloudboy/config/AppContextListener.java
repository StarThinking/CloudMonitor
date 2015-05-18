package cn.sjtu.cloudboy.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import cn.sjtu.cloudboy.analysis.AnalysisManager;
import cn.sjtu.cloudboy.entity.Cloud;
import cn.sjtu.cloudboy.monitor.ResponseGenerator;
import cn.sjtu.cloudboy.monitor.ResponseStore;

public class AppContextListener implements ServletContextListener {
	
	private static final String CONFIG_FILE_PATH = "CONFIG_FILE_PATH";
	private static final String VM_CONFIG_FILE_PATH = "VM_CONFIG_FILE_PATH";
	/*private static final String XEN_SERVER_HOSTIPS = "XEN_SERVER_HOSTIPS";
	private static final String XEN_SERVER_HOSTNAMES = "XEN_SERVER_HOSTNAMES";
	private static final String XEN_SERVER_USERNAME = "XEN_SERVER_USERNAME";
	private static final String XEN_SERVER_PASSWORD = "XEN_SERVER_PASSWORD";
	private static final String XEN_SERVER_SSHPORT = "XEN_SERVER_SSHPORT";
	private static final String XEN_SERVER_NUMBER = "XEN_SERVER_NUMBER";*/
	
	private static Properties properties;
	private static Properties vmProperties;
	
	public static Properties getVmProperties() {
		return vmProperties;
	}

	public static Properties getProperties() {
		return properties;
	}
	
	public AppContextListener() {
		AppContextListener.properties = new Properties();
		AppContextListener.vmProperties = new Properties();
	}

	public static Properties load(File configFile) {
		Properties properties = new Properties();

		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(configFile));
			properties.load(in);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
		}

		return properties;
	}
	

	public String getParameter(String key) {
		if (AppContextListener.properties == null)
			return null;

		return AppContextListener.properties.getProperty(key);
	}

	public void setParameter(String key, String value) {
		if (AppContextListener.properties == null)
			return;

		AppContextListener.properties.setProperty(key, value);
	}
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("contextInitialized!!!!!");
		
		String configFilePath = sce.getServletContext().getInitParameter(
				CONFIG_FILE_PATH);
		configFilePath = sce.getServletContext().getRealPath(configFilePath);
		properties = load(new File(configFilePath));
		String vmConfigFilePath = getParameter(VM_CONFIG_FILE_PATH);
		vmConfigFilePath = sce.getServletContext().getRealPath(vmConfigFilePath);
		vmProperties = load(new File(vmConfigFilePath));
		
		/*String xenServerHostIPs = getParameter(XEN_SERVER_HOSTIPS);
		String xenServerHostNames = getParameter(XEN_SERVER_HOSTNAMES);
		String xenServerUserName = getParameter(XEN_SERVER_USERNAME);
		String xenServerPassword = getParameter(XEN_SERVER_PASSWORD);
		String xenServerSshPort = getParameter(XEN_SERVER_SSHPORT);		
		String xenServerNumber = getParameter(XEN_SERVER_NUMBER);
		Cloud cloud = Cloud.getInstance();
		cloud.setupCloud(xenServerHostNames, xenServerHostIPs, xenServerUserName, xenServerPassword, xenServerSshPort, xenServerNumber);
		
		ResponseGenerator responseGenerator = ResponseGenerator.getInstance();
		//responseGenerator.addIps(cloud);
		responseGenerator.start();

		AnalysisManager analysisManager = AnalysisManager.getInstance();
		analysisManager.start();*/
		

	}
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("contextDestroyed");
		
	}



}
