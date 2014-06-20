<%@page import="java.util.ArrayList"%>
<%@page import="edu.nju.healthClub.model.*"%>
<%@page import="edu.nju.healthClub.service.UserService"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/common.css">
<link rel="stylesheet" type="text/css" href="css/waiter.css">
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/waiter.js"></script>
<title>waiter</title>
</head>
<body>
	<%@include file="checkSession.jsp"%>
	<jsp:include page="header.jsp"/>
	<div class="background-shadow"></div>
	<div class="waiter-activity">
		<a href="activity_create.jsp" class="btn btn-primary btn-block" >新建活动</a>
		<form class="waiter-activity-search" method="POST" action="searchActivity">
			<input class="waiter-activity-search-input input-block-level" type="text" name="key" placeholder="活动ID / 活动名称  / 活动介绍">
			<input class="waiter-activity-search-submit btn btn-primary" type="submit" name="submit" value="搜索"> 
		</form>
	</div>
	<div class="waiter-VIP">
		<form class="waiter-VIP-search" method="POST" action="searchUser">
			<input class="waiter-VIP-search-input input-block-level" type="text" name="key" placeholder="会员ID / 用户名">
			<input class="waiter-VIP-search-submit btn btn-primary" type="submit" name="submit" value="搜索"> 
		</form>
	</div>
</body>
</html>