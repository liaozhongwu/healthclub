package edu.nju.healthClub.action;

import java.io.IOException;

import javax.servlet.ServletException;

import edu.nju.healthClub.service.VIPService;

public class FreezeVIPAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private VIPService service = new VIPService();
	
	public String execute()
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getSession().getAttribute("userID") == null){
			return "noLogin";
		}else{
			String userID = (String)request.getSession().getAttribute("userID");
			String userType = service.getUserType(userID);
			if(userType.equals("VIP")){
				if(request.getSession().getAttribute("userID") != null){
					service.freeze(userID);
				}
				return SUCCESS;
			}else{
				return userType;
			}
		}
	}
}
