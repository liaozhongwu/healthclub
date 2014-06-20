package edu.nju.healthClub.helper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ActivityHelper {
	public int parseCoachID(String ID){
		if(ID != null && !ID.equals("")){
			Pattern pat = Pattern.compile("^\\d+$");  
			Matcher mat = pat.matcher(ID);
			if(mat.find()){
				return Integer.parseInt(ID);
			}
		}
		return -1;
	}
	public int parseSession(String session){
		if(session != null && !session.equals("")){
			Pattern pat = Pattern.compile("^\\d+$");  
			Matcher mat = pat.matcher(session);
			if(mat.find()){
				return Integer.parseInt(session);
			}
		}
		return -1;
	}
	public Date parseDate(String date){
		if(date != null && !date.equals("")){
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			try {
				return format.parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	public ArrayList<String> parseCoachIDs(String coach){
		if(coach != null && !coach.equals("")){
			ArrayList<String> results = new ArrayList<String>();
			Pattern pat = Pattern.compile("[\\d]+");  
			Matcher mat = pat.matcher(coach);
			while(mat.find()){
				results.add(mat.group());
			}
			return results;
		}
		return null;
	}
	public int parseActivityID(String ID) {
		// TODO Auto-generated method stub
		if(ID != null && !ID.equals("")){
			Pattern pat = Pattern.compile("^\\d+$");  
			Matcher mat = pat.matcher(ID);
			if(mat.find()){
				return Integer.parseInt(ID);
			}
		}
		return -1;
	}
}
