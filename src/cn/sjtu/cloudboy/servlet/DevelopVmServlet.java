package cn.sjtu.cloudboy.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.sjtu.cloudboy.manager.CloudManager;

public class DevelopVmServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		//out.write("{id: 'node02',name: '0.2',data: {},children: [{id: 'node13',name: '1.3',data: {},children: [{id: 'node24',name: '2.4',data: {},children: [{id: 'node35',name: '3.5',data: {},children: []}, {id: 'node312',name: '3.12',data: {},children: []}]}, {id: 'node222',name: '2.22',data: {},children: []}]}, {id: 'node125',name: '1.25',data: {},children: [{id: 'node226',name: '2.26',data: {},children: [{id: 'node330',name: '3.30',data: {},children: []}, {id: 'node332',name: '3.32',data: {},children: [{id: 'node433',name: '4.33',data: {},children: []}, {id: 'node436',name: '4.36',data: {},children: []}]}]}]}]}");
		
		String json = "{id:'cloud',name:'cloud',data:{},children:[{id:'ZONE1',name:'ZONE1',data:{},children:[{id:'PM1',name:'PM1',data:{},children:[" +
				"]},{id:'PM2',name:'PM2',data:{},children:[" +
				"]}]},{id:'ZONE2',name:'ZONE2',data:{},children:[{id:'PM3',name:'PM3',data:{},children:[" +
				"]},{id:'PM4',name:'PM4',data:{},children:[]}]}]}";
		
		Map<String, List<String>> vmMap = CloudManager.getInstance().getCurrentLayout();
		System.out.println("vmMap = " + vmMap);
		
		if(vmMap.size() == 0){
			;
		}
		else{
			List<String> pm1 = vmMap.get("ov1");
			List<String> pm2 = vmMap.get("ov2");
			List<String> pm3 = vmMap.get("ov3");
			List<String> pm4 = vmMap.get("ov4");
			json = "{id:'cloud',name:'cloud',data:{},children:[{id:'ZONE1',name:'ZONE1',data:{},children:[{id:'PM1',name:'PM1',data:{},children:[";
			Iterator<String> it = pm1.iterator();
			while (it.hasNext()) {
			    String vm = it.next();
			    if(it.hasNext()) {
			    	json += "{id:'" + vm + "',name:'" + vm + "',data:{},children:[]},";
			    } else {
			    	json += "{id:'" + vm + "',name:'" + vm + "',data:{},children:[]}";
			    }
			}
			json += "]},{id:'PM2',name:'PM2',data:{},children:[";
			it = pm2.iterator();
			while (it.hasNext()) {
			    String vm = it.next();
			    if(it.hasNext()) {
			    	json += "{id:'" + vm + "',name:'" + vm + "',data:{},children:[]},";
			    } else {
			    	json += "{id:'" + vm + "',name:'" + vm + "',data:{},children:[]}";
			    }
			}
			json += "]}]},{id:'ZONE2',name:'ZONE2',data:{},children:[{id:'PM3',name:'PM3',data:{},children:[";
			it = pm3.iterator();
			while (it.hasNext()) {
			    String vm = it.next();
			    if(it.hasNext()) {
			    	json += "{id:'" + vm + "',name:'" + vm + "',data:{},children:[]},";
			    } else {
			    	json += "{id:'" + vm + "',name:'" + vm + "',data:{},children:[]}";
			    }
			}
			json += "]},{id:'PM4',name:'PM4',data:{},children:[";
			it = pm4.iterator();
			while (it.hasNext()) {
			    String vm = it.next();
			    if(it.hasNext()) {
			    	json += "{id:'" + vm + "',name:'" + vm + "',data:{},children:[]},";
			    } else {
			    	json += "{id:'" + vm + "',name:'" + vm + "',data:{},children:[]}";
			    }
			}
			json += "]}]}]}";
		}
			
		
		out.write(json);

		//request.setAttribute("json", json);
		out.close();
	}
	 
	protected void doPost(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}
}