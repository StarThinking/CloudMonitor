<%@ page language="java" import="java.util.*,cn.sjtu.cloudboy.monitor.*,cn.sjtu.cloudboy.entity.*,java.util.concurrent.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'status.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="refresh" content="10"> 
	
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body  onload="init()">
    	<%
    		Status latesStatus = StatusStore.getInstance().getLatestStatus();
    	%>
	<h4>Cloud Client LatesStatus Info</h4>
	<table border="2">
		<tbody>
			<tr>
				<td>UpTime:</td>
				<td><%=latesStatus.getUpTime()%></td>
			</tr>
			<tr>
				<td>CpuNum:</td>
				<td><%=latesStatus.getCpuNum()%></td>
			</tr>
			<tr>
				<td>CpuMhz:</td>
				<td><%=latesStatus.getCpuMhz()%></td>
			</tr>
			<tr>
				<td>CpuUsage:</td>
				<td><%=latesStatus.getCpuUsage()%></td>
			</tr>
			<tr>
				<td>MemCap:</td>
				<td><%=latesStatus.getMemCap()%></td>
			</tr>
			<tr>
				<td>MemUsage:</td>
				<td><%=latesStatus.getMemUsage()%></td>
			</tr>
				<tr>
				<td>SwapCap:</td>
				<td><%=latesStatus.getSwapCap()%></td>
			</tr>
			<tr>
				<td>SwapUsage:</td>
				<td><%=latesStatus.getSwapUsage()%></td>
			</tr>
			<tr>
				<td>NetworkIOCap:</td>
				<td><%=latesStatus.getNetworkIOCap()%></td>
			</tr>
			<tr>
				<td>NetworkIO:</td>
				<td><%=latesStatus.getNetworkIO()%></td>
			</tr>
		</tbody>
	</table>
  </body>
</html>
