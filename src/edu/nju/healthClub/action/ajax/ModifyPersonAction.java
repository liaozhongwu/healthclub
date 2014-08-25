package edu.nju.healthClub.action.ajax;

import java.io.IOException;

import javax.servlet.ServletException;

import edu.nju.healthClub.action.BaseAction;
import edu.nju.healthClub.helper.AccountHelper;
import edu.nju.healthClub.model.user.Person;
import edu.nju.healthClub.service.CommonService;

public class ModifyPersonAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CommonService service = new CommonService();
	
	public String execute()
			throws ServletException, IOException {
		if(request.getSession().getAttribute("userID") != null){
			String personID = request.getParameter("personID");
			String name = request.getParameter("name");
			String sex = request.getParameter("sex");
			String birthday = request.getParameter("birthday");
			String address = request.getParameter("address");
			String telephone = request.getParameter("telephone");
			String email = request.getParameter("email");
			String resident = request.getParameter("resident");
			String userType = (String)request.getSession().getAttribute("userType");
			if(userType.equals("VIP") || userType.equals("Waiter")){
				if(AccountHelper.validateID(personID) &&
					AccountHelper.validateSex(sex) &&
					AccountHelper.validateDate(birthday) &&
					AccountHelper.validateTelephone(telephone) &&
					AccountHelper.validateEmail(email) &&
					AccountHelper.validateResident(resident)
				){
					Person person = service.getPersonByID(Integer.parseInt(personID));
					person.setName(name);
					person.setSex(sex);
					person.setBirthday(birthday);
					person.setAddress(address);
					person.setTelephone(telephone);
					person.setEmail(email);
					person.setResident(resident);
					service.modify(person);
					response.getWriter().print("success");
				}
				else{
					response.getWriter().print("invalid data");
				}
			}
		}
		return null;
	}
}

