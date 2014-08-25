package edu.nju.healthClub.action.ajax;

import java.io.IOException;

import javax.servlet.ServletException;

import edu.nju.healthClub.action.BaseAction;
import edu.nju.healthClub.model.user.VIP;
import edu.nju.healthClub.service.VIPService;

public class FreezeVIPAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private VIPService service = new VIPService();
	
	public String execute()
			throws ServletException, IOException {
		if(request.getSession().getAttribute("userID") != null){
			int userID = (Integer)request.getSession().getAttribute("userID");
			String password = request.getParameter("password");
			if(password != null && !password.equals("")){
				VIP vip = service.getVIPByID(userID);
				if(vip.getPassword().equals(password)){
					service.freeze(userID);
					response.getWriter().print("freeze success");
				}else{
					response.getWriter().print("check password failed");
				}
			}else{
				response.getWriter().print("empty password");
			}
		}
		return null;
	}
}
