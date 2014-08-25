package edu.nju.healthClub.dao;

import java.util.ArrayList;

import edu.nju.healthClub.model.Payment;
import edu.nju.healthClub.model.user.Manager;
import edu.nju.healthClub.model.user.VIP;
import edu.nju.healthClub.model.user.Waiter;

public interface AdminDao extends UserDao{
	public void save(Waiter waiter);
	public void save(Manager manager);
	public void deleteWaiter(int waiterID);
	public void deleteManager(int managerID);
	public void update(Waiter waiter);
	public void update(Manager manager);
	public ArrayList<Waiter> getAllWaiter();
	public ArrayList<Manager> getAllManager();

	public ArrayList<VIP> getAllVIP();	
	public int searchNoPayment(int VIPID);
	public void saveNoPayment(int VIPID);
	public void save(Payment payment);
	public void updateState(VIP vip);
	public void updateBalance(VIP vip);
	
}
