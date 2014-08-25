package edu.nju.healthClub.service;

import java.util.ArrayList;

import edu.nju.healthClub.model.user.Manager;
import edu.nju.healthClub.model.user.Waiter;

public interface AdminServiceInterface {
	
	public void create(Waiter waiter);
	public void create(Manager manager);
	public void modify(Waiter waiter);
	public void modify(Manager manager);
	public void removeWaiter(int waiterID);
	public void removeManager(int managerID);
	public ArrayList<Waiter> getAllWaiter();
	public ArrayList<Manager> getAllManager();
}
