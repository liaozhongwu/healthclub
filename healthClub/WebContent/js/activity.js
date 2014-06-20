$(function(){
	activity_init();
});
function activity_init(){
	activity_VIP_appointment_init();
	activity_appointment_init();
	activity_record_init();
	$(".activity-session-label").unbind("click").click(activity_session_slide);
	$(".activity-VIP-appointment-create-button").unbind("click").click(activity_VIP_appointment_create);
}
function activity_session_slide(){
	$(this).closest(".activity-session").find(".activity-session-info").stop(true).slideToggle(200);
}
function activity_VIP_appointment_init(){
	$(".activity-VIP-appointment-create").find(".dropdown-presentation").unbind("click").click(activity_VIP_appointment_person_select);
	$(".activity-VIP-appointment-create-button").unbind(".click").click(activity_VIP_appointment_create);
	$(".activity-VIP-appointment-remove").unbind(".click").click(activity_VIP_appointment_remove);
}
function activity_VIP_appointment_person_select(){
	var personID = $(this).attr("value");
	var realname = $(this).find(".dropdown-presentation-main").attr("value");
	var telephone = $(this).find(".dropdown-presentation-main").attr("telephone");
	$(this).closest(".activity-VIP-appointment-create").find("input[name=personID]").val(personID);
	$(this).closest(".activity-VIP-appointment-create").find("input[name=realname]").attr("value",realname);
	$(this).closest(".activity-VIP-appointment-create").find("input[name=telephone]").val(telephone);
}
function activity_VIP_appointment_create(){
	var div = $(this).closest(".activity-VIP-appointment-create");
	var personID = $(this).closest(".activity-VIP-appointment-create").find("input[name=personID]").val();
	var activityID = $(this).closest(".activity").find("input[name=ID]").val();
	var activitySession = $(this).closest(".activity-session").find("input[name=activitySession]").val();
	if(personID && activityID && activitySession){
		$.post("model/appointment_create.jsp",{"personID":personID,"activityID":activityID,"activitySession":activitySession},function(data){
			if(data){
				div.before(data);
				$(".activity-VIP-appointment-remove").unbind(".click").click(activity_VIP_appointment_remove);
			}
		});
	}
}
function activity_VIP_appointment_remove(){
	var line = $(this).closest(".activity-VIP-appointment-line");
	var personID = line.find("input[name=personID]").val();
	var activityID = $(this).closest(".activity").find("input[name=ID]").val();
	var activitySession = $(this).closest(".activity-session").find("input[name=activitySession]").val();
	$.post("removeAppointment",{"personID":personID,"activityID":activityID,"activitySession":activitySession},function(data){
		if(data=="success")line.remove();
	});
}
function activity_appointment_init(){
	$(".activity-appointment-show").unbind("click").click(activity_appointment_show);
	$(".activity-appointment-create-button").unbind(".click").click(activity_appointment_create);
	$(".activity-appointment-remove").unbind(".click").click(activity_appointment_remove);
	$(".activity-appointment-create").find("input[name=VIPID]").unbind("blur").blur(activity_appointment_create_hint);
}
function activity_appointment_show(){
	$(this).closest(".activity-session").find(".activity-record").stop().slideUp(200);
	$(this).closest(".activity-session").find(".activity-appointment").stop().slideToggle(200);
}
function activity_appointment_remove(){
	var line = $(this).closest(".activity-appointment-line");
	var VIPID = line.find("input[name=VIPID]").val();
	var personID = line.find("input[name=personID]").val();
	var activityID = $(this).closest(".activity").find("input[name=ID]").val();
	var activitySession = $(this).closest(".activity-session").find("input[name=activitySession]").val();
	$.post("removeAppointment",{"VIPID":VIPID,"personID":personID,"activityID":activityID,"activitySession":activitySession},function(data){
		if(data=="success")line.remove();
	});
}
function activity_appointment_create(){
	var div = $(this).closest(".activity-appointment-create");
	var VIPID = $(this).closest(".activity-appointment-create").find("input[name=VIPID]").val();
	var personID = $(this).closest(".activity-appointment-create").find("input[name=personID]").val();
	var activityID = $(this).closest(".activity").find("input[name=ID]").val();
	var activitySession = $(this).closest(".activity-session").find("input[name=activitySession]").val();
	if(VIPID && personID && activityID && activitySession){
		$.post("model/appointment_create.jsp",{"VIPID":VIPID,"personID":personID,"activityID":activityID,"activitySession":activitySession},function(data){
			if(data){
				div.before(data);
				$(".activity-appointment-remove").unbind(".click").click(activity_appointment_remove);
			}
		});
	}
}
function activity_appointment_create_hint(){
	var VIPID = $(this).val();
	var menu = $(this).closest(".activity-appointment-create").find(".dropdown-menu");
	$.post("dropdown/dropdown_VIP_person.jsp",{"VIPID":VIPID},function(data){
		menu.html(data);
		$(".dropdown-presentation").unbind("click").click(activity_appointment_person_select);
	});
}
function activity_appointment_person_select(){
	var personID = $(this).attr("value");
	var realname = $(this).find(".dropdown-presentation-main").attr("value");
	var telephone = $(this).find(".dropdown-presentation-main").attr("telephone");
	$(this).closest(".activity-appointment-create").find("input[name=personID]").val(personID);
	$(this).closest(".activity-appointment-create").find("input[name=realname]").attr("value",realname);
	$(this).closest(".activity-appointment-create").find("input[name=telephone]").val(telephone);
}
function activity_record_init(){
	$(".activity-record-show").unbind("click").click(activity_record_show);
	$(".activity-record-create-button").unbind(".click").click(activity_record_create);
	$(".activity-record-remove").unbind(".click").click(activity_record_remove);
	$(".activity-record-create").find("input[name=VIPID]").unbind("blur").blur(activity_record_create_hint);
}
function activity_record_show(){
	$(this).closest(".activity-session").find(".activity-appointment").stop().slideUp(200);
	$(this).closest(".activity-session").find(".activity-record").stop(200).slideToggle(200);
}
function activity_record_remove(){
	var line = $(this).closest(".activity-record-line");
	var VIPID = line.find("input[name=VIPID]").val();
	var personID = line.find("input[name=personID]").val();
	var activityID = $(this).closest(".activity").find("input[name=ID]").val();
	var activitySession = $(this).closest(".activity-session").find("input[name=activitySession]").val();
	$.post("removeRecord",{"VIPID":VIPID,"personID":personID,"activityID":activityID,"activitySession":activitySession},function(data){
		if(data=="success")line.remove();
	});
}
function activity_record_create(){
	var div = $(this).closest(".activity-record-create");
	var VIPID = $(this).closest(".activity-record-create").find("input[name=VIPID]").val();
	var personID = $(this).closest(".activity-record-create").find("input[name=personID]").val();
	var activityID = $(this).closest(".activity").find("input[name=ID]").val();
	var activitySession = $(this).closest(".activity-session").find("input[name=activitySession]").val();
	if(VIPID && personID && activityID && activitySession){
		$.post("model/record_create.jsp",{"VIPID":VIPID,"personID":personID,"activityID":activityID,"activitySession":activitySession},function(data){
			if(data){
				div.before(data);
				$(".activity-record-remove").unbind(".click").click(activity_record_remove);
			}else{
				alert("已预约");
			}
		});
	}
}
function activity_record_create_hint(){
	var VIPID = $(this).val();
	var menu = $(this).closest(".activity-record-create").find(".dropdown-menu");
	$.post("dropdown/dropdown_VIP_person.jsp",{"VIPID":VIPID},function(data){
		menu.html(data);
		$(".dropdown-presentation").click(activity_record_person_select);
	});
}
function activity_record_person_select(){
	var personID = $(this).attr("value");
	var realname = $(this).find(".dropdown-presentation-main").attr("value");
	var telephone = $(this).find(".dropdown-presentation-main").attr("telephone");
	$(this).closest(".activity-record-create").find("input[name=personID]").val(personID);
	$(this).closest(".activity-record-create").find("input[name=realname]").attr("value",realname);
	$(this).closest(".activity-record-create").find("input[name=telephone]").val(telephone);
}