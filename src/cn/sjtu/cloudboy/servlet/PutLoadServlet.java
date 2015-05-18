package cn.sjtu.cloudboy.servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PutLoadServlet extends HttpServlet {

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
		String slafile = request.getParameter("slafile");
		
		System.out.println(responsetime);
		System.out.println(reliability);
		System.out.println(transitionPoint1);
		System.out.println(transitionPoint2);
		System.out.println(revenueCurveParameter);
		//System.out.println(slafile);
		PrintWriter out = response.getWriter();
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