package edu.nju.healthClub.action;

import java.io.IOException;

import javax.servlet.ServletException;

import edu.nju.healthClub.model.user.Waiter;
import edu.nju.healthClub.service.AdminService;

public class UpdateWaiterAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AdminService service = new AdminService();
	
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
				
				Waiter waiter = new Waiter();
				waiter.setID(Integer.parseInt(ID));
				waiter.setUsername(username);
				waiter.setPassword(password);
				waiter.setName(name);
				
				service.modify(waiter);
				return SUCCESS;
			}else{
				return userType;
			}
		}
	}
}
