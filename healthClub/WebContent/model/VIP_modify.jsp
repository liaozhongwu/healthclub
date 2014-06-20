<%@page import="java.util.ArrayList"%>
<%@page import="edu.nju.healthClub.model.user.vip.*"%>
<%@page import="edu.nju.healthClub.model.*"%>
<%@page import="edu.nju.healthClub.service.UserService"%>
<%@page import="java.util.Date"%>
<%@page import="edu.nju.healthClub.model.*"%>
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
			<input type="text" name="username" value="<%=user.getUsername() %>">
		</div>
		<div class="VIP-block-password">
			<label>密码: </label>
			<input type="password" name="password">
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
					<input type="text" name="petname" value="<%=((VIPPerson)user).getPerson().getPetname() %>">
				</div>
				<div class="VIP-block-person-age">
					<label>年龄: </label>
					<input type="text" name="age" value="<%=((VIPPerson)user).getPerson().getAge() %>">
				</div>
				<div class="VIP-block-person-sex">
					<label>性别: </label>
					<div class="dropdown">
						<input class="dropdown-button dropdown-toggle sr-only btn btn-block" type="button" data-toggle="dropdown" name="sex" value="<%=((VIPPerson)user).getPerson().getSex() %>">
						<div class="dropdown-menu" role="menu">
							<div class="dropdown-item" value="male">male(男)</div>
							<div class="dropdown-item" value="female">female(女)</div>
						</div>
					</div>
				</div>
				<div class="VIP-block-person-address">
					<label>地址: </label>
					<input type="text" name="address" value="<%=((VIPPerson)user).getPerson().getAddress() %>">
				</div>
				<div class="VIP-block-person-telephone">
					<label>手机: </label>
					<input type="text" name="telephone" value="<%=((VIPPerson)user).getPerson().getTelephone() %>">
				</div>
				<div class="VIP-block-person-email">
					<label>邮箱: </label>
					<input type="text" name="email" value="<%=((VIPPerson)user).getPerson().getEmail() %>">
				</div>
				<div class="VIP-block-person-realname">
					<label>姓名: </label>
					<input type="text" name="realname" value="<%=((VIPPerson)user).getPerson().getRealname() %>">
				</div>
				<div class="VIP-block-person-residentID">
					<label>身份证: </label>
					<input type="text" name="residentID" value="<%=((VIPPerson)user).getPerson().getResidentID() %>">
				</div>
				<div class="VIP-block-person-birthday">
					<label>生日: </label>
					<input type="date" name="birthday" value="<%=((VIPPerson)user).getPerson().getBirthday() %>">
				</div>
			</div>
		</div>
		<div class="VIP-block-btngroup">
			<input class="btn btn-primary" type="button" name="reset" value="还原信息">
			<input class="btn btn-primary" type="button" name="save" value="保存信息">
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
				<input type="text" name="username" value="<%=user.getUsername() %>">
			</div>
			<div class="VIP-block-password">
				<label>密码: </label>
				<input type="password" name="password">
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
				<input type="button" class="VIP-block-person-slide btn btn-primary btn-block" value="<%=person.getPetname() %>">
				<div class="VIP-block-person-info">
					<div class="VIP-block-person-ID none">
						<input type="text" name="personID" value="<%=person.getID() %>"/>
					</div>
					<div class="VIP-block-person-petname">
						<label>昵称: </label>
						<input type="text" name="petname" value="<%=person.getPetname() %>">
					</div>
					<div class="VIP-block-person-age">
						<label>年龄: </label>
						<input type="text" name="age" value="<%=person.getAge() %>">
					</div>
					<div class="VIP-block-person-sex">
						<label>性别: </label>
						<div class="dropdown">
							<input class="dropdown-button dropdown-toggle sr-only btn btn-block" type="button" data-toggle="dropdown" name="sex" value="<%=person.getSex() %>">
							<div class="dropdown-menu" role="menu">
								<div class="dropdown-presentation" value="男"><div class="dropdown-presentation-main" value="男">男</div></div>
								<div class="dropdown-presentation" value="女"><div class="dropdown-presentation-main" value="女">女</div></div>
								<div class="dropdown-presentation" value="male"><div class="dropdown-presentation-main" value="stop">male</div></div>
								<div class="dropdown-presentation" value="female"><div class="dropdown-presentation-main" value="cancel">female</div></div>
							</div>
						</div>
					</div>
					<div class="VIP-block-person-address">
						<label>地址: </label>
						<input type="text" name="address" value="<%=person.getAddress() %>">
					</div>
					<div class="VIP-block-person-telephone">
						<label>手机: </label>
						<input type="text" name="telephone" value="<%=person.getTelephone() %>">
					</div>
					<div class="VIP-block-person-email">
						<label>邮箱: </label>
						<input type="text" name="email" value="<%=person.getEmail() %>">
					</div>
					<div class="VIP-block-person-realname">
						<label>姓名: </label>
						<input type="text" name="realname" value="<%=person.getRealname() %>">
					</div>
					<div class="VIP-block-person-residentID">
						<label>身份证: </label>
						<input type="text" name="residentID" value="<%=person.getResidentID() %>">
					</div>
					<div class="VIP-block-person-birthday">
						<label>生日: </label>
						<input type="date" name="birthday" value="<%=person.getBirthday() %>">
					</div>
					<input type="button" class="VIP-block-person-remove btn btn-primary btn-block" value="删除成员">
				</div>
			</div>
			<% } %>
			<input id="VIP-block-addPerson" type="button" class="btn btn-primary btn-block" value="添加家庭成员">
			<div class="VIP-block-btngroup">
				<input class="btn btn-primary" type="button" name="reset" value="还原信息">
				<input class="btn btn-primary" type="button" name="save" value="保存信息">
			</div>
	<% } %>