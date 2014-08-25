<%@page import="edu.nju.healthClub.service.WaiterService"%>
<%@page import="edu.nju.healthClub.helper.AccountHelper"%>
<%@page import="edu.nju.healthClub.model.user.Person"%>
<%@page import="edu.nju.healthClub.model.ActivitySession"%>
<%@page import="edu.nju.healthClub.model.Record"%>
<%@page import="java.util.ArrayList"%>
<%@page import="edu.nju.healthClub.model.user.VIP"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<% 
	String VIPID = request.getParameter("VIPID");
	if(AccountHelper.validateID(VIPID)){
		WaiterService service = new WaiterService();
		VIP vip = service.getVIPByID(Integer.parseInt(VIPID));
%>
<table id="VIP-record">
	<thead>
		<tr>
			<td>活动名称</td>
			<td>参加成员</td>
			<td>活动场次</td>
			<td>场次日期</td>
			<td>场次时间</td>
			<td>场次地点</td>
		</tr>
	</thead>
	<tbody>
<%
	ArrayList<Record> records = service.getRecordsOfVIP(vip.getID());
	for(Record record : records){
		ActivitySession activitySession = record.getActivitySession();
		Person person = record.getPerson();
%>
	<tr>
		<td><a href="activity.jsp?ID=<%=activitySession.getActivity().getID()%>"><%=activitySession.getActivity().getName()%></a></td>
		<td><%=person.getName()%></td>
		<td><%=activitySession.getName()%></td>
		<td><%=activitySession.getDate()%></td>
		<td><%=activitySession.getStarttime() %></td>
		<td><%=activitySession.getPlace().getName() %></td>
	</tr>
<%
	}
%>
	</tbody>
</table>
<%}%>