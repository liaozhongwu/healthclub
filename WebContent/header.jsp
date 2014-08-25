<%@page import="edu.nju.healthClub.model.user.VIPState"%>
<%@page import="edu.nju.healthClub.model.user.Waiter"%>
<%@page import="edu.nju.healthClub.model.user.VIP"%>
<%@page import="edu.nju.healthClub.model.user.User"%>
<%@page import="edu.nju.healthClub.service.CommonService"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	CommonService service = new CommonService();
	String userType;
	int userID;
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
		userType = (String) session.getAttribute("userType");
		if(userType.equals("VIP")){
%>
		<div class="navbar-static-top header">
			<div class="container">
				<div class="header-logo"><a href="index.jsp">healthClub</a></div>
				<div class="header-nav">
					<a href="activity_index.jsp">活动主页</a>
					<a href="VIP.jsp">个人主页</a>
					<a href="logout">注销</a>
					<a href="about.jsp">关于</a>
				</div>
			</div>
		</div>
<%
		}else if(userType.equals("Waiter")){
%>
			<div class="navbar-static-top header">
				<div class="container">
					<div class="header-logo"><a href="index.jsp">healthClub</a></div>
					<div class="header-nav">
						<a href="waiter.jsp">服务主页</a>
						<a href="logout">注销</a>
						<a href="about.jsp">关于</a>
					</div>
				</div>
			</div>
<%
		}else if(userType.equals("Manager")){
%>
	
	<div class="navbar-static-top header">
		<div class="container">
			<div class="header-logo"><a href="index.jsp">healthClub</a></div>
			<div class="header-nav">
				<a href="about.jsp">关于</a>
				<a href="logout">注销</a>
			</div>
		</div>
	</div>
<%
		}
	}
%>