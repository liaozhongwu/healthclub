<%@page import="edu.nju.healthClub.service.CommonService"%>
<%@page import="edu.nju.healthClub.model.Coach"%>
<%@page import="edu.nju.healthClub.model.Place"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <% CommonService service = new CommonService(); %>
<div id="VIP-activity-search">
	<input id="VIP-activity-search-activityID" class="input-block-level" type="text" name="activityID" placeholder="活动ID">
	<input id="VIP-activity-search-name" class="input-block-level" type="text" name="name" placeholder="活动名称">
	<input id="VIP-activity-search-date" class="input-block-level" type="text" name="date" placeholder="日期">
	<input id="VIP-activity-search-time" class="input-block-level" type="text" name="time" placeholder="时间">
	<div id="VIP-activity-search-dropdown-place" class="dropdown">
		<input id="VIP-activity-search-placeID" class="dropdown-toggle" style="display: none" type="text" name="placeID" >
		<input class="dropdown-input sr-only input-block-level" type="text" data-toggle="dropdown" placeholder="场地">
		<div class="dropdown-menu" role="menu">
		<%
			ArrayList<Place> places = service.searchPlace("");
			for(Place place : places){
		%>
			<div class="dropdown-item" placeID="<%=place.getID()%>" name="<%=place.getName()%>">
				<div class="dropdown-place">
					<div><%=place.getName() %></div>	
				</div>
			</div>
		<%
			}
		%>
		</div>
	</div>
	<div id="VIP-activity-search-dropdown-coach" class="dropdown">
		<input id="VIP-activity-search-coachID" class="dropdown-toggle" style="display: none" type="text" name="coachID" >
		<input class="dropdown-input sr-only input-block-level" type="text" data-toggle="dropdown" placeholder="教练">
		<div class="dropdown-menu" role="menu">
		<%
			ArrayList<Coach> coachs = service.searchCoach("");
			for(Coach coach : coachs){
		%>
			<div class="dropdown-item" coachID="<%=coach.getID()%>" name="<%=coach.getName()%>">
				<div class="dropdown-coach">
					<img src="<%=coach.getPortrait() %>">
					<div class="dropdown-coach-info">
						<div>姓名: <%=coach.getName() %></div>
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
	<input id="VIP-activity-search-button" class="btn btn-info btn-block" type="button" value="搜索"> 
</div>
<div id="VIP-activity-search-result"></div>
<script type="text/javascript">
	$("#VIP-activity-search-dropdown-place").find(".dropdown-item").click(hint_newActivitySession_dropdown_selectPlace);
	$("#VIP-activity-search-dropdown-coach").find(".dropdown-item").click(hint_newActivitySession_dropdown_selectCoach);
	$("#VIP-activity-search-date").datepicker();
	$("#VIP-activity-search-button").click(function(){
		var activityID = $("#VIP-activity-search-activityID").val();
		var name = $("#VIP-activity-search-name").val();
		var date = $("#VIP-activity-search-date").val();
		var time = $("#VIP-activity-search-time").val();
		var placeID = $("#VIP-activity-search-placeID").val();
		var coachID = $("#VIP-activity-search-coachID").val();
		$.post("model/activity_search_result.jsp",{"activityID":activityID,"name":name,"date":date,"time":time,"placeID":placeID,"coachID":coachID},function(data){
			$("#VIP-activity-search").animate({"left":0},300);
			var result = $("#VIP-activity-search-result");
			result.animate({"left":640,"opacity": 0}, 300, function(){
				result.html(data).animate({"left":320,"opacity": 1});
			});
		});
	});
	function hint_newActivitySession_dropdown_selectPlace(){
		var placeID = $(this).attr("placeID");
		var name = $(this).attr("name");
		var dropdown = $(this).closest(".dropdown");
		dropdown.find(".dropdown-toggle").attr("value", placeID);
		dropdown.find(".dropdown-input").val(name);			
	}
	function hint_newActivitySession_dropdown_selectCoach(){
		var coachID = $(this).attr("coachID");
		var name = $(this).attr("name");
		var dropdown = $(this).closest(".dropdown");
		dropdown.find(".dropdown-toggle").attr("value", coachID);
		dropdown.find(".dropdown-input").val(name);			
	}
</script>