$(function(){
	tabs_init();
	$("#waiter-VIP-info-tab").click();
});
function tabs_init(){
	$("#waiter-VIP-info-tab").click(function(){
		$(".active-tab").removeClass("active-tab").addClass("inactive-tab");
		$(this).removeClass("inactive-tab").addClass("active-tab");
		waiter_VIP_info_load();
	});
	$("#waiter-VIP-appointment-tab").click(function(){
		$(".active-tab").removeClass("active-tab").addClass("inactive-tab");
		$(this).removeClass("inactive-tab").addClass("active-tab");
		waiter_VIP_appointment_load();
	});
	$("#waiter-VIP-record-tab").click(function(){
		$(".active-tab").removeClass("active-tab").addClass("inactive-tab");
		$(this).removeClass("inactive-tab").addClass("active-tab");
		waiter_VIP_record_load();
	});
	$("#waiter-VIP-payment-tab").click(function(){
		$(".active-tab").removeClass("active-tab").addClass("inactive-tab");
		$(this).removeClass("inactive-tab").addClass("active-tab");
		waiter_VIP_payment_load();
	});
}
function waiter_VIP_info_load(){
	var VIPID = $("#waiter-VIP-ID").val();
	$.post("model/waiter_VIP_info.jsp",{"VIPID":VIPID},function(data){
		$("#showPanel").html(data);
		waiter_VIP_info_init();
	});
}
function waiter_VIP_appointment_load(){
	var VIPID = $("#waiter-VIP-ID").val();
	$.post("model/waiter_VIP_appointment.jsp",{"VIPID":VIPID},function(data){
		$("#showPanel").html(data);
	});
}
function waiter_VIP_record_load(){
	var VIPID = $("#waiter-VIP-ID").val();
	$.post("model/waiter_VIP_record.jsp",{"VIPID":VIPID},function(data){
		$("#showPanel").html(data);
	});
}
function waiter_VIP_payment_load(){
	var VIPID = $("#waiter-VIP-ID").val();
	$.post("model/waiter_VIP_payment.jsp",{"VIPID":VIPID},function(data){
		$("#showPanel").html(data);
	});
}
function waiter_VIP_info_init(){
	$(".waiter-VIP-info-persons-options-listItem").click(waiter_VIP_info_person_show);
	$("#waiter-VIP-info-persons-options-add").click(waiter_VIP_info_person_add);
	$("#waiter-VIP-info-persons-options-modify").click(waiter_VIP_info_person_modify);  
	$("#waiter-VIP-info-persons-options-remove").click(waiter_VIP_info_person_remove);
}
function waiter_VIP_info_person_show(){
	var index = $(this).attr("index");
	var currPerson = $("#waiter-VIP-info-currPerson-info");
	var showPerson = $(".waiter-VIP-info-person-info[index="+index+"]");
	currPerson.stop(true).slideUp(200,function(){
		currPerson.removeAttr("id");
		showPerson.stop(true).slideToggle(200).attr("id","waiter-VIP-info-currPerson-info");
	});
}
function  waiter_VIP_info_person_modify(){
	var personID = $("#waiter-VIP-info-currPerson-info").find("input[name=personID]").attr("value");
	$.post("model/waiter_VIP_info_person_modify.jsp",{"personID":personID},function(data){
		$("body").append(data);
	});
}
function  waiter_VIP_info_person_add(){
	$.post("model/waiter_VIP_info_person_add.html",function(data){
		$("body").append(data);
	});
}
function  waiter_VIP_info_person_remove(){
	var name = $("#waiter-VIP-info-currPerson-info").find("input[name=name]").val();
	if(confirm("确认要删除成员"+name+"么?")){
		var personID = $("#waiter-VIP-info-currPerson-info").find("input[name=personID]").val();
		$.post("removePerson",{"personID":personID},function(data){
			VIP_info_load();
		});
	}
}