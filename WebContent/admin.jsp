<%@page import="edu.nju.healthClub.service.AdminService"%>
<%@page import="edu.nju.healthClub.model.user.*"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="css/bootstrap.css" >
<link rel="stylesheet" type="text/css" href="css/common.css" >
<link rel="stylesheet" type="text/css" href="css/admin.css" >
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/admin.js"></script>
<title>admin</title>
</head>
<body>
	<%@include file="checkSession.jsp"%>
	<div class="navbar-static-top header">
		<div class="container">
			<div class="header-logo"><a href="index.jsp">healthClub</a></div>
			<div class="header-nav">
				<a href="about.jsp">关于</a>
				<a href="logout">注销登录</a>
			</div>
		</div>
	</div>
	<% 
		AdminService serive = new AdminService();
	%>
	<div class="main">
		<input type="button" class="btn btn-primary btn-block" name="waiter" value="服务员">
		<% for(Waiter waiter : serive.getAllWaiter()){ %>
			<div class="waiter">
				<input type="text" name="ID" readonly="readonly" value="<%=waiter.getID()%>">
				<input type="text" name="username" value="<%=waiter.getUsername()%>">
				<input type="text" name="password" value="<%=waiter.getPassword()%>">
				<input type="text" name="name" value="<%=waiter.getName()%>">
				<input type="button" class="btn btn-primary" name="save" value="保存">
				<input type="button" class="btn btn-primary" name="remove" value="删除">
			</div>
		<% } %>
		<form method="post" action="addWaiter">
			<input type="text" name="ID" placeholder="ID">
			<input type="text" name="username" placeholder="用户名">
			<input type="text" name="password" placeholder="密码">
			<input type="text" name="name" placeholder="昵称">
			<input type="submit" class="btn btn-primary " name="add" value="添加">
		</form>
		<input type="button" class="btn btn-primary btn-block" name="manager" value="总经理">
		<% for(Manager manager : serive.getAllManager()){ %>
			<div class="manager">
				<input type="text" name="ID" readonly="readonly" value="<%=manager.getID()%>">
				<input type="text" name="username" value="<%=manager.getUsername()%>">
				<input type="text" name="password" value="<%=manager.getPassword()%>">
				<input type="text" name="name" value="<%=manager.getName()%>">
				<input type="button" class="btn btn-primary " name="save" value="保存">
				<input type="button" class="btn btn-primary " name="remove" value="删除">
			</div>
		<% } %>
		<form method="post" action="addWaiter">
			<input type="text" name="ID" placeholder="ID">
			<input type="text" name="username" placeholder="用户名">
			<input type="text" name="password" placeholder="密码">
			<input type="text" name="name" placeholder="昵称">
			<input type="submit" class="btn btn-primary " name="add" value="添加">
		</form>
	</div>
</body>
</html>