<%@page import="edu.nju.healthClub.model.ActivitySessionState"%>
<%@page import="edu.nju.healthClub.service.CommonService"%>
<%@page import="edu.nju.healthClub.model.ActivitySession"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
   <div id="activity-today">
		<%  
			CommonService service = new CommonService();
			ArrayList<ActivitySession> todayActivitySessions = service.getTodayActivitySessions();
			if(todayActivitySessions.size() == 0){
		%>
			<h4>今天没有任何活动哦亲，不妨尝试<a class="activity-index-search">活动搜索</a></h4>
		<%
			}else{
		%>
			<h4>今日活动，共有<%=todayActivitySessions.size() %>场</h4>
			<table class="activity-table">
			<thead>
				<tr>
					<td>活动名称</td>
					<td>场次名称</td>
					<td>场次日期</td>
					<td>开始时间</td>
					<td>结束时间</td>
					<td>场次地点</td>
					<td>场次教练</td>
					<td>场次状态</td>
				</tr>
			</thead>
			<tbody>
			<%
				for(ActivitySession activitysession : todayActivitySessions){
			%>
				<tr class="activity-table-row">
					<td><a href="activity.jsp?activitySessionID=<%=activitysession.getID()%>"><%=activitysession.getActivity().getName() %></a></td>
					<td><%=activitysession.getName() %></td>
					<td><%=activitysession.getDate() %></td>
					<td><%=activitysession.getStarttime() %></td>
					<td><%=activitysession.getEndtime() %></td>
					<td><%=activitysession.getPlace().getName() %></td>
					<td><%=activitysession.getCoach().getName() %></td>
					<td><%=ActivitySessionState.parseState(activitysession) %></td>
				<tr>
			<%
				}
			%>
			</tbody>
		</table>
		<%
			}
		%>
	</div>
	<script type="text/javascript">
		$(".activity-index-search").click(function(){
			$("#VIP-searchActivity-tab").click();
		});
		$(".activity-table-row").click(function(){
			location.href = $(this).find("a").attr("href");
		});
	</script>