package edu.nju.healthClub.action.data;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;

import edu.nju.healthClub.action.BaseAction;
import edu.nju.healthClub.service.ManagerService;

public class PlaceUsageAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ManagerService service = new ManagerService();
	public String execute()
			throws ServletException, IOException {
		ArrayList<String> places = service.getPlacesUsage();
		ArrayList<String> results = new ArrayList<String>();
		for(String place : places){
			results.add(new String(place.getBytes("utf-8"),"ISO-8859-1"));
		}
		response.getWriter().print(results);
		return null;
	}
}