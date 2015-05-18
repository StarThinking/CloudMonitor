package cn.sjtu.cloudboy.monitor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentLinkedQueue;


import cn.sjtu.cloudboy.config.AppContextListener;
import cn.sjtu.cloudboy.entity.Cloud;
import cn.sjtu.cloudboy.entity.Response;
import cn.sjtu.cloudboy.entity.XenServer;
import cn.sjtu.cloudboy.entity.XenVM;
import cn.sjtu.cloudboy.manager.CloudManager;

public class ResponseStore implements Runnable {

	private static ResponseStore instance;

	static {
		instance = new ResponseStore();
	}

	public static ResponseStore getInstance() {
		return instance;
	}
	
	private volatile Thread blinker;
	private static final String XEN_VM_RESPONSE_ROOTPATH = "XEN_VM_RESPONSE_ROOTPATH";
	private static Properties properties;
	private static String rootPath;
	private static Map<String, ConcurrentLinkedQueue<Response>> responseMap;
	private static List<String> ipList;
	private Queue<Double> responseQueue = new LinkedList<Double>();
	private Double lastResponse = 0.0;

	public ResponseStore() {
		properties = AppContextListener.getVmProperties();
		responseMap = new HashMap<String, ConcurrentLinkedQueue<Response>>();
		ipList = new ArrayList<String>();
		blinker = new Thread(this, "ResponseStore");
		rootPath = getParameter(XEN_VM_RESPONSE_ROOTPATH);
		System.out.println("rootPath = " + rootPath);
	}

	public void recordResponseHistory(Map<String, Double> map){
		if(map != null){ // normal situation
			Iterator<String> it = map.keySet().iterator();
			Double sum = 0.0;
			Double avgResponse = 0.0;
			while(it.hasNext()){
				String key = it.next();
				Double tmp = map.get(key);
				sum += tmp;	
			}
			if(map.size() != 0) 
				avgResponse = sum/map.size();
			if(responseQueue.size() < 10){
				responseQueue.add(avgResponse);
				lastResponse = avgResponse;
			}
			else{
				responseQueue.poll();
			}
		}
		else{  // not normal situation, double item
			if(responseQueue.size() >= 10){
				responseQueue.poll();
			}
			responseQueue.add(lastResponse);
		}
		System.out.println("responseQueue = " + responseQueue);
	}
	
	public Queue<Double> getResponseHistory(){
		return responseQueue;
	}
	
	public Map<String, Double> getResponseMap(){
		//System.out.println("before get : responseMap queue size = " + responseMap.get(0).size());
		System.out.println("getResponseMap");
		Map<String, ConcurrentLinkedQueue<Response>> map = new HashMap<String, ConcurrentLinkedQueue<Response>>();
		Map<String, Double> mapReturn = new HashMap<String, Double>();
		Iterator<String> it = responseMap.keySet().iterator();
		while(it.hasNext()){
			String ip = it.next();
			ConcurrentLinkedQueue<Response> queue = new ConcurrentLinkedQueue<Response>();
			map.put(ip, queue);
			ConcurrentLinkedQueue<Response> responseQueue = responseMap.get(ip);
			while(!responseQueue.isEmpty())
				queue.add(responseQueue.poll());
		}
		int flag = 0;
		Iterator<String> itt = map.keySet().iterator();
		while(itt.hasNext()){
			String ip = itt.next();
			ConcurrentLinkedQueue<Response> responseQueue = map.get(ip);
			if(responseQueue.size() < 4){
				System.out.println("queue size = " + responseQueue.size() + " Error input , ignore");
				flag = 1;
			}
			Double sumRes = 0.0;
			int index = 0;
			while(!responseQueue.isEmpty()){
				Response r = responseQueue.poll();
				System.out.println("response = " + r);
				Double res = Double.valueOf(r.getRespTime());
				if(res/1000 >= 1 || res < 10){
				    flag = 1;
				}
				sumRes += res;
			    index++;
			}
			if(index != 0 ){
				Double average = sumRes/index;
				System.out.println("ip : " + ip + " average resTime : " + average);
				mapReturn.put(ip, average);
			}
		}
		if(flag == 1){
			mapReturn = null;
		}
		recordResponseHistory(mapReturn);
		return mapReturn;
	}
	
	private Response transformToResponse(String line){
		List<String> fieldList = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(line);
		while(st.hasMoreElements()){
			fieldList.add(st.nextToken());
		}
		Response response = new Response(fieldList);
		return response;
	}
	
	public void storeRespocseFromFile(String ip) {
		//System.out.println("storeRespocseFromFile");
		String configFilePath = rootPath + ip + ".tsv";
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(configFilePath));
			String line;
			int lineNum = 0;
			while ((line = br.readLine()) != null) {
				if(lineNum == 1){
					Response response = transformToResponse(line);
					//System.out.println("ip : " + ip + " " + response);
					ConcurrentLinkedQueue<Response> responseQueue = responseMap.get(ip);
					responseQueue.add(response);
				}
				lineNum++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		System.out.println("ResponseStore run");
		Thread thisThread = Thread.currentThread();
		while (this.blinker == thisThread) {
			try {
				for (int index = 0; index < ipList.size(); index++) {
					storeRespocseFromFile(ipList.get(index));
				}
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void start() {
		System.out.println("ResponseStore start");
	//	blinker.setDaemon(true);
		blinker.start();
	}

	public void stop() {
		System.out.println("ResponseStore stop");
		Thread moribund = this.blinker;
		this.blinker = null;
		if (moribund != null) {
			moribund.interrupt();
		}
	}

	public void addIp(String ip) {
		System.out.println("addIp!!!!!!!!!!!!!!!!!!");
		ConcurrentLinkedQueue<Response> queue = new ConcurrentLinkedQueue<Response>();
		ResponseStore.responseMap.put(ip, queue);
		System.out.println("addIp : responceMap size = " + responseMap.size());
		ResponseStore.ipList.add(ip);
	}
	
	public void removeIp(String ip) {
		System.out.println("removeIp!!!!!!!!!!!!!!!!!!");
		ResponseStore.responseMap.remove(ip);
		System.out.println("removeIp : responceMap size = " + responseMap.size());
		ResponseStore.ipList.remove(ip);
	}
	
	public void addIps(Cloud cloud) {
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
				ipList.add(ip);
			}
		}
	}
	
	public String getParameter(String key) {
		if (this.properties == null)
			return null;
		return this.properties.getProperty(key);
	}
	
	public static void main(String[] args) {

		ResponseStore r = ResponseStore.getInstance();
		r.addIp("172.16.1.11");
		//r.start();
	}

}
