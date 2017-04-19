package com.info.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.info.wrapper.MyHttpServletRequestWrapper;

public class HttpServletRequestReplacedFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		ServletRequest requestWrapper = null;  
		if(request instanceof HttpServletRequest) {  
			requestWrapper = new MyHttpServletRequestWrapper((HttpServletRequest) request);  
		}  
		if(null == requestWrapper) {
			
			chain.doFilter(request, response);
			
		} else {
			
			chain.doFilter(requestWrapper, response);
		}  
		
	}

	public void destroy() {
		// TODO Auto-generated method stub
		
	}  
} 