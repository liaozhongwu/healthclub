package edu.nju.healthClub.action;

import java.io.IOException;

import javax.servlet.ServletException;

import edu.nju.healthClub.model.Activity;
import edu.nju.healthClub.service.UserService;

public class ViewActivityAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserService service = new UserService();
	
	public String execute()
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String ID = request.getParameter("ID");
		Activity activity = service.getActivityByID(ID);
		if(activity != null){
			request.setAttribute("activity", activity);
			return SUCCESS;
		}else{
			return null;
		}
	}
}
