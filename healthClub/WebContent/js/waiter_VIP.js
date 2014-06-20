$(function(){
	$(".waiter-VIP-person-label").click(waiter_VIP_person_slide);
	$("input[name=save]").click(waiter_VIP_save);
	$("#waiter-VIP-addPerson").unbind("click").click(waiter_VIP_addPerson);
	waiter_VIP_person_init();
});
function waiter_VIP_save(){
	var userID = $(this).closest(".waiter-VIP").find("input[name=userID]").val();
	var username = $(this).closest(".waiter-VIP").find("input[name=username]").val();
	var password = $(this).closest(".waiter-VIP").find("input[name=password]").val();
	var balance = $(this).closest(".waiter-VIP").find("input[name=balance]").val();
	var state = $(this).closest(".waiter-VIP").find("input[name=state]").val();
	var registerDate = $(this).closest(".waiter-VIP").find("input[name=registerDate]").val();
	var personIDInput = $(this).closest(".waiter-VIP").find("input[name=personID]");
	if(personIDInput.length == 0){
		var personID = personIDInput.val();
		var petname = $(this).closest(".waiter-VIP").find("input[name=petname]").val();
		var age = $(this).closest(".waiter-VIP").find("input[name=age]").val();
		var sex = $(this).closest(".waiter-VIP").find("input[name=sex]").val();
		var address = $(this).closest(".waiter-VIP").find("input[name=address]").val();
		var telephone = $(this).closest(".waiter-VIP").find("input[name=telephone]").val();
		var email = $(this).closest(".waiter-VIP").find("input[name=email]").val();
		var realname = $(this).closest(".waiter-VIP").find("input[name=realname]").val();
		var residentID = $(this).closest(".waiter-VIP").find("input[name=residentID]").val();
		var birthday = $(this).closest(".waiter-VIP").find("input[name=birthday]").val();
		$.post("saveUser",{"userID":userID,"username":username,"password":password,
			"balance":balance,"state":state,"registerDate":registerDate,
			"personID":personID,"petname":petname,"age":age,"sex":sex,
			"address":address,"telephone":telephone,"email":email,
			"realname":realname,"residentID":residentID,"birthday":birthday},function(data){
				alert(data);
		});
	}else{
		var personID = [];
		personIDInput.each(function(){personID.push($(this).val());});
		var petnameInput = $(this).closest(".waiter-VIP").find("input[name=petname]");
		var petname = [];
		petnameInput.each(function(){petname.push($(this).val());});
		var ageInput = $(this).closest(".waiter-VIP").find("input[name=age]");
		var age = [];
		ageInput.each(function(){age.push($(this).val());});
		var sexInput = $(this).closest(".waiter-VIP").find("input[name=sex]");
		var sex = [];
		sexInput.each(function(){sex.push($(this).val());});
		var addressInput = $(this).closest(".waiter-VIP").find("input[name=address]");
		var address = [];
		addressInput.each(function(){address.push($(this).val());});
		var telephoneInput = $(this).closest(".waiter-VIP").find("input[name=telephone]");
		var telephone = [];
		telephoneInput.each(function(){telephone.push($(this).val());});
		var emailInput = $(this).closest(".waiter-VIP").find("input[name=email]");
		var email = [];
		emailInput.each(function(){email.push($(this).val());});
		var realnameInput = $(this).closest(".waiter-VIP").find("input[name=realname]");
		var realname = [];
		realnameInput.each(function(){realname.push($(this).val());});
		var residentIDInput = $(this).closest(".waiter-VIP").find("input[name=residentID]");
		var residentID = [];
		residentIDInput.each(function(){residentID.push($(this).val());});
		var birthdayInput = $(this).closest(".waiter-VIP").find("input[name=birthday]");
		var birthday = [];
		birthdayInput.each(function(){birthday.push($(this).val());});
		$.post("saveUser",{"userID":userID,"username":username,"password":password,
			"balance":balance,"state":state,"registerDate":registerDate,
			"personID":personID,"petname":petname,"age":age,"sex":sex,
			"address":address,"telephone":telephone,"email":email,
			"realname":realname,"residentID":residentID,"birthday":birthday},function(data){
				alert(data);
		});
	}
}
function waiter_VIP_person_slide(){
	$(this).parent().find(".waiter-VIP-person-info").stop().slideToggle(200);
}
function waiter_VIP_person_init(){
	$(".waiter-VIP-person-slide").unbind("click").click(waiter_VIP_person_slide);
	$(".waiter-VIP-person-remove").unbind("click").click(waiter_VIP_person_remove);
}
function waiter_VIP_person_remove(){
	$(this).closest(".waiter-VIP-person-panel").remove();
}
function waiter_VIP_addPerson(){
	var button = $(this);
	$.post("model/waiter_VIP_person.html",function(data){
		button.before(data);
		waiter_VIP_person_init();
		stepTwo_dropdown_init();
	});
}