package cn.sjtu.cloudboy.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

 protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException{
  response.setContentType("text;html;charset=utf-8");
  String username=request.getParameter("username");
  String password=request.getParameter("password");
  System.out.println("username="+username);
  System.out.println("password="+password);
  System.out.println("servlet test success....");
 }

protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException{
 doGet(request,response);
 }
}