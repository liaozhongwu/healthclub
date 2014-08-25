$(function(){
	tabs_init();
	$("#waiter-activity-tab").click();
});
function tabs_init(){
	$("#waiter-activity-tab").click(function(){
		$(".active-tab").removeClass("active-tab").addClass("inactive-tab");
		$(this).removeClass("inactive-tab").addClass("active-tab");
		waiter_activity_load();
	});
	$("#waiter-VIP-tab").click(function(){
		$(".active-tab").removeClass("active-tab").addClass("inactive-tab");
		$(this).removeClass("inactive-tab").addClass("active-tab");
		waiter_VIP_load();
	});
	$("#waiter-addActivity-tab").click(function(){
		$(".active-tab").removeClass("active-tab").addClass("inactive-tab");
		$(this).removeClass("inactive-tab").addClass("active-tab");
		Waiter_addActivity_load();
	});
}
function waiter_activity_load(){
	$.post("model/waiter_activity_search.jsp",function(data){
		$("#showPanel").html(data);
	});
}
function waiter_VIP_load(){
	$.post("model/waiter_VIP_search.jsp",function(data){
		$("#showPanel").html(data);
	});
}
function Waiter_addActivity_load(){
	$.post("model/waiter_addActivity.html",function(data){
		$("#showPanel").html(data);
	});
}