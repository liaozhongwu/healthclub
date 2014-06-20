package edu.nju.healthClub.model;

import java.util.Date;

public class Payment {
	private int ID;
	private int VIPID;
	private Date date;
	private int bankCard;
	private int payment;
	private String remark;
	
	public int getVIPID() {
		return VIPID;
	}
	public void setVIPID(int VIPID) {
		this.VIPID = VIPID;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
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
