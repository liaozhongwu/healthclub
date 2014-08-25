<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<div id="waiter-VIP-search">
	<input id="waiter-VIP-search-VIPID" class="input-block-level" type="text" name="VIPID" placeholder="会员ID">
	<input id="waiter-VIP-search-username" class="input-block-level" type="text" name="username" placeholder="会员用户名">
	<input id="waiter-VIP-search-registerDate" class="input-block-level" type="text" name="registerDate" placeholder="注册时间">
	<input id="waiter-VIP-search-name" class="input-block-level" type="text" name="name" placeholder="姓名">
	<input id="waiter-VIP-search-telephone" class="input-block-level" type="text" name="telephone" placeholder="手机">
	<input id="waiter-VIP-search-email" class="input-block-level" type="text" name="email" placeholder="邮箱">
	<input id="waiter-VIP-search-button" class="btn btn-info btn-block" type="button" value="搜索"> 
</div>
<div id="waiter-VIP-search-result"></div>
<script type="text/javascript">
	$("#waiter-VIP-search-registerDate").datepicker({
		format: 'yyyy-mm-dd',
		autoclose: true
	});
	$("#waiter-VIP-search-button").click(function(){
		var VIPID = $("#waiter-VIP-search-VIPID").val();
		var username = $("#waiter-VIP-search-username").val();
		var registerDate = $("#waiter-VIP-search-registerDate").val();
		var name = $("#waiter-VIP-search-name").val();
		var telephone = $("#waiter-VIP-search-telephone").val();
		var email = $("#waiter-VIP-search-email").val();
		$.post("model/waiter_VIP_search_result.jsp",{"VIPID":VIPID,"username":username,"registerDate":registerDate,"name":name,"telephone":telephone,"email":email},function(data){
			$("#waiter-VIP-search").animate({"left":0},300);
			var result = $("#waiter-VIP-search-result");
			result.animate({"left":640,"opacity": 0}, 300, function(){
				result.html(data).animate({"left":320,"opacity": 1});
			});
		});
	});
</script>