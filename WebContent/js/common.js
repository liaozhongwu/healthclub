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
function checkUsername(){return checkUsernameOf($(this));}
function checkUsernameOf(input){
	var username = input.val();
	if(username){
		var unExited = false;
		$.ajaxSetup({async:false});
		$.post("checkUsername",{"username":username},function(data){
			if(data == "true") input_error_show(input,"用户名已存在");
			else{
				input.attr("state","success");
				unExited = true;
			}
		});
		return unExited;
	}else input_error_show(input,"请填写您的用户名");
	return false;
}
function checkPassword(){return checkPasswordOf($(this));}
function checkPasswordOf(input){
	var password = input.val();
	if(password){
		input.attr("state","success");
		return true;
	}else input_error_show(input,"请填写您的密码");
	return false;
}
function checkRepassword(){return checkRepasswordOf($(this));}
function checkRepasswordOf(input){
	var password = $("input[name=password]").val();
	var repassword = input.val();
	if(repassword){
		if(password == repassword){
			input.attr("state","success");
			return true;
		}else input_error_show(input,"确认密码不一致");
	}else input_error_show(input,"请确认您的密码");
	return false;
}
function checkName(){return checkNameOf($(this));}
function checkNameOf(input){
	var name = input.val();
	if(name){
		if(name.match(/^[A-Za-z\u4e00-\u9fa5]+$/)){
			input.attr("state","success");
			return true;
		}else{
			input_error_show(input,"请填写正确姓名");
			return false;
		}
	}else{
		input_error_show(input,"请填写您的姓名");
		return false;
	}
	
}
function checkSex(){return checkSexOf($(this));}
function checkSexOf(input){
	var sex = input.val();
	if(sex){
		input.attr("state","success");
		return true;
	}else{
		input_error_show(input,"请选择您的性别");
		return false;
	}
}
function checkBirthday(){return checkBirthdayOf($(this));}
function checkBirthdayOf(input){
	var birthday = input.val();
	if(birthday){
		if(birthday.match(/^(19\d{2}|20(0\d|1[0-4]))-((01|03|05|07|08|10|12)-(0\d|(1|2)\d|3(0|1))|(04|06|09|11)-(0\d|(1|2)\d|30)|02-(0\d|1\d|2[0-8]))$/)){
			input.attr("state","success");
			return true;
		}else {
			input_error_show(input,"请填写正确生日");
			return false;
		}
	}else{
		input_error_show(input,"请填写您的生日");
		return false;
	}
}
function checkAddress(){return checkAddressOf($(this));}
function checkAddressOf(input){
	var address = input.val();
	if(address){
		if(address.match(/^.*$/)){
			input.attr("state","success");
			return true;
		}else {
			input_error_show(input,"请填写正确地址");
			return false;
		}
	}else{
		input_error_show(input,"请填写您的地址");
		return false;
	}
}
function checkTelephone(){return checkTelephoneOf($(this));}
function checkTelephoneOf(input){
	var telephone = input.val();
	if(telephone){
		if(telephone.match(/^1[3|4|5|8][0-9]\d{8}$/)){
			input.attr("state","success");
			return true;
		}else {
			input_error_show(input,"请填写正确手机号");
			return false;
		}
	}else{
		input.attr("state","warning");
		return true;
	}
}
function checkEmail(){return checkEmailOf($(this));}
function checkEmailOf(input){
	var email = input.val();
	if(email){
		if(email.match(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/)){
			input.attr("state","success");
			return true;
		}else {
			input_error_show(input,"请填写正确邮箱");
			return false;
		}
	}else{
		input.attr("state","warning");
		return true;
	}
}
function checkResident(){return checkResidentOf($(this));}
function checkResidentOf(input){
	var resident = input.val();
	if(resident){
		if(resident.match(/^[0-9]{18}|[0-9]{17}(x|X)$/)){
			input.attr("state","success");
			return true;
		}else {
			input_error_show(input,"请填写正确身份证");
			return false;
		}
	}else{
		input.attr("state","warning");
		return true;
	}
}
function checkBankCard(){return checkBankCardOf($(this));}
function checkBankCardOf(input){
	var bankCard = input.val();
	if(bankCard){
		if(bankCard.match(/^\d*$/)){
			input.attr("state","success");
			return true;
		}else {
			input_error_show(input,"请填写正确银行卡号");
			return false;
		}
	}else{
		input_error_show(input,"请填写您的银行卡号");
		return false;
	}
}
function checkContent(){return checkContentOf($(this));}
function checkContentOf(input){
	var content = input.val();
	if(content){
		input.attr("state","success");
		return true;
	}else{
		input_error_show(input,"内容不能为空");
		return false;
	}
}
function checkDate(){return checkDateOf($(this));}
function checkDateOf(input){
	var date = input.val();
	if(date){
		if(date.match(/^\d{4}-\d{2}-\d{2}$/)){
			input.attr("state","success");
			return true;
		}else{
			input_error_show(input,"请选择正确日期yyyy-MM-dd");
			return false;
		}
	}else{
		input_error_show(input,"请选择日期yyyy-MM-dd");
		return false;
	}
}
function checkTime(){return checkTimeOf($(this));}
function checkTimeOf(input){
	var time = input.val();
	if(time){
		if(time.match(/^\d{2}:\d{2}$/)){
			input.attr("state","success");
			return true;
		}else{
			input_error_show(input,"请填写正确时间HH:mm");
			return false;
		}
	}else{
		input_error_show(input,"请输入时间HH:mm");
		return false;
	}
}
function checkPlace(){return checkPlaceOf($(this));}
function checkPlaceOf(input){
	var place = input.val();
	if(place){
		if(place.match(/^\d*$/)){
			input.attr("state","success");
			return true;
		}else{
			input_error_show(input,"请选择正确场地");
			return false;
		}
	}else{
		input_error_show(input,"请选择场地");
		return false;
	}
}
function checkCoach(){return checkCoachOf($(this));}
function checkCoachOf(input){
	var coach = input.val();
	if(coach){
		if(coach.match(/^\d*$/)){
			input.attr("state","success");
			return true;
		}else{
			input_error_show(input,"请选择正确教练");
			return false;
		}
	}else{
		input_error_show(input,"请选择教练");
		return false;
	}
}
function input_error_show(input,message){
	if($(".error").length == 0){
		input.attr("state","error");
		var error = $("<div>").addClass("error").html(message);
		input.after(error).focus(function(){
			input_error_remove(input,error);
		});
		error.slideDown(300).click(function(){
			input_error_remove(input,error);
		});
	}
}
function input_error_remove(input,error){
	error.slideUp(300,function(){
		input.attr("state","inactive");
		error.remove();
	});
}
function radios_error_show(radios,message){
	if(radios.parent().find(".error").length == 0){
		var error = $("<div>").addClass("error").html(message);
		radios.each(function(index){
			if(index == radios.length - 1) $(this).after(error);
			$(this).change(function(){error_remove(error);});
		});
		error.slideDown(300).click(function(){
			error_remove(error);
		});
	}
}
function error_remove(error){
	error.slideUp(300,function(){
		error.remove();
	});
}