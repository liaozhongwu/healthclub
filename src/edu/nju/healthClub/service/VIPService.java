package edu.nju.healthClub.service;

import edu.nju.healthClub.dao.VIPDaoImpl;
import edu.nju.healthClub.model.Payment;
import edu.nju.healthClub.model.user.VIP;

public class VIPService extends CommonService implements VIPServiceInterface{
	private VIPDaoImpl dao = new VIPDaoImpl();
	
	@Override
	public void register(VIP vip) {
		dao.save(vip);
	}
	@Override
	public void create(Payment payment) {
		dao.save(payment);
	}

	@Override
	public void freeze(int VIPID) {
		dao.freeze(VIPID);
	}
}
