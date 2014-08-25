<%@page import="edu.nju.healthClub.model.Payment"%>
<%@page import="edu.nju.healthClub.model.Record"%>
<%@page import="java.util.ArrayList"%>
<%@page import="edu.nju.healthClub.model.user.VIP"%>
<%@page import="edu.nju.healthClub.service.CommonService"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<% 
    int userID = (Integer)session.getAttribute("userID");
	CommonService service = new CommonService();
	VIP vip = service.getVIPByID(userID);
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
</table>