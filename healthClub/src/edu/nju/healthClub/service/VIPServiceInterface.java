package edu.nju.healthClub.service;

import edu.nju.healthClub.model.Payment;
import edu.nju.healthClub.model.User;

public interface VIPServiceInterface {
	
	public void register(User user);
	public void create(Payment payment);
	public void freeze(String VIPID);
}
