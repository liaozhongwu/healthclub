<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%
    	String ID = request.getParameter("ID");
    	String name = request.getParameter("name");
    %>
<div class="dropdown-mutiSelect-tag activity-coach-tag" value="<%=ID %>"><%=name %><div class="dropdown-mutiSelect-tag-remove"><img src="image/remove.png"></div></div>
