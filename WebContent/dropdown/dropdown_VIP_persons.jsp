<%@page import="edu.nju.healthClub.helper.AccountHelper"%>
<%@page import="edu.nju.healthClub.model.user.Person"%>
<%@page import="edu.nju.healthClub.model.user.VIP"%>
<%@page import="edu.nju.healthClub.service.CommonService"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	String VIPID = request.getParameter("VIPID");
	CommonService service = new CommonService();
	if(AccountHelper.validateID(VIPID)){
		VIP vip = service.getVIPByID(Integer.parseInt(VIPID));
		for(Person person : service.getPersonsOfVIP(vip)){
%>
	<div class="dropdown-item" personID="<%=person.getID()%>" name="<%=person.getName() %>" telephone="<%=person.getTelephone()%>"><%=person.getName() %></div>
<%
		}		
	}
%>