<%@page import="edu.nju.healthClub.model.user.Waiter"%>
<%@page import="edu.nju.healthClub.model.user.VIP"%>
<%@page import="edu.nju.healthClub.model.User"%>
<%@page import="edu.nju.healthClub.service.UserService"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%	
	UserService service = new UserService();
	String userID = null;
	User user = null;
	if(session.getAttribute("userID") == null){
%>
	<div class="navbar-static-top header">
		<div class="container">
			<div class="header-logo"><a href="index.jsp">healthClub</a></div>
			<div class="header-nav">
				<a href="activity_index.jsp">活动主页</a>
				<a href="login.jsp">登录</a>
				<a href="register.jsp">注册</a>
				<a href="about.jsp">关于</a>
			</div>
		</div>
	</div>
<%
	}else{
		userID = (String) session.getAttribute("userID");
		user = service.getUserByID(userID);
		if(user instanceof VIP){
%>
		<div class="navbar-static-top header">
			<div class="container">
				<div class="header-logo"><a href="index.jsp">healthClub</a></div>
				<div class="header-nav">
					<a href="activity_index.jsp">活动主页</a>
					<a href="VIP.jsp">个人主页</a>
					<a href="logout">注销</a>
					<% if(!((VIP)user).getState().equals("freezing")){ %>
					<a id="freezeVIP" href="freezeVIP">冻结账户</a>
					<% } %>
					<a href="about.jsp">关于</a>
				</div>
			</div>
		</div>
<%
		}else if(user instanceof Waiter){
%>
			<div class="navbar-static-top header">
				<div class="container">
					<div class="header-logo"><a href="index.jsp">healthClub</a></div>
					<div class="header-nav">
						<a href="activity_index.jsp">活动主页</a>
						<a href="waiter_VIP.jsp">用户管理</a>
						<a href="logout">注销</a>
						<a href="about.jsp">关于</a>
					</div>
				</div>
			</div>
<%
		}
	}
%>