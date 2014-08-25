package edu.nju.healthClub.action;

import java.io.IOException;

import javax.servlet.ServletException;

import edu.nju.healthClub.helper.AccountHelper;
import edu.nju.healthClub.model.user.User;
import edu.nju.healthClub.service.UserService;

public class LoginAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserService service = new UserService();
	
	public String execute()
			throws ServletException, IOException {
		User user = null;
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if(username!=null)
		{
			if(AccountHelper.validateID(username)){
				user = service.validateUser(Integer.parseInt(username), password);
			}
			else if(AccountHelper.validateUsername(username)){
				user = service.validateUser(username, password);
			}
			if(user != null)
			{
				request.getSession().setAttribute("userID", user.getID());
				request.getSession().setAttribute("username", user.getUsername());
				request.getSession().setAttribute("userType", user.getClass().getSimpleName());
				request.setAttribute("user", user);
				return user.getClass().getSimpleName();
			}
		}
		return INPUT;
	}
}
