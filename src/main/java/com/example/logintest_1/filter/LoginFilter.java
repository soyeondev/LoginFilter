package com.example.logintest_1.filter;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.Base64.Decoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.logintest_1.wrapper.CharResponseWrapper;
import com.example.logintest_1.wrapper.DecryptRequestWrapper;
import com.example.logintest_1.wrapper.ResponseWrapper;

public class LoginFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("Login Filter 생성");
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		Filter.super.destroy();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		CharResponseWrapper wrapper = new CharResponseWrapper((HttpServletResponse) response);
        ResponseWrapper rw = new ResponseWrapper(response);
        DecryptRequestWrapper rWrapper = new DecryptRequestWrapper((HttpServletRequest) request);

        if(rWrapper.getMethod().equals("GET") && !rWrapper.getServletPath().equals("/login")){
        	System.out.println("GET들어옴");
        	chain.doFilter(rWrapper, response);

        }
        
        if(rWrapper.getMethod().equals("GET") && rWrapper.getServletPath().equals("/login")){
        	System.out.println("GET /login 들어옴");
        	chain.doFilter(rWrapper, wrapper);
        	rw.charWrite(response, wrapper);

        }
        
        if(rWrapper.getMethod().equals("POST") && rWrapper.getServletPath().equals("/login")){
        	System.out.println("POST 들어옴");
        	
	        String e_id = request.getParameter("username");
	        String e_pwd = request.getParameter("password");
	        
	        Decoder decoder = Base64.getDecoder();
	        
	        // Decoder#decode(bytes[] src) 
	        byte[] decodedBytesId = decoder.decode(e_id);
	        byte[] decodedBytesPwd = decoder.decode(e_pwd);
	
			// 디코딩한 문자열을 표시
			String d_id = new String(decodedBytesId);
//			System.out.println("d_id: "+d_id);
	
			String d_pwd = new String(decodedBytesPwd);
//			System.out.println("d_pwd: "+d_pwd);
	        
	        rWrapper.setDecryptParameter(request.getParameterMap(), d_id, d_pwd);
	        
	        chain.doFilter(rWrapper, wrapper);
	        
        }
        
	}

}
