package edu.nju.healthClub.action;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;

import edu.nju.healthClub.helper.AccountHelper;
import edu.nju.healthClub.model.user.Person;
import edu.nju.healthClub.model.user.VIP;
import edu.nju.healthClub.model.user.VIPState;
import edu.nju.healthClub.model.user.VIPType;
import edu.nju.healthClub.service.VIPService;

public class RegisterAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private VIPService service = new VIPService();
	
	public String execute()
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String repassword = request.getParameter("repassword");
		if(password.equals(repassword)){
			VIP vip = new VIP();
			VIPType type = VIPType.valueOf(request.getParameter("type"));
			vip.setType(type);
			vip.setUsername(username);
			vip.setPassword(password);
			vip.setRegisterDate(new Date(System.currentTimeMillis()).toString());
			vip.setState(VIPState.UNACTIVE);
			
			service.register(vip);
			if(type == VIPType.SINGLE){
				String name = request.getParameter("name");
				String sex = request.getParameter("sex");
				String birthday = request.getParameter("birthday");
				String address = request.getParameter("address");
				String telephone = request.getParameter("telephone");
				String email = request.getParameter("email");
				String resident = request.getParameter("resident");
				
				Person person = new Person();
				person.setVIP(vip);
				if(AccountHelper.validateSex(sex) &&
					AccountHelper.validateTelephone(telephone) &&
					AccountHelper.validateEmail(email) &&
					AccountHelper.validateResident(resident) &&
					AccountHelper.validateDate(birthday)
				){
					person.setName(name);
					person.setSex(sex);
					person.setBirthday(birthday);
					person.setAddress(address);
					person.setTelephone(telephone);
					person.setEmail(email);
					person.setResident(resident);
					service.add(person);
				}
				else return "fail";
			}
			else if(type.equals("family")){
				String[] names = request.getParameterValues("name");
				String[] sexs = request.getParameterValues("sex");
				String[] birthdays = request.getParameterValues("birthday");
				String[] addresses = request.getParameterValues("address");
				String[] telephones = request.getParameterValues("telephone");
				String[] emails = request.getParameterValues("email");
				String[] residents = request.getParameterValues("resident");
				if(names.length == sexs.length &&
					sexs.length == birthdays.length &&
					birthdays.length == addresses.length &&
					addresses.length == telephones.length &&
					telephones.length == emails.length &&
					emails.length == residents.length
				){
					for(int i=0 ; i < names.length ; i++){
						Person person = new Person();
						person.setVIP(vip);
						if(AccountHelper.validateSex(sexs[i]) &&
							AccountHelper.validateDate(birthdays[i]) &&
							AccountHelper.validateTelephone(telephones[i]) &&
							AccountHelper.validateEmail(emails[i]) &&
							AccountHelper.validateResident(residents[i])
						){
							person.setName(names[i]);
							person.setSex(sexs[i]);
							person.setBirthday(birthdays[i]);
							person.setAddress(addresses[i]);
							person.setTelephone(telephones[i]);
							person.setEmail(emails[i]);
							person.setResident(residents[i]);
							service.add(person);
						}
						else return "fail";
					}
				}
			}
			request.setAttribute("user", vip);
			request.getSession().setAttribute("userID", vip.getID());
			request.getSession().setAttribute("username", vip.getUsername());
			request.getSession().setAttribute("userType", "VIP");
			return SUCCESS;
		}
		return null;
	}
}
