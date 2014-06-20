package edu.nju.healthClub.action.ajax;

import java.io.IOException;

import javax.servlet.ServletException;

import edu.nju.healthClub.action.BaseAction;
import edu.nju.healthClub.model.User;
import edu.nju.healthClub.model.user.VIP;
import edu.nju.healthClub.model.user.Waiter;
import edu.nju.healthClub.service.UserService;

public class RemoveAppointmentAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserService service = new UserService();
	
	public String execute()
			throws ServletException, IOException {
		if(request.getSession().getAttribute("userID") != null){
			String userID = (String) request.getSession().getAttribute("userID");
			User user = service.getUserByID(userID);
			if(user instanceof VIP){
				String VIPID = userID;
				String personID = request.getParameter("personID");
				String activityID = request.getParameter("activityID");
				String activitySession = request.getParameter("activitySession");
				service.removeAppointment(VIPID, personID, activityID, activitySession);
				response.getWriter().print("success");
			}else if(user instanceof Waiter){
				String VIPID = request.getParameter("VIPID");
				String personID = request.getParameter("personID");
				String activityID = request.getParameter("activityID");
				String activitySession = request.getParameter("activitySession");
				service.removeAppointment(VIPID, personID, activityID, activitySession);
				response.getWriter().print("success");
			}
		}
		return null;
	}
}
