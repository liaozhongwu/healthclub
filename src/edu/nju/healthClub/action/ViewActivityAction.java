package edu.nju.healthClub.action;

import java.io.IOException;

import javax.servlet.ServletException;

import edu.nju.healthClub.helper.AccountHelper;
import edu.nju.healthClub.model.Activity;
import edu.nju.healthClub.service.CommonService;

public class ViewActivityAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CommonService service = new CommonService();
	
	public String execute()
			throws ServletException, IOException {
		String ID = request.getParameter("ID");
		if(AccountHelper.validateID(ID)){
			Activity activity = service.getActivityByID(Integer.parseInt(ID));
			if(activity != null){
				request.setAttribute("activity", activity);
				return SUCCESS;
			}
		}
		return null;
	}
}
