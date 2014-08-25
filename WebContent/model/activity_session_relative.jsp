<%@page import="edu.nju.healthClub.model.ActivitySessionState"%>
<%@page import="edu.nju.healthClub.model.ActivitySession"%>
<%@page import="edu.nju.healthClub.service.CommonService"%>
<%@page import="edu.nju.healthClub.helper.AccountHelper"%>
<%@page import="edu.nju.healthClub.model.Record"%>
<%@page import="edu.nju.healthClub.model.user.VIPType"%>
<%@page import="edu.nju.healthClub.model.user.VIPState"%>
<%@page import="edu.nju.healthClub.model.user.Person"%>
<%@page import="edu.nju.healthClub.model.Appointment"%>
<%@page import="java.util.ArrayList"%>
<%@page import="edu.nju.healthClub.model.user.VIP"%>
<%@page import="edu.nju.healthClub.service.WaiterService"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <% 
    	String activitySessionID = request.getParameter("activitySessionID");
    	if(AccountHelper.validateID(activitySessionID)){
		    WaiterService service = new WaiterService();
    		ActivitySession currActivitySession = service.getActivitySessionByID(Integer.parseInt(activitySessionID));
    		ActivitySessionState state = ActivitySessionState.parseState(currActivitySession);
			int userID;
			String userType;
			VIP vip = null;
			if(request.getSession().getAttribute("userID") != null){
				userID = (Integer) request.getSession().getAttribute("userID");
				userType = (String) request.getSession().getAttribute("userType");
			}else{
				userID = 0;
				userType = "noUser";
			} 
	%>
    <% if(userType.equals("noUser")){ %>
		<h4>您还没有登录，请先<a href="index.jsp">登录</a>或<a href="register.jsp">注册</a></h4>
	<% } else if(userType.equals("VIP")){ 
		ArrayList<Appointment> appointments = service.getAppointmentsOfActivitySession(currActivitySession);
		vip = service.getVIPByID(userID);
		ArrayList<Person> persons = service.getPersonsOfVIP(vip);
	%>
		<div class="activity-session-appointment-VIP" activitySessionID="<%=currActivitySession.getID()%>">
			<%if(state == ActivitySessionState.END){%>
				<h4>本活动场次已结束，请选择其他场次</h4>
			<%}else{%>
			<h4>预约记录</h4>
			<%if(vip.getState() == VIPState.ACTIVE){ 
				if(vip.getType() == VIPType.SINGLE){
					Person person = persons.get(0);
					Appointment appointment = service.findAppointment(person.getID(), currActivitySession.getID());
					if(appointment == null){
			%>
					<h4>您未预约本场次，共有<%=appointments.size() %>人预约本场次</h4>
					<div class="activity-session-appointment-VIP-create">
						<input class="dropdown-toggle" type="text" name="personID" style="display:none"  value="<%=person.getID()%>">
						<input type="text" readonly="readonly" name="name" value="<%=person.getName()%>">
						<input type="text" readonly="readonly" name="telephone" value="<%=person.getTelephone()%>">
						<input type="button" class="activity-session-appointment-VIP-create-button btn btn-info" value="预约本场次"> 
					</div>
			<%
					}
					else{
			%>
					<div class="activity-session-appointment-VIP-line">
						<input type="text" style="display:none" name="personID" value="<%=appointment.getPerson().getID()%>">
						<input type="text" readonly="readonly" name="name" value="<%=appointment.getPerson().getName()%>">
						<input type="text" readonly="readonly" name="telephone" value="<%=appointment.getPerson().getTelephone()%>">
						<input type="button" class="activity-session-appointment-VIP-remove-button btn btn-primary" value="取消预约">
					</div>
					<h4>您已预约本场次，共有<%=appointments.size() %>人预约本场次</h4>
			<%
					}
				}else if(vip.getType() == VIPType.FAMILY){
					int appointNum = 0;
					for(Person person : persons){
						Appointment appointment = service.findAppointment(person.getID(), currActivitySession.getID());
						if(appointment != null){
							appointNum++;
			%>
						<div class="activity-session-appointment-VIP-line">
							<input type="text" style="display:none" name="personID" value="<%=appointment.getPerson().getID()%>">
							<input type="text" readonly="readonly" name="name" value="<%=appointment.getPerson().getName()%>">
							<input type="text" readonly="readonly" name="telephone" value="<%=appointment.getPerson().getTelephone()%>">
							<input type="button" class="activity-session-appointment-VIP-remove-button btn btn-primary" value="取消预约">
						</div>
			<%
						}
					}
					if(appointNum == 0){
			%>
					<h4>您未预约本场次，共有<%=appointments.size() %>人预约本场次</h4>
					<div class="activity-session-appointment-VIP-create">
						<input class="dropdown-toggle" type="text" name="personID" style="display:none">
						<div class="dropdown">
							<input class="dropdown-button btn" type="button" data-toggle="dropdown" name="name" value="请选择成员">
							<div class="dropdown-menu" role="menu">
								<% for(Person person : persons){%>
								<div class="dropdown-item" personID="<%=person.getID()%>" name="<%=person.getName()%>" telephone="<%=person.getTelephone()%>" "><%=person.getName()%></div>
								<% } %>
							</div>
						</div>
						<input type="text" readonly="readonly" name="telephone">
						<input type="button" class="activity-session-appointment-VIP-create-button btn btn-info" value="预约本场次"> 
					</div>
			<%		
					}else if(appointNum < persons.size()){
			%>
					<h4>您有<%=appointNum %>位成员已预约本场次，共有<%=appointments.size() %>人预约本场次</h4>
					<div class="activity-session-appointment-VIP-create">
						<input class="dropdown-toggle" type="text" name="personID" style="display:none">
						<div class="dropdown">
							<input class="dropdown-button btn" type="button" data-toggle="dropdown" name="name" value="请选择成员">
							<div class="dropdown-menu" role="menu">
								<% for(Person person : persons){%>
								<div class="dropdown-item" personID="<%=person.getID()%>" name="<%=person.getName()%>" telephone="<%=person.getTelephone()%>" ><%=person.getName()%></div>
								<% } %>
							</div>
						</div>
						<input type="text" readonly="readonly" name="telephone">
						<input type="button" class="activity-session-appointment-VIP-create-button btn btn-info" value="预约本场次"> 
					</div>
			<%			
					}else{
			%>
					<h4>您的所有成员已预约本场次，共有<%=appointments.size() %>人预约本场次</h4>
			<%			
					}
				}
			%>
				
			<% } else if(vip.getState() == VIPState.UNACTIVE) { %>
				<div>对不起，您的账户未激活，请转到<a href="VIP.jsp">个人主页</a></div>
			<% } else { %>
				<div>对不起，您的账户异常，请转到<a href="VIP.jsp">个人主页</a></div>
			<% } %>
		</div>
	<% }
	} else if(userType.equals("Waiter")){
		if(state == ActivitySessionState.END){
	%>
		<h4>本活动场次已结束，请选择其他场次</h4>
	<%}else{
		ArrayList<Appointment> appointments = service.getAppointmentsOfActivitySession(currActivitySession);
		ArrayList<Record> records = service.getRecordsOfActivitySession(currActivitySession);
	%>
		<div class="activity-session-appointment-waiter" activitySessionID="<%=currActivitySession.getID()%>">
			<h4>预约记录</h4>
		<% for(Appointment appointment : appointments){ %>
			<div class="activity-session-appointment-waiter-line">
				<input type="text" readonly="readonly" name="VIPID" value="<%=appointment.getPerson().getVIP().getID()%>">
				<input type="text" style="display:none" name="personID" value="<%=appointment.getPerson().getID()%>">
				<input type="text" readonly="readonly" name="name" value="<%=appointment.getPerson().getName()%>">
				<input type="text" readonly="readonly" name="telephone" value="<%=appointment.getPerson().getTelephone()%>">
				<input type="button" class="activity-session-appointment-waiter-remove-button btn btn-primary" value="取消预约">
			</div>
		<% }%>
			<div class="activity-session-appointment-waiter-create">
				<input type="text" name="VIPID" placeholder="会员卡号">
				<input class="dropdown-toggle" type="text" name="personID" style="display:none">
				<div class="dropdown">
					<input class="dropdown-button btn" type="button" data-toggle="dropdown" name="name" value="请选择成员">
					<div class="dropdown-menu" role="menu"></div>
				</div>
				<input type="text" readonly="readonly" name="telephone" placeholder="手机号">
				<input type="button" class="activity-session-appointment-waiter-create-button btn btn-info" value="预约本场次"> 
			</div>
		</div>
		<div class="activity-session-record" activitySessionID="<%=currActivitySession.getID()%>">
			<h4>参加记录</h4>
		<% for(Record record : records){ %>
			<div class="activity-session-record-line">
				<input type="text" readonly="readonly" name="VIPID" value="<%=record.getPerson().getVIP().getID()%>">
				<input type="text" style="display:none" name="personID" value="<%=record.getPerson().getID()%>">
				<input type="text" readonly="readonly" name="name" value="<%=record.getPerson().getName()%>">
				<input type="text" readonly="readonly" name="telephone" value="<%=record.getPerson().getTelephone()%>">
				<input type="button" class="activity-session-record-remove-button btn btn-primary" value="删除记录">
			</div>
		<% }%>
			<div class="activity-session-record-create">
				<input type="text" name="VIPID" placeholder="会员卡号">
				<input class="dropdown-toggle" type="text" name="personID" style="display:none">
				<div class="dropdown">
					<input class="dropdown-button btn" type="button" data-toggle="dropdown" name="name" value="请选择成员">
					<div class="dropdown-menu" role="menu"></div>
				</div>
				<input type="text" readonly="readonly" name="telephone" placeholder="手机号">
				<input type="button" class="activity-session-record-create-button btn btn-info" value="记录本场次"> 
			</div>
		</div>
	<%
			}
		}
	}
	%>