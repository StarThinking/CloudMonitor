package cn.sjtu.cloudboy.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.sjtu.cloudboy.manager.StartCloud;

public class StartVmServlet extends HttpServlet {

	 protected void doGet(HttpServletRequest request,HttpServletResponse response)
			 throws ServletException, IOException {
	  request.setCharacterEncoding("utf-8");
	  response.setCharacterEncoding("utf-8");
	  response.setContentType("application/json;charset=utf-8");
	  System.out.println("Start VMs and Apps");
//	  PrintWriter out = response.getWriter();
//	  out.write("");
//	  out.close();
//	  RequestDispatcher rd = request.getRequestDispatcher("kitchensink/app/node.html");
//		if (rd != null) {
//			rd.forward(request, response);
//		}
     StartCloud sc = StartCloud.getInstance();
     sc.start();
	 System.out.println("over");
	 }

	protected void doPost(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException {
	 doGet(request,response);
	 }
}