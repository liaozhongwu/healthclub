package edu.nju.healthClub.quartzJob;

import java.util.ArrayList;
import java.util.Date;

import edu.nju.healthClub.dao.AdminDaoImpl;
import edu.nju.healthClub.helper.AccountHelper;
import edu.nju.healthClub.model.Payment;
import edu.nju.healthClub.model.user.Person;
import edu.nju.healthClub.model.user.VIP;
import edu.nju.healthClub.model.user.VIPState;
import edu.nju.healthClub.model.user.VIPType;

public class PayJob {
	private static final int VIPPersonRegisterPayment = 75; 
	private static final int VIPFamilyRegisterPayment = 100;
	private AdminDaoImpl dao = new AdminDaoImpl();
	
	public void payPerMonth(){
		ArrayList<VIP> VIPs = dao.getAllVIP();
		Payment payment = new Payment();
		for(VIP vip : VIPs){
			if(vip.getBalance() < getPaymentPerMonth(vip)){
				int month = dao.searchNoPayment(vip.getID());
				if(month >= 5)
					vip.setState(VIPState.STOP);
				else
					vip.setState(VIPState.PAUSE);
				dao.saveNoPayment(vip.getID());
				dao.updateState(vip);
			}else{
				payment.setVIP(vip);
				payment.setRemark("自动扣费");
				payment.setBankCard(0);
				payment.setDate(new Date(System.currentTimeMillis()).toString());
				payment.setPayment(getPaymentPerMonth(vip));
				vip.setBalance(vip.getBalance() - getPaymentPerMonth(vip));
				dao.save(payment);
				dao.updateBalance(vip);
			}
		}
	}
	public int getVIPPersonRegisterPayment() {
		return VIPPersonRegisterPayment;
	}
	public static int getVIPFamilyRegisterPayment() {
		return VIPFamilyRegisterPayment;
	}
	public int getPaymentPerMonth(VIP vip){
		if(vip.getType() == VIPType.SINGLE){
			return 40;
		}else if(vip.getType() == VIPType.FAMILY){
			int manCnt = 0;
			int womanCnt = 0;
			int childCnt = 0;
			for(Person person : dao.queryPersonsOfVIP(vip)){
				if(AccountHelper.getPersonAge(person) <= 18){
					childCnt ++;
				}else if(person.getSex().equals("male")){
					manCnt ++;
				}else if(person.getSex().equals("female")){
					womanCnt ++;
				}
			}
			if(manCnt > womanCnt){
				return childCnt * 10 + womanCnt * 15 + manCnt * 40;
			}else{
				return childCnt * 10 + manCnt * 15 + womanCnt * 40;
			}
		}
		return 0;
	}
}
