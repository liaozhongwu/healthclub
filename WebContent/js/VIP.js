$(function(){
	tabs_init();
	$("#VIP-info-tab").click();
});
function tabs_init(){
	$("#VIP-info-tab").click(function(){
		$(".active-tab").removeClass("active-tab").addClass("inactive-tab");
		$(this).removeClass("inactive-tab").addClass("active-tab");
		VIP_info_load();
	});
	$("#VIP-appointment-tab").click(function(){
		$(".active-tab").removeClass("active-tab").addClass("inactive-tab");
		$(this).removeClass("inactive-tab").addClass("active-tab");
		VIP_appointment_load();
	});
	$("#VIP-record-tab").click(function(){
		$(".active-tab").removeClass("active-tab").addClass("inactive-tab");
		$(this).removeClass("inactive-tab").addClass("active-tab");
		VIP_record_load();
	});
	$("#VIP-payment-tab").click(function(){
		$(".active-tab").removeClass("active-tab").addClass("inactive-tab");
		$(this).removeClass("inactive-tab").addClass("active-tab");
		VIP_payment_load();
	});
}
function VIP_info_load(){
	$.post("model/VIP_info.jsp",function(data){
		$("#showPanel").html(data);
		VIP_info_init();
	});
}
function VIP_appointment_load(){
	$.post("model/VIP_appointment.jsp",function(data){
		$("#showPanel").html(data);
	});
}
function VIP_record_load(){
	$.post("model/VIP_record.jsp",function(data){
		$("#showPanel").html(data);
	});
}
function VIP_payment_load(){
	$.post("model/VIP_payment.jsp",function(data){
		$("#showPanel").html(data);
	});
}
function VIP_info_init(){
	$("#VIP-info-account-options-modifyPassword").click(VIP_info_account_modifyPassword);
	$("#VIP-info-account-options-activate").click(VIP_info_account_activate);
	$("#VIP-info-account-options-pay").click(VIP_info_account_pay);
	$("#VIP-info-account-options-freeze").click(VIP_info_account_freeze);
	$(".VIP-info-persons-options-listItem").click(VIP_info_person_show);
	$("#VIP-info-persons-options-add").click(VIP_info_person_add);
	$("#VIP-info-persons-options-modify").click(VIP_info_person_modify);  
	$("#VIP-info-persons-options-remove").click(VIP_info_person_remove);
}
function VIP_info_account_modifyPassword(){
	$.post("model/VIP_info_account_modifyPassword.html",function(data){
		$("body").append(data);
	});
}
function VIP_info_account_activate(){
	$.post("model/VIP_info_account_activate.html",function(data){
		$("body").append(data);
	});
}
function VIP_info_account_pay(){
	$.post("model/VIP_info_account_pay.html",function(data){
		$("body").append(data);
	});
}
function VIP_info_account_freeze(){
	$.post("model/VIP_info_account_freeze.html",function(data){
		$("body").append(data);
	});
}
function VIP_info_person_show(){
	var index = $(this).attr("index");
	var currPerson = $("#VIP-info-currPerson-info");
	var showPerson = $(".VIP-info-person-info[index="+index+"]");
	currPerson.stop(true).slideUp(200,function(){
		currPerson.removeAttr("id");
		showPerson.stop(true).slideToggle(200).attr("id","VIP-info-currPerson-info");
	});
}
function VIP_info_person_modify(){
	var personID = $("#VIP-info-currPerson-info").find("input[name=personID]").attr("value");
	$.post("model/VIP_info_person_modify.jsp",{"personID":personID},function(data){
		$("body").append(data);
	});
}
function VIP_info_person_add(){
	$.post("model/VIP_info_person_add.html",function(data){
		$("body").append(data);
	});
}
function VIP_info_person_remove(){
	var name = $("#VIP-info-currPerson-info").find("input[name=name]").val();
	if(confirm("确认要删除成员"+name+"么?")){
		var personID = $("#VIP-info-currPerson-info").find("input[name=personID]").val();
		$.post("removePerson",{"personID":personID},function(data){
			VIP_info_load();
		});
	}
}