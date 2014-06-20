package edu.nju.healthClub.model;

import java.util.Date;

public class Person {
	
	private int ID;
	private String petname;
	private String sex;
	private String address;
	private String telephone;
	private String email;
	private String realname;
	private String residentID;
	private Date birthday;

	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getPetname() {
		return petname;
	}
	public void setPetname(String petname) {
		this.petname = petname;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getResidentID() {
		return residentID;
	}
	public void setResidentID(String residentID) {
		this.residentID = residentID;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public int getAge() {
		return (int)(System.currentTimeMillis() - birthday.getTime()) % (1000 * 60 * 60 * 24 * 365);
	}

}
