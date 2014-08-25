<%@page import="edu.nju.healthClub.model.ActivitySession"%>
<%@page import="edu.nju.healthClub.helper.ActivityHelper"%>
<%@page import="edu.nju.healthClub.helper.AccountHelper"%>
<%@page import="edu.nju.healthClub.model.Place"%>
<%@page import="edu.nju.healthClub.model.Coach"%>
<%@page import="java.util.ArrayList"%>
<%@page import="edu.nju.healthClub.service.WaiterService"%>
<%@page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <% WaiterService service = new WaiterService(); 
    	String activitySessionID = request.getParameter("activitySessionID");
    	if(ActivityHelper.validateID(activitySessionID)){
			ActivitySession activitySession = service.getActivitySessionByID(Integer.parseInt(activitySessionID));
    %>
<div id="hint">
	<div id="hint-content">
		<div id="hint-title">修改场次信息</div>
		<img id="hint-close" src="image/close.png" alt="close">
		<input id="hint-modifyActivitySession-ID" style="display:none" type="text" name="activitySessionID" value="<%=activitySession.getID() %>">
		<input id="hint-modifyActivitySession-name" class="input-block-level" type="text" name="name" state="inactive" placeholder="名称" value="<%=activitySession.getName()%>">
		<input id="hint-modifyActivitySession-date" class="input-block-level" type="text" name="date" state="inactive" placeholder="日期" value="<%=activitySession.getDate()%>">
		<input id="hint-modifyActivitySession-starttime" class="input-block-level" type="text" name="starttime" state="inactive" placeholder="开始时间" value="<%=activitySession.getStarttime()%>">
		<input id="hint-modifyActivitySession-endtime" class="input-block-level" type="text" name="endtime" state="inactive" placeholder="结束时间" value="<%=activitySession.getEndtime()%>">
		<div id="hint-modifyActivitySession-dropdown-place" class="dropdown">
			<input class="dropdown-input sr-only input-block-level" type="text" data-toggle="dropdown" placeholder="请选择场地" value="<%=activitySession.getPlace().getName()%>">
			<input id="hint-modifyActivitySession-placeID" class="dropdown-toggle" style="display: none" type="text" name="placeID" value="<%=activitySession.getPlace().getID()%>">
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
		<div id="hint-modifyActivitySession-dropdown-coach" class="dropdown">
			<input class="dropdown-input sr-only input-block-level" type="text" data-toggle="dropdown" placeholder="请选择教练" value="<%=activitySession.getCoach().getName()%>">
			<input id="hint-modifyActivitySession-coachID" class="dropdown-toggle" style="display: none" type="text" name="coachID" value="<%=activitySession.getCoach().getID()%>">
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
		<input id="hint-confirm" type="button" class="btn btn-info btn-block" value="确认修改">
	</div>
	<%} %>
	<script type="text/javascript">
		$("#hint-content").animate({"top" : "20%"}, 300);
		hint_modifyActivitySession_bind();
		$("#hint-close").click(hint_close);
		$("#hint-confirm").click(hint_confirm);
		function hint_confirm(){
			if(hint_modifyActivitySession_check()){
				$("#hint-content").animate({"top" : 0}, 300,function(){
					var activitySessionID = $("#hint-modifyActivitySession-ID").val();
					var name = $("#hint-modifyActivitySession-name").val();
					var date = $("#hint-modifyActivitySession-date").val();
					var starttime = $("#hint-modifyActivitySession-starttime").val();
					var endtime = $("#hint-modifyActivitySession-endtime").val();
					var placeID = $("#hint-modifyActivitySession-placeID").val();
					var coachID = $("#hint-modifyActivitySession-coachID").val();
					$.post("modifyActivitySession",{"activitySessionID":activitySessionID,"name":name,"date":date,"starttime":starttime,"endtime":endtime,"placeID":placeID,"coachID":coachID},function(data){
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
		function hint_modifyActivitySession_bind(){
			$("#hint-modifyActivitySession-name").blur(checkContent);
			$("#hint-modifyActivitySession-date").datepicker({
				format: 'yyyy-mm-dd',
				autoclose: true
			});
			$("#hint-modifyActivitySession-starttime").blur(checkTime);
			$("#hint-modifyActivitySession-endtime").blur(checkTime);
			$("#hint-modifyActivitySession-dropdown-place").find(".dropdown-item").click(hint_modifyActivitySession_dropdown_selectPlace);
			$("#hint-modifyActivitySession-dropdown-coach").find(".dropdown-item").click(hint_modifyActivitySession_dropdown_selectCoach);
		}
		function hint_modifyActivitySession_dropdown_selectPlace(){
			var placeID = $(this).attr("placeID");
			var name = $(this).attr("name");
			var dropdown = $(this).closest(".dropdown");
			dropdown.find(".dropdown-toggle").attr("value", placeID);
			dropdown.find(".dropdown-input").val(name);			
		}
		function hint_modifyActivitySession_dropdown_selectCoach(){
			var coachID = $(this).attr("coachID");
			var name = $(this).attr("name");
			var dropdown = $(this).closest(".dropdown");
			dropdown.find(".dropdown-toggle").attr("value", coachID);
			dropdown.find(".dropdown-input").val(name);			
		}
		function hint_modifyActivitySession_check(){
			return checkContentOf($("#hint-modifyActivitySession-name")) &&
			checkDateOf($("#hint-modifyActivitySession-date")) &&
			checkTimeOf($("#hint-modifyActivitySession-starttime")) &&
			checkTimeOf($("#hint-modifyActivitySession-endtime")) && 
			checkPlaceOf($("#hint-modifyActivitySession-placeID")) && 
			checkCoachOf($("#hint-modifyActivitySession-coachID"));
		}
	</script>
</div>