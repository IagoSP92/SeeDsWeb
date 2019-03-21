package com.seeds.web.utils;

import javax.servlet.http.HttpServletRequest;

public class ParameterUtils {
	
	public static final String getParameter(HttpServletRequest request, String name) {	
		String value = (String) request.getParameter(name);							
		if (value==null) value = "";
		return value;
	}

}
