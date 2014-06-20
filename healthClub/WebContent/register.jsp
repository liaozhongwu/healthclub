<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="css/datepicker.css">
<link rel="stylesheet" type="text/css" href="css/common.css" >
<link rel="stylesheet" type="text/css" href="css/register.css" >
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script type="text/javascript" src="js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/register.js"></script>
<title>注册</title>
</head>
<body>
	<jsp:include page="header.jsp" flush="true"/>
	<div class="main">
	<form id="register-form" method="POST" action="registerSecond">
		<div class="register-step-hr"></div>
		<div class="register-stepOne-label">1</div>
		<div class="register-stepTwo-label">2</div>
		<div class="register-stepThree-label">3</div>
		<div class="register-step-prev">
			<div class="register-step-arowLeft"></div>
			<div class="register-step-prevMessage">上一步</div>
		</div>
		<div class="register-step-next">
			<div class="register-step-arowRight"></div>
			<div class="register-step-nextMessage">下一步</div>
		</div>
		<div id="register-stepOne">
			<h4>请输入您的用户名和密码</h4>
		    <input type="text" class="input-block-level" name="username" placeholder="用户名，字母和数字组成">
		    <input type="password" class="input-block-level" name="password" placeholder="密码">
		    <input type="password" class="input-block-level" name="repassword" placeholder="重复密码">
			<h4>请选择您的会员类型</h4>
			<input id="register-stepOne-selectPerson" class="btn btn-block btn-primary" type="button" value="个人">
			<input id="register-stepOne-selectFamily" class="btn btn-block btn-primary" type="button" value="家庭">
			<input id="register-stepOne-selectPerson-check" class="none" type="radio" name="register-type" value="person">
			<input id="register-stepOne-selectFamily-check" class="none" type="radio" name="register-type" value="family">
		</div> 
		<div id="register-stepTwo"></div> 
		<div id="register-stepThree">
			<h4>请认真阅读以下声明</h4>
			<textarea id="register-stepThree-notice" class ="input-block-level" readonly="readonly" >&#13;&#10;1.会员注册费为75美元/个人,100美元/家庭&#13;&#10;&#13;&#10;2.会员每月收费为40美元/成人,55美元/夫妻,10美元/10-18岁孩子&#13;&#10;&#13;&#10;3.系统每月1日自动扣费,请保证您的余额充足,否则我们会暂停您的服务,若6个月未交费,则停止服务
			</textarea>
			<input id="register-stepThree-agree" type="checkbox">我已认真阅读以上声明并同意
			<h4>请输入您的银行卡账号</h4>
			<input id="register-stepThree-bankCard" type="text" class="input-block-level" name="bankCard" placeholder="银行卡号">
			<input id="register-stepThree-submit" type="submit" class="btn btn-primary btn-block disabled" name="submit" value="注册"> 
		</div>
	</form>
	</div>
</body>
</html>