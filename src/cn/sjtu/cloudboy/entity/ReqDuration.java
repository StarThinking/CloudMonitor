package cn.sjtu.cloudboy.entity;

public class ReqDuration {
	private String reqRate;
	private int duration;
	
	public ReqDuration(String reqRate, int duration){
		this.reqRate = reqRate;
		this.duration = duration;
	}
	
	public String getReqRate() {
		return reqRate;
	}
	
	public void setReqRate(String reqRate) {
		this.reqRate = reqRate;
	}
	
	public int getDuration() {
		return duration;
	}
	
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	
}
