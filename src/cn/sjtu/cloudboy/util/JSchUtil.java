package cn.sjtu.cloudboy.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class JSchUtil {

	  private static final int timeout = 10000;
	  
	  public static String executeCommand(String user, String passwd, String host, int port, String command) 
	    {   
		    System.out.println("executeCommand!");   
	        String charset = "UTF-8";    
	           
	        JSch jsch = new JSch();   
	        Session session;
			try {
				session = jsch.getSession(user, host, port);
				session.setPassword(passwd);   
		        java.util.Properties config = new java.util.Properties();   
		        config.put("StrictHostKeyChecking", "no");   
		        session.setConfig(config);   
		        session.connect(timeout);   
		        Channel channel = session.openChannel("exec");   
		        ((ChannelExec) channel).setCommand(command);   
		        channel.setInputStream(null);   
		        ((ChannelExec) channel).setErrStream(System.err);   
		           
		        channel.connect(timeout);    
		        InputStream in = channel.getInputStream();   
		        BufferedReader reader = new BufferedReader(new InputStreamReader(in, Charset.forName(charset)));   
		        String buf = null;   
		      /*  while ((buf = reader.readLine()) != null)   
		        {   
		            System.out.println("here!");   
		            System.out.println(buf);   
		        }   */
		        reader.close();   
		        channel.disconnect();   
		        session.disconnect();   
			} catch (JSchException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			  catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}   
			
			return "OK";
	        
	    }   
	       
	  public static void executeCommandNoResult(String user, String passwd, String host, int port, String command) 
	    {   
		    System.out.println("executeCommandNoResult!");   
	        String charset = "UTF-8";    
	           
	        JSch jsch = new JSch();   
	        Session session;
			try {
				session = jsch.getSession(user, host, port);
				session.setPassword(passwd);   
		        java.util.Properties config = new java.util.Properties();   
		        config.put("StrictHostKeyChecking", "no");   
		        session.setConfig(config);   
		        session.connect(timeout);   
		        Channel channel = session.openChannel("exec");   
		        ((ChannelExec) channel).setCommand(command);   
		        channel.setInputStream(null);   
		        ((ChannelExec) channel).setErrStream(System.err);   
		           
		        channel.connect(timeout);    
		        InputStream in = channel.getInputStream();   
		        BufferedReader reader = new BufferedReader(new InputStreamReader(in, Charset.forName(charset)));   
		        reader.close();   
		        channel.disconnect();   
		        session.disconnect();   
			} catch (JSchException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			  catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}   
	        
	    }  

}
