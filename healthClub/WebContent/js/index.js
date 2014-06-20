$(function(){
	login_init();
	$('.carousel').carousel({
		interval: 2000
	});
});
function login_init(){
	$(".loginForm").submit(login_submit_check);
}
function login_submit_check(){
	var username = $("input[name=username]").val();
	var password = $("input[name=password]").val();
	if(username){
		if(password){
			return true;
		}else{
			alert("请输入密码");
			return false;
		}
	}else{
		alert("请输入用户名");
		return false;
	}
}