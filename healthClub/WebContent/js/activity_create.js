$(function(){
	activity_create_init();
	$(".activity").submit(activity_create_submit);
});
function activity_create_init(){
	$(".activity-session-slide").unbind("click").click(activity_session_slide);
	$(".activity-addSession").unbind("click").click(activity_addSession);
	$(".activity-session-remove").unbind("click").click(activity_session_remove);
	$(".dropdown-coach-input").unbind("keyup").keyup(dropdown_coach_refresh);
}
function activity_session_slide(){
	$(this).closest(".activity-session").find(".activity-session-info").stop(true).slideToggle(200);
}
function activity_addSession(){
	var button = $(this);
	var num = button.attr("nextSession");
	$.post("model/activity_session.jsp",{"num":num},function(data){
		button.before(data);
		activity_create_init();
		stepTwo_dropdown_init();
		activity_session_sort();
	});
}
function activity_session_remove(){
	$(this).closest(".activity-session").remove();
	activity_session_sort();
}
function activity_session_sort(){
	var num = 1;
	$(".activity-session").each(function(){
		$(this).find(".activity-session-slide").attr("value","第"+num+"场");
		$(this).find("input[name=activitySession]").attr("value",num).val(num);
		num++;
	});
}
function dropdown_coach_refresh(){
	var key = $(this).val();
	var menu = $(this).closest(".dropdown").find(".dropdown-menu");
	$.post("dropdown/dropdown_menu_coach.jsp",{"key":key},function(data){
		menu.html(data);
		stepTwo_dropdown_init();
	});
}
function activity_create_submit(){
	$("input[name=coach]").each(function(){
		$(this).val("");
	});
	$(".activity-coach-tag").each(function(){
		var input = $(this).closest(".activity-session-coach").find("input[name=coach]");
		var value = input.val();
		value = value + $(this).attr("value") + " ";
		input.val(value);
	});
	return true;
}