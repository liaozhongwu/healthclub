<%@page import="edu.nju.healthClub.model.ActivitySessionState"%>
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
<link rel="stylesheet" type="text/css" href="css/datepicker.css">
<link rel="stylesheet" type="text/css" href="css/common.css">
<link rel="stylesheet" type="text/css" href="css/activity_index.css">
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/activity_index.js"></script>
<title>活动主页</title>
</head>
<body>
	<% CommonService service = new CommonService(); %>
	<jsp:include page="header.jsp" flush="true"/>
	<div class="main">
		<div class="tabs">
			<div id="VIP-todayActivity-tab" class="inactive-tab">今日活动</div>
			<div id="VIP-tomorrowActivity-tab" class="inactive-tab">明日活动</div>
			<div id="VIP-searchActivity-tab" class="inactive-tab">活动搜索</div>
		</div>
		<div id="showPanel"></div>
	</div>
</body>
</html>