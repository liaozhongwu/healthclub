package edu.nju.healthClub.action.ajax;

import java.io.IOException;

import javax.servlet.ServletException;

import edu.nju.healthClub.action.BaseAction;
import edu.nju.healthClub.helper.AccountHelper;
import edu.nju.healthClub.model.user.Person;
import edu.nju.healthClub.model.user.VIP;
import edu.nju.healthClub.service.CommonService;

public class AddPersonAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CommonService service = new CommonService();
	
	public String execute()
			throws ServletException, IOException {
		if(request.getSession().getAttribute("userID") != null){
			String name = request.getParameter("name");
			String sex = request.getParameter("sex");
			String birthday = request.getParameter("birthday");
			String address = request.getParameter("address");
			String telephone = request.getParameter("telephone");
			String email = request.getParameter("email");
			String resident = request.getParameter("resident");
			String userType = (String)request.getSession().getAttribute("userType");
			int VIPID;
			if(userType.equals("VIP")){
				VIPID = (Integer) request.getSession().getAttribute("userID");
			}
			else if(userType.equals("Waiter")){
				VIPID = Integer.parseInt(request.getParameter("VIPID"));
			}
			else{
				return null;
			}
			if(AccountHelper.validateSex(sex) &&
					AccountHelper.validateDate(birthday) &&
					AccountHelper.validateTelephone(telephone) &&
					AccountHelper.validateEmail(email) &&
					AccountHelper.validateResident(resident)
				){
					VIP vip = service.getVIPByID(VIPID);
					Person person = new Person();
					person.setVIP(vip);
					person.setName(name);
					person.setSex(sex);
					person.setBirthday(birthday);
					person.setAddress(address);
					person.setTelephone(telephone);
					person.setEmail(email);
					person.setResident(resident);
					service.add(person);
					response.getWriter().print("success");
				}
				else{
					response.getWriter().print("invalid data");
				}
		}
		return null;
	}
}

