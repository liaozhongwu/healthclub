package edu.nju.healthClub.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchHelper {
	public String getUserSearchType(String key){
		Pattern pat = Pattern.compile("^\\d*$");  
		Matcher mat = pat.matcher(key);
		if(mat.find()){
			return "ID";
		}else{
			return "username";
		}
	}
}
