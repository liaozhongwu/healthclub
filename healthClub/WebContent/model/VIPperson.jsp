<%@page import="edu.nju.healthClub.model.*"%>
<%@page import="edu.nju.healthClub.model.user.vip.*"%>
<%@page import="edu.nju.healthClub.service.UserService"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
		<%
			VIPPerson VIPperson = (VIPPerson)request.getAttribute("user");
			
		%>
	<div class="user-block">
		<div class="user-block-ID">
			<label>ID: </label>
			<input type="text" name="userID" readonly="readonly" value="<%=VIPperson.getID() %>">
		</div>
		<div class="user-block-username">
			<label>用户名: </label>
			<input type="text" name="username" readonly="readonly" value="<%=VIPperson.getUsername() %>">
		</div>
		<div class="user-block-balance">
			<label>账户余额: </label>
			<input type="text" name="balance" readonly="readonly" value="<%=VIPperson.getBalance() %>">
		</div>
		<div class="user-block-state">
			<label>账户状态: </label>
			<input type="text" name="state" readonly="readonly" value="<%=VIPperson.getState() %>">
		</div>
		<div class="user-block-registerDate">
			<label>注册时间: </label>
			<input type="text" name="registerDate" readonly="readonly" value="<%=VIPperson.getRegisterDate() %>">
		</div>
		<div class="user-block-person-panel">
			<div class="user-block-person-label">
				<%=VIPperson.getPerson().getPetname() %>
			</div>
			<div class="user-block-person-info">
				<div class="user-block-person-petname">
					<label>昵称: </label>
					<input type="text" name="petname" readonly="readonly" value="<%=VIPperson.getPerson().getPetname() %>">
				</div>
				<div class="user-block-person-age">
					<label>年龄: </label>
					<input type="text" name="age" readonly="readonly" value="<%=VIPperson.getPerson().getAge() %>">
				</div>
				<div class="user-block-person-sex">
					<label>性别: </label>
					<input type="text" name="sex" readonly="readonly" value="<%=VIPperson.getPerson().getSex() %>">
				</div>
				<div class="user-block-person-address">
					<label>地址: </label>
					<input type="text" name="address" readonly="readonly" value="<%=VIPperson.getPerson().getAddress() %>">
				</div>
				<div class="user-block-person-telephone">
					<label>手机: </label>
					<input type="text" name="telephone" readonly="readonly" value="<%=VIPperson.getPerson().getTelephone() %>">
				</div>
				<div class="user-block-person-email">
					<label>邮箱: </label>
					<input type="text" name="email" readonly="readonly" value="<%=VIPperson.getPerson().getEmail() %>">
				</div>
				<div class="user-block-person-realname">
					<label>姓名: </label>
					<input type="text" name="realname" readonly="readonly" value="<%=VIPperson.getPerson().getRealname() %>">
				</div>
				<div class="user-block-person-residentID">
					<label>身份证: </label>
					<input type="text" name="residentID" readonly="readonly" value="<%=VIPperson.getPerson().getResidentID() %>">
				</div>
				<div class="user-block-person-birthday">
					<label>生日: </label>
					<input type="text" name="birthday" readonly="readonly" value="<%=VIPperson.getPerson().getBirthday() %>">
				</div>
			</div>
		</div>
	</div>