package edu.nju.healthClub.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.nju.healthClub.model.user.Person;
import edu.nju.healthClub.model.user.VIPState;
import edu.nju.healthClub.model.user.VIPType;

public class AccountHelper {
	
	public static int getPersonAge(Person person){
		System.currentTimeMillis();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
		try {
			Date birthday = format.parse(person.getBirthday());
			return (int)((System.currentTimeMillis() - birthday.getTime()) % (1000 * 60 * 60 * 24 * 365));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}
	public static boolean validateID(String ID){
		if(ID != null){
			Pattern pat = Pattern.compile("^\\d+$");  
			Matcher mat = pat.matcher(ID);
			if(mat.matches()){
				return true;
			}
		}
		return false;
	}
	public static boolean validateUsername(String username){
		if(username != null){
			Pattern pat = Pattern.compile("^[a-zA-z]+\\d*$");  
			Matcher mat = pat.matcher(username);
			if(mat.matches()){
				return true;
			}
		}
		return false;
	}

	public static boolean ValidateType(String type){
		for(VIPType temp : VIPType.values()){
			if(type.equals(temp.name())) return true;
		}
		return false;
	}
	public static boolean validateBalance(String balance){
		if(balance != null){
			Pattern pat = Pattern.compile("^\\d+$");  
			Matcher mat = pat.matcher(balance);
			if(mat.matches()){
				return true;
			}
		}
		return false;
	}
	public static boolean ValidateState(String state){
		for(VIPState temp : VIPState.values()){
			if(state.equals(temp.name())) return true;
		}
		return false;
	}
	public static boolean validateSex(String sex){
		if(sex != null && (sex.equals("male")||sex.equals("female"))){
			return true;
		}
		return false;
	}
	public static boolean validateTelephone(String telephone){
		if(telephone != null){
			Pattern pat = Pattern.compile("^1[3|4|5|8][0-9]\\d{8}$");  
			Matcher mat = pat.matcher(telephone);
			if(mat.matches() || telephone.equals("")){
				return true;
			}
		}
		return false;
	}
	public static boolean validateEmail(String email){
		if(email != null){
			Pattern pat = Pattern.compile("^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$");  
			Matcher mat = pat.matcher(email);
			if(mat.matches() || email.equals("")){
				return true;
			}
		}
		return false;
	}
	public static boolean validateResident(String resident){
		if(resident != null){
			Pattern pat = Pattern.compile("^[0-9]{18}|[0-9]{17}(x|X)$");  
			Matcher mat = pat.matcher(resident);
			if(mat.matches() || resident.equals("")){
				return true;
			}
		}
		return false;
	}
	public static boolean validateDate(String date){
		if(date != null){
			Pattern pat = Pattern.compile("^(19\\d{2}|20(0\\d|1[0-4]))-((01|03|05|07|08|10|12)-(0\\d|(1|2)\\d|3(0|1))|(04|06|09|11)-(0\\d|(1|2)\\d|30)|02-(0\\d|1\\d|2[0-8]))$");  
			Matcher mat = pat.matcher(date);
			if(mat.matches()){
				return true;
			}
		}
		return false;
	}
	public static boolean validateBankcard(String bankcard){
		if(bankcard != null && !bankcard.equals("")){
			Pattern pat = Pattern.compile("^\\d+$");  
			Matcher mat = pat.matcher(bankcard);
			if(mat.matches()){
				return true;
			}
		}
		return false;
	}
	public static boolean validatePayment(String payment){
		if(payment != null && !payment.equals("")){
			Pattern pat = Pattern.compile("^\\d+$");  
			Matcher mat = pat.matcher(payment);
			if(mat.matches()){
				return true;
			}
		}
		return false;
	}
}
