package edu.nju.healthClub.model.user;


public class Person {
	private int ID;
	private VIP VIP;
	private String name;
	private String sex;
	private String birthday;
	private String address;
	private String telephone;
	private String email;
	private String resident;

	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public VIP getVIP() {
		return VIP;
	}
	public void setVIP(VIP VIP) {
		this.VIP = VIP;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
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
	public String getResident() {
		return resident;
	}
	public void setResident(String resident) {
		this.resident = resident;
	}
}
