package edu.nju.healthClub.action.ajax;

import java.io.IOException;

import javax.servlet.ServletException;

import edu.nju.healthClub.action.BaseAction;
import edu.nju.healthClub.helper.ActivityHelper;
import edu.nju.healthClub.service.WaiterService;

public class RemoveActivitySessionAction extends BaseAction{

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
				String activitySessionID = request.getParameter("activitySessionID");
				if(ActivityHelper.validateID(activitySessionID)){
					service.removeActivitySession(Integer.parseInt(activitySessionID));
					response.getWriter().write("success");
				}else{
					response.getWriter().write("invalidID");
				}
			}else{
				response.getWriter().write("noAuthority");
			}
		}
		return null;
	}
}

