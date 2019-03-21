package com.seeds.web.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	DateFormat df = null;

	public DateUtils () {

		df = new SimpleDateFormat("dd/MM/yyyy");
	}

	public Date dateFormat (String fechaAntes) {

		Date fechaDespues = null;

		if(fechaAntes != null) {

			try {
				fechaDespues = df.parse(fechaAntes);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return fechaDespues;
	}
}
