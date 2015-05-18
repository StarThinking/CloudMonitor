package cn.sjtu.cloudboy.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.sjtu.cloudboy.analysis.Profit;
import cn.sjtu.cloudboy.analysis.SLA;

public class GetSlaServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request,HttpServletResponse response)
			 throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json; charset=utf-8");
		System.out.println("Get SLA");
		String responsetime = request.getParameter("responsetime");
		String reliability = request.getParameter("reliability");
		String transitionPoint1 = request.getParameter("transitionPoint1");
		String transitionPoint2 = request.getParameter("transitionPoint2");
		String revenueCurveParameter = request.getParameter("revenueCurveParameter");
		System.out.println(responsetime);
		System.out.println(reliability);
		System.out.println(transitionPoint1);
		System.out.println(transitionPoint2);
		System.out.println(revenueCurveParameter);
		PrintWriter out = response.getWriter();
		SLA.slaResponseTime = Integer.valueOf(responsetime);
		SLA.reliability = Integer.valueOf(reliability);
		Profit.transitionPoint1 = Integer.valueOf(transitionPoint1);
		Profit.transitionPoint2 = Integer.valueOf(transitionPoint2);
		Profit.revenueCurveParameter = Integer.valueOf(revenueCurveParameter);
		Profit.maxRevenue = Profit.revenueCurveParameter/Profit.transitionPoint1 + 1;
		boolean success = true;
		
		
		
		if(success) {
			out.write("{success:true,info:'Submit sla successfully!'}");
		}
		else {
			out.write("{success:false,error:'Submit sla failed!'}");
			response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
		}
		out.close();
	}

	protected void doPost(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException {
	 doGet(request,response);
	 }
}