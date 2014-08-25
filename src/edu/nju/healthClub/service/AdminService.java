package edu.nju.healthClub.service;

import java.util.ArrayList;

import edu.nju.healthClub.dao.AdminDaoImpl;
import edu.nju.healthClub.model.user.Manager;
import edu.nju.healthClub.model.user.Waiter;

public class AdminService implements AdminServiceInterface{
	private AdminDaoImpl dao = new AdminDaoImpl();
	
	@Override
	public void create(Waiter waiter) {
		dao.save(waiter);
	}
	@Override
	public void create(Manager manager) {
		dao.save(manager);
	}
	@Override
	public void modify(Waiter waiter) {
		dao.update(waiter);
	}
	@Override
	public void modify(Manager manager) {
		dao.update(manager);
	}
	@Override
	public void removeWaiter(int waiterID) {
		dao.deleteWaiter(waiterID);
	}
	@Override
	public void removeManager(int managerID) {
		dao.deleteManager(managerID);
	}
	@Override
	public ArrayList<Waiter> getAllWaiter() {
		return dao.getAllWaiter();
	}
	@Override
	public ArrayList<Manager> getAllManager() {
		return dao.getAllManager();
	}
}
