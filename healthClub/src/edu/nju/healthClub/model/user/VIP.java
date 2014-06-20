package edu.nju.healthClub.model.user;

import java.util.Date;

import edu.nju.healthClub.model.User;


public abstract class VIP extends User{
	private int balance;
	private Date registerDate;
	private String state;

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
