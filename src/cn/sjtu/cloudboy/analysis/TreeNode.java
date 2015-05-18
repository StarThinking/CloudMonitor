package cn.sjtu.cloudboy.analysis;

import java.util.ArrayList;
import java.util.List;

public class TreeNode implements Comparable<Object> {

	private String name;
	private TreeNode parent;
	private double failure;
	private double temp;
	private List<TreeNode> children = new ArrayList<TreeNode>();

	public TreeNode(String name, TreeNode parent, double failure, double temp) {
		this.name = name;
		this.parent = parent;
		if (parent != null)
			parent.addChild(this);
		this.failure = failure;
		this.temp = temp;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TreeNode getParent() {
		return parent;
	}

	public void setParent(TreeNode parent) {
		this.parent = parent;
	}

	public double getFailure() {
		return failure;
	}

	public void setFailure(double failure) {
		this.failure = failure;
	}

	public void setTemp(double temp) {
		this.temp = temp;
	}

	public double getTemp() {
		return temp;
	}

	@Override
	public int compareTo(Object o) {
		if (this.name.equals(((TreeNode) o).name)) {
			return 0;
		} else
			return this.name.compareTo(((TreeNode) o).name);
	}

	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}

	public List<TreeNode> getChildren() {
		return children;
	}

	public void addChild(TreeNode node) {
		children.add(node);
	}

	public List<TreeNode> getBrothers() {
		List<TreeNode> list = new ArrayList<TreeNode>();
		list.addAll(this.getParent().getChildren());
		list.remove(this);
		return list;
	}
}
