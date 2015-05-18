package cn.sjtu.cloudboy.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import cn.sjtu.cloudboy.entity.Cloud;
import cn.sjtu.cloudboy.entity.XenServer;
import cn.sjtu.cloudboy.entity.XenVM;

public class CloudManager {

	private static CloudManager instance;

	static {
		instance = new CloudManager();
	}

	public static CloudManager getInstance() {
		return instance;
	}
	

	

	public Map<String, List<String>> getCurrentLayout(){
		System.out.println("getCurrentLayout");
		Map<String, List<String>> vmMap = new HashMap<String, List<String>>();
		Cloud cloud = Cloud.getInstance();
		List<XenServer> serverList= cloud.getServerlist();
		for(int i=0; i<serverList.size(); i++){
			List<XenVM> activeVMs = serverList.get(i).getActiveVMs();
			List<String> vmList = new ArrayList<String>();
			for(int j=0; j<activeVMs.size(); j++)
				vmList.add(activeVMs.get(j).getHostName());
			vmMap.put(serverList.get(i).getHostName(), vmList);
		}
		return vmMap;
	}
	
	public int getCurrentActiveVMs(){
		int sum = 0;
		Iterator<String> it = getCurrentLayout().keySet().iterator();
		while(it.hasNext()){
			String key = it.next();
			List<String> vms = getCurrentLayout().get(key);
			sum += vms.size();
		}
		return sum;
	}
	

}
