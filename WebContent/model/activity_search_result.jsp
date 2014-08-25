<%@page import="edu.nju.healthClub.model.ActivitySession"%>
<%@page import="edu.nju.healthClub.service.CommonService"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <% 
    	CommonService service = new CommonService();
    	String activityID = request.getParameter("activityID");
    	String name = request.getParameter("name");
    	String date = request.getParameter("date");
    	String time = request.getParameter("time");
    	String placeID = request.getParameter("placeID");
    	String coachID = request.getParameter("coachID");
    %>
<table class="activity-table">
	<thead>
		<tr>
			<td>活动名称</td>
			<td>场次</td>
			<td>活动日期</td>
			<td>开始时间</td>
			<td>结束时间</td>
			<td>活动地点</td>
			<td>活动教练</td>
		</tr>
	</thead>
	<tbody>
	<%
		for(ActivitySession activitysession : service.searchActivitySessions(activityID,name,date,time,placeID,coachID)){
	%>
		<tr class="activity-table-row">
			<td><a href="activity.jsp?ID=<%=activitysession.getActivity().getID() %>"><%=activitysession.getActivity().getName() %></a></td>
			<td><%=activitysession.getName() %></td>
			<td><%=activitysession.getDate() %></td>
			<td><%=activitysession.getStarttime() %></td>
			<td><%=activitysession.getEndtime() %></td>
			<td><%=activitysession.getPlace().getName() %></td>
			<td><%=activitysession.getCoach().getName() %></td>
		<tr>
	<%
		}
	%>
	</tbody>
</table>
<script type="text/javascript">
	$(".activity-table-row").click(function(){
		location.href = $(this).find("a").attr("href");
	});
</script>