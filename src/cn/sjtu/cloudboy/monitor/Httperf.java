package cn.sjtu.cloudboy.monitor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import cn.sjtu.cloudboy.entity.ReqDuration;

public class Httperf implements Runnable {

	private volatile Thread blinker;
	private List<ReqDuration> perfPlan;
	private int currentTimes;
	private int num;
	private String ip;

	public Httperf(List<ReqDuration> perfPlan, int num, int currentTimes, String ip){
		this.perfPlan = perfPlan;
		this.num = num;
		this.currentTimes = currentTimes;
		this.ip = ip;
	}
	
	private String calReqRate() {
		int index = 0;
		int duration = 0;
		int firstDuration = perfPlan.get(index).getDuration();
		duration += firstDuration;
		while (currentTimes > duration) {
			index++;
			if (index < perfPlan.size())
				duration += perfPlan.get(index).getDuration();
			else { // httperf end
				index = -1;
				break;
			}
		}
		if (index == -1)
			return "0";
		else {
			if (num != 0)
				return String.valueOf((Integer.valueOf(perfPlan.get(index)
						.getReqRate()) / num));
			else
				return "0";
		}
	}

	private void doHttperf() {
		String req = calReqRate();
		// System.out.println("reqRate = " + req);
		// String req = "100";
		String cmd = "perl " + "/home/msx/test/scripts/genReq " + ip + " "
				+ req;
		System.out.println(cmd);
		StringBuffer resultStringBuffer = new StringBuffer();

		String lineToRead = "";
		int exitValue = 0;
		try {
			Process proc = Runtime.getRuntime().exec(cmd);
			InputStream inputStream = proc.getInputStream();
			BufferedReader bufferedRreader = new BufferedReader(
					new InputStreamReader(inputStream));

			// save first line
			if ((lineToRead = bufferedRreader.readLine()) != null) {
				resultStringBuffer.append(lineToRead);
			}

			// save next lines
			while ((lineToRead = bufferedRreader.readLine()) != null) {
				resultStringBuffer.append("\r\n");
				resultStringBuffer.append(lineToRead);
			}

			// Always reading STDOUT first, then STDERR, exitValue last
			proc.waitFor(); // wait for reading STDOUT and STDERR over
			exitValue = proc.exitValue();
		} catch (IOException e) {
			e.printStackTrace();
			resultStringBuffer = new StringBuffer("");
			exitValue = 2;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		Thread thisThread = Thread.currentThread();
		if (this.blinker == thisThread) 
			doHttperf();
	}

	public void start() {
		//System.out.println("Httperf start " + ip);
		blinker = new Thread(this, "StartVM");
		blinker.start();
	}

	public void stop() {
		System.out.println("Httperf stop");
		Thread moribund = this.blinker;
		this.blinker = null;
		if (moribund != null) {
			moribund.interrupt();
		}
	}

}
