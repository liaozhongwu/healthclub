<%@page import="edu.nju.healthClub.model.Person"%>
<%@page import="java.util.ArrayList"%>
<%@page import="edu.nju.healthClub.model.user.vip.VIPFamily"%>
<%@page import="edu.nju.healthClub.service.UserService"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	VIPFamily VIPfamily = (VIPFamily)request.getAttribute("user");
%>
	<div class="user-block">
		<div class="user-block-ID">
			<label>ID: </label>
			<input type="text" name="userID" readonly="readonly" value="<%=VIPfamily.getID() %>">
		</div>
		<div class="user-block-username">
			<label>用户名: </label>
			<input type="text" name="username" readonly="readonly" value="<%=VIPfamily.getUsername() %>">
		</div>
		<div class="user-block-balance">
			<label>账户余额: </label>
			<input type="text" name="balance" readonly="readonly" value="<%=VIPfamily.getBalance() %>">
		</div>
		<div class="user-block-state">
			<label>账户状态: </label>
			<input type="text" name="state" readonly="readonly" value="<%=VIPfamily.getState() %>">
		</div>
		<div class="user-block-registerDate">
			<label>注册时间: </label>
			<input type="text" name="registerDate" readonly="readonly" value="<%=VIPfamily.getRegisterDate() %>">
		</div>
		<% 
			ArrayList<Person> persons = VIPfamily.getPersons();
			for(Person person : persons){
		%>
		<div class="user-block-person-panel">
			<div class="user-block-person-label">
				<%=person.getPetname() %>
			</div>
			<div class="user-block-person-info">
				<div class="user-block-person-ID none">
					<input type="text" name="personID" value="<%=person.getID() %>"/>
				</div>
				<div class="user-block-person-petname">
					<label>昵称: </label>
					<input type="text" name="petname" readonly="readonly" value="<%=person.getPetname() %>">
				</div>
				<div class="user-block-person-age">
					<label>年龄: </label>
					<input type="text" name="age" readonly="readonly" value="<%=person.getAge() %>">
				</div>
				<div class="user-block-person-sex">
					<label>性别: </label>
					<input type="text" name="sex" readonly="readonly" value="<%=person.getSex() %>">
				</div>
				<div class="user-block-person-address">
					<label>地址: </label>
					<input type="text" name="address" readonly="readonly" value="<%=person.getAddress() %>">
				</div>
				<div class="user-block-person-telephone">
					<label>手机: </label>
					<input type="text" name="telephone" readonly="readonly" value="<%=person.getTelephone() %>">
				</div>
				<div class="user-block-person-email">
					<label>邮箱: </label>
					<input type="text" name="email" readonly="readonly" value="<%=person.getEmail() %>">
				</div>
				<div class="user-block-person-realname">
					<label>姓名: </label>
					<input type="text" name="realname" readonly="readonly" value="<%=person.getRealname() %>">
				</div>
				<div class="user-block-person-residentID">
					<label>身份证: </label>
					<input type="text" name="residentID" readonly="readonly" value="<%=person.getResidentID() %>">
				</div>
				<div class="user-block-person-birthday">
					<label>生日: </label>
					<input type="text" name="birthday" readonly="readonly" value="<%=person.getBirthday() %>">
				</div>
			</div>
		</div>
		<% } %>
	</div>