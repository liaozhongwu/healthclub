<%@page import="edu.nju.healthClub.service.WaiterService"%>
<%@page import="java.util.Date"%>
<%@page import="edu.nju.healthClub.model.ActivitySession"%>
<%@page import="edu.nju.healthClub.model.Person"%>
<%@page import="edu.nju.healthClub.helper.AccountHelper"%>
<%@page import="edu.nju.healthClub.model.Appointment"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<% 
	String VIPID = request.getParameter("VIPID");
	String personID = request.getParameter("personID");
	String activityID = request.getParameter("activityID");
	String activitySession = request.getParameter("activitySession");
	
	WaiterService service = new WaiterService();
	if(service.findRecord(VIPID, personID, activityID, activitySession) == null){
		service.createRecord(VIPID, personID, activityID, activitySession);
		Person person = service.getPersonByID(personID);
%>
	<div class="activity-record-line">
		<input type="text" readonly="readonly" name="VIPID" value="<%=VIPID %>">
		<input style="display:none" type="text" name="personID" value="<%=personID %>">
		<input type="text" readonly="readonly" name="realname" value="<%=person.getRealname() %>">
		<input type="text" readonly="readonly" name="telephone" value="<%=person.getTelephone() %>">
		<input class="btn btn-primary activity-record-remove" type="button" value="取消">
	</div>
<%
	}
%>