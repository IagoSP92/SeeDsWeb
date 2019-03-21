package com.seeds.web.utils;

import com.mysql.cj.util.StringUtils;

public class ValidationUtils {
	
public static Long validateLong (String longAntes) {
		
		Long longDespues = null;
		
		if(!StringUtils.isEmptyOrWhitespaceOnly(longAntes)) {
			longDespues= Long.parseLong(longAntes);
		} else {
			// AÑADIR ERROR
		}
		
		return longDespues;
	}
	
	public static String validateString (String stringAntes) {
		
		String stringDespues = null;
		
		if(!StringUtils.isEmptyOrWhitespaceOnly(stringAntes)) {
			stringDespues= stringAntes.trim();
		} else {
			// AÑADIR ERROR
		}
		
		return stringDespues;
	}
	
	public static Integer validateInt (String intAntes) {
		
		Integer intDespues = null;
		
		if(!StringUtils.isEmptyOrWhitespaceOnly(intAntes)) {
			intDespues= Integer.parseInt(intAntes);
		} else {
			// AÑADIR ERROR
		}
		
		return intDespues;
	}

}
