package edu.nju.healthClub.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;

import edu.nju.healthClub.helper.ActivityHelper;
import edu.nju.healthClub.model.Activity;
import edu.nju.healthClub.model.ActivitySession;
import edu.nju.healthClub.model.Coach;
import edu.nju.healthClub.service.WaiterService;

public class CreateActivityAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private WaiterService service = new WaiterService();
	private ActivityHelper helper = new ActivityHelper();
	
	public String execute()
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getSession().getAttribute("userID") == null){
			return "noLogin";
		}else{
			String userID = (String)request.getSession().getAttribute("userID");
			String userType = service.getUserType(userID);
			if(userType.equals("Waiter")){
				String name = request.getParameter("name");
				String introduction = request.getParameter("introduction");
				String[] activitySessions = request.getParameterValues("activitySession");
				String[] dates = request.getParameterValues("date");
				String[] starttimes = request.getParameterValues("starttime");
				String[] endtimes = request.getParameterValues("endtime");
				String[] places = request.getParameterValues("place");
				String[] coachs = request.getParameterValues("coach");
				Activity activity = new Activity();
				activity.setName(name);
				activity.setIntroduction(introduction);
				ArrayList<ActivitySession> activitySessionList = new ArrayList<ActivitySession>();
				ActivitySession activitySessionItem;
				for(int i = 0 ; i < activitySessions.length ; i++){
					activitySessionItem = new ActivitySession();
					activitySessionItem.setSession(helper.parseSession(activitySessions[i]));
					activitySessionItem.setDate(helper.parseDate(dates[i]));
					activitySessionItem.setStarttime(starttimes[i]);
					activitySessionItem.setEndtime(endtimes[i]);
					activitySessionItem.setPlace(places[i]);
					ArrayList<String> coachIDs = helper.parseCoachIDs(coachs[i]);
					ArrayList<Coach> coachList = new ArrayList<Coach>();
					Coach coach;
					if(coachIDs != null){
						for(String tem : coachIDs){
							coach = service.getCoachByID(tem);
							if(coach != null)coachList.add(coach);
						}
					}
					activitySessionItem.setCoachs(coachList);
					activitySessionList.add(activitySessionItem);
				}
				activity.setSessions(activitySessionList);
				
				service.create(activity);
				request.setAttribute("activity", activity);
				return SUCCESS;
			}else{
				return userType;
			}
		}
	}
}
