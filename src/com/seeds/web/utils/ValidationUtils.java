package com.seeds.web.utils;

import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.isp.seeds.service.PaisServiceImpl;
import com.isp.seeds.service.spi.PaisService;
import com.mysql.cj.util.StringUtils;
import com.seeds.web.model.ErrorCodes;
import com.seeds.web.model.ErrorManager;

public class ValidationUtils {
	
	public static Boolean validCheck (ErrorManager errors, String check, String parameter, Boolean required) {
		
		Boolean booleanDespues = null;
		if(check==null) {check="False";}
		if(check=="on") {check="True";}
		
		if(!StringUtils.isEmptyOrWhitespaceOnly(check)) {
			booleanDespues= Boolean.parseBoolean(check);
		} else {
			if(required) {
				errors.add(parameter, ErrorCodes.MANDATORY_PARAMETER);
			}
			// AÑADIR ERROR
		}		
		return booleanDespues;
	}
	
	public static Boolean validBoolean (ErrorManager errors, String booleanAntes, String parameter, Boolean required) {
		
		Boolean booleanDespues = null;
		
		if(!StringUtils.isEmptyOrWhitespaceOnly(booleanAntes)) {
			booleanDespues= Boolean.parseBoolean(booleanAntes);
		} else {
			if(required) {
				errors.add(parameter, ErrorCodes.MANDATORY_PARAMETER);
			}
			// AÑADIR ERROR
		}		
		return booleanDespues;
	}
	
	public static Long validLong (ErrorManager errors, String longAntes, String parameter, Boolean required) {
			
			Long longDespues = null;
			
			if(!StringUtils.isEmptyOrWhitespaceOnly(longAntes)) {
				longDespues= Long.parseLong(longAntes);
			} else {
				if(required) {
					errors.add(parameter, ErrorCodes.MANDATORY_PARAMETER);
				}
				// AÑADIR ERROR
			}
			
			return longDespues;
		}
	
	public static Double validDouble (ErrorManager errors, String doubleAntes, String parameter, Boolean required) {
		
		Double doubleDespues = null;
		
		if(!StringUtils.isEmptyOrWhitespaceOnly(doubleAntes)) {
			doubleDespues= Double.parseDouble(doubleAntes);
		} else {
			if(required) {
				errors.add(parameter, ErrorCodes.MANDATORY_PARAMETER);
			}
			// AÑADIR ERROR
		}	
		return doubleDespues;
	}
	
	public static Integer validInt (ErrorManager errors, String intAntes, String parameter, Boolean required) {
		
		Integer intDespues = null;
		
		if(!StringUtils.isEmptyOrWhitespaceOnly(intAntes)) {
			intDespues= Integer.parseInt(intAntes);
		} else {
			if(required) {
				errors.add(parameter, ErrorCodes.MANDATORY_PARAMETER);
			}
			// AÑADIR ERROR
		}	
		return intDespues;
	}

	
	public static String validPais (ErrorManager errors, String pais, String parameter, Boolean required, List<String> paises) {
		pais= pais.trim();
		
		if(StringUtils.isEmptyOrWhitespaceOnly(pais)) {
			if(required) {
				errors.add(parameter, ErrorCodes.MANDATORY_PARAMETER);
			}
			return null;
		}
		
		if(paisIsValid(pais, paises)) {
			return pais;
		} else {
			errors.add(parameter, ErrorCodes.INVALID_COUNTRY);
			return null;
		}
		
	}

	
	public static String validString (ErrorManager errors, String cadenaCaracteres, String parameter, Boolean required) {
		
		cadenaCaracteres = cadenaCaracteres.trim();
		
		if(StringUtils.isEmptyOrWhitespaceOnly(cadenaCaracteres)) {
			if(required) {
				errors.add(parameter, ErrorCodes.MANDATORY_PARAMETER);
			}
			return null;
		}
		
		if(stringIsValid(cadenaCaracteres)) {
			return cadenaCaracteres;
		} else {
			errors.add(parameter, ErrorCodes.INVALID_PARAMETER);
			return null;
		}
	}
	
	
	public static String validMail (ErrorManager errors, String email, String parameter, Boolean required) {
		email= email.trim();
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
		pass= pass.trim();
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
	
	public static Date validDate (ErrorManager errors, String date, String parameter, Boolean required, DateUtils dateUtils) {

			date=date.trim();
			if(StringUtils.isEmptyOrWhitespaceOnly(date)) {
				if(required) {
					errors.add(parameter, ErrorCodes.MANDATORY_PARAMETER);
				}
				return null;
			}
			
			return dateUtils.dateFormat(date);
	}
	
	
	
	private static boolean paisIsValid (String pais, List<String> paises) {
		if(paises.contains(pais)) {
			System.out.println(pais);
			return true;
		}
		else {
			return false;
		}
	}
	
	private static boolean passIsValid (String pass) {

		return true;
	}
	
	private static boolean stringIsValid (String cadena) {

		return true;
	}
	
	
	private static boolean emailIsValid (String email) {

		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		Pattern p = Pattern.compile(ePattern);
		Matcher m = p.matcher(email);
		return m.matches();
	}
	

}
