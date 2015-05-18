package cn.sjtu.cloudboy.analysis;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Reliability {

	// private Tree tree = new Tree();

	private int vmCount = 0;
	private int vmAddCount = 0;
	private List<String> historyAdd = new ArrayList<String>();
	private List<TreeNode> totalTreeNodes = new ArrayList<TreeNode>();

	public Reliability() {
		this.initTree(1, 0);
	}

	public void initTree(int number, int distance) {
		for (int i = 1; i <= number; i++) {
			TreeNode vm = null;

			switch (i % 4) {
			case 1:
				vm = new TreeNode("vm" + i, Tree.pm1, Tree.hostFailure, Tree.hostFailure);
				break;
			case 2:
				vm = new TreeNode("vm" + i, Tree.pm3, Tree.hostFailure, Tree.hostFailure);
				break;
			case 3:
				vm = new TreeNode("vm" + i, Tree.pm2, Tree.hostFailure, Tree.hostFailure);
				break;
			case 0:
				vm = new TreeNode("vm" + i, Tree.pm4, Tree.hostFailure, Tree.hostFailure);
				break;
			}

			vmCount++;
			System.out.println("New TreeNode " + vm.getName()
					+ " in initialization.");
		}
	}

	public String getVMPosition(int count) {
		if (count != 1 && count != -1) {
			return null;
		}
		System.out.println("reliability count:" + count);
		Random rand = new Random();
		if (count == 1) {
			TreeNode vm = null;
			vmCount++;
			
			System.out.println("vmCount:" + vmCount + " " + "vmAddCount:" + vmAddCount);

			switch (vmAddCount % 4) {
			case 1:
				if (vmAddCount < 4) {
					vm = new TreeNode("vm" + vmCount, Tree.pm2,
							Tree.hostFailure, Tree.hostFailure);
				} else if (rand.nextInt(1) < 0.5) {
					vm = new TreeNode("vm" + vmCount, Tree.pm2,
							Tree.hostFailure, Tree.hostFailure);
				} else {
					vm = new TreeNode("vm" + vmCount, Tree.pm1,
							Tree.hostFailure, Tree.hostFailure);
				}
				break;
			case 2:
				if (vmAddCount < 4) {
					vm = new TreeNode("vm" + vmCount, Tree.pm4,
							Tree.hostFailure, Tree.hostFailure);
				} else if (rand.nextInt(1) < 0.5) {
					vm = new TreeNode("vm" + vmCount, Tree.pm4,
							Tree.hostFailure, Tree.hostFailure);
				} else {
					vm = new TreeNode("vm" + vmCount, Tree.pm3,
							Tree.hostFailure, Tree.hostFailure);
				}
				break;
			case 3:
				if (vmAddCount < 4) {
					vm = new TreeNode("vm" + vmCount, Tree.pm1,
							Tree.hostFailure, Tree.hostFailure);
				} else if (rand.nextInt(1) < 0.5) {
					vm = new TreeNode("vm" + vmCount, Tree.pm1,
							Tree.hostFailure, Tree.hostFailure);
				} else {
					vm = new TreeNode("vm" + vmCount, Tree.pm2,
							Tree.hostFailure, Tree.hostFailure);
				}
				break;
			case 0:
				if (vmAddCount < 4) {
					vm = new TreeNode("vm" + vmCount, Tree.pm3,
							Tree.hostFailure, Tree.hostFailure);
				} else if (rand.nextInt(1) < 0.5) {
					vm = new TreeNode("vm" + vmCount, Tree.pm3,
							Tree.hostFailure, Tree.hostFailure);
				} else {
					vm = new TreeNode("vm" + vmCount, Tree.pm4,
							Tree.hostFailure, Tree.hostFailure);
				}
				break;
			}
			
			totalTreeNodes.add(vm);
			System.out.println(vm.getParent().getName() + " " + Tree.pm1.getName());
			
			String returnIP = null;
			if (vm.getParent().getName().equals(Tree.pm1.getName())) {
				returnIP = "172.16.1.1";
			}
			if (vm.getParent().getName().equals(Tree.pm2.getName())) {
				returnIP = "172.16.1.2";
			}
			if (vm.getParent().getName().equals(Tree.pm3.getName())) {
				returnIP = "172.16.1.3";
			}
			if (vm.getParent().getName().equals(Tree.pm4.getName())) {
				returnIP = "172.16.1.4";
			}
			
			vmAddCount++;
			System.out
					.println("Add TreeNode in " + returnIP);
			if (returnIP != null) {
				historyAdd.add(returnIP);
			}
			return returnIP;
		} else {
			vmCount--;

			String returnIP = historyAdd.get(historyAdd.size() - 1);
			System.out
					.println("Remove TreeNode in " + returnIP);
			
			totalTreeNodes.remove(totalTreeNodes.size() - 1);
			return returnIP;
		}
	}

	public double getReliability() {
//		List<TreeNode> vmList = new ArrayList<TreeNode>();

		// match corresponding vm position
		return TreeReliability.AvailabilityIn(2, totalTreeNodes);
	}

	public double getMaxReliability() {
		List<TreeNode> pmList = new ArrayList<TreeNode>();

		pmList.add(Tree.pm1);
		pmList.add(Tree.pm2);
		pmList.add(Tree.pm3);
		pmList.add(Tree.pm4);
		return TreeReliability.AvailabilityIn(2, pmList);
	}

	public String getJSON() {
		String result = "";

		// cloud
		result += "{id:'" + Tree.cloud.getName() + "',name:'"
				+ Tree.cloud.getName() + "',data:{},children:[";
		// zone1
		result += "{id:'" + Tree.z1.getName() + "',name:'" + Tree.z1.getName()
				+ "',data:{},children:[";
		// pm1
		result += "{id:'" + Tree.pm1.getName() + "',name:'"
				+ Tree.pm1.getName() + "',data:{},children:[";
		for (int i = 0; i < Tree.pm1.getChildren().size(); i++) {
			result += "{id:'" + Tree.pm1.getChildren().get(i).getName()
					+ "',name:'" + Tree.pm1.getChildren().get(i).getName()
					+ "',data:{},children:[]}";

			if (i != Tree.pm1.getChildren().size() - 1) {
				result += ",";
			}
		}
		// end pm1
		result += "]";

		// start pm2
		result += "},";

		// pm2
		result += "{id:'" + Tree.pm2.getName() + "',name:'"
				+ Tree.pm2.getName() + "',data:{},children:[";
		for (int i = 0; i < Tree.pm2.getChildren().size(); i++) {
			result += "{id:'" + Tree.pm2.getChildren().get(i).getName()
					+ "',name:'" + Tree.pm2.getChildren().get(i).getName()
					+ "',data:{},children:[]}";
			if (i != Tree.pm2.getChildren().size() - 1) {
				result += ",";
			}
		}
		// end pm2
		result += "]";

		// end zone1 child
		result += "}]";

		// end zone1
		result += "},";

		// zone2
		result += "{id:'" + Tree.z2.getName() + "',name:'" + Tree.z2.getName()
				+ "',data:{},children:[";

		// pm3
		result += "{id:'" + Tree.pm3.getName() + "',name:'"
				+ Tree.pm3.getName() + "',data:{},children:[";
		for (int i = 0; i < Tree.pm3.getChildren().size(); i++) {
			result += "{id:'" + Tree.pm3.getChildren().get(i).getName()
					+ "',name:'" + Tree.pm3.getChildren().get(i).getName()
					+ "',data:{},children:[]}";

			if (i != Tree.pm3.getChildren().size() - 1) {
				result += ",";
			}
		}
		// end pm3
		result += "]";

		// start pm4
		result += "},";

		// pm4
		result += "{id:'" + Tree.pm4.getName() + "',name:'"
				+ Tree.pm4.getName() + "',data:{},children:[";
		for (int i = 0; i < Tree.pm4.getChildren().size(); i++) {
			result += "{id:'" + Tree.pm4.getChildren().get(i).getName()
					+ "',name:'" + Tree.pm4.getChildren().get(i).getName()
					+ "',data:{},children:[]}";
			if (i != Tree.pm4.getChildren().size() - 1) {
				result += ",";
			}
		}
		// end pm4
		result += "]";

		// end zone2 children
		result += "}]";

		// end zone2
		result += "}]";

		// end cloud
		result += "}";

		return result;
	}

	public String getJSONDebug() {
		String result = "";

		// cloud
		result += "{\nid: '" + Tree.cloud.getName() + "',\nname: '"
				+ Tree.cloud.getName() + "',\ndata: {},\nchildren: [";
		// zone1
		result += "{\nid: '" + Tree.z1.getName() + "',\nname: '"
				+ Tree.z1.getName() + "',\ndata: {},\nchildren:[";
		// pm1
		result += "{\nid: '" + Tree.pm1.getName() + "',\nname: '"
				+ Tree.pm1.getName() + "',\ndata: {},\nchildren: [";
		for (int i = 0; i < Tree.pm1.getChildren().size(); i++) {
			result += "{\nid: '" + Tree.pm1.getChildren().get(i).getName()
					+ "',\nname: '" + Tree.pm1.getChildren().get(i).getName()
					+ "',\ndata: {},\nchildren: []\n}";

			if (i != Tree.pm1.getChildren().size() - 1) {
				result += ",";
			}
		}
		// end pm1
		result += "]\n";

		// start pm2
		result += "},";

		// pm2
		result += "{\nid: '" + Tree.pm2.getName() + "',\nname: '"
				+ Tree.pm2.getName() + "',\ndata: {},\nchildren: [";
		for (int i = 0; i < Tree.pm2.getChildren().size(); i++) {
			result += "{\nid: '" + Tree.pm2.getChildren().get(i).getName()
					+ "',\nname: '" + Tree.pm2.getChildren().get(i).getName()
					+ "',\ndata: {},\nchildren: []\n}";
			if (i != Tree.pm2.getChildren().size() - 1) {
				result += ",";
			}
		}
		// end pm2
		result += "]\n";

		// end zone1 child
		result += "}]\n";

		// end zone1
		result += "},";

		// zone2
		result += "{\nid: '" + Tree.z2.getName() + "',\nname: '"
				+ Tree.z2.getName() + "',\ndata: {},\nchildren:[";

		// pm3
		result += "{\nid: '" + Tree.pm3.getName() + "',\nname: '"
				+ Tree.pm3.getName() + "',\ndata: {},\nchildren: [";
		for (int i = 0; i < Tree.pm3.getChildren().size(); i++) {
			result += "{\nid: '" + Tree.pm3.getChildren().get(i).getName()
					+ "',\nname: '" + Tree.pm3.getChildren().get(i).getName()
					+ "',\ndata: {},\nchildren: []\n}";

			if (i != Tree.pm3.getChildren().size() - 1) {
				result += ",";
			}
		}
		// end pm3
		result += "]\n";

		// start pm4
		result += "},";

		// pm4
		result += "{\nid: '" + Tree.pm4.getName() + "',\nname: '"
				+ Tree.pm4.getName() + "',\ndata: {},\nchildren: [";
		for (int i = 0; i < Tree.pm4.getChildren().size(); i++) {
			result += "{\nid: '" + Tree.pm4.getChildren().get(i).getName()
					+ "',\nname: '" + Tree.pm4.getChildren().get(i).getName()
					+ "',\ndata: {},\nchildren: []\n}";
			if (i != Tree.pm4.getChildren().size() - 1) {
				result += ",";
			}
		}
		// end pm4
		result += "]\n";

		// end zone2 children
		result += "}]\n";

		// end zone2
		result += "}]\n";

		// end cloud
		result += "}";

		return result;
	}

	public static void main(String[] args) {
		Reliability r = new Reliability();

		r.initTree(7, 0);

		System.out.println(r.getJSON());
	}

}
