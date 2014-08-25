package edu.nju.healthClub.model.user;



public class VIP extends User{
	private VIPType type;
	private int balance;
	private String registerDate;
	private VIPState state;

	public VIPType getType() {
		return type;
	}
	public void setType(VIPType type) {
		this.type = type;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public String getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}
	public VIPState getState() {
		return state;
	}
	public void setState(VIPState state) {
		this.state = state;
	}
}
