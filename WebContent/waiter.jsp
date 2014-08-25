<%@page import="java.util.ArrayList"%>
<%@page import="edu.nju.healthClub.model.*"%>
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
<link rel="stylesheet" type="text/css" href="css/waiter.css">
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/waiter.js"></script>
<title>waiter</title>
</head>
<body>
	<%@include file="checkSession.jsp"%>
	<jsp:include page="header.jsp"/>
	<div class="main">
		<div class="tabs">
			<div id="waiter-activity-tab" class="inactive-tab">活动搜索</div>
			<div id="waiter-VIP-tab" class="inactive-tab">用户搜索</div>
			<div id="waiter-addActivity-tab" class="inactive-tab">新建活动</div>
		</div>
		<div id="showPanel"></div>
	</div>
</body>
</html>