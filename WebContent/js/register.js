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
	var radios = $("input[name=type]");
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
	$.post("model/register_stepTwo_single.html",function(data){
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
	stepTwo_person_bind($("#register-stepTwo-single"));
}
function stepTwo_family_init(){
	var memberLabels = $("#register-stepTwo-family-labels");
	var members = $("#register-stepTwo-family-members");
	var addMember_button = $("#register-stepTwo-family-addMember");
	stepTwo_family_sort();
	stepTwo_family_member_bind($("#register-stepTwo-family-currMember"));
	stepTwo_family_memberLabel_bind($("#register-stepTwo-family-currMemberLabel"));
	addMember_button.click(function(){
		$.post("model/register_stepTwo_family_member.html",function(data){
			var memberLabel = $("<div>").addClass("register-stepTwo-family-memberLabel");
			var member = $("<div>").addClass("register-stepTwo-family-member").html(data);
			memberLabels.append(memberLabel);
			members.append(member);
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
				var member = $(".register-stepTwo-family-member[index="+label.attr("index")+"]");
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
	person.find(".register-stepTwo-name").blur(checkName);
	person.find(".dropdown-item").click(function(){$(this).closest(".dropdown").find(".dropdown-button").attr("value",$(this).attr("value"));});
	person.find(".register-stepTwo-birthday").datepicker({
		format: 'yyyy-mm-dd',
		autoclose: true
	});
	person.find(".register-stepTwo-address").blur(checkAddress);
	person.find(".register-stepTwo-telephone").keypress(function(event){if(event.keyCode < 48 || event.keyCode > 58){return false;}}).blur(checkTelephone);
	person.find(".register-stepTwo-email").blur(checkEmail);
	person.find(".register-stepTwo-resident").keypress(function(event){if(event.keyCode < 48 || event.keyCode > 58){return false;}}).blur(checkResident);
}
function stepTwo_family_member_bind(member){
	member.find(".register-stepTwo-name").blur(checkName);
	member.find(".dropdown-item").click(function(){$(this).closest(".dropdown").find(".dropdown-button").attr("value",$(this).attr("value"));});
	member.find(".register-stepTwo-birthday").datepicker({
		format: 'yyyy-mm-dd',
		autoclose: true,
	});
	member.find(".register-stepTwo-address").blur(checkAddress);
	member.find(".register-stepTwo-telephone").keypress(function(event){if(event.keyCode < 48 || event.keyCode > 58){return false;}}).blur(checkTelephone);
	member.find(".register-stepTwo-email").blur(checkEmail);
	member.find(".register-stepTwo-resident").keypress(function(event){if(event.keyCode < 48 || event.keyCode > 58){return false;}}).blur(checkResident);
}
function stepTwo_family_memberLabel_bind(memberLabel){
	memberLabel.click(function(){
		var member = $(".register-stepTwo-family-member[index="+memberLabel.attr("index")+"]");
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
		$(this).attr("index",i + 1).html(i + 1);
	});
	members.each(function(i){
		$(this).attr("index",i + 1);
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
	var name = member.find("input[name=name]");
	var sex = member.find("input[name=sex]");
	var birthday = member.find("input[name=birthday]");
	var address = member.find("input[name=address]");
	var telephone = member.find("input[name=telephone]");
	var email = member.find("input[name=email]");
	var resident = member.find("input[name=resident]");
	if(checkNameOf(name) && 
		checkSexOf(sex) && 
		checkBirthdayOf(birthday) && 
		checkAddressOf(address) && 
		checkTelephoneOf(telephone) && 
		checkEmailOf(email) && 
		checkResidentOf(resident))
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
	$("#register-stepThree-agree").click(stepThree_checkAgree);
	$("#register-form").submit(function(){
		if(submitCheck()){return true;}
		else{return false;}
	});
}
function stepThree_checkAgree(){
	if($(this).is(":checked") == true){$("#register-stepThree-submit").removeClass("disabled");}
	else{$("#register-stepThree-submit").addClass("disabled");}
}
function submitCheck(){
	if(stepOne_check() && stepTwo_check()) return true;
	else return false;
}
