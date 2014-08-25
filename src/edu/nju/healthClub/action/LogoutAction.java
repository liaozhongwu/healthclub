package edu.nju.healthClub.action;

import java.io.IOException;

import javax.servlet.ServletException;

public class LogoutAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String execute()
			throws ServletException, IOException {
		request.getSession().removeAttribute("userID");
		request.getSession().removeAttribute("username");
		return SUCCESS;
	}
}

