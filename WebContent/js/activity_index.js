$(function(){
	tabs_init();
	$("#VIP-todayActivity-tab").click();
});
function tabs_init(){
	$("#VIP-todayActivity-tab").click(function(){
		$(".active-tab").removeClass("active-tab").addClass("inactive-tab");
		$(this).removeClass("inactive-tab").addClass("active-tab");
		VIP_todayActivity_load();
	});
	$("#VIP-tomorrowActivity-tab").click(function(){
		$(".active-tab").removeClass("active-tab").addClass("inactive-tab");
		$(this).removeClass("inactive-tab").addClass("active-tab");
		VIP_tomorrowActivity_load();
	});
	$("#VIP-searchActivity-tab").click(function(){
		$(".active-tab").removeClass("active-tab").addClass("inactive-tab");
		$(this).removeClass("inactive-tab").addClass("active-tab");
		VIP_searchActivity_load();
	});
}
function VIP_todayActivity_load(){
	$.post("model/activity_today.jsp",function(data){
		$("#showPanel").html(data);
	});
}
function VIP_tomorrowActivity_load(){
	$.post("model/activity_tomorrow.jsp",function(data){
		$("#showPanel").html(data);
	});
}
function VIP_searchActivity_load(){
	$.post("model/VIP_activity_search.jsp",function(data){
		$("#showPanel").html(data);
	});
}