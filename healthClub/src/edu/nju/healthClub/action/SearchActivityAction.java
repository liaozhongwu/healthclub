package edu.nju.healthClub.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;

import edu.nju.healthClub.model.Activity;
import edu.nju.healthClub.service.UserService;

public class SearchActivityAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserService service = new UserService();
	
	public String execute()
			throws ServletException, IOException {
		if(request.getSession().getAttribute("userID") == null){
			return "noLogin";
		}else{
			String userID = (String)request.getSession().getAttribute("userID");
			String userType = service.getUserType(userID);
			if(userType.equals("Waiter") || userType.equals("VIP")){
				String key = request.getParameter("key");
				ArrayList<Activity> activities = service.getActivityByKey(key);
				if(activities != null){
					request.setAttribute("activities", activities);
					return SUCCESS;
				}else{
					return INPUT;
				}
			}else{
				return userType;
			}
		}
	}
}
