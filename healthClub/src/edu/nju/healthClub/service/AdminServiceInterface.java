package edu.nju.healthClub.service;

import java.util.ArrayList;

import edu.nju.healthClub.model.user.Manager;
import edu.nju.healthClub.model.user.Waiter;

public interface AdminServiceInterface {
	
	public void create(Waiter waiter);
	public void create(Manager manager);
	public void update(Waiter waiter);
	public void update(Manager manager);
	public void remove(Waiter waiter);
	public void remove(Manager manager);
	public ArrayList<Waiter> getAllWaiter();
	public ArrayList<Manager> getAllManager();
}
