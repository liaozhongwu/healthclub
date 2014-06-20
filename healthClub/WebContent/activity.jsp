<%@page import="edu.nju.healthClub.service.VIPService"%>
<%@page import="edu.nju.healthClub.service.WaiterService"%>
<%@page import="edu.nju.healthClub.model.user.vip.VIPFamily"%>
<%@page import="edu.nju.healthClub.model.user.vip.VIPPerson"%>
<%@page import="edu.nju.healthClub.model.user.VIP"%>
<%@page import="edu.nju.healthClub.model.user.Waiter"%>
<%@page import="edu.nju.healthClub.model.*"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="css/bootstrap.css" >
<link rel="stylesheet" type="text/css" href="css/common.css" >
<link rel="stylesheet" type="text/css" href="css/activity.css" >
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/activity.js"></script>
<title>activity</title>
</head>
<body>
	
	<%
		String ID = request.getParameter("ID");
		WaiterService waiterService = new WaiterService();
		VIPService vipService = new VIPService(); 
		Activity activity = null;
		if(ID == null || ID.equals("")){
			activity = (Activity) request.getAttribute("activity");
		}else{
			activity = waiterService.getActivityByID(ID);
		}
		if(activity != null){
			int type = 0;
			if(session.getAttribute("userID") == null){
				type = 0;// no user
			}else{
				String userID = (String) session.getAttribute("userID");
				User user = waiterService.getUserByID(userID);
				if(user instanceof VIP){
					type = 1;// VIP type
				}else if(user instanceof Waiter){
					type = 2;// waiter type
				}
			}
	%>
		
		<jsp:include page="header.jsp" flush="true"/>
			<div class="activity">
				<div class="activity-display">
					<h1><%=activity.getName() %></h1>
					<h3><%=activity.getIntroduction() %></h3>
				</div>
				<div class="activity-ID">
					<label>活动ID:</label>
					<input class="input-block-level" type="text" readonly="readonly" name="ID" value="<%=activity.getID() %>">
				</div>
			<%
				ArrayList<ActivitySession> activitySessions = activity.getSessions();
				for(ActivitySession activitySession : activitySessions){
			%>
				<div class="activity-session">
					<div class="activity-session-label">
						第<%=activitySession.getSession() %>场
					</div>
					<div class="activity-session-info">
						<input type="text" style="display:none" name="activitySession" value="<%=activitySession.getSession() %>">
						<div class="activity-session-date">
							<label>日期:</label>
							<input class="input-block-level" type="text" readonly="readonly" name="date" value="<%=activitySession.getDate() %>">
						</div>
						<div class="activity-session-starttime">
							<label>开始时间:</label>
							<input class="input-block-level" type="text" readonly="readonly" name="starttime" value="<%=activitySession.getStarttime() %>">
						</div>
						<div class="activity-session-starttime">
							<label>结束时间:</label>
							<input class="input-block-level" type="text" readonly="readonly" name="endtime" value="<%=activitySession.getEndtime() %>">
						</div>
						<div class="activity-session-place">
							<label>地点:</label>
							<input class="input-block-level" type="text" readonly="readonly" name="place" value="<%=activitySession.getPlace() %>">
						</div>
						<%
							ArrayList<Coach> coachs = activitySession.getCoachs();
							String coach = "";
							for(Coach temp : coachs){
								coach = coach + temp.getName() + " ";
							}
						%>
						<div class="activity-session-coach">
							<label>教练:</label>
							<input class="input-block-level" type="text" readonly="readonly" value="<%=coach %>">
						</div>
						<% 
						if(type == 2){
						%>
							<div class="activity-session-btngroup">
								<input type="button" class="btn btn-primary activity-appointment-show" value="会员预约">
								<input type="button" class="btn btn-primary activity-record-show" value="会员记录">
							</div>
							<div class="activity-appointment">
								<%
								ArrayList<Appointment> appointments = waiterService.getAppointmentsByActivity(activitySession.getActivityID(),activitySession.getSession());
								for(Appointment appointment : appointments){
								%>
								<div class="activity-appointment-line">
									<input type="text" readonly="readonly" name="VIPID" value="<%=appointment.getVIPID() %>">
									<input style="display:none" type="text" name="personID" value="<%=appointment.getPerson().getID() %>">
									<input type="text" readonly="readonly" name="realname" value="<%=appointment.getPerson().getRealname() %>">
									<input type="text" readonly="readonly" name="telephone" value="<%=appointment.getPerson().getTelephone() %>">
									<input class="btn btn-primary activity-appointment-remove" type="button" value="取消">
								</div>
								<% 
								}
								%>
								<div class="activity-appointment-create">
									<input type="text" name="VIPID" placeholder="会员卡ID">
									<input style="display:none" type="text" name="personID">
									<div class="dropdown activity-appointment-realname">
										<input class="dropdown-button dropdown-toggle sr-only input-block-level" type="button" data-toggle="dropdown" name="realname" value="请选择成员">
										<div class="dropdown-menu" role="menu"></div>
									</div>
									<input type="text" name="telephone" placeholder="手机">
									<input class="btn btn-primary activity-appointment-create-button" type="button" value="预约">
								</div>
							</div>
							<div class="activity-record">
							<%
								ArrayList<Record> records = waiterService.getRecordsByActivity(activitySession.getActivityID(),activitySession.getSession());
								for(Record record : records){
							%>
								<div class="activity-record-line">
									<input type="text" readonly="readonly" name="VIPID" value="<%=record.getVIPID() %>">
									<input style="display:none" type="text" name="personID" value="<%=record.getPerson().getID() %>">
									<input type="text" readonly="readonly" name="realname" value="<%=record.getPerson().getRealname() %>">
									<input type="text" readonly="readonly" name="telephone" value="<%=record.getPerson().getTelephone() %>">
									<input class="btn btn-primary activity-record-remove" type="button" value="取消">
								</div>
							<% 
								}
							%>
								<div class="activity-record-create">
									<input type="text" name="VIPID" placeholder="会员卡ID">
									<input style="display:none" type="text" name="personID">
									<div class="dropdown activity-record-realname">
										<input class="dropdown-button dropdown-toggle sr-only input-block-level" type="button" data-toggle="dropdown" name="realname" value="请选择成员">
										<div class="dropdown-menu" role="menu"></div>
									</div>
									<input type="text" name="telephone" placeholder="手机">
									<input class="btn btn-primary activity-record-create-button" type="button" value="添加">
								</div>
							</div>
						<%
						}else if(type == 1){
							String userID = (String) session.getAttribute("userID");
							VIP user = (VIP)vipService.getUserByID(userID);
							if(user.getState().equals("active")){
						%>
							<div class="activity-VIP-appointment">
						<%
							ArrayList<Appointment> appointments = vipService.getAppointmentsByVIP((String) session.getAttribute("userID"));
							for(Appointment appointment : appointments){
								if(appointment.getActivityID() == activitySession.getActivityID() && appointment.getSession() == activitySession.getSession()){
						%>
							<div class="activity-VIP-appointment-line">
								<input type="text" style="display:none" name="personID" value="<%=appointment.getPerson().getID() %>">
								<input type="text" readonly="readonly" name="realname" value="<%=appointment.getPerson().getRealname() %>">
								<input type="text" readonly="readonly" name="telephone" value="<%=appointment.getPerson().getTelephone() %>">
								<input type="button" class="activity-VIP-appointment-remove btn btn-primary" value="取消预约">
							</div>
						<%
								}
							}
						%>
							<div class="activity-VIP-appointment-create">
								<div class="dropdown activity-VIP-appointment-realname">
									<input style="display:none" type="text" name="personID">
									<input class="dropdown-button dropdown-toggle sr-only input-block-level" type="button" data-toggle="dropdown" name="realname" value="请选择成员">
									<div class="dropdown-menu" role="menu">
									<%
										if(user instanceof VIPPerson){
									%>
										<div class="dropdown-presentation" value="<%=((VIPPerson)user).getPerson().getID()%>">
											<div class="dropdown-presentation-main" value="<%=((VIPPerson)user).getPerson().getRealname() %>" telephone="<%=((VIPPerson)user).getPerson().getTelephone() %>"><%=((VIPPerson)user).getPerson().getRealname() %></div>
										</div>
									<%
										} else if(user instanceof VIPFamily){
											for(Person person : ((VIPFamily)user).getPersons()){
									%>
										<div class="dropdown-presentation" value="<%=person.getID()%>">
											<div class="dropdown-presentation-main" value="<%=person.getRealname() %>" telephone="<%=person.getTelephone() %>"><%=person.getRealname() %></div>
										</div>
									<%		
											}
										}
									%>
									</div>
								</div>
								<input type="text" name="telephone">
								<input class="activity-VIP-appointment-create-button btn btn-primary" type="button" value="预约">
							</div>
							</div>
						<%
						}}
						%>
					</div>
				</div>
			<%
				}
				if(type == 2){
			%>
				<div class="activity-btngroup">
					<form method="POST" action="activity_modify.jsp">
						<input type="text" style="display:none" name="ID" value="<%= activity.getID()%>">
						<input type="submit" class="btn btn-primary activity-modify" value="修改活动信息">
					</form>
				</div>
			<%
				}
			%>
			</div>
	<%
		}
	%>
</body>
</html>