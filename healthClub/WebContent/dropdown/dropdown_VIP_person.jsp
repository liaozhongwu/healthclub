<%@page import="edu.nju.healthClub.model.Person"%>
<%@page import="edu.nju.healthClub.model.user.vip.VIPFamily"%>
<%@page import="edu.nju.healthClub.model.User"%>
<%@page import="edu.nju.healthClub.model.user.vip.VIPPerson"%>
<%@page import="edu.nju.healthClub.service.UserService"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	String VIPID = request.getParameter("VIPID");
	UserService service = new UserService();
	User user = service.getUserByID(VIPID);
	if(user instanceof VIPPerson){
%>
	<div class="dropdown-presentation" value="<%=((VIPPerson)user).getPerson().getID()%>">
		<div class="dropdown-presentation-main" value="<%=((VIPPerson)user).getPerson().getRealname() %>" telephone="<%=((VIPPerson)user).getPerson().getTelephone() %>"><%=((VIPPerson)user).getPerson().getRealname() %></div>
	</div>
<%
	} else if(user instanceof VIPFamily){
		for(Person person : ((VIPFamily)user).getPersons()){
%>
	<div class="dropdown-presentation" value="<%=person.getID()%>">
		<div class="dropdown-presentation-main" value="<%=person.getRealname() %>" telephone="<%=person.getTelephone() %>"><%=person.getRealname() %></div>
	</div>
<%		
}
	}
%>