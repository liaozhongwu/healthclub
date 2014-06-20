package edu.nju.healthClub.action;

import java.io.IOException;

import javax.servlet.ServletException;

import edu.nju.healthClub.model.user.Manager;
import edu.nju.healthClub.service.AdminService;
import edu.nju.healthClub.service.UserService;

public class RemoveManagerAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserService();
	private AdminService adminService = new AdminService();
	
	public String execute()
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getSession().getAttribute("userID") == null){
			return "noLogin";
		}else{
			String userID = (String)request.getSession().getAttribute("userID");
			String userType = userService.getUserType(userID);
			if(userType.equals("Admin")){
				String ID = request.getParameter("ID");
				
				Manager manager = new Manager();
				manager.setID(Integer.parseInt(ID));
				
				adminService.remove(manager);
				return SUCCESS;
			}else{
				return userType;
			}
		}
	}
}
