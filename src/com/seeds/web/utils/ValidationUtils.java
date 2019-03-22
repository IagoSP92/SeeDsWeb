package com.seeds.web.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mysql.cj.util.StringUtils;
import com.seeds.web.model.ErrorCodes;
import com.seeds.web.model.ErrorManager;

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
	
	
	public static String validMail (ErrorManager errors, String email, String parameter, Boolean required) {
		
		if(StringUtils.isEmptyOrWhitespaceOnly(email)) {
			if(required) {
				errors.add(parameter, ErrorCodes.MANDATORY_PARAMETER);
			}
			return null;
		}
		
		if(emailIsValid(email)) {
			return email;
		} else {
			errors.add(parameter, ErrorCodes.INVALID_EMAIL);
			return null;
		}
	}
	
	public static String validPass (ErrorManager errors, String pass, String parameter, Boolean required) {
		
		if(StringUtils.isEmptyOrWhitespaceOnly(pass)) {
			if(required) {
				errors.add(parameter, ErrorCodes.MANDATORY_PARAMETER);
			}
			return null;
		}
		
		if(passIsValid(pass)) {
			return pass;
		} else {
			errors.add(parameter, ErrorCodes.INVALID_PASSWORD);
			return null;
		}
	}
	
	
	
	private static boolean passIsValid (String pass) {

		return true;
	}
	
	
	private static boolean emailIsValid (String email) {
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		Pattern p = Pattern.compile(ePattern);
		Matcher m = p.matcher(email);
		return m.matches();
	}
	

}
