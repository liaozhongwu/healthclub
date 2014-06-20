package edu.nju.healthClub.dao;

import java.util.ArrayList;

import edu.nju.healthClub.model.Payment;
import edu.nju.healthClub.model.user.Manager;
import edu.nju.healthClub.model.user.VIP;
import edu.nju.healthClub.model.user.Waiter;

public interface AdminDao{
	public void save(Waiter waiter);
	public void save(Manager manager);
	public void removeWaiter(String ID);
	public void removeManager(String ID);
	public void update(Waiter waiter);
	public void update(Manager manager);
	public ArrayList<Waiter> getAllWaiter();
	public ArrayList<Manager> getAllManager();

	public ArrayList<VIP> getAllVIP();	
	public int findNoPayment(String VIPID);
	public void saveNoPayment(String VIPID);
	public void save(Payment payment);
	public void updateState(VIP vip);
	public void updateBalance(VIP vip);
	public void updateVIPState(String VIPID,String State);
	
}
