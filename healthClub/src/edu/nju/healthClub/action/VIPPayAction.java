package edu.nju.healthClub.action;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;

import edu.nju.healthClub.helper.AccountHelper;
import edu.nju.healthClub.model.Payment;
import edu.nju.healthClub.model.User;
import edu.nju.healthClub.model.user.VIP;
import edu.nju.healthClub.service.VIPService;

public class VIPPayAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private VIPService service = new VIPService();
	private AccountHelper helper = new AccountHelper();
	public String execute()
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getSession().getAttribute("userID") == null){
			return "noLogin";
		}else{
			String userID = (String)request.getSession().getAttribute("userID");
			String userType = service.getUserType(userID);
			if(userType.equals("VIP")){
				String bankcard = request.getParameter("bankcard");
				String money = request.getParameter("money");
				
				Payment payment = new Payment();
				User user = service.getUserByID(userID);
				if(user instanceof VIP){
					payment.setVIPID(user.getID());
					payment.setBankCard(helper.parseBankcard(bankcard));
					payment.setPayment(helper.parsePayment(money));
					payment.setRemark("充值");
					payment.setDate(new Date(System.currentTimeMillis()));
					service.create(payment);
					((VIP) user).setBalance(((VIP) user).getBalance() + payment.getPayment());
					service.update(user);
					return user.getClass().getSimpleName();
				}
				return "fail";
			}else{
				return userType;
			}
		}
	}
}
