package edu.nju.healthClub.action.ajax;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;

import edu.nju.healthClub.action.BaseAction;
import edu.nju.healthClub.helper.AccountHelper;
import edu.nju.healthClub.model.Person;
import edu.nju.healthClub.model.User;
import edu.nju.healthClub.model.user.vip.VIPFamily;
import edu.nju.healthClub.model.user.vip.VIPPerson;
import edu.nju.healthClub.service.UserService;

public class UpdateUserAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserService service = new UserService();
	private AccountHelper helper = new AccountHelper();
	public String execute()
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String ID = request.getParameter("userID");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String balance = request.getParameter("balance");
		String state = request.getParameter("state");
		User user = service.getUserByID(ID);
		if(user != null){
			user.setUsername(username);
			user.setPassword(password);
			if(user instanceof VIPPerson){
				if(helper.parseBalance(balance) != -1){((VIPPerson) user).setBalance(helper.parseBalance(balance));}
				else{response.getWriter().print("ERROR:balance");return null;}
				if(!helper.parseState(state).equals("")){((VIPPerson) user).setState(helper.parseState(state));}
				else{response.getWriter().print("ERROR:state");return null;}
				
				String petname = request.getParameter("petname");
				String sex = request.getParameter("sex");
				String address = request.getParameter("address");
				String telephone = request.getParameter("telephone");
				String email = request.getParameter("email");
				String realname = request.getParameter("realname");
				String residentID = request.getParameter("residentID");
				String birthday = request.getParameter("birthday");
				
				Person person = ((VIPPerson) user).getPerson();
				person.setPetname(petname);
				if(!helper.parseSex(sex).equals("")){person.setSex(sex);}
				else{response.getWriter().print("ERROR:sex");return null;}
				if(!helper.parseAddress(address).equals("")){person.setAddress(address);}
				else{response.getWriter().print("ERROR:address");return null;}
				person.setTelephone(helper.parseTelephone(telephone));
				person.setEmail(helper.parseEmail(email));
				person.setRealname(helper.parseRealname(realname));
				person.setResidentID(helper.parseResidentID(residentID));
				person.setBirthday(helper.parseDate(birthday));
				
				service.update(user);
				
				String result = "SUCCESS:保存成功";
				response.getWriter().print(new String(result.getBytes("utf-8"),"ISO-8859-1"));
				return null;
			}
			else if(user instanceof VIPFamily){
				if(helper.parseBalance(balance) != -1){((VIPFamily) user).setBalance(helper.parseBalance(balance));}
				else{response.getWriter().print("ERROR:balance");return null;}
				if(!helper.parseState(state).equals("")){((VIPFamily) user).setState(helper.parseState(state));}
				else{response.getWriter().print("ERROR:state");return null;}
				String[] personIDs = request.getParameterValues("personID[]");
				String[] petnames = request.getParameterValues("petname[]");
				String[] sexs = request.getParameterValues("sex[]");
				String[] addresses = request.getParameterValues("address[]");
				String[] telephones = request.getParameterValues("telephone[]");
				String[] emails = request.getParameterValues("email[]");
				String[] realnames = request.getParameterValues("realname[]");
				String[] residentIDs = request.getParameterValues("residentID[]");
				String[] birthdays = request.getParameterValues("birthday[]");
				ArrayList<Person> persons = new ArrayList<Person>();
				for(int i = 0;i < personIDs.length;i++){
					String personID = personIDs[i];
					String petname = petnames[i];
					String sex = sexs[i];
					String address = addresses[i];
					String telephone = telephones[i];
					String email = emails[i];
					String realname = realnames[i];
					String residentID = residentIDs[i];
					String birthday = birthdays[i];
					
					Person person = new Person();
					if(helper.parseID(personID) != -1){person.setID(helper.parseID(personID));}
					else{response.getWriter().print("ERROR:personID");return null;}
					person.setPetname(petname);
					if(!helper.parseSex(sex).equals("")){person.setSex(sex);}
					else{response.getWriter().print("ERROR:sex");return null;}
					if(!helper.parseAddress(address).equals("")){person.setAddress(address);}
					else{response.getWriter().print("ERROR:address");return null;}
					person.setTelephone(helper.parseTelephone(telephone));
					person.setEmail(helper.parseEmail(email));
					person.setRealname(helper.parseRealname(realname));
					person.setResidentID(helper.parseResidentID(residentID));
					person.setBirthday(helper.parseDate(birthday));
					
					persons.add(person);
				}
				((VIPFamily) user).setPersons(persons);
				
				service.update(user);
				
				String result = "SUCCESS:保存成功";
				response.getWriter().print(new String(result.getBytes("utf-8"),"ISO-8859-1"));
			}
		}
		
		return null;
	}
}
