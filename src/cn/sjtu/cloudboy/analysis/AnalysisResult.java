package cn.sjtu.cloudboy.analysis;


public class AnalysisResult {
	
	private double comsumerRevenue = -1;
	private double comsumerCost = -1;
	private double comsumerProfit = -1;
	
	// 1 to add, -1 to remove
	private int countChange = 0;
	private String vmPositionChange = null;
	
	private double reliability = -1;

//	private ArrayList<String> vmList = new ArrayList<String>();
	
	public double getReliability() {
		return reliability;
	}

	public void setReliability(double reliability) {
		this.reliability = reliability;
	}

	public double getComsumerRevenue() {
		return comsumerRevenue;
	}

	public void setComsumerRevenue(double comsumerRevenue) {
		this.comsumerRevenue = comsumerRevenue;
	}

	public double getComsumerCost() {
		return comsumerCost;
	}

	public void setComsumerCost(double comsumerCost) {
		this.comsumerCost = comsumerCost;
	}

	public double getComsumerProfit() {
		return comsumerProfit;
	}

	public void setComsumerProfit(double comsumerProfit) {
		this.comsumerProfit = comsumerProfit;
	}

	public int getCountChange() {
		return countChange;
	}

	public void setCountChange(int countChange) {
		this.countChange = countChange;
	}

	public String getVmPositionChange() {
		return vmPositionChange;
	}

	public void setVmPositionChange(String vmPositionChange) {
		this.vmPositionChange = vmPositionChange;
	}

//	public ArrayList<String> getVmList() {
//		return vmList;
//	}
//
//	public void setVmList(Map<String, Double> vmMap, int change, String element) {
//		Iterator<String> it = vmMap.keySet().iterator();
//		
//		// copy vm list
//		String tempIP = null;
//		while(it.hasNext()){
//			tempIP = it.next();
//			vmList.add(tempIP);
//		}
//		
//		if (change == 0) {
//			// do nothing
//		} else if (change == 1) {
//			vmList.add(element);
//		} else if (change == 0) {
//			vmList.remove(element);
//		} else {
//			// should not arrive here
//		}
//	}

}
