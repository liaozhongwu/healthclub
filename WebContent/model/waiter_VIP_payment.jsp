<%@page import="edu.nju.healthClub.service.WaiterService"%>
<%@page import="edu.nju.healthClub.helper.AccountHelper"%>
<%@page import="edu.nju.healthClub.model.Payment"%>
<%@page import="java.util.ArrayList"%>
<%@page import="edu.nju.healthClub.model.user.VIP"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<% 
	String VIPID = request.getParameter("VIPID");
	if(AccountHelper.validateID(VIPID)){
		WaiterService service = new WaiterService();
		VIP vip = service.getVIPByID(Integer.parseInt(VIPID));
%>
<table id="VIP-payment">
	<thead>
		<tr>
			<td>回执编号</td>
			<td>缴费时间</td>
			<td>银行卡</td>
			<td>缴费金额</td>
			<td>备注</td>
		</tr>
	</thead>
	<tbody>
	<%
		ArrayList<Payment> payments = service.getPaymentsOfVIP(vip.getID());
		for(Payment payment : payments){
	%>
	<tr>
		<td><%=payment.getID() %></td>
		<td><%=payment.getDate() %></td>
		<td><%=payment.getBankCard() %></td>
		<td><%=payment.getPayment() %></td>
		<td><%=payment.getRemark() %></td>
	</tr>
	<% }%>
	</tbody>
</table><%}%>