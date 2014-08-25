package edu.nju.healthClub.service;

import edu.nju.healthClub.model.Payment;
import edu.nju.healthClub.model.user.VIP;

public interface VIPServiceInterface extends CommonServiceInterface{
	
	public void register(VIP vip);
	public void create(Payment payment);
	public void freeze(int VIPID);
}
