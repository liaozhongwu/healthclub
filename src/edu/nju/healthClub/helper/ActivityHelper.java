package edu.nju.healthClub.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ActivityHelper {
	public static boolean validateID(String ID){
		if(ID != null){
			Pattern pat = Pattern.compile("^\\d+$");  
			Matcher mat = pat.matcher(ID);
			if(mat.find()){
				return true;
			}
		}
		return false;
	}
	public static boolean validateContent(String ID){
		if(ID != null && !ID.equals("")){
			return true;
		}
		return false;
	}
	public static boolean validateIndex(String index){
		if(index != null){
			Pattern pat = Pattern.compile("^\\d+$");  
			Matcher mat = pat.matcher(index);
			if(mat.find()){
				return true;
			}
		}
		return false;
	}
	public static boolean validateDate(String date){
		if(date != null){
			Pattern pat = Pattern.compile("^(19\\d{2}|20(0\\d|1[0-4]))-((01|03|05|07|08|10|12)-(0\\d|(1|2)\\d|3(0|1))|(04|06|09|11)-(0\\d|(1|2)\\d|30)|02-(0\\d|1\\d|2[0-8]))$");  
			Matcher mat = pat.matcher(date);
			if(mat.matches()){
				return true;
			}
		}
		return false;
	}
}
