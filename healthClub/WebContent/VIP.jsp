<%@page import="edu.nju.healthClub.service.VIPService"%>
<%@page import="edu.nju.healthClub.model.user.*"%>
<%@page import="edu.nju.healthClub.model.*"%>
<%@page import="edu.nju.healthClub.model.user.vip.*"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="css/bootstrap.css" >
<link rel="stylesheet" type="text/css" href="css/common.css" >
<link rel="stylesheet" type="text/css" href="css/VIP.css" >
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/VIP.js"></script>
<title>个人主页</title>
</head>
<body>
	<%@include file="checkSession.jsp"%>
	<jsp:include page="header.jsp" flush="true"/>
	<%
		VIPService service = new VIPService();
		User user = null; 
		if(session.getAttribute("userID") != null){
			if(request.getAttribute("user") != null){
				user = (User)request.getAttribute("user");
			}
			else{
				String ID = (String)session.getAttribute("userID");
				user = service.getUserByID(ID); 
			}
			if(user instanceof VIPPerson){
	%>
	<div class="background-shadow"></div>
	<div class="main">
		<div class="tabs">
			<div class="active-tab" panel=".VIP-info">个人信息</div>
			<div class="inactive-tab" panel=".VIP-appointment">预约记录</div>
			<div class="inactive-tab" panel=".VIP-record">活动记录</div>
			<div class="inactive-tab" panel=".VIP-payment">缴费记录</div>
		</div>
		<div class="VIP-info">
			<div class="VIP-block">
				<div class="VIP-block-ID">
					<label>ID: </label>
					<input type="text" name="userID" readonly="readonly" value="<%=user.getID() %>">
				</div>
				<div class="VIP-block-username">
					<label>用户名: </label>
					<input type="text" name="username" readonly="readonly" value="<%=user.getUsername() %>">
				</div>
				<div class="VIP-block-balance">
					<label>账户余额: </label>
					<input type="text" name="balance" readonly="readonly" value="<%=((VIPPerson)user).getBalance() %>">
				</div>
				<div class="VIP-block-state">
					<label>账户状态: </label>
					<input type="text" name="state" readonly="readonly" value="<%=((VIPPerson)user).getState() %>">
				</div>
				<div class="VIP-block-registerDate">
					<label>注册时间: </label>
					<input type="text" name="registerDate" readonly="readonly" value="<%=((VIPPerson)user).getRegisterDate() %>">
				</div>
				<div class="VIP-block-person-panel">
					<div class="VIP-block-person-label">
						<%=((VIPPerson)user).getPerson().getPetname() %>
					</div>
					<div class="VIP-block-person-info">
						<div class="VIP-block-person-petname">
							<label>昵称: </label>
							<input type="text" name="petname" readonly="readonly" value="<%=((VIPPerson)user).getPerson().getPetname() %>">
						</div>
						<div class="VIP-block-person-age">
							<label>年龄: </label>
							<input type="text" name="age" readonly="readonly" value="<%=((VIPPerson)user).getPerson().getAge() %>">
						</div>
						<div class="VIP-block-person-sex">
							<label>性别: </label>
							<input type="text" name="sex" readonly="readonly" value="<%=((VIPPerson)user).getPerson().getSex() %>">
						</div>
						<div class="VIP-block-person-address">
							<label>地址: </label>
							<input type="text" name="address" readonly="readonly" value="<%=((VIPPerson)user).getPerson().getAddress() %>">
						</div>
						<div class="VIP-block-person-telephone">
							<label>手机: </label>
							<input type="text" name="telephone" readonly="readonly" value="<%=((VIPPerson)user).getPerson().getTelephone() %>">
						</div>
						<div class="VIP-block-person-email">
							<label>邮箱: </label>
							<input type="text" name="email" readonly="readonly" value="<%=((VIPPerson)user).getPerson().getEmail() %>">
						</div>
						<div class="VIP-block-person-realname">
							<label>姓名: </label>
							<input type="text" name="realname" readonly="readonly" value="<%=((VIPPerson)user).getPerson().getRealname() %>">
						</div>
						<div class="VIP-block-person-residentID">
							<label>身份证: </label>
							<input type="text" name="residentID" readonly="readonly" value="<%=((VIPPerson)user).getPerson().getResidentID() %>">
						</div>
						<div class="VIP-block-person-birthday">
							<label>生日: </label>
							<input type="text" name="birthday" readonly="readonly" value="<%=((VIPPerson)user).getPerson().getBirthday() %>">
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="VIP-appointment">
				<div class="VIP-appointment-activity">
					活动名称
				</div>
				<div class="VIP-appointment-session">
					活动场次
				</div>
				<div class="VIP-appointment-person">
					参加成员
				</div>
				<div class="VIP-appointment-date">
					活动日期
				</div>
				<div class="VIP-appointment-starttime">
					开始时间
				</div>
				<div class="VIP-appointment-place">
					活动地点
				</div>
			<%
				ArrayList<Appointment> appointments = service.getAppointmentsByVIP(user.getID());
				for(Appointment appointment : appointments){
					Activity activity = service.getActivityByID(appointment.getActivityID());
					ActivitySession activitySession = service.getActivitySessionByID(appointment.getActivityID(), appointment.getSession());
			%>
				<div class="VIP-appointment-activity">
					<a href="activity.jsp?ID=<%= activity.getID()%>"><%=activity.getName() %></a>
				</div>
				<div class="VIP-appointment-session">
					<%=appointment.getSession() %>
				</div>
				<div class="VIP-appointment-person">
					<%=appointment.getPerson().getRealname() %>
				</div>
				<div class="VIP-appointment-date">
					<%=activitySession.getDate() %>
				</div>
				<div class="VIP-appointment-starttime">
					<%=activitySession.getStarttime() %>
				</div>
				<div class="VIP-appointment-place">
					<%=activitySession.getPlace() %>
				</div>
			<%
				}
			%>
		</div>
		<div class="VIP-record">
			<div class="VIP-record-activity">
					活动名称
				</div>
				<div class="VIP-record-session">
					活动场次
				</div>
				<div class="VIP-record-person">
					参加成员
				</div>
				<div class="VIP-record-date">
					活动日期
				</div>
			<%
				ArrayList<Record> records = service.getRecordsByVIP(user.getID());
				for(Record record : records){
					Activity activity = service.getActivityByID(record.getActivityID());
					ActivitySession activitySession = service.getActivitySessionByID(record.getActivityID(), record.getSession());
			%>
				<div class="VIP-record-activity">
					<a href="activity.jsp?ID=<%= activity.getID()%>"><%=activity.getName() %></a>
				</div>
				<div class="VIP-record-session">
					<%=record.getSession() %>
				</div>
				<div class="VIP-record-person">
					<%=record.getPerson().getRealname() %>
				</div>
				<div class="VIP-record-date">
					<%=activitySession.getDate() %>
				</div>
			<%
				}
			%>
		</div>
		<div class="VIP-payment">
			<div class="VIP-balance">
				您的账户余额为$<%=((VIPPerson)user).getBalance() %>
				<form class="VIP-charge" method="post" action="VIPPay">
					<input type="text" name="bankcard" placeholder="银行卡">
					<input type="text" name="money" placeholder="金额">
					<input class="btn btn-primary" type="submit" name="submit" value="充值">
				</form>
			</div>
			<div class="VIP-payment-ID">
				回执编号
			</div>
			<div class="VIP-payment-date">
				缴费时间
			</div>
			<div class="VIP-payment-bankCard">
				银行卡
			</div>
			<div class="VIP-payment-num">
				缴费金额
			</div>
			<div class="VIP-payment-remark">
				备注
			</div>
			<%
				ArrayList<Payment> payments = service.getPaymentByVIP(user.getID());
				for(Payment payment : payments){
			%>
				<div class="VIP-payment-ID">
					<%=payment.getID() %>
				</div>
				<div class="VIP-payment-date">
					<%=payment.getDate() %>
				</div>
				<div class="VIP-payment-bankCard">
					<%=payment.getBankCard() %>
				</div>
				<div class="VIP-payment-num">
					<%=payment.getPayment() %>
				</div>
				<div class="VIP-payment-remark">
					<%=payment.getRemark() %>
				</div>
			<%
				}
			%>
		</div>
	</div>
	<%
		} else if(user instanceof VIPFamily){
	%>
	<div class="background-shadow"></div>
	<div class="main">
		<div class="tabs">
			<div class="active-tab" panel=".VIP-card">我的名片</div>
			<div class="inactive-tab" panel=".VIP-appointment">预约记录</div>
			<div class="inactive-tab" panel=".VIP-record">活动记录</div>
			<div class="inactive-tab" panel=".VIP-payment">缴费记录</div>
		</div>
		<div class="VIP-card">
			<div class="VIPfamily-card-label">我的名片</div>
			<div class="VIP-block">
				<div class="VIP-block-ID">
					<label>ID: </label>
					<input type="text" name="userID" readonly="readonly" value="<%=user.getID() %>">
				</div>
				<div class="VIP-block-username">
					<label>用户名: </label>
					<input type="text" name="username" readonly="readonly" value="<%=user.getUsername() %>">
				</div>
				<div class="VIP-block-balance">
					<label>账户余额: </label>
					<input type="text" name="balance" readonly="readonly" value="<%=((VIPFamily)user).getBalance() %>">
				</div>
				<div class="VIP-block-state">
					<label>账户状态: </label>
					<input type="text" name="state" readonly="readonly" value="<%=((VIPFamily)user).getState() %>">
				</div>
				<div class="VIP-block-registerDate">
					<label>注册时间: </label>
					<input type="text" name="registerDate" readonly="readonly" value="<%=((VIPFamily)user).getRegisterDate() %>">
				</div>
				<% 
					ArrayList<Person> persons = ((VIPFamily)user).getPersons();
					for(Person person : persons){
				%>
				<div class="VIP-block-person-panel">
					<div class="VIP-block-person-label">
						<%=person.getPetname() %>
					</div>
					<div class="VIP-block-person-info">
						<div class="VIP-block-person-ID none">
							<input type="text" name="personID" value="<%=person.getID() %>"/>
						</div>
						<div class="VIP-block-person-petname">
							<label>昵称: </label>
							<input type="text" name="petname" readonly="readonly" value="<%=person.getPetname() %>">
						</div>
						<div class="VIP-block-person-age">
							<label>年龄: </label>
							<input type="text" name="age" readonly="readonly" value="<%=person.getAge() %>">
						</div>
						<div class="VIP-block-person-sex">
							<label>性别: </label>
							<input type="text" name="sex" readonly="readonly" value="<%=person.getSex() %>">
						</div>
						<div class="VIP-block-person-address">
							<label>地址: </label>
							<input type="text" name="address" readonly="readonly" value="<%=person.getAddress() %>">
						</div>
						<div class="VIP-block-person-telephone">
							<label>手机: </label>
							<input type="text" name="telephone" readonly="readonly" value="<%=person.getTelephone() %>">
						</div>
						<div class="VIP-block-person-email">
							<label>邮箱: </label>
							<input type="text" name="email" readonly="readonly" value="<%=person.getEmail() %>">
						</div>
						<div class="VIP-block-person-realname">
							<label>姓名: </label>
							<input type="text" name="realname" readonly="readonly" value="<%=person.getRealname() %>">
						</div>
						<div class="VIP-block-person-residentID">
							<label>身份证: </label>
							<input type="text" name="residentID" readonly="readonly" value="<%=person.getResidentID() %>">
						</div>
						<div class="VIP-block-person-birthday">
							<label>生日: </label>
							<input type="text" name="birthday" readonly="readonly" value="<%=person.getBirthday() %>">
						</div>
					</div>
				</div>
				<% } %>
			</div>
		</div>
		<div class="VIP-appointment">
				<div class="VIP-appointment-activity">
					活动名称
				</div>
				<div class="VIP-appointment-session">
					活动场次
				</div>
				<div class="VIP-appointment-person">
					参加成员
				</div>
				<div class="VIP-appointment-date">
					活动日期
				</div>
				<div class="VIP-appointment-starttime">
					开始时间
				</div>
				<div class="VIP-appointment-place">
					活动地点
				</div>
			<%
				ArrayList<Appointment> appointments = service.getAppointmentsByVIP(user.getID());
				for(Appointment appointment : appointments){
					Activity activity = service.getActivityByID(appointment.getActivityID());
					ActivitySession activitySession = service.getActivitySessionByID(appointment.getActivityID(), appointment.getSession());
			%>
				<div class="VIP-appointment-activity">
					<a href="activity.jsp?ID=<%= activity.getID()%>"><%=activity.getName() %></a>
				</div>
				<div class="VIP-appointment-session">
					<%=appointment.getSession() %>
				</div>
				<div class="VIP-appointment-person">
					<%=appointment.getPerson().getRealname() %>
				</div>
				<div class="VIP-appointment-date">
					<%=activitySession.getDate() %>
				</div>
				<div class="VIP-appointment-starttime">
					<%=activitySession.getStarttime() %>
				</div>
				<div class="VIP-appointment-place">
					<%=activitySession.getPlace() %>
				</div>
			<%
				}
			%>
		</div>
		<div class="VIP-record">
			<div class="VIP-record-activity">
					活动名称
				</div>
				<div class="VIP-record-session">
					活动场次
				</div>
				<div class="VIP-record-person">
					参加成员
				</div>
				<div class="VIP-record-date">
					活动日期
				</div>
			<%
				ArrayList<Record> records = service.getRecordsByVIP(user.getID());
				for(Record record : records){
					Activity activity = service.getActivityByID(record.getActivityID());
					ActivitySession activitySession = service.getActivitySessionByID(record.getActivityID(), record.getSession());
			%>
				<div class="VIP-record-activity">
					<a href="activity.jsp?ID=<%= activity.getID()%>"><%=activity.getName() %></a>
				</div>
				<div class="VIP-record-session">
					<%=record.getSession() %>
				</div>
				<div class="VIP-record-person">
					<%=record.getPerson().getRealname() %>
				</div>
				<div class="VIP-record-date">
					<%=activitySession.getDate() %>
				</div>
			<%
				}
			%>
		</div>
		<div class="VIP-payment">
			<div class="VIP-balance">
				您的账户余额为$<%=((VIPFamily)user).getBalance() %>
				<form method="post" action="VIPPay">
					<input type="text" name="bankcard" placeholder="银行卡">
					<input type="text" name="money" placeholder="金额">
					<input class="btn btn-primary" type="submit" name="submit" value="充值">
				</form>
			</div>
			<div class="VIP-payment-ID">
				回执编号
			</div>
			<div class="VIP-payment-date">
				缴费时间
			</div>
			<div class="VIP-payment-bankCard">
				银行卡
			</div>
			<div class="VIP-payment-num">
				缴费金额
			</div>
			<div class="VIP-payment-remark">
				备注
			</div>
			<%
				ArrayList<Payment> payments = service.getPaymentByVIP(user.getID());
				for(Payment payment : payments){
			%>
				<div class="VIP-payment-ID">
					<%=payment.getID() %>
				</div>
				<div class="VIP-payment-date">
					<%=payment.getDate() %>
				</div>
				<div class="VIP-payment-bankCard">
					<%=payment.getBankCard() %>
				</div>
				<div class="VIP-payment-num">
					<%=payment.getPayment() %>
				</div>
				<div class="VIP-payment-remark">
					<%=payment.getRemark() %>
				</div>
			<%
				}
			%>
		</div>
	</div>
	<%
		}
	}
	%>
</body>
</html>