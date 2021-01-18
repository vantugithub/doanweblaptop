<%@page import="BEAN.Role"%>
<%@page import="BEAN.MyUser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employee</title>
</head>
<body>

<%
response.setHeader("Cache-Control","no-cache");
response.setHeader("Cache-Control","no-store");
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", 0);

if(session.getAttribute("USERMODEL")==null)
    response.sendRedirect("/Laptop/Login");
if(session.getAttribute("USERMODEL")!=null) {
	MyUser myUser =(MyUser) session.getAttribute("USERMODEL");
	Role role = myUser.getRole();
	if(role.getRoleName().equals("ROLE_EMPLOYEE")==false){
		response.sendRedirect("/Laptop/Login");
	}
}
%>

	<h1>Day la trang cua Manager</h1>
	<a href="/Laptop/Logout"> Logout </a>
</body>
</html>