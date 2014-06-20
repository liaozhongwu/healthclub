$(function(){
	activity_index_init();
});
function activity_index_init(){
	$(".activity-index").unbind("click").click(activity_index_view);
}
function activity_index_view(){
	$(this).closest("form").submit();
}