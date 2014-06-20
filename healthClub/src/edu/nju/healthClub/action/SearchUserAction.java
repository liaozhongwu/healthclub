package edu.nju.healthClub.action;

import java.io.IOException;

import javax.servlet.ServletException;

import edu.nju.healthClub.helper.SearchHelper;
import edu.nju.healthClub.model.User;
import edu.nju.healthClub.service.UserService;

public class SearchUserAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private UserService service = new UserService();
	private SearchHelper helper = new SearchHelper();
	
	public String execute()
			throws ServletException, IOException {
		if(request.getSession().getAttribute("userID") == null){
			return "noLogin";
		}else{
			String userID = (String)request.getSession().getAttribute("userID");
			String userType = service.getUserType(userID);
			if(userType.equals("Waiter")){
				String key = request.getParameter("key");
				String searchType = helper.getUserSearchType(key);
				User user = service.searchUser(searchType,key);
				if(user != null){
					request.setAttribute("user", user);
					return SUCCESS;
				}else{
					return INPUT;
				}
			}else{
				return userType;
			}
		}
	}
}
