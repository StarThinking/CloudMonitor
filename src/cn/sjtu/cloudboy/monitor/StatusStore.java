package cn.sjtu.cloudboy.monitor;

import java.util.concurrent.ConcurrentLinkedQueue;

import cn.sjtu.cloudboy.entity.Status;

public class StatusStore {
	private static StatusStore instance;
	static {
		instance = new StatusStore();
	}

	public static StatusStore getInstance() {
		return instance;
	}
	
	private ConcurrentLinkedQueue<Status> statusQueue = new ConcurrentLinkedQueue<Status>();
	private Status latestStatus = new Status();
	
	public void setStatus(Status status) {
		this.statusQueue.add(status);
		this.latestStatus = status;
	}
	
	public ConcurrentLinkedQueue<Status> getStatusQueue() {
		return this.statusQueue;
	}
	
	public Status getLatestStatus() {
		return this.latestStatus;
	}

}
