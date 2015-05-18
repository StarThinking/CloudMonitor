package cn.sjtu.cloudboy.analysis;

import java.util.Iterator;
import java.util.Map;

public class Performance {

	private int slaResponseTime;
	private double totalAverageResponseTime = -1;

	private double lastConsumrProfit;
	private double lastAverageResponseTime;
	private boolean hasLastConsumerProfit = false;

	private boolean isStable = false;

	private boolean increasing = true;

	private int currentVMCount = 1;

	public int getSlaResponseTime() {
		return slaResponseTime;
	}

	public void setSlaResponseTime(int slaResponseTime) {
		this.slaResponseTime = slaResponseTime;
	}

	public double getTotalAverageResponseTime() {
		return totalAverageResponseTime;
	}

	public int getVMCount(Map<String, Double> responseMap) {
		double totalResponse = 0;
		int totalVMCount = responseMap.size();

		if (currentVMCount == totalVMCount) {
			// get average response
			Iterator<String> it = responseMap.keySet().iterator();
			while (it.hasNext()) {
				String ip = it.next();
				totalResponse += responseMap.get(ip);
				System.out.println("totalResponse:" + totalResponse);
			}
			totalAverageResponseTime = totalResponse / totalVMCount;
			System.out.println("totalResponse:" + totalResponse
					+ " totalVMCount:" + totalVMCount
					+ " totalAverageResponseTime:" + totalAverageResponseTime);

			// if (hasLastConsumerProfit) {
			// // do not conform to SLA, add one VM
			// if (totalAverageResponseTime >= slaResponseTime
			// || totalAverageResponseTime > lastAverageResponseTime + 200) {
			// isStable = false;
			// increasing = true;
			//
			// currentVMCount = totalVMCount + 1;
			// return totalVMCount + 1;
			// }
			//
			// // use a specific method to analyze whether to decrease VM count
			// if (totalAverageResponseTime <= lastAverageResponseTime / 2 &&
			// totalVMCount > 1) {
			// isStable = false;
			// increasing = false;
			//
			// currentVMCount = totalVMCount - 1;
			// return totalVMCount - 1;
			// }
			// }

			// normal feedback procedure
			if (isStable) {
				// do not conform to SLA, add one VM
				if (totalAverageResponseTime >= slaResponseTime
						|| totalAverageResponseTime > lastAverageResponseTime + 200) {
					isStable = false;
					increasing = true;

					currentVMCount = totalVMCount + 1;
					return totalVMCount + 1;
				}

				// use a specific method to analyze whether to decrease VM count
				if (totalAverageResponseTime <= lastAverageResponseTime / 2
						&& totalVMCount > 1) {
					isStable = false;
					increasing = false;

					currentVMCount = totalVMCount - 1;
					return totalVMCount - 1;
				}

				// update response time and profit
				lastAverageResponseTime = totalAverageResponseTime;
				lastConsumrProfit = Profit.getProfit(totalAverageResponseTime,
						totalVMCount);

				currentVMCount = totalVMCount;
				return totalVMCount;
			} else {
				if (!hasLastConsumerProfit) {
					// the first time in loop
					hasLastConsumerProfit = true;
					lastAverageResponseTime = totalAverageResponseTime;
					lastConsumrProfit = Profit.getProfit(
							totalAverageResponseTime, totalVMCount);

					currentVMCount = totalVMCount + 1;
					return totalVMCount + 1;
				} else if (Profit.getProfit(totalAverageResponseTime,
						totalVMCount) > lastConsumrProfit) {
					// the profit is increasing
					// add one VM
					lastAverageResponseTime = totalAverageResponseTime;
					lastConsumrProfit = Profit.getProfit(
							totalAverageResponseTime, totalVMCount);

					if (increasing) {
						currentVMCount = totalVMCount + 1;
						return totalVMCount + 1;
					} else if (totalVMCount != 1) {
						currentVMCount = totalVMCount - 1;
						return totalVMCount - 1;
					} else {
						currentVMCount = totalVMCount;
						return totalVMCount;
					}
				} else {
					// the profit is decreasing
					// use last strategy as a stable one
					isStable = true;

					if (increasing) {
						currentVMCount = totalVMCount - 1;
						return totalVMCount - 1;
					} else {
						currentVMCount = totalVMCount + 1;
						return totalVMCount + 1;
					}
				}
			}
		} else {
			System.out.println("Analyze give up at " + currentVMCount);
			return totalVMCount;
		}
	}

}
