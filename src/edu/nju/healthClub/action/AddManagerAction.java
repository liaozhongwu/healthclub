package edu.nju.healthClub.action;

import java.io.IOException;

import javax.servlet.ServletException;

import edu.nju.healthClub.model.user.Manager;
import edu.nju.healthClub.service.AdminService;

public class AddManagerAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AdminService adminService = new AdminService();
	
	public String execute()
			throws ServletException, IOException {
		if(request.getSession().getAttribute("userID") == null){
			return "noLogin";
		}else{
			String userType = (String)request.getSession().getAttribute("userType");
			if(userType.equals("Admin")){
				String ID = request.getParameter("ID");
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				String name = request.getParameter("name");
				
				Manager manager = new Manager();
				manager.setID(Integer.parseInt(ID));
				manager.setUsername(username);
				manager.setPassword(password);
				manager.setName(name);
				
				adminService.create(manager);
				return SUCCESS;
			}else{
				return userType;
			}
		}
		
	}
}
