package edu.nju.healthClub.action.ajax;

import java.io.IOException;

import javax.servlet.ServletException;

import edu.nju.healthClub.action.BaseAction;
import edu.nju.healthClub.helper.AccountHelper;
import edu.nju.healthClub.helper.CurrTime;
import edu.nju.healthClub.model.Payment;
import edu.nju.healthClub.model.user.VIP;
import edu.nju.healthClub.model.user.VIPState;
import edu.nju.healthClub.service.VIPService;

public class VIPPayAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private VIPService service = new VIPService();
	public String execute()
			throws ServletException, IOException {
		if(request.getSession().getAttribute("userID") != null){
			int userID = (Integer)request.getSession().getAttribute("userID");
			String userType = (String)request.getSession().getAttribute("userType");
			if(userType.equals("VIP")){
				String bankcard = request.getParameter("bankcard");
				String money = request.getParameter("money");
				if(AccountHelper.validateBankcard(bankcard) &&
					AccountHelper.validatePayment(money)){
					Payment payment = new Payment();
					VIP vip = service.getVIPByID(userID);
					payment.setVIP(vip);
					payment.setBankCard(Integer.parseInt(bankcard));
					payment.setPayment(Integer.parseInt(money));
					payment.setRemark("充值");
					payment.setDate(CurrTime.getCurrTime("yyyy-MM-dd HH:mm:ss"));
					service.create(payment);
					vip.setState(VIPState.ACTIVE);
					vip.setBalance(vip.getBalance() + payment.getPayment());
					service.modify(vip);
					response.getWriter().write("success");
				}
				else{
					response.getWriter().write("invalid data");
				}
			}
		}
		return null;
	}
}
