<%@page import="edu.nju.healthClub.model.Coach"%>
<%@page import="java.util.ArrayList"%>
<%@page import="edu.nju.healthClub.helper.ActivityHelper"%>
<%@page import="edu.nju.healthClub.model.Activity"%>
<%@page import="edu.nju.healthClub.service.WaiterService"%>
<%@page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<div id="hint">
	<% 
		WaiterService service = new WaiterService();
		String activityID = request.getParameter("activityID");
		if(ActivityHelper.validateID(activityID)){
			Activity activity = service.getActivityByID(Integer.parseInt(activityID));
	%>
	<div id="hint-content">
		<div id="hint-title">修改活动信息</div>
		<img id="hint-close" src="image/close.png" alt="close">
		<input id="hint-modifyActivity-ID" style="display:none" type="text" name="activityID" value="<%= activity.getID()%>">
		<input id="hint-modifyActivity-name" class="input-block-level" type="text" name="name" state="inactive" placeholder="名称" value="<%= activity.getName()%>">
		<input id="hint-modifyActivity-introduction" class="input-block-level" type="text" name="introduction" state="inactive" placeholder="介绍" value="<%= activity.getIntroduction()%>">
		<input id="hint-confirm" type="button" class="btn btn-info btn-block" value="确认修改">
	</div>
	<%} %>
	<script type="text/javascript">
		$("#hint-content").animate({"top" : "20%"}, 300);
		$("#hint-close").click(hint_close);
		$("#hint-confirm").click(hint_confirm);
		function hint_confirm(){
			if(hint_modifyActivity_check()){
				$("#hint-content").animate({"top" : 0}, 300,function(){
					var activityID = $("#hint-modifyActivity-ID").val();
					var name = $("#hint-modifyActivity-name").val();
					var introduction = $("#hint-modifyActivity-introduction").val();
					$.post("modifyActivity",{"activityID":activityID,"name":name,"introduction":introduction},function(data){
						if(data == "success"){
							$("#activity-name").html(name);
							$("#activity-introduction").html(introduction);
						}
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
		function hint_modifyActivity_bind(){
			$("#hint-modifyActivity-name").blur(checkContent);
			$("#hint-modifyActivity-introduction").blur(checkContent);
		}
		function hint_modifyActivity_check(){
			return checkContentOf($("#hint-modifyActivity-name")) &&
				checkContentOf($("#hint-modifyActivity-introduction"));
		}
	</script>
</div>