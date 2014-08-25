<%@page import="edu.nju.healthClub.model.Place"%>
<%@page import="edu.nju.healthClub.model.Coach"%>
<%@page import="java.util.ArrayList"%>
<%@page import="edu.nju.healthClub.service.WaiterService"%>
<%@page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <% WaiterService service = new WaiterService(); %>
<div id="hint">
	<div id="hint-content">
		<div id="hint-title">添加场次</div>
		<img id="hint-close" src="image/close.png" alt="close">
		<input id="hint-newActivitySession-name" class="input-block-level" type="text" name="name" state="inactive" placeholder="名称">
		<input id="hint-newActivitySession-date" class="input-block-level" type="text" name="date" state="inactive" placeholder="日期">
		<input id="hint-newActivitySession-starttime" class="input-block-level" type="text" name="starttime" state="inactive" placeholder="开始时间">
		<input id="hint-newActivitySession-endtime" class="input-block-level" type="text" name="endtime" state="inactive" placeholder="结束时间">
		<div id="hint-newActivitySession-dropdown-place" class="dropdown">
			<input class="dropdown-input sr-only input-block-level" type="text" data-toggle="dropdown" placeholder="请选择场地">
			<input id="hint-newActivitySession-placeID" class="dropdown-toggle" style="display: none" type="text" name="placeID" >
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
		<div id="hint-newActivitySession-dropdown-coach" class="dropdown">
			<input class="dropdown-input sr-only input-block-level" type="text" data-toggle="dropdown" placeholder="请选择教练">
			<input id="hint-newActivitySession-coachID" class="dropdown-toggle" style="display: none" type="text" name="coachID" >
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
		<input id="hint-confirm" type="button" class="btn btn-info btn-block" value="确认添加">
	</div>
	<script type="text/javascript">
		$("#hint-content").animate({"top" : "20%"}, 300);
		hint_newActivitySession_bind();
		$("#hint-close").click(hint_close);
		$("#hint-confirm").click(hint_confirm);
		function hint_confirm(){
			if(hint_newActivitySession_check()){
				$("#hint-content").animate({"top" : 0}, 300,function(){
					var activityID = $("#activity-ID").val();
					var name = $("#hint-newActivitySession-name").val();
					var date = $("#hint-newActivitySession-date").val();
					var starttime = $("#hint-newActivitySession-starttime").val();
					var endtime = $("#hint-newActivitySession-endtime").val();
					var placeID = $("#hint-newActivitySession-placeID").val();
					var coachID = $("#hint-newActivitySession-coachID").val();
					$.post("addActivitySession",{"activityID":activityID,"name":name,"date":date,"starttime":starttime,"endtime":endtime,"placeID":placeID,"coachID":coachID},function(data){
						location.reload();
					});
					hint_remove();
				});
			}
		}
		function hint_close(){
			$("#hint-content").animate({"top" : 0}, 300,function(){
				hint_remove();
			});
		}
		function hint_remove(){
			$("#hint").remove();
		}
		function hint_newActivitySession_bind(){
			$("#hint-newActivitySession-name").blur(checkContent);
			$("#hint-newActivitySession-date").datepicker({
				format: 'yyyy-mm-dd',
				autoclose: true
			});
			$("#hint-newActivitySession-starttime").blur(checkTime);
			$("#hint-newActivitySession-endtime").blur(checkTime);
			$("#hint-newActivitySession-dropdown-place").find(".dropdown-item").click(hint_newActivitySession_dropdown_selectPlace);
			$("#hint-newActivitySession-dropdown-coach").find(".dropdown-item").click(hint_newActivitySession_dropdown_selectCoach);
		}
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
		function hint_newActivitySession_check(){
			return checkContentOf($("#hint-newActivitySession-name")) &&
					checkDateOf($("#hint-newActivitySession-date")) &&
					checkTimeOf($("#hint-newActivitySession-starttime")) &&
					checkTimeOf($("#hint-newActivitySession-endtime")) && 
					checkPlaceOf($("#hint-newActivitySession-placeID")) && 
					checkCoachOf($("#hint-newActivitySession-coachID"));
		}
	</script>
</div>