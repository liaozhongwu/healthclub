package edu.nju.healthClub.action;

import java.io.IOException;

import javax.servlet.ServletException;

import edu.nju.healthClub.model.Activity;
import edu.nju.healthClub.service.WaiterService;

public class AddActivityAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private WaiterService service = new WaiterService();

	public String execute()
			throws ServletException, IOException {
		if(request.getSession().getAttribute("userID") == null){
			return "noLogin";
		}else{
			String userType = (String)request.getSession().getAttribute("userType");
			if(userType.equals("Waiter")){
				String name = request.getParameter("name");
				String introduction = request.getParameter("introduction");
				Activity activity = new Activity();
				activity.setName(name);
				activity.setIntroduction(introduction);
				service.create(activity);
				response.sendRedirect("activity.jsp?ID="+activity.getID());
				return null;
			}else{
				return userType;
			}
		}
	}
}
