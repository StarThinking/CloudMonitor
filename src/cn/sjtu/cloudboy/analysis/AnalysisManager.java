package cn.sjtu.cloudboy.analysis;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import cn.sjtu.cloudboy.entity.Cloud;
import cn.sjtu.cloudboy.entity.XenServer;
import cn.sjtu.cloudboy.entity.XenVM;
import cn.sjtu.cloudboy.manager.CloudManager;
import cn.sjtu.cloudboy.manager.StartVM;
import cn.sjtu.cloudboy.manager.StopVM;
import cn.sjtu.cloudboy.monitor.ResponseStore;

public class AnalysisManager implements Runnable {

	private static AnalysisManager instance;

	static {
		instance = new AnalysisManager();
	}

	public static AnalysisManager getInstance() {
		return instance;
	}

	private int addCounter = 0;
	private int deleteCounter = 0;
	// private boolean adding = false;
	// private boolean deleting = false;
	private volatile Thread blinker;
	private Map<String, Double> responseMap;
	//private List<XenServer> serverList = Cloud.getInstance().getServerlist();
	private Queue<Double> costQueue = new LinkedList<Double>();
	private Queue<Double> profitQueue = new LinkedList<Double>();
	private Queue<Double> revenueQueue = new LinkedList<Double>();
	private Queue<Integer> vmNumQueue = new LinkedList<Integer>();
	private Queue<Double> reliabilityQueue = new LinkedList<Double>();
	private Queue<Double> vmProfitQueue = new LinkedList<Double>();
	private int vmNum = 1;
	private Double lastCost = 0.0;
	private Double lastProfit = 0.0;
	private Double lastRevenue = 0.0;
	private Double lastReliability = 0.0;
	private Integer lastVmNum = 0;

	private Analyze analyze = new Analyze();

	public AnalysisManager() {
		responseMap = new HashMap<String, Double>();
		blinker = new Thread(this, "AnalysisManager");
	}

	public Queue<Double> getCostHistory(){
		return costQueue;
	}
	
	public Queue<Double> getProfitHistory(){
		return profitQueue;
	}
	
	public Queue<Double> getRevenueHistory(){
		return revenueQueue;
	}
	
	public Queue<Integer> getVmNumHistory(){
		return vmNumQueue;
	}
	
	public Queue<Double> getReliabilityHistory(){
		return reliabilityQueue;
	}
	
	public Queue<Double> getVmProfitQueue(){
		return vmProfitQueue;
	}
	
	public void setVmProfitQueue(int vmNum_, Double profit){
		if(vmNum_ >= vmNum){
			vmProfitQueue.add(profit);
			vmNum_++;
		}
	}
	
	public void recordHistory(Double cost, Double profit, Double revenue, Double reliability, int flag){
		if(flag == 0){ // normal situation
			if(costQueue.size() < 20){
				costQueue.add(cost);
				profitQueue.add(profit);
				revenueQueue.add(revenue);
				reliabilityQueue.add(reliability);
				vmNumQueue.add(CloudManager.getInstance().getCurrentActiveVMs());
				lastCost = cost;
				lastProfit = profit;
				lastRevenue = revenue;
				lastReliability = reliability;
				lastVmNum = CloudManager.getInstance().getCurrentActiveVMs();
			}
			else{
				costQueue.poll();
				profitQueue.poll();
				revenueQueue.poll();
				vmNumQueue.poll();
				reliabilityQueue.poll();
			}
		}
		else{  // not normal situation, double item
			if(costQueue.size() >= 20){
				costQueue.poll();
				profitQueue.poll();
				revenueQueue.poll();
				vmNumQueue.poll();
				reliabilityQueue.poll();
			}
			costQueue.add(lastCost);
			profitQueue.add(lastProfit);
			revenueQueue.add(lastRevenue);
			vmNumQueue.add(lastVmNum);
			reliabilityQueue.add(lastReliability);
			reliabilityQueue.add(reliability);
		}
	}
	
	@Override
	public void run() {
		System.out.println("AnalysisManager run");
		Thread thisThread = Thread.currentThread();
		while (this.blinker == thisThread) {
			try {
				System.out.println("Do analysis every 30s");
				System.out.println("sleep 30s");
				Thread.sleep(30000);
				ResponseStore responseStore = ResponseStore.getInstance();
				responseMap = responseStore.getResponseMap();

				if(responseMap == null){
					recordHistory(0.0, 0.0, 0.0, 0.0, 1);
					continue;
				}
				System.out.println("start analyze");
				// do analyze sTime : 355.8
				analyze.doAnalyze(responseMap);
				System.out.println("end analyze");

				int result = analyze.getAnalysisResult().getCountChange();
				String pmIp = analyze.getAnalysisResult().getVmPositionChange();
				Double cost = analyze.getAnalysisResult().getComsumerCost();
				Double profit = analyze.getAnalysisResult().getComsumerProfit();
				Double revenue = analyze.getAnalysisResult()
						.getComsumerRevenue();
				Double reliability = analyze.getAnalysisResult().getReliability();
				recordHistory(cost, profit, revenue, reliability, 0);
				System.out.println("pmIp = " + pmIp);
				System.out.println("cost = " + cost);
				System.out.println("profit = " + profit);
				System.out.println("revenue = " + revenue);
				System.out.println("reliability = " + reliability);
				System.out.println("CloudManager.getInstance().getCurrentActiveVMs() = " + CloudManager.getInstance().getCurrentActiveVMs());
				XenServer server = Cloud.getInstance().getServerByIp(pmIp);
				setVmProfitQueue(CloudManager.getInstance().getCurrentActiveVMs(), profit);

				if (result == 0) {
					// do nothing here
					// addCounter = 0;
					// deleteCounter = 0;
				} else if (result == 1) {
					// add one manually now TODO
					/*
					 * addCounter++; if(adding == true){ System.out.println(
					 * "One vm is adding, clear add count = 0!!!!"); addCounter
					 * = 0; } else{
					 */
					/*
					 * if(addCounter >= 2){
					 * System.out.println("add one manually now TODO!!!!!!!");
					 * XenVM vm = server.getUnActiceVMs().remove(0); StartVM
					 * startVM = new StartVM(server, vm); startVM.start();
					 * System.out.println("add one manually DONE!!!!!!");
					 * addCounter = 0; adding = true; } else
					 * System.out.println("do nothing here!!!!!!!");
					 */
					System.out.println("add one manually now TODO!!!!!!!");
					XenVM vm = server.getUnActiceVMs().remove(0);
					StartVM startVM = new StartVM(server, vm);
					startVM.start();
					System.out.println("add one manually DONE!!!!!!");
					// }

				} else if (result == -1) {
					// delete one manually now TODO
					/*
					 * deleteCounter++; if(deleting == true){
					 * System.out.println(
					 * "One vm is deleting, clear delete count = 0!!!!");
					 * deleteCounter = 0; } else{ if(deleteCounter >= 2){
					 * System.
					 * out.println("delete one manually now TODO!!!!!!!"); XenVM
					 * vm =
					 * server.getActiveVMs().remove(server.getActiveVMs().size
					 * ()-1); StopVM stopVM = new StopVM(server, vm);
					 * stopVM.start();
					 * System.out.println("delete one manually DONE!!!!!!");
					 * deleteCounter = 0; deleting = true; } else
					 * System.out.println("do nothing here!!!!!!!"); }
					 */
					System.out.println("delete one manually now TODO!!!!!!!");
					XenVM vm = server.getActiveVMs().remove(
							server.getActiveVMs().size() - 1);
					StopVM stopVM = new StopVM(server, vm);
					stopVM.start();
					System.out.println("delete one manually DONE!!!!!!");
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void start() {
		System.out.println("AnalysisManager start");
		// responseStore = ResponseStore.getInstance();
		blinker.start();
	}

	public void stop() {
		System.out.println("AnalysisManager stop");
		Thread moribund = this.blinker;
		this.blinker = null;
		if (moribund != null) {
			moribund.interrupt();
		}
	}

	public static void main(String[] args) {

		AnalysisManager a = AnalysisManager.getInstance();
		a.start();
		// r.start();
	}
}
