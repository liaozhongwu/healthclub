<%@page import="edu.nju.healthClub.model.Coach"%>
<%@page import="java.util.ArrayList"%>
<%@page import="edu.nju.healthClub.service.WaiterService"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	String key = request.getParameter("key");
	WaiterService service = new WaiterService();
	ArrayList<Coach> coachs = service.getCoachByKey(key);
	for(Coach coach : coachs){
%>
<div class="dropdown-presentation" value="<%=coach.getID()%>">
	<div class="dropdown-coach">
		<img src="<%=coach.getPortrait() %>">
		<div class="dropdown-coach-info">
			<div class="dropdown-presentation-main" value="<%=coach.getName() %>">姓名: <%=coach.getName() %></div>
			<div>性别: <%=coach.getSex() %></div>
			<div>技能: <%=coach.getMajor() %></div>
			<div>简介: <%=coach.getIntroduction() %></div>
		</div>
	</div>
</div>
<%
	}
%>