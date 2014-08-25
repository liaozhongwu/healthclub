package edu.nju.healthClub.dao;

import edu.nju.healthClub.model.Payment;
import edu.nju.healthClub.model.user.VIP;

public interface VIPDao extends CommonDao{
	
	public void save(VIP vip);
	public void save(Payment payment);
	public void freeze(int VIPID);
}
