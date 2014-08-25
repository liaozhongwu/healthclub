<%@page import="edu.nju.healthClub.service.VIPService"%>
<%@page import="edu.nju.healthClub.model.user.*"%>
<%@page import="edu.nju.healthClub.model.*"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="css/bootstrap.css" >
<link rel="stylesheet" type="text/css" href="css/datepicker.css">
<link rel="stylesheet" type="text/css" href="css/common.css" >
<link rel="stylesheet" type="text/css" href="css/VIP.css" >
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/VIP.js"></script>
<title>个人主页</title>
</head>
<body>
	<%@include file="checkSession.jsp"%>
	<jsp:include page="header.jsp" flush="true"/>
	<div class="main">
		<div class="tabs">
			<div id="VIP-info-tab" class="inactive-tab">个人信息</div>
			<div id="VIP-appointment-tab" class="inactive-tab">预约记录</div>
			<div id="VIP-record-tab" class="inactive-tab">活动记录</div>
			<div id="VIP-payment-tab" class="inactive-tab">缴费记录</div>
		</div>
		<div id="showPanel"></div>
	</div>
</body>
</html>