package edu.nju.healthClub.model.user;

public enum VIPType {
	SINGLE("个人用户"),FAMILY("家庭用户");
	private String string;
	private VIPType(String string){
		this.string = string;
	}
	public String toString(){
		return this.string;
	}
}
