package com.seeds.web.filter;

import java.util.Locale;

public class PruebaLocale {
	
	public static final void main (String args[]) {
		
		Locale aLocale = new Locale.Builder().setLanguage("fr").setRegion("CA").build();

		
		for (String s: Locale.getISOCountries()) {
			System.out.println(s);
		}
		
		System.out.println("Languages:");
		
		for (String s: Locale.getISOLanguages()) {
			System.out.println(s);
		}
	}
	
}
