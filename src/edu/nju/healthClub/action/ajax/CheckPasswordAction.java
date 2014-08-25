package edu.nju.healthClub.action.ajax;

import java.io.IOException;

import javax.servlet.ServletException;

import edu.nju.healthClub.action.BaseAction;
import edu.nju.healthClub.model.user.VIP;
import edu.nju.healthClub.service.CommonService;

public class CheckPasswordAction extends BaseAction {
	
	private static final long serialVersionUID = 1L;
	private CommonService service = new CommonService();
	
	public String execute() throws ServletException, IOException {
		int userID = (Integer) request.getSession().getAttribute("userID");
		String password = request.getParameter("password");
		
		if(password != null && !password.equals("")){
			VIP vip = service.getVIPByID(userID);
			if(vip.getPassword().equals(password)){
				response.getWriter().print("true");
			}else{
				response.getWriter().print("false");
			}
		}else{
			response.getWriter().print("false");
		}
		return null;
	}
}
