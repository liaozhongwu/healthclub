<%@page import="edu.nju.healthClub.service.WaiterService"%>
<%@page import="edu.nju.healthClub.model.Activity"%>
<%@page import="edu.nju.healthClub.model.ActivitySession"%>
<%@page import="java.util.ArrayList"%>
<%@page import="edu.nju.healthClub.model.Coach"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="css/bootstrap.css" >
<link rel="stylesheet" type="text/css" href="css/common.css" >
<link rel="stylesheet" type="text/css" href="css/activity_modify.css" >
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/activity_modify.js"></script>
<title>Insert title here</title>
</head>
<body>
	<%@include file="checkSession.jsp"%>
	<%
		String ID = request.getParameter("ID");
		WaiterService service = new WaiterService();
		Activity activity = service.getActivityByID(ID);
		if(activity != null){
	%>
	<jsp:include page="header.jsp" flush="true"/>
	<form class="activity" method="POST" action="updateActivity">
		<div class="activity-ID">
			<label>活动ID:</label>
			<input class="input-block-level" type="text" readonly="readonly" name="ID" value="<%=activity.getID() %>">
		</div>
		<div class="activity-name">
			<label>活动名称:</label> 
			<input class="input-block-level" type="text" name="name" value="<%=activity.getName() %>">
		</div>
		<div class="activity-introduction">
			<label>活动介绍:</label> 
			<input class="input-block-level" type="text" name="introduction" value="<%=activity.getIntroduction() %>">
		</div>
	<%
		ArrayList<ActivitySession> activitySessions = activity.getSessions();
		for(ActivitySession activitySession : activitySessions){
	%>
		<div class="activity-session">
			<input type="button" class="activity-session-slide btn btn-primary" value="第<%=activitySession.getSession() %>场">
			<input type="text" style="display:none" name="activitySession" value="<%=activitySession.getSession() %>">
			<input type="button" class="activity-session-remove btn btn-primary" value="删除本场次">
			<div class="activity-session-info">
				<div class="activity-session-date">
					<label>日期:</label>
					<input class="input-block-level" type="date" name="date" value="<%=activitySession.getDate() %>">
				</div>
				<div class="activity-session-starttime">
					<label>开始时间:</label>
					<input class="input-block-level" type="time" name="starttime" value="<%=activitySession.getStarttime() %>">
				</div>
				<div class="activity-session-endtime">
					<label>结束时间:</label>
					<input class="input-block-level" type="time" name="endtime" value="<%=activitySession.getEndtime() %>">
				</div>
				<div class="activity-session-place">
					<label>地点:</label>
					<input class="input-block-level" type="text" name="place" value="<%=activitySession.getPlace() %>">
				</div>
				<div class="dropdown dropdown-search activity-session-coach">
				<label>教练: </label>
				<input class="dropdown-mutiSelect-input dropdown-coach-input dropdown-toggle sr-only input-block-level" type="text" data-toggle="dropdown" name="coach" placeholder="请选择教练">
				<%
					ArrayList<Coach> coachs = activitySession.getCoachs();
					for(Coach coach : coachs){
				%>
					<div class="dropdown-mutiSelect-tag activity-coach-tag" value="<%=coach.getID() %>"><%=coach.getName() %><div class="dropdown-mutiSelect-tag-remove"><img src="image/remove.png"></div></div>
				<%
					}
				%>
				<div class="dropdown-menu" role="menu">
					<%
						coachs = service.getCoachByKey("");
						for(Coach coach : coachs){
					%>
					<div class="dropdown-presentation" value="<%=coach.getID()%>">
						<div class="dropdown-coach">
							<img src="<%=coach.getPortrait() %>">
							<div class="dropdown-coach-info">
								<div class="dropdown-presentation-main" value="<%=coach.getName() %>">姓名: <%=coach.getName() %></div>
								<div>性别: <%=coach.getSex() %></div>								
								<div>技能: <%=coach.getMajor() %></div>
								<div>简介: <%=coach.getIntroduction() %></div>
							</div>
						</div>
					</div>
					<%
						}
					%>
				</div>
			</div>
			</div>
		</div>
	<%
		}
	%>
		<input type="button" class="activity-addSession btn btn-primary btn-block" value="添加新的场次" nextSession="2">
	<%
		}
	%>
		<div class="activity-btngroup">
			<input type="submit" class="btn btn-primary activity-update" name="submit" value="保存活动信息">
		</div>
	</form>
</body>
</html>