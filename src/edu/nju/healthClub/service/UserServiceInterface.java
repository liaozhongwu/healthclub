package edu.nju.healthClub.service;

import edu.nju.healthClub.model.user.User;

public interface UserServiceInterface {
	public User validateUser(int ID, String password);
	public User validateUser(String username,String password);
	public User getUser(int username);
	public User getUser(String username);
}
