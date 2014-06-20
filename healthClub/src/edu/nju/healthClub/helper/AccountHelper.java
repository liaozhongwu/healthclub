package edu.nju.healthClub.helper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.nju.healthClub.model.Person;
import edu.nju.healthClub.model.user.VIP;
import edu.nju.healthClub.model.user.vip.VIPFamily;
import edu.nju.healthClub.model.user.vip.VIPPerson;

public class AccountHelper {
	private final int VIPPersonRegisterPayment = 75; 
	private final int VIPFamilyRegisterPayment = 100;
	public int getVIPPersonRegisterPayment() {
		return VIPPersonRegisterPayment;
	}
	public int getVIPFamilyRegisterPayment() {
		return VIPFamilyRegisterPayment;
	}
	public int getPaymentPerMonth(VIP vip){
		if(vip instanceof VIPPerson){
			return 40;
		}else if(vip instanceof VIPFamily){
			int manCnt = 0;
			int womanCnt = 0;
			int childCnt = 0;
			for(Person person : ((VIPFamily) vip).getPersons()){
				if(person.getAge() <= 18){
					childCnt ++;
				}else if(person.getSex().equals("男") || person.getSex().equals("male")){
					manCnt ++;
				}else if(person.getSex().equals("女") || person.getSex().equals("female")){
					womanCnt ++;
				}
			}
			if(manCnt > womanCnt){
				return childCnt * 10 + womanCnt * 15 + manCnt * 40;
			}else{
				return childCnt * 10 + manCnt * 15 + womanCnt * 40;
			}
		}
		return -1;
	}
	public int parseID(String ID){
		if(ID != null && !ID.equals("")){
			Pattern pat = Pattern.compile("^\\d*$");  
			Matcher mat = pat.matcher(ID);
			if(mat.find()){
				return Integer.parseInt(ID);
			}
		}
		return -1;
	}
	public int parseBalance(String balance){
		if(balance != null && !balance.equals("")){
			Pattern pat = Pattern.compile("^\\d*$");  
			Matcher mat = pat.matcher(balance);
			if(mat.find()){
				return Integer.parseInt(balance);
			}
		}
		return -1;
	}
	public String parseState(String state){
		if(state.equals("active") || state.equals("suspend") || state.equals("stop") || state.equals("freezing")){
			return state;
		}else{
			return "";
		}
	}
	public int parseAge(String age){
		if(age != null && !age.equals("")){
			Pattern pat = Pattern.compile("^\\d{1,2}$");  
			Matcher mat = pat.matcher(age);
			if(mat.find()){
				return Integer.parseInt(age);
			}
		}
		return -1;
	}
	public String parseSex(String sex){
		if(sex != null && !sex.equals("")){
			if(sex.equals("男")||sex.equals("女")||sex.equals("male")||sex.equals("female")){
				return sex;
			}
		}
		return "";
	}
	public String parseAddress(String address){
		if(address != null && !address.equals("")){
			return address;
		}
		return "";
	}
	public String parseTelephone(String telephone){
		if(telephone != null && !telephone.equals("")){
			Pattern pat = Pattern.compile("^1[3|4|5|8][0-9]\\d{8}$");  
			Matcher mat = pat.matcher(telephone);
			if(mat.find()){
				return telephone;
			}
		}
		return "";
	}
	public String parseEmail(String email){
		if(email != null && !email.equals("")){
			Pattern pat = Pattern.compile("^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$");  
			Matcher mat = pat.matcher(email);
			if(mat.find()){
				return email;
			}
		}
		return "";
	}
	public String parseRealname(String realname){
		if(realname != null && !realname.equals("")){
			Pattern pat = Pattern.compile("^[A-Za-z\u4e00-\u9fa5]+$");  
			Matcher mat = pat.matcher(realname);
			if(mat.find()){
				return realname;
			}
		}
		return "";
	}
	public String parseResidentID(String ID){
		if(ID != null && !ID.equals("")){
			Pattern pat = Pattern.compile("^[0-9]{18}|[0-9]{17}(x|X)$");  
			Matcher mat = pat.matcher(ID);
			if(mat.find()){
				return ID;
			}
		}
		return "";
	}
	public Date parseDate(String date){
		if(date != null && !date.equals("")){
			Pattern pat = Pattern.compile("^(19\\d{2}|20(0\\d|1[0-4]))-((01|03|05|07|08|10|12)-(0\\d|(1|2)\\d|3(0|1))|(04|06|09|11)-(0\\d|(1|2)\\d|30)|02-(0\\d|1\\d|2[0-8]))$");  
			Matcher mat = pat.matcher(date);
			if(mat.find()){
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				try {
					return format.parse(date);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	public int parseBankcard(String bankcard){
		if(bankcard != null && !bankcard.equals("")){
			Pattern pat = Pattern.compile("^\\d*$");  
			Matcher mat = pat.matcher(bankcard);
			if(mat.find()){
				return Integer.parseInt(bankcard);
			}
		}
		return -1;
	}
	public int parsePayment(String payment){
		if(payment != null && !payment.equals("")){
			Pattern pat = Pattern.compile("^\\d*$");  
			Matcher mat = pat.matcher(payment);
			if(mat.find()){
				return Integer.parseInt(payment);
			}
		}
		return -1;
	}
}
