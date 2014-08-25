<%@page import="edu.nju.healthClub.model.user.Person"%>
<%@page import="edu.nju.healthClub.model.ActivitySession"%>
<%@page import="edu.nju.healthClub.model.user.VIP"%>
<%@page import="edu.nju.healthClub.service.CommonService"%>
<%@page import="edu.nju.healthClub.model.Appointment"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <% 
	    int userID = (Integer)session.getAttribute("userID");
		CommonService service = new CommonService();
		VIP vip = service.getVIPByID(userID);
	%>
<table id="VIP-appointment">
	<thead>
		<tr>
		   	<td>活动名称</td>
			<td>参加成员</td>
			<td>活动场次</td>
			<td>场次日期</td>
			<td>场次时间</td>
			<td>场次地点</td>
			<td>预约时间</td>
		</tr>
	</thead>
	<tbody>
<%
	ArrayList<Appointment> appointments = service.getAppointmentsOfVIP(vip.getID());
	for(Appointment appointment : appointments){
		ActivitySession activitySession = appointment.getActivitySession();
		Person person = appointment.getPerson();
%>
		<tr>
			<td><a href="activity.jsp?ID=<%=activitySession.getActivity().getID()%>"><%=activitySession.getActivity().getName()%></a></td>
			<td><%=person.getName()%></td>
			<td><%=activitySession.getName()%></td>
			<td><%=activitySession.getDate()%></td>
			<td><%=activitySession.getStarttime()%></td>
			<td><%=activitySession.getPlace().getName()%></td>
			<td><%=appointment.getDate() %></td>
		</tr>
<%
	}
%>
	</tbody>
</table>