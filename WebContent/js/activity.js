$(function(){
	activity_init();
	activity_option_bind();
});
function activity_init(){
	activity_session_show();
	activity_session_loadRelative();
}
function activity_session_show(){
	var sessionHead = $(".activity-sessionHead");
	var sessionHead_width = sessionHead.width() + parseInt(sessionHead.css("border-width")) * 2;
	var sessionHead_left = 0;
	sessionHead.each(function(){
		$(this).css("left",sessionHead_left + "px");
		sessionHead_left = sessionHead_left + sessionHead_width;
	});
	var sessionHead_curr_left = parseInt($("#activity-sessionHead-curr").css("left"));
	var sessionHead_container_left = 0 - sessionHead_curr_left + sessionHead_width * 5;
	$(".activity-sessionHead-container").animate({"left": sessionHead_container_left},300);
	var session = $(".activity-session");
	var session_width = session.width();
	var session_left = 0;
	session.each(function(){
		$(this).css("left",session_left + "px");
		session_left = session_left + session_width;
	});
	var index = $("#activity-sessionHead-curr").attr("index");
	var session_curr = $(".activity-session[index="+index+"]");
	var session_curr_left = parseInt(session_curr.css("left"));
	var session_container_left = 0 - session_curr_left + session_width;
	$(".activity-session-container").animate({"left": session_container_left},300);
	$(".activity-sessionHead").click(activity_session_slide);
}
function activity_session_slide(){
	var sessionHead = $(".activity-sessionHead");
	var sessionHead_width = sessionHead.width() + parseInt(sessionHead.css("border-width")) * 2;
	var session = $(".activity-session");
	var session_width = session.width();
	var sessionHead_curr = $("#activity-sessionHead-curr");
	var sessionHead_this = $(this);
	var sessionHead_this_left = parseInt(sessionHead_this.css("left"));
	var sessionHead_container_left = 0 - sessionHead_this_left + sessionHead_width * 5;
	var session_this = $(".activity-session[index="+sessionHead_this.attr("index")+"]");
	var session_this_left = parseInt(session_this.css("left"));
	var session_container_left = 0 - session_this_left + session_width;
	sessionHead_curr.removeAttr("id");
	sessionHead_this.attr("id","activity-sessionHead-curr");
	$(".activity-sessionHead-container").animate({"left": sessionHead_container_left}, 300);
	$(".activity-session-container").animate({"left": session_container_left},300);
	activity_session_loadRelative();
	
}
function activity_session_loadRelative(){
	var currActivitySessionID = $("#activity-sessionHead-curr").attr("index");
	$.post("model/activity_session_relative.jsp",{"activitySessionID":currActivitySessionID},function(data){
		$(".activity-session-relative").html(data);
		activity_session_appointment_VIP_init();
		activity_session_appointment_waiter_init();
		activity_session_record_init();
	});
	
}
function activity_session_appointment_VIP_init(){
	$(".activity-session-appointment-VIP-create").find(".dropdown-item").unbind("click").click(activity_session_appointment_VIP_person_select);
	$(".activity-session-appointment-VIP-create-button").unbind("click").click(activity_session_appointment_VIP_create);
	$(".activity-session-appointment-VIP-remove-button").unbind(".click").click(activity_session_appointment_VIP_remove);
}
function activity_session_appointment_VIP_person_select(){
	var personID = $(this).attr("personID");
	var name = $(this).attr("name");
	var telephone = $(this).attr("telephone");
	$(this).closest(".activity-session-appointment-VIP-create").find("input[name=personID]").val(personID);
	$(this).closest(".activity-session-appointment-VIP-create").find("input[name=name]").attr("value",name);
	$(this).closest(".activity-session-appointment-VIP-create").find("input[name=telephone]").val(telephone);
}
function activity_session_appointment_VIP_create(){
	var personID = $(this).closest(".activity-session-appointment-VIP-create").find("input[name=personID]").val();
	var activitySessionID = $(this).closest(".activity-session-appointment-VIP").attr("activitySessionID");
	if(personID && activitySessionID){
		$.post("addAppointment",{"personID":personID,"activitySessionID":activitySessionID},function(data){
			activity_session_loadRelative();
		});
	}
}
function activity_session_appointment_VIP_remove(){
	var line = $(this).closest(".activity-session-appointment-VIP-line");
	var personID = line.find("input[name=personID]").val();
	var activitySessionID = $(this).closest(".activity-session-appointment-VIP").attr("activitySessionID");
	if(personID && activitySessionID){
		$.post("removeAppointment",{"personID":personID,"activitySessionID":activitySessionID},function(data){
			activity_session_loadRelative();
		});
	}
}
function activity_session_appointment_waiter_init(){
	$(".activity-session-appointment-waiter-create").find("input[name=VIPID]").unbind("blur").blur(activity_session_appointment_waiter_create_getPersons);
	$(".activity-session-appointment-waiter-create-button").unbind(".click").click(activity_session_appointment_waiter_create);
	$(".activity-session-appointment-waiter-remove-button").unbind(".click").click(activity_session_appointment_waiter_remove);
}
function activity_session_appointment_waiter_create(){
	var div = $(this).closest(".activity-session-appointment-waiter-create");
	var personID = div.find("input[name=personID]").val();
	var activitySessionID = $(this).closest(".activity-session-appointment-waiter").attr("activitySessionID");
	if(personID && activitySessionID){
		$.post("addAppointment",{"personID":personID,"activitySessionID":activitySessionID},function(data){
			activity_session_loadRelative();
		});
	}
}
function activity_session_appointment_waiter_remove(){
	var line = $(this).closest(".activity-session-appointment-waiter-line");
	var personID = line.find("input[name=personID]").val();
	var activitySessionID = $(this).closest(".activity-session-appointment-waiter").attr("activitySessionID");
	$.post("removeAppointment",{"personID":personID,"activitySessionID":activitySessionID},function(data){
		activity_session_loadRelative();
	});
}
function activity_session_appointment_waiter_create_getPersons(){
	var VIPID = $(this).val();
	var menu = $(this).closest(".activity-session-appointment-waiter-create").find(".dropdown-menu");
	$.post("dropdown/dropdown_VIP_persons.jsp",{"VIPID":VIPID},function(data){
		menu.html(data);
		$(".dropdown-item").unbind("click").click(activity_session_appointment_waiter_selectPerson);
	});
}
function activity_session_appointment_waiter_selectPerson(){
	var personID = $(this).attr("personID");
	var name = $(this).attr("name");
	var telephone = $(this).attr("telephone");
	$(this).closest(".activity-session-appointment-waiter-create").find("input[name=personID]").val(personID);
	$(this).closest(".activity-session-appointment-waiter-create").find("input[name=name]").attr("value",name);
	$(this).closest(".activity-session-appointment-waiter-create").find("input[name=telephone]").val(telephone);
}
function activity_session_record_init(){
	$(".activity-session-record-create").find("input[name=VIPID]").unbind("blur").blur(activity_session_record_create_getPersons);
	$(".activity-session-record-create-button").unbind(".click").click(activity_session_record_create);
	$(".activity-session-record-remove-button").unbind(".click").click(activity_session_record_remove);
}
function activity_session_record_remove(){
	var line = $(this).closest(".activity-session-record-line");
	var personID = line.find("input[name=personID]").val();
	var activitySessionID = $(this).closest(".activity-session-record").attr("activitySessionID");
	if(personID && activitySessionID){
		$.post("removeRecord",{"personID":personID,"activitySessionID":activitySessionID},function(data){
			activity_session_loadRelative();
		});
	}
}
function activity_session_record_create(){
	var div = $(this).closest(".activity-session-record-create");
	var personID = div.find("input[name=personID]").val();
	var activitySessionID = $(this).closest(".activity-session-record").attr("activitySessionID");
	if(personID && activitySessionID){
		$.post("addRecord",{"personID":personID,"activitySessionID":activitySessionID},function(data){
			activity_session_loadRelative();
		});
	}
}
function activity_session_record_create_getPersons(){
	var VIPID = $(this).val();
	var menu = $(this).closest(".activity-session-record-create").find(".dropdown-menu");
	$.post("dropdown/dropdown_VIP_persons.jsp",{"VIPID":VIPID},function(data){
		menu.html(data);
		$(".dropdown-item").click(activity_session_record_selectPerson);
	});
}
function activity_session_record_selectPerson(){
	var personID = $(this).attr("personID");
	var name = $(this).attr("name");
	var telephone = $(this).attr("telephone");
	$(this).closest(".activity-session-record-create").find("input[name=personID]").val(personID);
	$(this).closest(".activity-session-record-create").find("input[name=name]").attr("value",name);
	$(this).closest(".activity-session-record-create").find("input[name=telephone]").val(telephone);
}
function activity_option_bind(){
	$("#activity-modify").click(activity_modify);
	$("#activitySession-modify").click(activity_session_modify);
	$("#activitySession-add").click(activity_session_add);
	$("#activitySession-delete").click(activity_session_delete);
}
function activity_modify(){
	var activityID = $("#activity-ID").val();
	$.post("model/activity_modify.jsp",{"activityID":activityID},function(data){
		$("body").append(data);
	});
}
function activity_session_modify(){
	var activitySessionID = $("#activity-sessionHead-curr").attr("index");
	$.post("model/activity_session_modify.jsp",{"activitySessionID":activitySessionID},function(data){
		$("body").append(data);
	});
}
function activity_session_add(){
	$.post("model/activity_session_add.jsp",function(data){
		$("body").append(data);
	});
}
function activity_session_delete(){
	var activitySessionID = $("#activity-sessionHead-curr").attr("index");
	if(confirm("确认要删除编号为"+activitySessionID+"的活动场次吗?")){
		$.post("removeActivitySession",{"activitySessionID":activitySessionID},function(data){
			location.reload();
		});
	}
}