package edu.nju.healthClub.helper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CurrTime {
	public static String getCurrTime(String format){
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}
}
