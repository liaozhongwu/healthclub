package edu.nju.healthClub.service;

import edu.nju.healthClub.dao.UserDaoImpl;
import edu.nju.healthClub.model.user.User;

public class UserService implements UserServiceInterface{

	private UserDaoImpl dao = new UserDaoImpl(); 
	@Override
	public User validateUser(int ID, String password) {
		User user = dao.findUser(ID);
		if(user != null && user.getPassword().equals(password))
			return user;
		else
			return null;
	}

	@Override
	public User validateUser(String username, String password) {
		User user = dao.findUser(username);
		if(user.getPassword().equals(password))
			return user;
		else
			return null;
	}

	@Override
	public User getUser(String username) {
		return dao.findUser(username);
	}
	@Override
	public User getUser(int ID) {
		return dao.findUser(ID);
	}

}
