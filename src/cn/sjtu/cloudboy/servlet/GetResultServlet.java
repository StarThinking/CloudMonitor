package cn.sjtu.cloudboy.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.sjtu.cloudboy.manager.CloudManager;


public class GetResultServlet extends HttpServlet {

 protected void doGet(HttpServletRequest request,HttpServletResponse response)
		 throws ServletException, IOException {
  request.setCharacterEncoding("utf-8");
  response.setCharacterEncoding("utf-8");
  response.setContentType("application/json;charset=utf-8");
  PrintWriter out = response.getWriter();
  String json = "{id:'cloud',name:'cloud',data:{},children:[{id:'ZONE1',name:'ZONE1',data:{},children:[{id:'PM1',name:'PM1',data:{},children:[" +
			"]},{id:'PM2',name:'PM2',data:{},children:[" +
			"]}]},{id:'ZONE2',name:'ZONE2',data:{},children:[{id:'PM3',name:'PM3',data:{},children:[" +
			"]},{id:'PM4',name:'PM4',data:{},children:[]}]}]}";

	out.write(json);
	System.out.println(json);
	out.close();
 }

protected void doPost(HttpServletRequest request,HttpServletResponse response)
		throws ServletException, IOException {
 doGet(request,response);
 }
}