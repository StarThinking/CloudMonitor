package cn.sjtu.cloudboy.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.sjtu.cloudboy.analysis.AnalysisManager;

public class GetResponsibilityServlet extends HttpServlet {

	 static int NUMBER = 1;
	  
	 protected void doGet(HttpServletRequest request,HttpServletResponse response)
			 throws ServletException, IOException {
	  request.setCharacterEncoding("utf-8");
	  response.setCharacterEncoding("utf-8");
	  response.setContentType("text/json;charset=utf-8");
	  System.out.println("getReliabilityHistory");
	  PrintWriter out = response.getWriter();

	  String json = "[{name:'null1',data1:0,data2:0,data3:0,data4:0}," +
	  		  		"{name:'null2',data1:0,data2:0,data3:0,data4:0}," +
	  		  		"{name:'null3',data1:0,data2:0,data3:0,data4:0}," +
	  		  		"{name:'null4',data1:0,data2:0,data3:0,data4:0}," +
	  		  		"{name:'null5',data1:0,data2:0,data3:0,data4:0}," +
	  		  		"{name:'null6',data1:0,data2:0,data3:0,data4:0}]";
	  
	  Queue<Double> queue = AnalysisManager.getInstance().getReliabilityHistory();
	 // Queue<Double> queue = new LinkedList<Double>();
	  //queue.add(1.1);
	  //queue.add(2.2);
	  System.out.println("getReliabilityHistory = " + queue);
	  //Queue<Double> queue = AnalysisManager.getInstance().getProfitHistory();

	  if(queue.size() > 0) {
		  json = "";
		  int i = 0;
		  for (Double d : queue) {
			  json += "{name:'"+ (NUMBER + i) +"',data1:"+ d +",data2:0,data3:0,data4:0},";
			  i++;
		  }
		  NUMBER++;
		  json = "[" + json + "]";
	  }
	  System.out.println(json);
	  out.write(json);
	  //out.flush();
	  out.close();
	 }

	protected void doPost(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException {
	 doGet(request,response);
	 }
}