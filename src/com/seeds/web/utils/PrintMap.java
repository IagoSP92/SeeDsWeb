package com.seeds.web.utils;



import java.util.Map;

public class PrintMap {
	
	public static String printStringMap (Map<String,  String[]> mapa) {
		
		StringBuilder cadena = new StringBuilder();
		String [] values = null;
		
		for(String esta: mapa.keySet() ) {
			
			cadena.append(esta).append("=[");
			cadena.append(esta.toString());
			values = mapa.get(esta);
			
			for(int i =0; i<values.length; i++) {
				
				cadena.append(values[i]).append(", ");
			}
			
			cadena.append(values[values.length-1]);
			cadena.append("}");
		}
		
		return cadena.toString();		
	}

}
