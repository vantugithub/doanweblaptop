<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
     "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Result</title>
</head>
<body>
	<form method="POST" action="ControllerConfirm">
		<h1><%=request.getAttribute("mess") != null ? request.getAttribute("mess") : ""%></h1>
		Mã: <input type="text" name="codeConfirm" id="codeConfirm" /> 
		<input type="submit" value="Xác nhận" />
	</form>
</body>
</html>