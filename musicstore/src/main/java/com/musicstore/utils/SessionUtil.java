package com.musicstore.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtil {
	// private static Logger logger = Logger.getLogger(SessionUtil.class);
	
	public static void setAttibute(HttpServletRequest request, String name, Object value) {
		HttpSession session = request.getSession(); 
		if(session != null) {
			session.setAttribute(name, value);
		}
	}
	
	public static Object getAttribute(HttpServletRequest request, String name) {
		HttpSession session = request.getSession();
		if(session != null) {
			return session.getAttribute(name);
		}
		return null;
	}
}
