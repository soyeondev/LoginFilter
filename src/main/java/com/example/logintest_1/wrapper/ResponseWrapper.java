package com.example.logintest_1.wrapper;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletResponse;
import javax.servlet.ServletResponseWrapper;

public class ResponseWrapper extends ServletResponseWrapper {

	public ResponseWrapper(ServletResponse response) {
		super(response);
		// TODO Auto-generated constructor stub
		
	}
	
	public void charWrite(ServletResponse response, CharResponseWrapper wrapper) {
	    	
	    PrintWriter responseWriter;
	    
		try {
			responseWriter = response.getWriter();
	     
	    if (wrapper.getContentType().contains("text/html")) {
	    	
	        CharArrayWriter charWriter = new CharArrayWriter();
	        String originalContent = wrapper.toString();
	        int indexOfCloseBodyTag = originalContent.indexOf("</body>") ;
	          
	        charWriter.write(originalContent.substring(0, indexOfCloseBodyTag));
	          
	        String copyrightInfo = 
	        		"       <script type=\"text/javascript\">\r\n" + 
	        		"       window.onload = function() {\r\n" + 
	        		"        	document.getElementById(\"l_button\").onclick = function() {\r\n" + 
	        		"            	var id = document.getElementById(\"id\").value;\r\n" + 
	        		"            	var pwd = document.getElementById(\"pwd\").value;\r\n" + 
	        		"       		document.getElementById(\"id\").value = btoa(id); 	\r\n" + 
	        		"       		document.getElementById(\"pwd\").value = btoa(pwd);}}\r\n" + 
	        		"       </script>";
	//        String closeHTMLTags = "</body></html>";
	         
	        charWriter.write(copyrightInfo);
	//        charWriter.write(closeHTMLTags);
	          
	        String alteredContent = charWriter.toString();
	        
	        response.setContentLength(alteredContent.length());
	        responseWriter.write(alteredContent);  
	        
	    }
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
