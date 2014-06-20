package edu.nju.healthClub.action.ajax;

import java.io.IOException;

import javax.servlet.ServletException;

import edu.nju.healthClub.action.BaseAction;
import edu.nju.healthClub.service.WaiterService;

public class RemoveRecordAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private WaiterService service = new WaiterService();
	
	public String execute()
			throws ServletException, IOException {
		String VIPID = request.getParameter("VIPID");
		String personID = request.getParameter("personID");
		String activityID = request.getParameter("activityID");
		String activitySession = request.getParameter("activitySession");
		
		service.removeRecord(VIPID, personID, activityID, activitySession);
		response.getWriter().print("success");
		return null;
	}
}
