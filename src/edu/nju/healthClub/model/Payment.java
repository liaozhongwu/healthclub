package edu.nju.healthClub.model;

import edu.nju.healthClub.model.user.VIP;
public class Payment {
	private int ID;
	private VIP VIP;
	private String date;
	private int bankCard;
	private int payment;
	private String remark;
	
	public VIP getVIP() {
		return VIP;
	}
	public void setVIP(VIP VIP) {
		this.VIP = VIP;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getBankCard() {
		return bankCard;
	}
	public void setBankCard(int bankCard) {
		this.bankCard = bankCard;
	}
	public int getPayment() {
		return payment;
	}
	public void setPayment(int payment) {
		this.payment = payment;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	
	
}
