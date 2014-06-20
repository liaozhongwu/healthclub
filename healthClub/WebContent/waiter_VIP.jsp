<%@page import="edu.nju.healthClub.service.WaiterService"%>
<%@page import="edu.nju.healthClub.model.user.VIP"%>
<%@page import="java.util.ArrayList"%>
<%@page import="edu.nju.healthClub.model.user.vip.*"%>
<%@page import="edu.nju.healthClub.model.*"%>
<%@page import="edu.nju.healthClub.service.UserService"%>
<%@page import="java.util.Date"%>
<%@page import="edu.nju.healthClub.model.*"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/common.css">
<link rel="stylesheet" type="text/css" href="css/waiter_VIP.css">
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/waiter_VIP.js"></script>
<title>waiter_VIP</title>
</head>
<body>
	<%@include file="checkSession.jsp"%>
	<jsp:include page="header.jsp" flush="true"/>
	<div class="waiter-VIP">
		<form class="waiter-VIP-search" method="POST" action="searchUser">
			<input class="waiter-VIP-search-input input-block-level" type="text" name="key" placeholder="会员ID / 用户名">
			<input class="waiter-VIP-search-submit btn btn-primary" type="submit" name="submit" value="搜索"> 
		</form>
		<div class="tabs">
			<div class="active-tab" panel=".waiter-VIP-info">用户信息</div>
			<div class="inactive-tab" panel=".waiter-VIP-appointment">预约记录</div>
			<div class="inactive-tab" panel=".waiter-VIP-record">活动记录</div>
			<div class="inactive-tab" panel=".waiter-VIP-payment">缴费记录</div>
		</div>
		<div class="waiter-VIP-info">
	<% 
		WaiterService service = new WaiterService();
	if(request.getAttribute("user") != null){
		User user = (User)request.getAttribute("user");
		if(user instanceof VIPPerson){
	%>
		<div class="waiter-VIP-ID">
			<label>ID: </label>
			<input type="text" name="userID" readonly="readonly" value="<%=user.getID() %>">
		</div>
		<div class="waiter-VIP-username">
			<label>用户名: </label>
			<input type="text" name="username" value="<%=user.getUsername() %>">
		</div>
		<div class="waiter-VIP-password">
			<label>密码: </label>
			<input type="password" name="password">
		</div>
		<div class="waiter-VIP-balance">
			<label>账户余额: </label>
			<input type="text" name="balance" readonly="readonly" value="<%=((VIPPerson)user).getBalance() %>">
		</div>
		<div class="waiter-VIP-state">
			<label>账户状态: </label>
			<input type="text" name="state" readonly="readonly" value="<%=((VIPPerson)user).getState() %>">
		</div>
		<div class="waiter-VIP-registerDate">
			<label>注册时间: </label>
			<input type="text" name="registerDate" readonly="readonly" value="<%=((VIPPerson)user).getRegisterDate() %>">
		</div>
		<div class="waiter-VIP-person-panel">
			<div class="waiter-VIP-person-label">
				<%=((VIPPerson)user).getPerson().getPetname() %>
			</div>
			<div class="waiter-VIP-person-info">
				<div class="waiter-VIP-person-petname">
					<label>昵称: </label>
					<input type="text" name="petname" value="<%=((VIPPerson)user).getPerson().getPetname() %>">
				</div>
				<div class="waiter-VIP-person-age">
					<label>年龄: </label>
					<input type="text" name="age" value="<%=((VIPPerson)user).getPerson().getAge() %>">
				</div>
				<div class="waiter-VIP-person-sex">
					<label>性别: </label>
					<div class="dropdown">
						<input class="dropdown-button dropdown-toggle sr-only btn btn-block" type="button" data-toggle="dropdown" name="sex" value="<%=((VIPPerson)user).getPerson().getSex() %>">
						<div class="dropdown-menu" role="menu">
							<div class="dropdown-presentation" value="男"><div class="dropdown-presentation-main" value="男">男</div></div>
							<div class="dropdown-presentation" value="女"><div class="dropdown-presentation-main" value="女">女</div></div>
							<div class="dropdown-presentation" value="male"><div class="dropdown-presentation-main" value="stop">male</div></div>
							<div class="dropdown-presentation" value="female"><div class="dropdown-presentation-main" value="cancel">female</div></div>
						</div>
					</div>
				</div>
				<div class="waiter-VIP-person-address">
					<label>地址: </label>
					<input type="text" name="address" value="<%=((VIPPerson)user).getPerson().getAddress() %>">
				</div>
				<div class="waiter-VIP-person-telephone">
					<label>手机: </label>
					<input type="text" name="telephone" value="<%=((VIPPerson)user).getPerson().getTelephone() %>">
				</div>
				<div class="waiter-VIP-person-email">
					<label>邮箱: </label>
					<input type="text" name="email" value="<%=((VIPPerson)user).getPerson().getEmail() %>">
				</div>
				<div class="waiter-VIP-person-realname">
					<label>姓名: </label>
					<input type="text" name="realname" value="<%=((VIPPerson)user).getPerson().getRealname() %>">
				</div>
				<div class="waiter-VIP-person-residentID">
					<label>身份证: </label>
					<input type="text" name="residentID" value="<%=((VIPPerson)user).getPerson().getResidentID() %>">
				</div>
				<div class="waiter-VIP-person-birthday">
					<label>生日: </label>
					<input type="date" name="birthday" value="<%=((VIPPerson)user).getPerson().getBirthday() %>">
				</div>
			</div>
		</div>
		<div class="waiter-VIP-btngroup">
			<input class="btn btn-primary" type="button" name="save" value="保存信息">
		</div>
	<%
		} else if(user instanceof VIPFamily){
	%>
			<div class="waiter-VIP-ID">
				<label>ID: </label>
				<input type="text" name="userID" readonly="readonly" value="<%=user.getID() %>">
			</div>
			<div class="waiter-VIP-username">
				<label>用户名: </label>
				<input type="text" name="username" value="<%=user.getUsername() %>">
			</div>
			<div class="waiter-VIP-password">
				<label>密码: </label>
				<input type="password" name="password">
			</div>
			<div class="waiter-VIP-balance">
				<label>账户余额: </label>
				<input type="text" name="balance" readonly="readonly" value="<%=((VIPFamily)user).getBalance() %>">
			</div>
			<div class="waiter-VIP-state">
				<label>账户状态: </label>
				<input type="text" name="state" readonly="readonly" value="<%=((VIPFamily)user).getState() %>">
			</div>
			<div class="waiter-VIP-registerDate">
				<label>注册时间: </label>
				<input type="text" name="registerDate" readonly="readonly" value="<%=((VIPFamily)user).getRegisterDate() %>">
			</div>
			<% 
				ArrayList<Person> persons = ((VIPFamily)user).getPersons();
				for(Person person : persons){
			%>
			<div class="waiter-VIP-person-panel">
				<input type="button" class="waiter-VIP-person-slide btn btn-primary btn-block" value="<%=person.getPetname() %>">
				<div class="waiter-VIP-person-info">
					<div class="waiter-VIP-person-ID none">
						<input type="text" name="personID" value="<%=person.getID() %>"/>
					</div>
					<div class="waiter-VIP-person-petname">
						<label>昵称: </label>
						<input type="text" name="petname" value="<%=person.getPetname() %>">
					</div>
					<div class="waiter-VIP-person-age">
						<label>年龄: </label>
						<input type="text" name="age" value="<%=person.getAge() %>">
					</div>
					<div class="waiter-VIP-person-sex">
						<label>性别: </label>
						<div class="dropdown">
							<input class="dropdown-button dropdown-toggle sr-only btn btn-block" type="button" data-toggle="dropdown" name="sex" value="<%=person.getSex() %>">
							<div class="dropdown-menu" role="menu">
								<div class="dropdown-presentation" value="男"><div class="dropdown-presentation-main" value="男">男</div></div>
								<div class="dropdown-presentation" value="女"><div class="dropdown-presentation-main" value="女">女</div></div>
								<div class="dropdown-presentation" value="male"><div class="dropdown-presentation-main" value="stop">male</div></div>
								<div class="dropdown-presentation" value="female"><div class="dropdown-presentation-main" value="cancel">female</div></div>
							</div>
						</div>
					</div>
					<div class="waiter-VIP-person-address">
						<label>地址: </label>
						<input type="text" name="address" value="<%=person.getAddress() %>">
					</div>
					<div class="waiter-VIP-person-telephone">
						<label>手机: </label>
						<input type="text" name="telephone" value="<%=person.getTelephone() %>">
					</div>
					<div class="waiter-VIP-person-email">
						<label>邮箱: </label>
						<input type="text" name="email" value="<%=person.getEmail() %>">
					</div>
					<div class="waiter-VIP-person-realname">
						<label>姓名: </label>
						<input type="text" name="realname" value="<%=person.getRealname() %>">
					</div>
					<div class="waiter-VIP-person-residentID">
						<label>身份证: </label>
						<input type="text" name="residentID" value="<%=person.getResidentID() %>">
					</div>
					<div class="waiter-VIP-person-birthday">
						<label>生日: </label>
						<input type="date" name="birthday" value="<%=person.getBirthday() %>">
					</div>
					<input type="button" class="waiter-VIP-person-remove btn btn-primary btn-block" value="删除成员">
				</div>
			</div>
			<% } %>
			<input id="waiter-VIP-addPerson" type="button" class="btn btn-primary btn-block" value="添加家庭成员">
			<div class="waiter-VIP-btngroup">
				<input class="btn btn-primary" type="button" name="save" value="保存信息">
			</div>
	<% } %>
	</div>
	<div class="waiter-VIP-appointment">
		<div class="waiter-VIP-appointment-activity">
			活动名称
		</div>
		<div class="waiter-VIP-appointment-session">
			活动场次
		</div>
		<div class="waiter-VIP-appointment-person">
			参加成员
		</div>
		<div class="waiter-VIP-appointment-date">
			活动日期
		</div>
		<div class="waiter-VIP-appointment-starttime">
			开始时间
		</div>
		<div class="waiter-VIP-appointment-place">
			活动地点
		</div>
	<%
		ArrayList<Appointment> appointments = service.getAppointmentsByVIP(user.getID());
		for(Appointment appointment : appointments){
			Activity activity = service.getActivityByID(appointment.getActivityID());
			ActivitySession activitySession = service.getActivitySessionByID(appointment.getActivityID(), appointment.getSession());
	%>
		<div class="waiter-VIP-appointment-activity">
			<a href="activity.jsp?ID=<%= activity.getID()%>"><%=activity.getName() %></a>
		</div>
		<div class="waiter-VIP-appointment-session">
			<%=appointment.getSession() %>
		</div>
		<div class="waiter-VIP-appointment-person">
			<%=appointment.getPerson().getRealname() %>
		</div>
		<div class="waiter-VIP-appointment-date">
			<%=activitySession.getDate() %>
		</div>
		<div class="waiter-VIP-appointment-starttime">
			<%=activitySession.getStarttime() %>
		</div>
		<div class="waiter-VIP-appointment-place">
			<%=activitySession.getPlace() %>
		</div>
	<%
		}
	%>
	</div>
	<div class="waiter-VIP-record">
		<div class="waiter-VIP-record-activity">
				活动名称
			</div>
			<div class="waiter-VIP-record-session">
				活动场次
			</div>
			<div class="waiter-VIP-record-person">
				参加成员
			</div>
			<div class="waiter-VIP-record-date">
				活动日期
			</div>
		<%
			ArrayList<Record> records = service.getRecordsByVIP(user.getID());
			for(Record record : records){
				Activity activity = service.getActivityByID(record.getActivityID());
				ActivitySession activitySession = service.getActivitySessionByID(record.getActivityID(), record.getSession());
		%>
			<div class="waiter-VIP-record-activity">
				<a href="activity.jsp?ID=<%= activity.getID()%>"><%=activity.getName() %></a>
			</div>
			<div class="waiter-VIP-record-session">
				<%=record.getSession() %>
			</div>
			<div class="waiter-VIP-record-person">
				<%=record.getPerson().getRealname() %>
			</div>
			<div class="waiter-VIP-record-date">
				<%=activitySession.getDate() %>
			</div>
		<%
			}
		%>
	</div>
	<div class="waiter-VIP-payment">
		<div class="waiter-VIP-payment-balance">
			账户余额为$<%=((VIP)user).getBalance() %>
		</div>
		<div class="waiter-VIP-payment-ID">
			回执编号
		</div>
		<div class="waiter-VIP-payment-date">
			缴费时间
		</div>
		<div class="waiter-VIP-payment-bankCard">
			银行卡
		</div>
		<div class="waiter-VIP-payment-num">
			缴费金额
		</div>
		<div class="waiter-VIP-payment-remark">
			备注
		</div>
		<%
			ArrayList<Payment> payments = service.getPaymentByVIP(user.getID());
			for(Payment payment : payments){
		%>
			<div class="waiter-VIP-payment-ID">
				<%=payment.getID() %>
			</div>
			<div class="waiter-VIP-payment-date">
				<%=payment.getDate() %>
			</div>
			<div class="waiter-VIP-payment-bankCard">
				<%=payment.getBankCard() %>
			</div>
			<div class="waiter-VIP-payment-num">
				<%=payment.getPayment() %>
			</div>
			<div class="waiter-VIP-payment-remark">
				<%=payment.getRemark() %>
			</div>
		<%
			}
		%>
	</div>
	<%
		}
	%>
	</div>
</body>
</html>