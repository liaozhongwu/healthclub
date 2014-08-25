<%@page import="edu.nju.healthClub.service.WaiterService"%>
<%@page import="edu.nju.healthClub.model.Coach"%>
<%@page import="java.util.ArrayList"%>
<%@page import="edu.nju.healthClub.service.CommonService"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%
    	String name = request.getParameter("name");
    %>
<div class="activity-session">
	<input type="button" class="activity-session-slide btn btn-primary" value="<%=name %>">
	<input type="button" class="activity-session-remove btn btn-primary" value="删除本场次">
	<div class="activity-session-info">
		<div class="activity-session-date">
			<label>日期:</label>
			<input class="input-block-level" type="text" name="date" placeholder="开始日期">
		</div>
		<div class="activity-session-starttime">
			<label>开始时间:</label>
			<input class="input-block-level" type="time" name="starttime" placeholder="开始时间">
		</div>
		<div class="activity-session-endtime">
			<label>结束时间:</label>
			<input class="input-block-level" type="time" name="endtime" placeholder="结束时间">
		</div>
		<div class="activity-session-place">
			<label>地点:</label>
			<input class="input-block-level" type="text" name="place" placeholder="地点">
		</div>
		<div class="dropdown activity-session-coach">
			<label>教练:</label>
			<input class="dropdown-mutiSelect-input dropdown-coach-input dropdown-toggle sr-only input-block-level" type="text" data-toggle="dropdown" name="coach" placeholder="请选择教练">
			<div class="dropdown-menu" role="menu">
				<%
					WaiterService service = new WaiterService();
									ArrayList<Coach> coachs = service.searchCoach("");
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
			</div>
		</div>
	</div>
</div>