$(function(){
	$(".VIP-block").click(block_show);
});
function block_show(){
	var origin_block = $(this);
	var origin_top = $(this).offset().top;
	var origin_left = $(this).offset().left;
	var origin_min_width = $(this).css("min-width");
	var origin_min_height = $(this).css("min-height");
	var show_min_width = "840px";
	var show_min_height = "420px";
	origin_block.addClass("VIP-block-show").css({"top":origin_top,"left":origin_left}).unbind().stop().animate({left:"200px",top:"50px","min-width":show_min_width,"min-height":show_min_height},400,function(){
		$.post("model/VIP_block_btngroup.html",function(data){
			origin_block.append(data);
			origin_block.addClass("overflow-auto");
			VIP_block_show_init();
		});
	});
	$(".background-shadow").unbind().show().click(function(){
		VIP_block_reset_(origin_block);
		origin_block.removeClass("scroll");
		origin_block.unbind().stop().animate({top:origin_top,left:origin_left,"min-width":origin_min_width,"min-height":origin_min_height},400,function(){
			origin_block.removeClass("VIP-block-show").css({"top":0,"left":0});
			$(".background-shadow").hide();
			VIP_block_show_destroy();
		}).click(block_show);
	});
}
function VIP_block_show_init(){
	$("input[name=modify]").unbind("click").click(VIP_block_modify);
	$(".VIP-block-person-label").unbind("click").click(VIP_block_person_slide);
	$(".VIP-block-person-slide").unbind("click").click(VIP_block_person_slide);
}
function VIP_block_show_destroy(){
	$("input[name=modify]").unbind();
	$("input[name=reset]").unbind();
	$("input[name=save]").unbind();
	$(".VIP-block-person-label").unbind();
	$(".VIP-block-person-slide").unbind();
}
function VIP_block_modify(){
	var button = $(this);
	var userID = button.closest(".VIP-block").find("input[name=userID]").val();
	$.get("model/VIP_modify.jsp",{"userID":userID},function(data){
		button.closest(".VIP-block").html(data);
		VIP_block_modify_init();
		stepTwo_dropdown_init();
	});
}
function VIP_block_modify_init(){
	$("input[name=reset]").unbind("click").click(VIP_block_reset);
	$("input[name=save]").unbind("click").click(VIP_block_save);
	$(".VIP-block-person-label").unbind("click").click(VIP_block_person_slide);
	$(".VIP-block-person-slide").unbind("click").click(VIP_block_person_slide);
	$(".VIP-block-person-remove").unbind("click").click(VIP_block_modify_person_remove);
	$("#VIP-block-addPerson").unbind("click").click(VIP_block_modify_addPerson);
}
function VIP_block_modify_person_remove(){
	$(this).closest(".VIP-block-person-panel").remove();
}
function VIP_block_modify_addPerson(){
	var button = $(this);
	$.post("model/VIP_modify_family_member.html",function(data){
		button.before(data);
		VIP_block_modify_init();
		stepTwo_dropdown_init();
	});
}
function VIP_block_reset(){
	var button = $(this);
	var userID = button.closest(".VIP-block").find("input[name=userID]").val();
	$.get("model/VIP_reset.jsp",{"userID":userID},function(data){
		button.closest(".VIP-block").html(data);
		VIP_block_show_init();
	});
}
function VIP_block_reset_(VIP_block){
	var userID = VIP_block.find("input[name=userID]").val();
	$.get("model/VIP_reset.jsp",{"userID":userID},function(data){
		VIP_block.html(data);
		VIP_block.find(".VIP-block-btngroup").remove();
	});
}
function VIP_block_save(){
	var userID = $(this).closest(".VIP-block").find("input[name=userID]").val();
	var username = $(this).closest(".VIP-block").find("input[name=username]").val();
	var password = $(this).closest(".VIP-block").find("input[name=password]").val();
	var balance = $(this).closest(".VIP-block").find("input[name=balance]").val();
	var state = $(this).closest(".VIP-block").find("input[name=state]").val();
	var registerDate = $(this).closest(".VIP-block").find("input[name=registerDate]").val();
	var personIDInput = $(this).closest(".VIP-block").find("input[name=personID]");
	if(personIDInput.length == 0){
		var personID = personIDInput.val();
		var petname = $(this).closest(".VIP-block").find("input[name=petname]").val();
		var age = $(this).closest(".VIP-block").find("input[name=age]").val();
		var sex = $(this).closest(".VIP-block").find("input[name=sex]").val();
		var address = $(this).closest(".VIP-block").find("input[name=address]").val();
		var telephone = $(this).closest(".VIP-block").find("input[name=telephone]").val();
		var email = $(this).closest(".VIP-block").find("input[name=email]").val();
		var realname = $(this).closest(".VIP-block").find("input[name=realname]").val();
		var residentID = $(this).closest(".VIP-block").find("input[name=residentID]").val();
		var birthday = $(this).closest(".VIP-block").find("input[name=birthday]").val();
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
		var petnameInput = $(this).closest(".VIP-block").find("input[name=petname]");
		var petname = [];
		petnameInput.each(function(){petname.push($(this).val());});
		var ageInput = $(this).closest(".VIP-block").find("input[name=age]");
		var age = [];
		ageInput.each(function(){age.push($(this).val());});
		var sexInput = $(this).closest(".VIP-block").find("input[name=sex]");
		var sex = [];
		sexInput.each(function(){sex.push($(this).val());});
		var addressInput = $(this).closest(".VIP-block").find("input[name=address]");
		var address = [];
		addressInput.each(function(){address.push($(this).val());});
		var telephoneInput = $(this).closest(".VIP-block").find("input[name=telephone]");
		var telephone = [];
		telephoneInput.each(function(){telephone.push($(this).val());});
		var emailInput = $(this).closest(".VIP-block").find("input[name=email]");
		var email = [];
		emailInput.each(function(){email.push($(this).val());});
		var realnameInput = $(this).closest(".VIP-block").find("input[name=realname]");
		var realname = [];
		realnameInput.each(function(){realname.push($(this).val());});
		var residentIDInput = $(this).closest(".VIP-block").find("input[name=residentID]");
		var residentID = [];
		residentIDInput.each(function(){residentID.push($(this).val());});
		var birthdayInput = $(this).closest(".VIP-block").find("input[name=birthday]");
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
function VIP_block_person_slide(){
	$(this).parent().find(".VIP-block-person-info").stop().slideToggle(200);
}