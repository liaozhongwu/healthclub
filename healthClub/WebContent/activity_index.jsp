<%@page import="edu.nju.healthClub.service.WaiterService"%>
<%@page import="edu.nju.healthClub.model.user.Waiter"%>
<%@page import="edu.nju.healthClub.model.user.VIP"%>
<%@page import="edu.nju.healthClub.model.User"%>
<%@page import="edu.nju.healthClub.model.Coach"%>
<%@page import="edu.nju.healthClub.model.ActivitySession"%>
<%@page import="edu.nju.healthClub.model.Activity"%>
<%@page import="java.util.ArrayList"%>
<%@page import="edu.nju.healthClub.service.UserService"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/common.css">
<link rel="stylesheet" type="text/css" href="css/activity_index.css">
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/activity_index.js"></script>
<title>活动主页</title>
</head>
<body>
	<jsp:include page="header.jsp" flush="true"/>
	<div class="main">
		<form class="activity-index-search" method="POST" action="searchActivity">
			<% if(session.getAttribute("userID") != null){
				UserService service = new UserService();
				String type = service.getUserType((String) session.getAttribute("userID"));
				if(type.equals("Waiter")){
			%>
			<a href="activity_create.jsp" class="btn btn-primary btn-block" >新建活动</a>
			<%
				}
			}%>
			<input class="activity-index-search-input" type="text" name="key" placeholder="活动ID / 活动名称 / 活动简介 ">
			<input class="activity-index-search-submit btn btn-primary" type="submit" name="submit" value="搜索"> 
		</form>
		<div class="activity-index-header">
			<div class="activity-index-name">
				活动名称
			</div>
			<div class="activity-index-introduction">
				活动介绍
			</div>
			<div class="activity-index-session-ID">
				场次
			</div>
			<div class="activity-index-session-date">
				活动日期
			</div>
			<div class="activity-index-session-starttime">
				开始时间
			</div>
			<div class="activity-index-session-endtime">
				结束时间
			</div>
			<div class="activity-index-session-place">
				活动地点
			</div>
			<div class="activity-index-session-coach">
				活动教练
			</div>
		</div>
	<%
		ArrayList<Activity> activities;
		if(request.getAttribute("activities") != null){
			activities = (ArrayList<Activity>) request.getAttribute("activities");
		}else{
			UserService service = new UserService();
			activities = service.getActivityByKey("");
		}
		for(Activity activity : activities){
	%>
		<form method="post" action="viewActivity">
		<div class="activity-index">
			<div class="activity-index-ID" style="display:none">
				<input type="text" name="ID" value="<%=activity.getID() %>">
			</div>
			<div class="activity-index-name">
				<%=activity.getName() %>
			</div>
			<div class="activity-index-introduction">
				<%=activity.getIntroduction() %>
			</div>
			<div class="activity-index-sessions">
			<%
				ArrayList<ActivitySession> activitySessions = activity.getSessions();
				for(ActivitySession activitySession : activitySessions){
			%>
				<div class="activity-index-session">
					<div class="activity-index-session-ID">
						<%=activitySession.getSession() %>
					</div>
					<div class="activity-index-session-date">
						<%=activitySession.getDate() %>
					</div>
					<div class="activity-index-session-starttime">
						<%=activitySession.getStarttime() %>
					</div>
					<div class="activity-index-session-endtime">
						<%=activitySession.getEndtime() %>
					</div>
					<div class="activity-index-session-place">
						<%=activitySession.getPlace() %>
					</div>
			<%
					String coachStr = "";
					for(Coach coach : activitySession.getCoachs()){
						coachStr += coach.getName() + " ";
					}
			%>
					<div class="activity-index-session-coach">
						<%=coachStr %>
					</div>
				</div>
			<%
				}
			%>
			</div>
		</div>
		</form>
	<%
		}
	%>
	</div>
</body>
</html>