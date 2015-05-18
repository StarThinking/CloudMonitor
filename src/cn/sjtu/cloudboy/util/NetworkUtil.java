package cn.sjtu.cloudboy.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

public class NetworkUtil {
	public static String getIpAddr(HttpServletRequest request) {
		String ip = null;

		String xForwardedFor = request.getHeader("x-forwarded-for");
		if (xForwardedFor != null) {
			String[] iplist = xForwardedFor.split(",");
			for (int i = 0; i < iplist.length; i++) {
				if (!"unknown".equalsIgnoreCase(iplist[i])) {
					ip = iplist[i];
					break;
				}
			}
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}

		return ip;
	}

	public static Collection<InetAddress> getAllHostAddress() {
		try {
			Enumeration<NetworkInterface> networkInterfaces = NetworkInterface
					.getNetworkInterfaces();
			Collection<InetAddress> addresses = new ArrayList<InetAddress>();

			while (networkInterfaces.hasMoreElements()) {
				NetworkInterface networkInterface = networkInterfaces
						.nextElement();
				Enumeration<InetAddress> inetAddresses = networkInterface
						.getInetAddresses();
				while (inetAddresses.hasMoreElements()) {
					InetAddress inetAddress = inetAddresses.nextElement();
					addresses.add(inetAddress);
				}
			}System.out.print("address"+addresses.toString());

			return addresses;
		} catch (SocketException e) {
			return null;
		}
	}

	public static Collection<String> getAllNoLoopbackAddresses() {
		Collection<InetAddress> allInetAddresses = getAllHostAddress();
		if (allInetAddresses == null)
			return null;

		Collection<String> noLoopbackAddresses = new ArrayList<String>();

		for (InetAddress address : allInetAddresses) {
			if (!address.isLoopbackAddress()) {
				noLoopbackAddresses.add(address.getHostAddress());
			}
		}

		return noLoopbackAddresses;
	}

	public static String getFirstNoLoopbackAddress() {
		Collection<String> allNoLoopbackAddresses = getAllNoLoopbackAddresses();
		if (allNoLoopbackAddresses != null && !allNoLoopbackAddresses.isEmpty()) {
			return allNoLoopbackAddresses.iterator().next();
		} else {
			return null;
		}
	}
}
