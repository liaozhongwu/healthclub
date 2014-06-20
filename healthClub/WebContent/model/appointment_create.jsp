<%@page import="edu.nju.healthClub.model.user.Waiter"%>
<%@page import="edu.nju.healthClub.model.user.VIP"%>
<%@page import="edu.nju.healthClub.model.User"%>
<%@page import="java.util.Date"%>
<%@page import="edu.nju.healthClub.model.ActivitySession"%>
<%@page import="edu.nju.healthClub.model.Person"%>
<%@page import="edu.nju.healthClub.helper.AccountHelper"%>
<%@page import="edu.nju.healthClub.model.Appointment"%>
<%@page import="edu.nju.healthClub.service.UserService"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	UserService service = new UserService();
	if(session.getAttribute("userID") != null){
		String userID = (String) session.getAttribute("userID");
		User user = service.getUserByID(userID);
		String VIPID = null;
		if(user instanceof VIP){
			VIPID = userID;
			String personID = request.getParameter("personID");
			String activityID = request.getParameter("activityID");
			String activitySession = request.getParameter("activitySession");
			if(service.findAppointment(VIPID, personID, activityID, activitySession) == null){
				service.createAppointment(VIPID, personID, activityID, activitySession);
				Person person = service.getPersonByID(personID);
%>
	<div class="activity-VIP-appointment-line">
		<input style="display:none" type="text" name="personID" value="<%=personID %>">
		<input type="text" readonly="readonly" name="realname" value="<%=person.getRealname() %>">
		<input type="text" readonly="readonly" name="telephone" value="<%=person.getTelephone() %>">
		<input class="btn btn-primary activity-VIP-appointment-remove" type="button" value="取消预约">
	</div>
<%
			}
		}else if(user instanceof Waiter){
			VIPID = request.getParameter("VIPID");
			String personID = request.getParameter("personID");
			String activityID = request.getParameter("activityID");
			String activitySession = request.getParameter("activitySession");
			if(service.findAppointment(VIPID, personID, activityID, activitySession) == null){
				service.createAppointment(VIPID, personID, activityID, activitySession);
				Person person = service.getPersonByID(personID);
		
%>
	<div class="activity-appointment-line">
		<input type="text" readonly="readonly" name="VIPID" value="<%=VIPID %>">
		<input style="display:none" type="text" name="personID" value="<%=personID %>">
		<input type="text" readonly="readonly" name="realname" value="<%=person.getRealname() %>">
		<input type="text" readonly="readonly" name="telephone" value="<%=person.getTelephone() %>">
		<input class="btn btn-primary activity-appointment-remove" type="button" value="取消">
	</div>
<%
			}
		}
	}
%>
