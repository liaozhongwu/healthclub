<%@page import="edu.nju.healthClub.model.user.Waiter"%>
<%@page import="edu.nju.healthClub.model.user.VIP"%>
<%@page import="edu.nju.healthClub.service.CommonService"%>
<%@page import="edu.nju.healthClub.model.user.User"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/common.css">
<link rel="stylesheet" type="text/css" href="css/index.css">
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/index.js"></script>
<title>welcome</title>
</head>
<body>
	<jsp:include page="header.jsp" flush="true"/>
	<div class="index-page">
		<div class="index-display">
			<div id="myCarousel" class="carousel slide">
			  <ol class="carousel-indicators">
			    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
			    <li data-target="#myCarousel" data-slide-to="1"></li>
			    <li data-target="#myCarousel" data-slide-to="2"></li>
			  </ol>
			  <div class="carousel-inner">
			    <div class="active item"><img src="image/display1.jpg"></div>
			    <div class="item"><img src="image/display2.jpg"></div>
			    <div class="item"><img src="image/display3.jpg"></div>
			  </div>
			  <a class="carousel-control left" href="#myCarousel" data-slide="prev">&lsaquo;</a>
			  <a class="carousel-control right" href="#myCarousel" data-slide="next">&rsaquo;</a>
			</div>
		</div>
		<%if(session.getAttribute("userID") == null){ %>
		<div class="index-account">
			<form class="loginForm" method="POST" action="login">
		        <input type="text" class="input-block-level" name="username" placeholder="用户名">
		        <input type="password" class="input-block-level" name="password" placeholder="密码">
		        <input type="submit" class="btn btn-primary btn-block" name="submit" value="登录">
		    </form>
		</div>
		 <%} %>
	</div>
</body>
</html>