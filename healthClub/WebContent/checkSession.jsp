<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<% if(session.getAttribute("userID") == null)response.sendRedirect("login.jsp");%>