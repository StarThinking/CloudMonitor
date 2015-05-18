package cn.sjtu.cloudboy.analysis;

public class Profit {

	// Consumer revenue and cost curve needed TODO
	
	public static int transitionPoint1 = 60;
	public static int transitionPoint2 = 200;
	
	public static int revenueCurveParameter = 5000;
	public static int maxRevenue = 100;
	public static int minRevenue = 0;
	
	private static int eachVMCost = 1;

	public static double getProfit(double averageResponseTime, int totalVMCount) {
		return Profit.getConsumerRevenue(averageResponseTime) - Profit.getConsumerCost(totalVMCount);
	}
	
	public static double getConsumerRevenue(double averageResponseTime) {
		if (averageResponseTime < transitionPoint1) {
			return maxRevenue;
		} else if (averageResponseTime > transitionPoint2){
			return minRevenue;
		} else {
			return revenueCurveParameter / averageResponseTime;
		}
	}
	
	public static double getConsumerCost(int totalVMCount) {
		return eachVMCost * totalVMCount;
	}

}
