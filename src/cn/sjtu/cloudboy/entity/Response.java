package cn.sjtu.cloudboy.entity;

import java.util.List;

public class Response {
	
	private String demReqRate;
	private String reqRate;
	private String conRate;
	private String minRepRate;
	private String avgRepRate;
	private String maxRepRate;
	private String stddevRepRate;
	private String respTime;
	private String netIO;
	private String errorType;
	
	public Response(String demReqRate, String reqRate, String conRate, String minRepRate, String avgRepRate, String maxRepRate
			 , String stddevRepRate, String respTime, String netIO, String errorType){
		this.demReqRate = demReqRate;
		this.reqRate = reqRate;
		this.conRate = conRate;
		this.minRepRate = minRepRate;
		this.avgRepRate = avgRepRate;
		this.maxRepRate = maxRepRate;
		this.stddevRepRate = stddevRepRate;
		this.respTime = respTime;
		this.netIO = netIO;
		this.errorType = errorType;
	}
	
	public Response(List<String> fieldList){
		this(fieldList.get(0), fieldList.get(1), fieldList.get(2), fieldList.get(3), fieldList.get(4), fieldList.get(5), fieldList.get(6)
				,fieldList.get(7), fieldList.get(8), fieldList.get(9));
	}
	
	public String toString(){
		return "Response : demReqRate = " + demReqRate + " respTime = " + respTime + " errorType = " + errorType;
	}

	public String getDemReqRate() {
		return demReqRate;
	}

	public void setDemReqRate(String demReqRate) {
		this.demReqRate = demReqRate;
	}

	public String getReqRate() {
		return reqRate;
	}

	public void setReqRate(String reqRate) {
		this.reqRate = reqRate;
	}

	public String getConRate() {
		return conRate;
	}

	public void setConRate(String conRate) {
		this.conRate = conRate;
	}

	public String getMinRepRate() {
		return minRepRate;
	}

	public void setMinRepRate(String minRepRate) {
		this.minRepRate = minRepRate;
	}

	public String getAvgRepRate() {
		return avgRepRate;
	}

	public void setAvgRepRate(String avgRepRate) {
		this.avgRepRate = avgRepRate;
	}

	public String getMaxRepRate() {
		return maxRepRate;
	}

	public void setMaxRepRate(String maxRepRate) {
		this.maxRepRate = maxRepRate;
	}

	public String getStddevRepRate() {
		return stddevRepRate;
	}

	public void setStddevRepRate(String stddevRepRate) {
		this.stddevRepRate = stddevRepRate;
	}

	public String getRespTime() {
		return respTime;
	}

	public void setRespTime(String respTime) {
		this.respTime = respTime;
	}

	public String getNetIO() {
		return netIO;
	}

	public void setNetIO(String netIO) {
		this.netIO = netIO;
	}

	public String getErrorType() {
		return errorType;
	}

	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}
	


}
