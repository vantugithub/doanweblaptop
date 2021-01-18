<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
Check mail
<form action="ResendEmail" method="POST">
<input type="hidden" name="username" value=<%= request.getAttribute("username") %> />
<button type="submit">gui lai</button>
</form>
</body>
</html>