package edu.nju.healthClub.action;

import java.io.IOException;

import javax.servlet.ServletException;

import edu.nju.healthClub.model.User;
import edu.nju.healthClub.model.user.InvalidUser;
import edu.nju.healthClub.service.UserService;

public class LoginAction extends BaseAction{

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
		String password = request.getParameter("password");
		
		if(username!=null)
		{
			user = service.validateUser(username,password);
			if(user instanceof InvalidUser)
			{	
				request.setAttribute("error", ((InvalidUser) user).getInfomation());
				return INPUT;
			}
			else
			{
				request.getSession().setAttribute("userID", Integer.toString(user.getID()));
				request.getSession().setAttribute("username",user.getUsername());
				request.setAttribute("user", user);
				return user.getClass().getSimpleName();
			}	
		}
		return INPUT;
	}
}
