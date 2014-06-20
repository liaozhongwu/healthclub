package edu.nju.healthClub.service;

import java.util.ArrayList;

import edu.nju.healthClub.dao.AdminDaoImpl;
import edu.nju.healthClub.model.user.Manager;
import edu.nju.healthClub.model.user.Waiter;

public class AdminService implements AdminServiceInterface{
	private AdminDaoImpl dao = new AdminDaoImpl();
	
	@Override
	public void create(Waiter waiter) {
		// TODO Auto-generated method stub
		dao.save(waiter);
	}
	@Override
	public void create(Manager manager) {
		// TODO Auto-generated method stub
		dao.save(manager);
	}
	@Override
	public void update(Waiter waiter) {
		// TODO Auto-generated method stub
		dao.update(waiter);
	}
	@Override
	public void update(Manager manager) {
		// TODO Auto-generated method stub
		dao.update(manager);
	}
	@Override
	public void remove(Waiter waiter) {
		// TODO Auto-generated method stub
		dao.removeWaiter(Integer.toString(waiter.getID()));
	}
	@Override
	public void remove(Manager manager) {
		// TODO Auto-generated method stub
		dao.removeManager(Integer.toString(manager.getID()));
	}
	@Override
	public ArrayList<Waiter> getAllWaiter() {
		// TODO Auto-generated method stub
		return dao.getAllWaiter();
	}
	@Override
	public ArrayList<Manager> getAllManager() {
		// TODO Auto-generated method stub
		return dao.getAllManager();
	}
}
