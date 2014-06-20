package edu.nju.healthClub.model.user;

import edu.nju.healthClub.model.User;

public class InvalidUser extends User{
	private String infomation;
	
	private InvalidUser(String info){
		setInfomation(info);
		setPassword(null);
	}
	public static InvalidUser invalidUser(String info){
		return new InvalidUser(info);
	}
	public String getInfomation() {
		return infomation;
	}
	private void setInfomation(String infomation) {
		this.infomation = infomation;
	}
}
