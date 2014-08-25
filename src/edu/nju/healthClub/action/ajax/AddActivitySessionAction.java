package edu.nju.healthClub.action.ajax;

import java.io.IOException;

import javax.servlet.ServletException;

import edu.nju.healthClub.action.BaseAction;
import edu.nju.healthClub.helper.ActivityHelper;
import edu.nju.healthClub.model.Activity;
import edu.nju.healthClub.model.ActivitySession;
import edu.nju.healthClub.model.Coach;
import edu.nju.healthClub.model.Place;
import edu.nju.healthClub.service.WaiterService;

public class AddActivitySessionAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private WaiterService service = new WaiterService();
	
	public String execute()
			throws ServletException, IOException {
		if(request.getSession().getAttribute("userID") != null){
			String userType = (String)request.getSession().getAttribute("userType");
			if(userType.equals("Waiter")){
				String activityID = request.getParameter("activityID");
				String name = request.getParameter("name");
				String date = request.getParameter("date");
				String starttime = request.getParameter("starttime");
				String endtime = request.getParameter("endtime");
				String placeID = request.getParameter("placeID");
				String coachID = request.getParameter("coachID");
				if(ActivityHelper.validateID(activityID) &&
					ActivityHelper.validateContent(name) &&
					ActivityHelper.validateDate(date) &&
					ActivityHelper.validateID(placeID) &&
					ActivityHelper.validateID(coachID)
				){
					Activity activity = service.getActivityByID(Integer.parseInt(activityID));
					ActivitySession activitySession = new ActivitySession();
					activitySession.setActivity(activity);
					activitySession.setName(name);
					activitySession.setDate(date);
					activitySession.setStarttime(starttime);
					activitySession.setEndtime(endtime);
					activitySession.setPlace(new Place(Integer.parseInt(placeID)));
					activitySession.setCoach(new Coach(Integer.parseInt(coachID)));
					service.add(activitySession);
				}
			}
		}
		return null;
	}
}

