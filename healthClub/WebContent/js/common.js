$(function(){
	tabs_init();
	header_init();
});
function dropdown_mutiSelect_init(){
	$(".dropdown-mutiSelect-tag").unbind("mouseenter mouseleave").mouseenter(drop_mutiSelect_remove_show).mouseleave(drop_mutiSelect_remove_hide);
	$(".dropdown-mutiSelect-tag-remove").unbind("click").click(drop_mutiSelect_remove);
}
function drop_mutiSelect_remove_show(){
	$(this).find(".dropdown-mutiSelect-tag-remove").stop().animate({"width":"10px"},200);
}
function drop_mutiSelect_remove_hide(){
	$(this).find(".dropdown-mutiSelect-tag-remove").stop().animate({"width": 0},200);
}
function drop_mutiSelect_remove(){
	$(this).closest(".dropdown-mutiSelect-tag").remove();
}
function dropdown_select(){
	var ID = $(this).attr("value");
	var name = $(this).find(".dropdown-presentation-main").attr("value");
	$(this).closest(".dropdown").find(".dropdown-button").val(name).attr("value",ID);
	if($(this).closest(".dropdown").find(".dropdown-mutiSelect-input")){
		var input = $(this).closest(".dropdown").find(".dropdown-mutiSelect-input");
		var tag = $(this).closest(".dropdown").find(".dropdown-mutiSelect-tag");
		var existed = false;
		tag.each(function(){
			if($(this).attr("value") == ID){
				existed = true;
			}
		});
		if(!existed){
			$.post("dropdown/dropdown_tag_coach.jsp",{"ID":ID,"name":name},function(data){
				input.after(data);
				dropdown_mutiSelect_init();
			});
		}
	}
}
function tabs_init(){
	$(".tabs").find(".active-tab").unbind("click");
	$(".tabs").find(".inactive-tab").unbind("click").click(tabs_click);
}
function tabs_click(){
	var activePanel = $(".active-tab").attr("panel");
	var panel = $(this).attr("panel");
	$(".active-tab").removeClass("active-tab").addClass("inactive-tab");
	$(this).removeClass("inactive-tab").addClass("active-tab");
	$(activePanel).hide(200);
	$(panel).show(200);
	tabs_init();
}
function header_init(){
	$("#freezeVIP").click(function(){
		if(confirm("确认要冻结账户吗？")) return true; 
		else return false;
	});
}