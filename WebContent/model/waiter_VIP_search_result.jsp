<%@page import="edu.nju.healthClub.model.user.Person"%>
<%@page import="edu.nju.healthClub.service.WaiterService"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <% 
    	WaiterService service = new WaiterService();
    	String VIPID = request.getParameter("VIPID");
    	String username = request.getParameter("username");
    	String registerDate = request.getParameter("registerDate");
    	String name = request.getParameter("name");
    	String telephone = request.getParameter("telephone");
    	String email = request.getParameter("email");
    %>
<table class="VIP-table">
	<thead>
		<tr>
			<td>会员ID</td>
			<td>用户名</td>
			<td>注册时间</td>
			<td>成员姓名</td>
			<td>手机</td>
			<td>邮箱</td>
		</tr>
	</thead>
	<tbody>
	<%
		for(Person person : service.searchPersons(VIPID,username,registerDate,name,telephone,email)){
	%>
		<tr class="VIP-table-row">
			<td><a href="waiter_VIP.jsp?ID=<%=person.getVIP().getID() %>"><%=person.getVIP().getID() %></a></td>
			<td><a href="waiter_VIP.jsp?ID=<%=person.getVIP().getID() %>"><%=person.getVIP().getUsername() %></a></td>
			<td><%=person.getVIP().getRegisterDate() %></td>
			<td><%=person.getName() %></td>
			<td><%=person.getTelephone() %></td>
			<td><%=person.getEmail() %></td>
		<tr>
	<%
		}
	%>
	</tbody>
</table>
<script type="text/javascript">
	$(".VIP-table-row").click(function(){
		location.href = $(this).find("a").attr("href");
	});
</script>