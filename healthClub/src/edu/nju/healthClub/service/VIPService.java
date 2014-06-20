package edu.nju.healthClub.service;

import edu.nju.healthClub.dao.VIPDaoImpl;
import edu.nju.healthClub.model.Payment;
import edu.nju.healthClub.model.User;
import edu.nju.healthClub.model.user.vip.VIPFamily;
import edu.nju.healthClub.model.user.vip.VIPPerson;

public class VIPService extends UserService implements VIPServiceInterface{
	private VIPDaoImpl dao = new VIPDaoImpl();
	
	@Override
	public void register(User user) {
		// TODO Auto-generated method stub
		if(user instanceof VIPPerson){
			dao.save((VIPPerson) user);
		}else if(user instanceof VIPFamily){
			dao.save((VIPFamily) user);
		}
	}
	
	@Override
	public void create(Payment payment) {
		// TODO Auto-generated method stub
		dao.save(payment);
	}

	@Override
	public void freeze(String VIPID) {
		// TODO Auto-generated method stub
		dao.freeze(VIPID);
	}
}
