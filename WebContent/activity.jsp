<%@page import="com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array"%>
<%@page import="edu.nju.healthClub.model.user.VIPType"%>
<%@page import="edu.nju.healthClub.model.user.VIPState"%>
<%@page import="edu.nju.healthClub.model.user.Person"%>
<%@page import="java.util.Date"%>
<%@page import="edu.nju.healthClub.service.CommonService"%>
<%@page import="edu.nju.healthClub.service.VIPService"%>
<%@page import="edu.nju.healthClub.service.WaiterService"%>
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
<link rel="stylesheet" type="text/css" href="css/datepicker.css" >
<link rel="stylesheet" type="text/css" href="css/common.css" >
<link rel="stylesheet" type="text/css" href="css/activity.css" >
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/activity.js"></script>
<title>activity</title>
</head>
<body>
	<%
		WaiterService service = new WaiterService();
		int userID;
		Activity activity = null;
		ArrayList<ActivitySession> activitySessions = null;
		ActivitySession currActivitySession = null;
		if(request.getAttribute("activity") != null){
			activity = (Activity) request.getAttribute("activity");
			activitySessions = service.getActivitySessionsOfActivity(activity);
			for(ActivitySession activitySession : activitySessions){
				if(ActivitySessionState.parseState(activitySession) != ActivitySessionState.END){
					currActivitySession = activitySession;
					break;
				}
			}
			if(currActivitySession == null){
				if(activitySessions.isEmpty()){
					currActivitySession = new ActivitySession();
					currActivitySession.setID(0);
				}else{
					currActivitySession = activitySessions.get(activitySessions.size() - 1);
				}
			}
		}
		if(request.getParameter("ID") != null){
			int ID = Integer.parseInt(request.getParameter("ID"));
			activity = service.getActivityByID(ID);
			activitySessions = service.getActivitySessionsOfActivity(activity);
			for(ActivitySession activitySession : activitySessions){
				if(ActivitySessionState.parseState(activitySession) != ActivitySessionState.END){
					currActivitySession = activitySession;
					break;
				}
			}
			if(currActivitySession == null){
				if(activitySessions.isEmpty()){
					currActivitySession = new ActivitySession();
					currActivitySession.setID(0);
				}else{
					currActivitySession = activitySessions.get(activitySessions.size() - 1);
				}
			}
		}
		if(request.getParameter("activitySessionID") != null){
			int activitySessionID = Integer.parseInt(request.getParameter("activitySessionID"));
			currActivitySession = service.getActivitySessionByID(activitySessionID);
			activity = currActivitySession.getActivity();
			activitySessions = service.getActivitySessionsOfActivity(activity);
		}
		if(activity != null){
		%>
		
		<jsp:include page="header.jsp" flush="true"/>
		<div class="activity">
			<div class="activity-display">
				<input id="activity-ID" type="text" style="display:none" value="<%=activity.getID()%>">
				<h1 id="activity-name"><%=activity.getName()%></h1>
				<h3 id="activity-introduction"><%=activity.getIntroduction()%></h3>
			</div>
			<div class="activity-sessionHead-window">
				<div class="activity-sessionHead-container">
			<%
				for(ActivitySession activitySession : activitySessions){
					ActivitySessionState state = ActivitySessionState.parseState(activitySession);
			%>
				<div class="activity-sessionHead<% if(state == ActivitySessionState.END){ %> activity-sessionHead-end<% } %>"index="<%=activitySession.getID() %>" <% if(activitySession.getID() == currActivitySession.getID()){ %>id="activity-sessionHead-curr"<%} %>>
					<div class="activity-sessionHead-name"><%=activitySession.getName()%></div>
					<div class="activity-sessionHead-date"><%=activitySession.getDate()%></div>
					<div class="activity-sessionHead-state"><%=state%></div>
				</div>
			<%
				}
			%>
				</div>
			</div>
			<div class="activity-session-window">
				<div class="activity-session-container">
				<%
					for(ActivitySession activitySession : activitySessions){
						ActivitySessionState state = ActivitySessionState.parseState(activitySession);
				%>
				<div class="activity-session" index="<%= activitySession.getID()%>" <% if(activitySession.getID() == currActivitySession.getID()){ %> id="activity-currSession" <%} %>>
					<div class="activity-session-info">
						<input type="text" style="display:none" name="activitySession" value="<%=activitySession.getID()%>">
						<div class="activity-session-info-name"><%=activitySession.getName()+"("+activitySession.getID()+")"%></div>
						<div class="activity-session-info-state"><% if(state == ActivitySessionState.END){ %>该活动场次已经结束，请选择其他场次<% } %><% if(state == ActivitySessionState.PROCEED){ %>该活动场次正在火爆进行!<% } %><% if(state == ActivitySessionState.FUTURE){ %>该活动场次尚未开始，敬请等待<% } %></div>
						<div class="activity-session-info-date"><%=activitySession.getDate()%></div>
						<div class="activity-session-info-time"><%=activitySession.getStarttime() + "至" + activitySession.getEndtime()%></div>
						<div class="activity-session-info-place">活动地点位于<%=activitySession.getPlace().getName()%></div>
						<div class="activity-session-info-coach">由著名教练<%=activitySession.getCoach().getName()%>执教</div>
					</div>
				</div>
				<%
					}
				%>
				</div>
			</div>
			<div class="activity-session-relative"></div>
			<%if(request.getSession().getAttribute("userID") != null && request.getSession().getAttribute("userType").equals("Waiter")){%>
				<input id="activity-modify" type="button" class="btn btn-info" value="编辑本活动">
				<input id="activitySession-modify" type="button" class="btn btn-info" value="编辑本场次">
				<input id="activitySession-add" type="button" class="btn btn-info" value="添加新场次">
				<input id="activitySession-delete" type="button" class="btn btn-warning" value="删除本场次">
			<%} %>
	<%
		}
	%>
	</div>
</body>
</html>