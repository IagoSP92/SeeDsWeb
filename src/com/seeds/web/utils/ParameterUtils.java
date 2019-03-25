package com.seeds.web.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.seeds.web.controller.ConstantValues;

public class ParameterUtils {
	
	public static String URLBuilder (String url, Map<String, String> valores) throws UnsupportedEncodingException {
		int cont = 1;
		StringBuilder urlBuilder = new StringBuilder();
		urlBuilder.append(url);
		for(String mapKey: valores.keySet()) {
			if(cont == 1) urlBuilder.append(ConstantValues.QUESTION_MARK);
			urlBuilder.append(URLEncoder.encode((mapKey).trim(), ConstantValues.ENCODING))
				.append(ConstantValues.EQUAL)
				.append(URLEncoder.encode((valores.get(mapKey)).trim(), ConstantValues.ENCODING));
			if(cont != valores.size()) {
				urlBuilder.append(ConstantValues.AMPERSAND_URL);
			}
			cont++;
		}
		return urlBuilder.toString();
	}
	
	
	public static final String getParameter(HttpServletRequest request, String name) {	
		String value = (String) request.getParameter(name);							
		if (value==null) value = "";
		return value;
	}

}
