package edu.nju.healthClub.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;

import edu.nju.healthClub.helper.AccountHelper;
import edu.nju.healthClub.model.Payment;
import edu.nju.healthClub.model.Person;
import edu.nju.healthClub.model.user.vip.VIPFamily;
import edu.nju.healthClub.model.user.vip.VIPPerson;
import edu.nju.healthClub.service.VIPService;

public class RegisterAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private VIPService service = new VIPService();
	private AccountHelper helper = new AccountHelper();
	
	public String execute()
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String repassword = request.getParameter("repassword");
		if(password.equals(repassword)){
			String type = request.getParameter("register-type");
			if(type.equals("person")){
				VIPPerson VIPperson = new VIPPerson();
				VIPperson.setUsername(username);
				VIPperson.setPassword(password);
				String petname = request.getParameter("petname");
				String sex = request.getParameter("sex");
				System.out.println(sex);
				String address = request.getParameter("address");
				String telephone = request.getParameter("telephone");
				String email = request.getParameter("email");
				String realname = request.getParameter("realname");
				String residentID = request.getParameter("residentID");
				String birthday = request.getParameter("birthday");
				String bankcard = request.getParameter("bankCard");
				
				Person person = new Person();
				
				person.setPetname(petname);
				if(helper.parseSex(sex) != null){person.setSex(sex);}else{return "fail";}
				if(helper.parseAddress(address) != null){person.setAddress(address);}else{return "fail";}
				person.setTelephone(helper.parseTelephone(telephone));
				person.setEmail(helper.parseEmail(email));
				person.setRealname(helper.parseRealname(realname));
				person.setResidentID(helper.parseResidentID(residentID));
				person.setBirthday(helper.parseDate(birthday));
				
				VIPperson.setPerson(person);
				
				if(helper.parseBankcard(bankcard) != 0){
					VIPperson.setRegisterDate(new Date(System.currentTimeMillis()));
					VIPperson.setState("active");
					service.register(VIPperson);
					Payment payment = new Payment();
					payment.setVIPID(VIPperson.getID());
					payment.setBankCard(helper.parseBankcard(bankcard));
					payment.setDate(new Date(System.currentTimeMillis()));
					payment.setRemark("激活");
					payment.setPayment(helper.getVIPPersonRegisterPayment());
					service.create(payment);
				}else{
					return "fail";
				}
				request.setAttribute("user",VIPperson);
				request.getSession().setAttribute("userID", Integer.toString(VIPperson.getID()));
				request.getSession().setAttribute("username", VIPperson.getUsername());
				return SUCCESS;
			}
			else if(type.equals("family")){
				VIPFamily VIPfamily = new VIPFamily();
				VIPfamily.setUsername(username);
				VIPfamily.setPassword(password);
				String[] petnames = request.getParameterValues("petname");
				String[] sexs = request.getParameterValues("sex");
				String[] addresses = request.getParameterValues("address");
				String[] telephones = request.getParameterValues("telephone");
				String[] emails = request.getParameterValues("email");
				String[] realnames = request.getParameterValues("realname");
				String[] residentIDs = request.getParameterValues("residentID");
				String[] birthdays = request.getParameterValues("birthday");
				String bankcard = request.getParameter("bankCard");
				if(petnames.length == sexs.length &&
					sexs.length	== telephones.length &&
					telephones.length == addresses.length &&
					addresses.length == emails.length &&
					emails.length == realnames.length &&
					realnames.length == residentIDs.length &&
					residentIDs.length == birthdays.length &&
					petnames.length > 0){
						ArrayList<Person> persons = new ArrayList<Person>();
						for(int i=0 ; i<petnames.length ; i++){
							Person person = new Person();
							String petname = petnames[i];
							String sex = sexs[i];
							String address = addresses[i];
							String telephone = telephones[i];
							String email = emails[i];
							String realname = realnames[i];
							String residentID = residentIDs[i];
							String birthday = birthdays[i];
							
							person.setPetname(petname);
							if(helper.parseSex(sex) != null){person.setSex(sex);}else{return "fail";}
							if(helper.parseAddress(address) != null){person.setAddress(address);}else{return "fail";}
							person.setTelephone(helper.parseTelephone(telephone));
							person.setEmail(helper.parseEmail(email));
							person.setRealname(helper.parseRealname(realname));
							person.setResidentID(helper.parseResidentID(residentID));
							person.setBirthday(helper.parseDate(birthday));
							
							persons.add(person);
						}

						VIPfamily.setPersons(persons);
						
						if(helper.parseBankcard(bankcard) != 0){
							VIPfamily.setRegisterDate(new Date(System.currentTimeMillis()));
							VIPfamily.setState("active");
							service.register(VIPfamily);
							Payment payment = new Payment();
							payment.setVIPID(VIPfamily.getID());
							payment.setBankCard(helper.parseBankcard(bankcard));
							payment.setDate(new Date(System.currentTimeMillis()));
							payment.setRemark("激活");
							payment.setPayment(helper.getVIPFamilyRegisterPayment());
							service.create(payment);
						}else{
							return "fail";
						}
						request.setAttribute("user",VIPfamily);
						request.getSession().setAttribute("userID", Integer.toString(VIPfamily.getID()));
						request.getSession().setAttribute("username", VIPfamily.getUsername());
						return SUCCESS;
				}
			}
			return "fail";
		}
		return null;
	}
}
