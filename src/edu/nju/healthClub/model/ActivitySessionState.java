package edu.nju.healthClub.model;

import java.sql.Date;
import java.sql.Time;

public enum ActivitySessionState {
	END("已结束"),PROCEED("正进行"),FUTURE("未开始");
	private String string;
	private ActivitySessionState(String string){
		this.string = string;
	}
	public String toString(){
		return this.string;
	}
	public static ActivitySessionState parseState(ActivitySession activitySession){
		String date = activitySession.getDate();
		String starttime = activitySession.getStarttime();
		String endtime = activitySession.getEndtime();
		Date currDate = new Date(System.currentTimeMillis());
		int cd = date.compareTo(currDate.toString());
		if(cd == 0){
			Time currTime = new Time(System.currentTimeMillis());
			int cst = starttime.compareTo(currTime.toString());
			int cet = endtime.compareTo(currTime.toString());
			if(cst > 0) return FUTURE;
			if(cet < 0) return END;
			if(cst <= 0 && cet >= 0) return PROCEED;
		}
		else if(cd > 0){
			return FUTURE;
		}
		else if(cd < 0){
			return END;
		}
		return null;
	}
	public static void main(String[] args) {
		Time currTime = new Time(System.currentTimeMillis());
		System.out.println("21:00".compareTo(currTime.toString()));
	}
}
