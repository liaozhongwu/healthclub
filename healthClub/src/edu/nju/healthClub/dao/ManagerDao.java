package edu.nju.healthClub.dao;

import java.util.ArrayList;

import edu.nju.healthClub.model.ActivitySession;
import edu.nju.healthClub.model.Record;
import edu.nju.healthClub.model.user.VIP;

public interface ManagerDao {

	public ArrayList<VIP> getAllVIP();
	public ArrayList<ActivitySession> getAllActivitySession();
	public ArrayList<Record> getAllRecord();
}
