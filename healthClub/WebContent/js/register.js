$(function(){
	stepOne_show();
});
function stepOne_show(){
	$("#register-currStep-label").removeAttr("id");
	$(".register-stepOne-label").attr("id","register-currStep-label");
	$(".register-step-prev").animate({opacity : 0},500).hide();
	$(".register-step-next").unbind("click").click(function(){
		if(stepOne_check()){
			$("#register-stepOne").animate({opacity : 0,left : 0},500,function(){
				$(this).hide();
			});
			$("#register-stepTwo").show().animate({opacity : 1,left : 320},500);
			stepTwo_show();
		}
	});
	stepOne_bind();
}
function stepOne_bind(){
	$("input[name=username]").blur(checkUsername);
	$("input[name=password]").blur(checkPassword);
	$("input[name=repassword]").blur(checkRepassword);
	$("#register-stepOne-selectPerson").click(stepOne_selectPerson);
	$("#register-stepOne-selectFamily").click(stepOne_selectFamily);
}
function stepOne_check(){
	if(checkUsernameOf($("input[name=username]")) && 
		checkPasswordOf($("input[name=password]")) && 
		checkRepasswordOf($("input[name=repassword]")) && 
		checkSelectType()) return true;
	return false;
}
function checkSelectType(){
	var radios = $("input[name=register-type]");
	var checked = false;
	radios.each(function(){
		if($(this).attr("checked")) checked = true; 
	});
	if(checked) return true;
	radios_error_show(radios,"请选择您的会员类型");
	return false;
}
function stepOne_selectPerson(){
	$(this).addClass("disabled");
	$("#register-stepOne-selectFamily").removeClass("disabled");
	$("#register-stepOne-selectPerson-check").click().attr("checked","checked");
	$("#register-stepOne-selectFamily-check").removeAttr("checked");
	$.post("model/register_stepTwo_person.html",function(data){
		$("#register-stepTwo").html(data);
		stepTwo_person_init();
	});
}
function stepOne_selectFamily(){
	$(this).addClass("disabled");
	$("#register-stepOne-selectPerson").removeClass("disabled");
	$("#register-stepOne-selectFamily-check").click().attr("checked","checked");
	$("#register-stepOne-selectPerson-check").removeAttr("checked");
	$.post("model/register_stepTwo_family.html",function(data){
		$("#register-stepTwo").html(data);
		stepTwo_family_init();
	});
}
function stepTwo_show(){
	$("#register-currStep-label").removeAttr("id");
	$(".register-stepTwo-label").attr("id","register-currStep-label");
	$(".register-step-prev").unbind().show().animate({opacity : 1},500).click(function(){
		$("#register-stepOne").show().animate({opacity : 1,left : 320},500);
		$("#register-stepTwo").animate({opacity : 0,left : 640},500,function(){
			$(this).hide();
		});
		stepOne_show();
	});
	$(".register-step-next").unbind().show().animate({opacity : 1},500).click(function(){
		if(stepTwo_check()){
			$("#register-stepTwo").animate({opacity : 0 ,left : 0},500,function(){
				$(this).hide();
			});
			$("#register-stepThree").show().animate({opacity : 1,left : 320},500);
			stepThree_show();
		}
	});
}
function stepTwo_person_init(){
	stepTwo_person_bind($("#register-stepTwo-person"));
}
function stepTwo_family_init(){
	var memberLabel_group = $("#register-stepTwo-family-labelGroup");
	var member_group = $("#register-stepTwo-family-memberGroup");
	var addMember_button = $("#register-stepTwo-family-addMember");
	stepTwo_family_sort();
	stepTwo_family_member_bind($("#register-stepTwo-family-currMember"));
	stepTwo_family_memberLabel_bind($("#register-stepTwo-family-currMemberLabel"));
	addMember_button.click(function(){
		$.post("model/register_stepTwo_family_member.html",function(data){
			var memberLabel = $("<div>").addClass("register-stepTwo-family-memberLabel");
			var member = $("<div>").addClass("register-stepTwo-family-member").html(data);
			memberLabel_group.append(memberLabel);
			member_group.append(member);
			stepTwo_family_sort();
			stepTwo_family_member_bind(member);
			stepTwo_family_memberLabel_bind(memberLabel);
			memberLabel.click();
		});
	});
	$("#register-stepTwo-family-removeMember").click(function(){
		var currMemberLabel = $("#register-stepTwo-family-currMemberLabel");
		var currMember = $("#register-stepTwo-family-currMember");
		var labels = $(".register-stepTwo-family-memberLabel[id!=register-stepTwo-family-currMemberLabel]");
		labels.each(function(i){
			if(i == labels.length - 1) {
				var label = $(this);
				var member = $(".register-stepTwo-family-member[data="+label.attr("member")+"]");
				currMember.stop(true).slideUp(200,function(){
					member.stop(true).slideDown(200,function(){
						label.attr("id","register-stepTwo-family-currMemberLabel");
						member.attr("id","register-stepTwo-family-currMember");
						currMemberLabel.remove();
						currMember.remove();
						stepTwo_family_sort();
					});
				});
			}
		});
	});
}
function stepTwo_person_bind(person){
	person.find(".register-stepTwo-petname").blur(checkPetname);
	person.find(".dropdown-item").click(function(){$(this).closest(".dropdown").find(".dropdown-button").attr("value",$(this).attr("value"));});
	person.find(".register-stepTwo-birthday").datepicker({
		format: 'yyyy-mm-dd',
		autoclose: true
	});
	person.find(".register-stepTwo-address").blur(checkAddress);
	person.find(".register-stepTwo-telephone").keypress(function(event){if(event.keyCode < 48 || event.keyCode > 58){return false;}}).blur(checkTelephone);
	person.find(".register-stepTwo-email").blur(checkEmail);
	person.find(".register-stepTwo-realname").blur(checkRealname);
	person.find(".register-stepTwo-residentID").keypress(function(event){if(event.keyCode < 48 || event.keyCode > 58){return false;}}).blur(checkResidentID);
}
function stepTwo_family_member_bind(member){
	member.find(".register-stepTwo-petname").blur(checkPetname);
	member.find(".dropdown-item").click(function(){$(this).closest(".dropdown").find(".dropdown-button").attr("value",$(this).attr("value"));});
	member.find(".register-stepTwo-birthday").datepicker({
		format: 'yyyy-mm-dd',
		autoclose: true,
	});
	member.find(".register-stepTwo-address").blur(checkAddress);
	member.find(".register-stepTwo-telephone").keypress(function(event){if(event.keyCode < 48 || event.keyCode > 58){return false;}}).blur(checkTelephone);
	member.find(".register-stepTwo-email").blur(checkEmail);
	member.find(".register-stepTwo-realname").blur(checkRealname);
	member.find(".register-stepTwo-residentID").keypress(function(event){if(event.keyCode < 48 || event.keyCode > 58){return false;}}).blur(checkResidentID);
}
function stepTwo_family_memberLabel_bind(memberLabel){
	memberLabel.click(function(){
		var member = $(".register-stepTwo-family-member[data="+memberLabel.attr("member")+"]");
		var currMemberLabel = $("#register-stepTwo-family-currMemberLabel");
		var currMember = $("#register-stepTwo-family-currMember");
		currMemberLabel.removeAttr("id");
		memberLabel.attr("id", "register-stepTwo-family-currMemberLabel");
		currMember.stop(true).slideUp(200,function(){
			currMember.removeAttr("id");
			member.stop(true).slideDown(200,function(){member.attr("id","register-stepTwo-family-currMember");});
		});
	});
}
function stepTwo_family_sort(){
	var labels = $(".register-stepTwo-family-memberLabel");
	var members = $(".register-stepTwo-family-member");
	labels.each(function(i){
		$(this).attr("member",i + 1).html(i + 1);
	});
	members.each(function(i){
		$(this).attr("data",i + 1);
	});
}
function stepTwo_check(){
	var labels = $(".register-stepTwo-family-memberLabel");
	var success = true;
	labels.each(function(i){
		var label = $(this);
		var member = $(".register-stepTwo-family-member[data="+label.attr("member")+"]");
		if(!stepTwo_member_check(member)){
			if(!label.attr("id")) label.click();
			success = false;
			return false;
		}
	});
	return success;
}
function stepTwo_member_check(member){
	var petname = member.find("input[name=petname]");
	var sex = member.find("input[name=sex]");
	var birthday = member.find("input[name=birthday]");
	var address = member.find("input[name=address]");
	var telephone = member.find("input[name=telephone]");
	var email = member.find("input[name=email]");
	var realname = member.find("input[name=realname]");
	var residentID = member.find("input[name=residentID]");
	if(checkPetnameOf(petname) && 
		checkSexOf(sex) && 
		checkBirthdayOf(birthday) && 
		checkAddressOf(address) && 
		checkTelephoneOf(telephone) && 
		checkEmailOf(email) && 
		checkRealnameOf(realname) && 
		checkResidentIDOf(residentID))
		return true;
	else return false;
}
function stepThree_show(){
	$("#register-currStep-label").removeAttr("id");
	$(".register-stepThree-label").attr("id","register-currStep-label");
	$(".register-step-prev").unbind().click(function(){
		$("#register-stepTwo").show().animate({opacity : 1,left : 320},500);
		$("#register-stepThree").animate({opacity : 0,left : 640},500,function(){
			$(this).hide();
		});
		stepTwo_show();
	});
	$(".register-step-next").unbind().animate({opacity : 0},500).hide();
	stepThree_bind();
}
function stepThree_bind(){
	$("#register-stepThree-bankCard").keypress(function(event){if(event.keyCode < 48 || event.keyCode > 58){return false;}});
	$("#register-stepThree-agree").click(stepThree_checkAgree);
	$("#register-stepThree-bankCard").blur(checkBankCard);
	$("#register-form").submit(function(){
		if(submitCheck()){return true;}
		else{return false;}
	});
}
function stepThree_check(){
	return checkBankCardOf($("#register-stepThree-bankCard"));
}
function stepThree_checkAgree(){
	if($(this).is(":checked") == true){$("#register-stepThree-submit").removeClass("disabled");}
	else{$("#register-stepThree-submit").addClass("disabled");}
}
function submitCheck(){
	if(stepOne_check() && stepTwo_check() && stepThree_check()) return true;
	else return false;
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
function checkPetname(){return checkPetnameOf($(this));}
function checkPetnameOf(input){
	var petname = input.val();
	if(petname){
		input.attr("state","success");
		return true;
	}else{
		input_error_show(input,"请填写您的昵称");
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
function checkRealname(){return checkRealnameOf($(this));}
function checkRealnameOf(input){
	var realname = input.val();
	if(realname){
		if(realname.match(/^[A-Za-z\u4e00-\u9fa5]+$/)){
			input.attr("state","success");
			return true;
		}else {
			input_error_show(input,"请填写正确姓名");
			return false;
		}
	}else{
		input.attr("state","warning");
		return true;
	}
}
function checkResidentID(){return checkResidentIDOf($(this));}
function checkResidentIDOf(input){
	var residentID = input.val();
	if(residentID){
		if(residentID.match(/^[0-9]{18}|[0-9]{17}(x|X)$/)){
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