package cn.sjtu.cloudboy.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import cn.sjtu.cloudboy.entity.Status;
import cn.sjtu.cloudboy.monitor.StatusStore;

public class SetGvmStatus extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	//private static Logger logger = Logger.getLogger(GetGvmStatus.class.getName());

	public SetGvmStatus() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("-------SetGvmStatus---------");
		
		Status status = new Status();

		status.setUpTime(request.getParameter("ut"));
		status.setCpuNum(request.getParameter("cn"));
		status.setCpuMhz(request.getParameter("cmz"));
		status.setCpuUsage(request.getParameter("cu"));
		status.setMemCap(request.getParameter("mc"));
		status.setMemUsage(request.getParameter("mu"));
		status.setSwapCap(request.getParameter("sc"));
		status.setSwapUsage(request.getParameter("su"));
		status.setDiskStorageCap(request.getParameter("dsc"));
		status.setDiskStorageUsage(request.getParameter("dsu"));
		status.setDiskIOCap(request.getParameter("dioc"));
		status.setDiskIO(request.getParameter("ddio"));
		status.setNetworkIOCap(request.getParameter("nioc"));
		status.setNetworkIO(request.getParameter("nio"));

		StatusStore.getInstance().setStatus(status);
		
		System.out.println("StatusQueue size = " + StatusStore.getInstance().getStatusQueue().size());	

	}
}
