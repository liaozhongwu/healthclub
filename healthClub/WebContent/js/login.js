$(function(){
	$("#login-showLogin").unbind().click(showLogin);
	$("#login-showRegister").unbind().click(showRegister);
	login_init();
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
		}
	}else{
		alert("请输入用户名");
	}
	return false;
}
function register_init(){
	$(".registerForm").submit(register_submit_check);
}
function register_submit_check(){
	
}