<%@page import="edu.nju.healthClub.model.Person"%>
<%@page import="java.util.ArrayList"%>
<%@page import="edu.nju.healthClub.model.user.vip.VIPFamily"%>
<%@page import="edu.nju.healthClub.model.user.vip.VIPPerson"%>
<%@page import="edu.nju.healthClub.model.User"%>
<%@page import="edu.nju.healthClub.service.UserService"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
	<%
		String ID = request.getParameter("userID");
		UserService service = new UserService();
		User user = service.getUserByID(ID);
		if(user instanceof VIPPerson){
	%>
		<div class="VIP-block-ID">
			<label>ID: </label>
			<input type="text" name="userID" readonly="readonly" value="<%=user.getID() %>">
		</div>
		<div class="VIP-block-username">
			<label>用户名: </label>
			<input type="text" name="username" readonly="readonly" value="<%=user.getUsername() %>">
		</div>
		<div class="VIP-block-balance">
			<label>账户余额: </label>
			<input type="text" name="balance" readonly="readonly" value="<%=((VIPPerson)user).getBalance() %>">
		</div>
		<div class="VIP-block-state">
			<label>账户状态: </label>
			<input type="text" name="state" readonly="readonly" value="<%=((VIPPerson)user).getState() %>">
		</div>
		<div class="VIP-block-registerDate">
			<label>注册时间: </label>
			<input type="text" name="registerDate" readonly="readonly" value="<%=((VIPPerson)user).getRegisterDate() %>">
		</div>
		<div class="VIP-block-person-panel">
			<div class="VIP-block-person-label">
				<%=((VIPPerson)user).getPerson().getPetname() %>
			</div>
			<div class="VIP-block-person-info">
				<div class="VIP-block-person-petname">
					<label>昵称: </label>
					<input type="text" name="petname" readonly="readonly" value="<%=((VIPPerson)user).getPerson().getPetname() %>">
				</div>
				<div class="VIP-block-person-age">
					<label>年龄: </label>
					<input type="text" name="age" readonly="readonly" value="<%=((VIPPerson)user).getPerson().getAge() %>">
				</div>
				<div class="VIP-block-person-sex">
					<label>性别: </label>
					<input type="text" name="sex" readonly="readonly" value="<%=((VIPPerson)user).getPerson().getSex() %>">
				</div>
				<div class="VIP-block-person-address">
					<label>地址: </label>
					<input type="text" name="address" readonly="readonly" value="<%=((VIPPerson)user).getPerson().getAddress() %>">
				</div>
				<div class="VIP-block-person-telephone">
					<label>手机: </label>
					<input type="text" name="telephone" readonly="readonly" value="<%=((VIPPerson)user).getPerson().getTelephone() %>">
				</div>
				<div class="VIP-block-person-email">
					<label>邮箱: </label>
					<input type="text" name="email" readonly="readonly" value="<%=((VIPPerson)user).getPerson().getEmail() %>">
				</div>
				<div class="VIP-block-person-realname">
					<label>姓名: </label>
					<input type="text" name="realname" readonly="readonly" value="<%=((VIPPerson)user).getPerson().getRealname() %>">
				</div>
				<div class="VIP-block-person-residentID">
					<label>身份证: </label>
					<input type="text" name="residentID" readonly="readonly" value="<%=((VIPPerson)user).getPerson().getResidentID() %>">
				</div>
				<div class="VIP-block-person-birthday">
					<label>生日: </label>
					<input type="text" name="birthday" readonly="readonly" value="<%=((VIPPerson)user).getPerson().getBirthday() %>">
				</div>
			</div>
		</div>
		<div class="VIP-block-btngroup">
			<input class="btn btn-primary" type="button" name="modify" value="修改信息">
		</div>
	<%
		} else if(user instanceof VIPFamily){
	%>
			<div class="VIP-block-ID">
				<label>ID: </label>
				<input type="text" name="userID" readonly="readonly" value="<%=user.getID() %>">
			</div>
			<div class="VIP-block-username">
				<label>用户名: </label>
				<input type="text" name="username" readonly="readonly" value="<%=user.getUsername() %>">
			</div>
			<div class="VIP-block-balance">
				<label>账户余额: </label>
				<input type="text" name="balance" readonly="readonly" value="<%=((VIPFamily)user).getBalance() %>">
			</div>
			<div class="VIP-block-state">
				<label>账户状态: </label>
				<input type="text" name="state" readonly="readonly" value="<%=((VIPFamily)user).getState() %>">
			</div>
			<div class="VIP-block-registerDate">
				<label>注册时间: </label>
				<input type="text" name="registerDate" readonly="readonly" value="<%=((VIPFamily)user).getRegisterDate() %>">
			</div>
			<% 
				ArrayList<Person> persons = ((VIPFamily)user).getPersons();
				for(Person person : persons){
			%>
			<div class="VIP-block-person-panel">
				<div class="VIP-block-person-label">
					<%=person.getPetname() %>
				</div>
				<div class="VIP-block-person-info">
					<div class="VIP-block-person-ID none">
						<input type="text" name="personID" value="<%=person.getID() %>"/>
					</div>
					<div class="VIP-block-person-petname">
						<label>昵称: </label>
						<input type="text" name="petname" readonly="readonly" value="<%=person.getPetname() %>">
					</div>
					<div class="VIP-block-person-age">
						<label>年龄: </label>
						<input type="text" name="age" readonly="readonly" value="<%=person.getAge() %>">
					</div>
					<div class="VIP-block-person-sex">
						<label>性别: </label>
						<input type="text" name="sex" readonly="readonly" value="<%=person.getSex() %>">
					</div>
					<div class="VIP-block-person-address">
						<label>地址: </label>
						<input type="text" name="address" readonly="readonly" value="<%=person.getAddress() %>">
					</div>
					<div class="VIP-block-person-telephone">
						<label>手机: </label>
						<input type="text" name="telephone" readonly="readonly" value="<%=person.getTelephone() %>">
					</div>
					<div class="VIP-block-person-email">
						<label>邮箱: </label>
						<input type="text" name="email" readonly="readonly" value="<%=person.getEmail() %>">
					</div>
					<div class="VIP-block-person-realname">
						<label>姓名: </label>
						<input type="text" name="realname" readonly="readonly" value="<%=person.getRealname() %>">
					</div>
					<div class="VIP-block-person-residentID">
						<label>身份证: </label>
						<input type="text" name="residentID" readonly="readonly" value="<%=person.getResidentID() %>">
					</div>
					<div class="VIP-block-person-birthday">
						<label>生日: </label>
						<input type="text" name="birthday" readonly="readonly" value="<%=person.getBirthday() %>">
					</div>
				</div>
			</div>
			<% } %>
			<div class="VIP-block-btngroup">
				<input class="btn btn-primary" type="button" name="modify" value="修改信息">
			</div>	
	<% } %>