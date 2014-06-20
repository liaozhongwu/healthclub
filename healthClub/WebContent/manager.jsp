<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/common.css">
<link rel="stylesheet" type="text/css" href="css/manager.css">
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/highcharts.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/manager.js"></script>
<title>总经理</title>
</head>
<body>
	<%@include file="checkSession.jsp"%>
	<div class="navbar-static-top header">
		<div class="container">
			<div class="header-logo"><a href="index.jsp">healthClub</a></div>
			<div class="header-nav">
				<a href="about.jsp">关于</a>
				<a href="logout">注销</a>
			</div>
		</div>
	</div>
	<div class="btn-group">
		<input id="personAge" type="button" class="btn btn-primary" value="年龄统计">
		<input id="personSex" type="button" class="btn btn-primary" value="性别统计">
		<input id="personAddress" type="button" class="btn btn-primary" value="地址统计">
		<input id="VIPState" type="button" class="btn btn-primary" value="会员状态统计">
		<input id="recordByDay" type="button" class="btn btn-primary" value="每日使用统计">
		<input id="placeUsage" type="button" class="btn btn-primary" value="场地使用统计">
		<input id="coachUsage" type="button" class="btn btn-primary" value="教练活动统计">
	</div>
	<div id="container"></div>
</body>
</html>