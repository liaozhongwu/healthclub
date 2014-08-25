<%@page import="edu.nju.healthClub.model.user.Person"%>
<%@page import="edu.nju.healthClub.service.CommonService"%>
<%@page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<div id="hint">
	<% 
		CommonService service = new CommonService();
		int personID = Integer.parseInt(request.getParameter("personID"));
		Person person = service.getPersonByID(personID);
	%>
	<div id="hint-content">
		<div id="hint-title">修改成员信息</div>
		<img id="hint-close" src="image/close.png" alt="close">
		<input id="hint-modifyPerson-personID" style="display:none" type="text" name="personID" value="<%= person.getID()%>">
		<input id="hint-modifyPerson-name" class="input-block-level" type="text" name="name" state="inactive" placeholder="姓名" value="<%= person.getName()%>">
		<div class="dropdown">
			<input id="hint-modifyPerson-sex" class="dropdown-button input-block-level" type="text" name="sex" state="inactive" placeholder="性别" data-toggle="dropdown" autocomplete="off"  value="<%= person.getSex()%>">
			<div class="dropdown-menu" role="menu">
				<div class="dropdown-item" value="male">male(男)</div>
				<div class="dropdown-item" value="female">female(女)</div>
			</div>
		</div>
		<input id="hint-modifyPerson-birthday" class="input-block-level" type="text" name="birthday" state="inactive" placeholder="生日" value="<%= person.getBirthday()%>">
		<input id="hint-modifyPerson-address" class="input-block-level" type="text" name="address" state="inactive" placeholder="居住地" value="<%= person.getAddress()%>">
		<input id="hint-modifyPerson-telephone" class="input-block-level" type="text" name="telephone" state="inactive" placeholder="电话" value="<%= person.getTelephone()%>">
		<input id="hint-modifyPerson-email" class="input-block-level" type="text" name="email" state="inactive" placeholder="邮箱" value="<%= person.getEmail()%>">
		<input id="hint-modifyPerson-resident" class="input-block-level" type="text" name="resident" state="inactive" placeholder="身份证" value="<%= person.getResident()%>">
	    <input id="hint-confirm" type="button" class="btn btn-info btn-block" value="确认修改">
	</div>
	<script type="text/javascript">
		$("#hint-content").animate({"top" : "20%"}, 300);
		hint_modifyPerson_bind();
		$("#hint-close").click(hint_close);
		$("#hint-confirm").click(hint_confirm);
		function hint_confirm(){
			if(hint_modifyPerson_check()){
				$("#hint-content").animate({"top" : 0}, 300,function(){
					var personID = $("#hint-modifyPerson-personID").val();
					var name = $("#hint-modifyPerson-name").val();
					var sex = $("#hint-modifyPerson-sex").val();
					var birthday = $("#hint-modifyPerson-birthday").val();
					var address = $("#hint-modifyPerson-address").val();
					var telephone = $("#hint-modifyPerson-telephone").val();
					var email = $("#hint-modifyPerson-email").val();
					var resident = $("#hint-modifyPerson-resident").val();
					$.post("modifyPerson",{"personID":personID,"name":name,"sex":sex,"birthday":birthday,"address":address,"telephone":telephone,"email":email,"resident":resident},function(data){
						waiter_VIP_info_load();
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
		function hint_modifyPerson_bind(){
			$("#hint-modifyPerson-name").blur(checkName);
			$(".dropdown-item").click(function(){$(this).closest(".dropdown").find(".dropdown-button").attr("value",$(this).attr("value"));});
			$("#hint-modifyPerson-birthday").datepicker({
				format: 'yyyy-mm-dd',
				autoclose: true,
			});
			$("#hint-modifyPerson-address").blur(checkAddress);
			$("#hint-modifyPerson-telephone").keypress(function(event){if(event.keyCode < 48 || event.keyCode > 58){return false;}}).blur(checkTelephone);
			$("#hint-modifyPerson-email").blur(checkEmail);
			$("#hint-modifyPerson-resident").keypress(function(event){if(event.keyCode < 48 || event.keyCode > 58){return false;}}).blur(checkResident);
		}
		function hint_modifyPerson_check(){
			return checkNameOf($("#hint-modifyPerson-name")) &&
				checkBirthdayOf($("#hint-modifyPerson-birthday")) &&
				checkAddressOf($("#hint-modifyPerson-address")) &&
				checkTelephoneOf($("#hint-modifyPerson-telephone")) &&
				checkEmailOf($("#hint-modifyPerson-email")) &&
				checkResidentOf($("#hint-modifyPerson-resident"));
		}
	</script>
</div>