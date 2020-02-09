package com.example.logintest_1.wrapper;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class DecryptRequestWrapper extends HttpServletRequestWrapper {
	private Map<String, String[]> params;
	
	public DecryptRequestWrapper(HttpServletRequest request) {
		super(request);
		// TODO Auto-generated constructor stub
	}
	
	public void setDecryptParameter(Map<String, String[]> map, String d_id, String d_pwd) {
		params = new HashMap(map);
		String[] user = map.get("username");
		String[] pwd = map.get("password");
		
		user[0] = d_id;
		pwd[0] = d_pwd;
		
		params.put("username", user);
		params.put("password", pwd);
		
	}

	@Override
	public String getParameter(String name) {
//		String[] values = getParameterValues(name);
		if (name.equals("username")) {
			name = params.get("username")[0];
			System.out.println("params.get(\"username\")[0]: "+name);
		}
		if (name.equals("password")) {
			name = params.get("password")[0];
			System.out.println(" params.get(\"password\")[0]: "+name);
		}
//		if (name.equals("_csrf")) {
//			name = "asd";
//		}
		if (name != null && name.length() > 0)
			return name;
		
		return name;

	}	

}
