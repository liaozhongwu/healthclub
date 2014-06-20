<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/common.css">
<link rel="stylesheet" type="text/css" href="css/login.css">
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/login.js"></script>
<title>login</title>
</head>
<body>
	<jsp:include page="header.jsp" flush="true"/>
	<div class="main">
		<% if(request.getAttribute("error") != null){ %>
			<div class="error"><%=request.getAttribute("error") %></div>
		<% } %>
		<form class="loginForm" method="post" action="login">
			<input class="input-block-level" type="text" name="username" placeholder="用户名">
			<input class="input-block-level" type="password" name="password" placeholder="密码">
			<input class="btn btn-primary btn-block" type="submit" name="submit" value="登录">
		</form>
	</div>
</body>
</html>