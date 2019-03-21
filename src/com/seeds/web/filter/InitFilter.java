package com.seeds.web.filter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Locale;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@WebFilter("/*")
public class InitFilter implements Filter {
	
	private static Logger logger = LogManager.getLogger(InitFilter.class);

    public InitFilter() {
    	
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
				
		Enumeration<String> headerNames = httpRequest.getHeaderNames();
		
		while(headerNames.hasMoreElements()) {
			String hName= headerNames.nextElement();
			logger.debug("Header: {} = {}",hName, httpRequest.getHeader(hName));
		}
		
		Locale locale = new Locale.Builder().setLanguage("fr").setRegion("CA").build();
		locale= Locale.GERMANY;
		logger.debug("Locale: {} =", locale.toString());
		
		String paxinaRafa = "/html/rafa.jsp";
		String ipRafa = "10.53.124.205";
		
		if(httpRequest.getRemoteAddr().equals(ipRafa) && !httpRequest.getServletPath().contains(paxinaRafa)) {
			
			logger.debug("Entrou ip: "+ httpRequest.getRemoteAddr());
			httpResponse.sendRedirect(httpRequest.getContextPath()+"/html/rafa.jsp");
			
		} else {
			
			logger.debug("Entrou ip: "+ httpRequest.getRemoteAddr());
			chain.doFilter(httpRequest, httpResponse);
		
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
