<%@page import="edu.nju.healthClub.service.WaiterService"%>
<%@page import="edu.nju.healthClub.model.user.Waiter"%>
<%@page import="edu.nju.healthClub.model.user.VIP"%>
<%@page import="edu.nju.healthClub.model.user.User"%>
<%@page import="edu.nju.healthClub.model.Coach"%>
<%@page import="edu.nju.healthClub.model.ActivitySession"%>
<%@page import="edu.nju.healthClub.model.Activity"%>
<%@page import="java.util.ArrayList"%>
<%@page import="edu.nju.healthClub.service.CommonService"%>
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
	<% CommonService service = new CommonService(); %>
	<jsp:include page="header.jsp" flush="true"/>
	<div class="main">
		<form id="activity-index-search" method="POST" action="searchActivity">
			<input id="activity-index-search-ID" class="input-block-level" type="text" name="search-ID" placeholder="活动ID">
			<input id="activity-index-search-name" class="input-block-level" type="text" name="search-name" placeholder="活动名称">
			<input id="activity-index-search-date" class="input-block-level" type="text" name="search-date" placeholder="日期">
			<input id="activity-index-search-time" class="input-block-level" type="text" name="search-time" placeholder="时间">
			<input id="activity-index-search-place" class="input-block-level" type="text" name="search-place" placeholder="地点">
			<input id="activity-index-search-coach" class="input-block-level" type="text" name="search-coach" placeholder="教练">
			<input id="activity-index-search-submit" class="btn btn-info btn-block" type="submit" name="submit" value="搜索"> 
		</form>
		<table id="activity-index-today">
			<thead>
				<tr>
					<td>活动名称</td>
					<td>活动介绍</td>
					<td>场次</td>
					<td>活动日期</td>
					<td>开始时间</td>
					<td>结束时间</td>
					<td>活动地点</td>
					<td>活动教练</td>
				</tr>
			</thead>
			<tbody>
			<%
				for(ActivitySession activitysession : service.getTodayActivitySessions()){
			%>
				<tr id="activity-index-today-activity">
					<td><a id="activity-index-today-link" href="activity.jsp?ID=<%=activitysession.getActivity().getID() %>">a</a></td>
					<td><%=activitysession.getActivity().getName() %></td>
					<td><%=activitysession.getActivity().getIntroduction() %></td>
					<td><%=activitysession.getName() %></td>
					<td><%=activitysession.getDate() %></td>
					<td><%=activitysession.getStarttime() %></td>
					<td><%=activitysession.getEndtime() %></td>
					<td><%=activitysession.getPlace().getName() %></td>
					<td><%=activitysession.getCoach().getName() %></td>
				<tr>
			<%
				}
			%>
			</tbody>
		</table>
	</div>
</body>
</html>