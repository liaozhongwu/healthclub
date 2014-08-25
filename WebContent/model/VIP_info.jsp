<%@page import="edu.nju.healthClub.model.user.VIPType"%>
<%@page import="edu.nju.healthClub.model.user.Person"%>
<%@page import="java.util.ArrayList"%>
<%@page import="edu.nju.healthClub.model.user.VIPState"%>
<%@page import="edu.nju.healthClub.model.user.VIP"%>
<%@page import="edu.nju.healthClub.service.CommonService"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<% 
	int userID = (Integer)session.getAttribute("userID");
	CommonService service = new CommonService();
	VIP vip = service.getVIPByID(userID);
%>
<div id="VIP-info">
	<div id="VIP-info-account">
		<div id="VIP-info-account-info">
			<label class="VIP-block-label">账户信息</label>
			<div class="VIP-info-account-info-ID">
				<label class="VIP-input-label">ID</label>
				<input type="text" name="userID" readonly="readonly" value="<%=vip.getID()%>">
			</div>
			<div class="VIP-info-account-info-username">
				<label class="VIP-input-label">用户名</label>
				<input type="text" name="username" readonly="readonly" value="<%=vip.getUsername()%>">
			</div>
			<div class="VIP-info-account-info-type">
				<label class="VIP-input-label">会员类型</label>
				<input type="text" name="type" readonly="readonly" value="<%=vip.getType()%>">
			</div>
			<div class="VIP-info-account-info-balance">
				<label class="VIP-input-label">账户余额</label>
				<input type="text" name="balance" readonly="readonly" value="<%=vip.getBalance()%>">
			</div>
			<div class="VIP-info-account-info-state">
				<label class="VIP-input-label">账户状态</label>
				<input type="text" name="state" readonly="readonly" value="<%=vip.getState()%>">
			</div>
			<div class="VIP-info-account-info-registerDate">
				<label class="VIP-input-label">注册时间</label>
				<input type="text" name="registerDate" readonly="readonly" value="<%=vip.getRegisterDate()%>">
			</div>
		</div>
		<div id="VIP-info-account-options">
			<label class="VIP-block-label">账户操作</label>
			<input id="VIP-info-account-options-modifyPassword" type="button" class="btn btn-info btn-block" value="修改密码">
			<% if(vip.getState() == VIPState.UNACTIVE){ %>
			<input id="VIP-info-account-options-activate" type="button" class="btn btn-primary btn-block" value="账户激活">
			<%}else if(vip.getState() != VIPState.FREEZE){ %>
			<input id="VIP-info-account-options-pay" type="button" class="btn btn-info btn-block" value="账户充值">
			<input id="VIP-info-account-options-freeze" type="button" class="btn btn-danger btn-block" value="冻结账户" >
			<% } %>
		</div>
	</div>
	<div id="VIP-info-persons">
		<div id="VIP-info-person">
			<label class="VIP-block-label">成员信息</label>
		<%
			ArrayList<Person> persons = service.getPersonsOfVIP(vip);
			for(int i = 0;i < persons.size(); i++){
				Person person = persons.get(i);
		%>
			<div class="VIP-info-person-info" index="<%=i+1 %>" <%if(i == 0){ %> id="VIP-info-currPerson-info" <% } %>>
				<input style="display:none" type="text" name="personID" value="<%=person.getID()%>"> 
				<div class="VIP-info-person-info-name">
					<label class="VIP-input-label">姓名</label>
					<input type="text" name="name" readonly="readonly" value="<%=person.getName()%>">
				</div>
				<div class="VIP-info-person-info-sex">
					<label class="VIP-input-label">性别</label>
					<input type="text" name="sex" readonly="readonly" value="<%=person.getSex()%>">
				</div>
				<div class="VIP-info-person-info-birthday">
					<label class="VIP-input-label">生日</label>
					<input type="text" name="birthday" readonly="readonly" value="<%=person.getBirthday()%>">
				</div>
				<div class="VIP-info-person-info-address">
					<label class="VIP-input-label">地址</label>
					<input type="text" name="address" readonly="readonly" value="<%=person.getAddress()%>">
				</div>
				<div class="VIP-info-person-info-telephone">
					<label class="VIP-input-label">手机</label>
					<input type="text" name="telephone" readonly="readonly" value="<%=person.getTelephone()%>">
				</div>
				<div class="VIP-info-person-info-email">
					<label class="VIP-input-label">邮箱</label>
					<input type="text" name="email" readonly="readonly" value="<%=person.getEmail()%>">
				</div>
				<div class="VIP-info-person-info-resident">
					<label class="VIP-input-label">身份证</label>
					<input type="text" name="resident" readonly="readonly" value="<%=person.getResident()%>">
				</div>
			</div>
		<% } %>
		</div>
		<div id="VIP-info-persons-options">
			<label class="VIP-block-label">成员列表</label>
			<%
				for(int i = 0;i < persons.size(); i++){
					Person person = persons.get(i);
			%>
			<input class="VIP-info-persons-options-listItem btn btn-info btn-block" type="button" value="<%=person.getName()%>" index="<%=i+1 %>" <%if(i == 0){ %> id="VIP-info-person-currLabel" <% } %>>
			<% 	} %>
			<label class="VIP-block-label">成员操作</label>
			<input id="VIP-info-persons-options-modify" type="button" class="btn btn-info btn-block" value="修改信息">
			<% if(vip.getType() == VIPType.FAMILY){ %>
			<input id="VIP-info-persons-options-add" type="button" class="btn btn-info btn-block" value="添加成员">
			<input id="VIP-info-persons-options-remove" type="button" class="btn btn-warning btn-block" value="删除成员">
			<% } %>
		</div>
	</div>
</div>
