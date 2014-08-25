package edu.nju.healthClub.action;

import java.io.IOException;

import javax.servlet.ServletException;

import edu.nju.healthClub.helper.AccountHelper;
import edu.nju.healthClub.service.AdminService;

public class RemoveManagerAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AdminService adminService = new AdminService();
	
	public String execute()
			throws ServletException, IOException {
		if(request.getSession().getAttribute("userID") == null)
			return "noLogin";
		else{
			String userType = (String)request.getSession().getAttribute("userType");
			if(userType.equals("Admin")){
				String ID = request.getParameter("ID");
				if(AccountHelper.validateID(ID)){
					adminService.removeManager(Integer.parseInt(ID));
					return SUCCESS;
				}else
					return "fail";
			}else
				return userType;
		}
	}
}
