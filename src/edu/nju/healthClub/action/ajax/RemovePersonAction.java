package edu.nju.healthClub.action.ajax;

import java.io.IOException;

import javax.servlet.ServletException;

import edu.nju.healthClub.action.BaseAction;
import edu.nju.healthClub.service.CommonService;

public class RemovePersonAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CommonService service = new CommonService();
	
	public String execute()
			throws ServletException, IOException {
		if(request.getSession().getAttribute("userID") != null){
			String personID = request.getParameter("personID");
			String userType = (String)request.getSession().getAttribute("userType");
			if(userType.equals("VIP") || userType.equals("Waiter")){
				service.removePerson(Integer.parseInt(personID));
			}
		}
		return null;
	}
}

