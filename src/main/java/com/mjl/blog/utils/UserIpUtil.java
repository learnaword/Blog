//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.mjl.blog.utils;

import jakarta.servlet.http.HttpServletRequest;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class UserIpUtil {
	public UserIpUtil() {
	}

	public static String getIp(HttpServletRequest httpRequest) {
		String ipAddress = httpRequest.getHeader("x-forwarded-for");
		if (ipAddress != null && ipAddress.length() != 0 && !"unknown".equalsIgnoreCase(ipAddress) && ipAddress.indexOf(",") != -1) {
			ipAddress = ipAddress.split(",")[0];
		}

		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = httpRequest.getHeader("Proxy-Client-IP");
		}

		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = httpRequest.getHeader("WL-Proxy-Client-IP");
		}

		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = httpRequest.getHeader("HTTP_CLIENT_IP");
		}

		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = httpRequest.getHeader("HTTP_X_FORWARDED_FOR");
		}

		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = httpRequest.getHeader("X-Real-IP");
		}

		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = httpRequest.getRemoteAddr();
			if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
				InetAddress inet = null;

				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException var4) {
					var4.printStackTrace();
				}

				ipAddress = inet.getHostAddress();
			}
		}

		return ipAddress;
	}
}
