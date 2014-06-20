package edu.nju.healthClub.quartzJob;

import java.util.ArrayList;
import java.util.Date;

import edu.nju.healthClub.dao.AdminDaoImpl;
import edu.nju.healthClub.helper.AccountHelper;
import edu.nju.healthClub.model.Payment;
import edu.nju.healthClub.model.user.VIP;

public class PayJob {
	private AdminDaoImpl dao = new AdminDaoImpl();
	private AccountHelper helper = new AccountHelper();
	
	public void payPerMonth(){
		ArrayList<VIP> VIPs = dao.getAllVIP();
		Payment payment = new Payment();
		for(VIP vip : VIPs){
			if(vip.getBalance() < helper.getPaymentPerMonth(vip)){
				int month = dao.findNoPayment(Integer.toString(vip.getID()));
				if(month >= 5){
					vip.setState("stop");
				}else{
					vip.setState("suspend");
				}
				dao.saveNoPayment(Integer.toString(vip.getID()));
				dao.updateState(vip);
			}else{
				payment.setVIPID(vip.getID());
				payment.setRemark("自动扣费");
				payment.setBankCard(0);
				payment.setDate(new Date(System.currentTimeMillis()));
				payment.setPayment(helper.getPaymentPerMonth(vip));
				vip.setBalance(vip.getBalance() - helper.getPaymentPerMonth(vip));
				dao.save(payment);
				dao.updateBalance(vip);
			}
		}
	}
}
