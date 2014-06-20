package edu.nju.healthClub.action.ajax;

import java.io.IOException;

import javax.servlet.ServletException;

import edu.nju.healthClub.action.BaseAction;
import edu.nju.healthClub.model.User;
import edu.nju.healthClub.service.UserService;

public class CheckUsernameAction extends BaseAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserService service = new UserService();
	private User user;
	
	public String execute()
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		
		if(username != null){
			user = service.searchUser("username", username);
			if(user == null){	
				response.getWriter().print("false");
			}else{
				response.getWriter().print("true");
			}
		}
		return null;
	}
}
