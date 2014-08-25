package edu.nju.healthClub.action.ajax;

import java.io.IOException;

import javax.servlet.ServletException;

import edu.nju.healthClub.action.BaseAction;
import edu.nju.healthClub.helper.AccountHelper;
import edu.nju.healthClub.service.CommonService;

public class AddAppointmentAction extends BaseAction{

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
			if(AccountHelper.validateID(personID) && AccountHelper.validateID(activitySessionID)){
				if(service.findAppointment(Integer.parseInt(personID), Integer.parseInt(activitySessionID)) == null){
					service.createAppointment(Integer.parseInt(personID), Integer.parseInt(activitySessionID));
					response.getWriter().write("success");
				}
			}
		}
		return null;
	}
}

