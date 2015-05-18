package cn.sjtu.cloudboy.analysis;

import java.util.Map;

public class Analyze {

	private Performance performance = new Performance();
	private Reliability reliability = new Reliability();
	private AnalysisResult analysisResult = new AnalysisResult();

	public AnalysisResult getAnalysisResult() {
		return analysisResult;
	}

	public void doAnalyze(Map<String, Double> responseMap) {
		// get sla first
		performance.setSlaResponseTime(SLA.slaResponseTime);
		System.out.println("sla response time " + SLA.slaResponseTime);

		// analyze performance to generate the configuration of VM count
		int oldVMCount = responseMap.size();
		int newVMCount = performance.getVMCount(responseMap);
		double averageResponseTime = performance.getTotalAverageResponseTime();
		
		System.out.println("newVMCount:" + newVMCount + " averageResponseTime:" + averageResponseTime);

		analysisResult.setComsumerRevenue(Profit
				.getConsumerRevenue(averageResponseTime));
		analysisResult.setComsumerCost(Profit.getConsumerCost(oldVMCount));
		analysisResult.setComsumerProfit(Profit.getProfit(averageResponseTime,
				oldVMCount));
		
		System.out.println("revenue:" + Profit
				.getConsumerRevenue(averageResponseTime) + " cost:" + Profit.getConsumerCost(oldVMCount) + " profit:" + Profit.getProfit(averageResponseTime,
						oldVMCount));

		// analyze reliability to generate a VM list
		if (newVMCount == oldVMCount) {
			// do nothing here
			analysisResult.setCountChange(0);
//			analysisResult.setVmList(responseMap, 0, null);
		} else if (newVMCount == oldVMCount + 1) {
			// add one manually now TODO
			String addVMPosition = reliability.getVMPosition(1);
			if (addVMPosition != null) {
				analysisResult.setCountChange(1);
				analysisResult.setVmPositionChange(addVMPosition);
				System.out.println("addVMPosition:" + addVMPosition);
//				analysisResult.setVmList(responseMap, 1, addVMPosition);
			} else {
				System.out.println("Analyze should not arrive here");
			}
			System.out.println("addVMPosition:" + addVMPosition);
		} else if (newVMCount == oldVMCount - 1) {
			// Iterator<String> it = responseMap.keySet().iterator();
			// // delete one manually now TODO
			// String tempIP = null;
			// while (it.hasNext()) {
			// tempIP = it.next();
			// break;
			// }
			String removeVMPosition = reliability.getVMPosition(-1);
			if (removeVMPosition != null) {
				analysisResult.setCountChange(-1);
				analysisResult.setVmPositionChange(removeVMPosition);
//				analysisResult.setVmList(responseMap, -1, removeVMPosition);
			} else {
				System.err.println("Analyze should not arrive here");
			}
		}
		
		analysisResult.setReliability(reliability.getReliability());
	}

}
