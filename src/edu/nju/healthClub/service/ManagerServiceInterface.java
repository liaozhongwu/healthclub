package edu.nju.healthClub.service;

import java.util.ArrayList;

public interface ManagerServiceInterface {
	
	public ArrayList<String> getPersonAges();
	public ArrayList<String> getPersonSexs();
	public ArrayList<String> getPersonAddresses();
	public ArrayList<String> getVIPStates();
	public ArrayList<String> getRecordsByDay();
	public ArrayList<String> getRecordsByMonth();
	public ArrayList<String> getPlacesUsage();
	public ArrayList<String> getCoachsUsage();
}
