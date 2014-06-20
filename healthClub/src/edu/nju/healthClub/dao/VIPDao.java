package edu.nju.healthClub.dao;

import edu.nju.healthClub.model.Payment;

public interface VIPDao {
	public void save(Payment payment);
	public void freeze(String VIPID);
}
