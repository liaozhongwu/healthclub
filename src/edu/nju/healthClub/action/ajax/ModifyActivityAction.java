package edu.nju.healthClub.action.ajax;

import java.io.IOException;

import javax.servlet.ServletException;

import edu.nju.healthClub.action.BaseAction;
import edu.nju.healthClub.helper.ActivityHelper;
import edu.nju.healthClub.model.Activity;
import edu.nju.healthClub.service.WaiterService;

public class ModifyActivityAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private WaiterService service = new WaiterService();
	
	public String execute()
			throws ServletException, IOException {
		if(request.getSession().getAttribute("userID") != null){
			String activityID = request.getParameter("activityID");
			String name = request.getParameter("name");
			String introduction = request.getParameter("introduction");
			String userType = (String)request.getSession().getAttribute("userType");
			if(userType.equals("Waiter")){
				if(ActivityHelper.validateID(activityID) &&
					ActivityHelper.validateContent(name) &&
					ActivityHelper.validateContent(introduction)
				){
					Activity activity = service.getActivityByID(Integer.parseInt(activityID));
					activity.setName(name);
					activity.setIntroduction(introduction);
					service.modify(activity);
					response.getWriter().print("success");
				}
				else{
					response.getWriter().print("invalid data");
				}
			}
		}
		return null;
	}
}

