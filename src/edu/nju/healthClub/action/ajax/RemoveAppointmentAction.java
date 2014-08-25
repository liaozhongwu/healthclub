package edu.nju.healthClub.action.ajax;

import java.io.IOException;

import javax.servlet.ServletException;

import edu.nju.healthClub.action.BaseAction;
import edu.nju.healthClub.helper.AccountHelper;
import edu.nju.healthClub.helper.ActivityHelper;
import edu.nju.healthClub.service.CommonService;

public class RemoveAppointmentAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CommonService service = new CommonService();
	
	public String execute()
			throws ServletException, IOException {
		if(request.getSession().getAttribute("userID") != null){
			String personID = request.getParameter("personID");
			String activitySessionID = request.getParameter("activitySessionID");
			if(AccountHelper.validateID(personID) && ActivityHelper.validateID(activitySessionID)){
				service.removeAppointment(Integer.parseInt(personID), Integer.parseInt(activitySessionID));
				response.getWriter().print("success");
			}
		}
		return null;
	}
}
