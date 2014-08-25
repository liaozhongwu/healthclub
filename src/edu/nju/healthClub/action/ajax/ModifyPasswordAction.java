package edu.nju.healthClub.action.ajax;

import java.io.IOException;

import javax.servlet.ServletException;

import edu.nju.healthClub.action.BaseAction;
import edu.nju.healthClub.model.user.VIP;
import edu.nju.healthClub.service.CommonService;

public class ModifyPasswordAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CommonService service = new CommonService();
	
	public String execute()
			throws ServletException, IOException {
		int userID = (Integer) request.getSession().getAttribute("userID");
		String originPassword = request.getParameter("originPassword");
		String newPassword = request.getParameter("newPassword");
		
		if(newPassword != null && !newPassword.equals("")){
			VIP vip = service.getVIPByID(userID);
			if(vip != null){
				if(vip.getPassword().equals(originPassword)){
					vip.setPassword(newPassword);
					service.modify(vip);
					response.getWriter().print("modify password success");
				}else{
					response.getWriter().print("origin password false");
				}
			}
		}else{
			response.getWriter().print("empty password");
		}
		return null;
	}
}
