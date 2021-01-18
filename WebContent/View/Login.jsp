<%@page import="BEAN.Role"%>
<%@page import="BEAN.MyUser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<!-- Font Icon -->
<link rel="stylesheet"
	href="Template/Login/fonts/material-icon/css/material-design-iconic-font.min.css">

<!-- Main css -->
<link rel="stylesheet" href="Template/Login/css/style.css">
</head>
<body>

<%
response.setHeader("Cache-Control","no-cache");
response.setHeader("Cache-Control","no-store");
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", 0);

/* if(session.getAttribute("USERMODEL")==null)
    response.sendRedirect("/Laptop/Login"); */
    
if(session.getAttribute("USERMODEL")!=null) {
	MyUser myUser =(MyUser) session.getAttribute("USERMODEL");
	Role role = myUser.getRole();
	if(role.getRoleName().equals("ROLE_ADMIN")==true){
		response.sendRedirect("/Laptop/admin");
	}
	if(role.getRoleName().equals("ROLE_EMPLOYEE")==true){
		response.sendRedirect("/Laptop/employee");
	}
	if(role.getRoleName().equals("ROLE_USER")==true){
		response.sendRedirect("/Laptop/Home");
	}
}
%>

<div class="main">
<!-- Sing in  Form -->
        <section class="sign-in">
            <div class="container">
                <div class="signin-content">
                    <div class="signin-image">
                        <figure><img src="Template/Login/images/signin-image.jpg" alt="sing up image"></figure>
                        <a href="/Laptop/Register" class="signup-image-link">Create an account</a>
                    </div>
                    <div class="signin-form">
                        <h2 class="form-title">Sign up</h2>
                        <h4><%=request.getAttribute("mess") != null ? request.getAttribute("mess") : ""%></h4>
                        <form method="POST" class="register-form" id="login-form" action="Login">
                            <div class="form-group">
                                <label for="your_name"><i class="zmdi zmdi-account material-icons-name"></i></label>
                                <input type="text" name="username" id="your_name" placeholder="Your Name"/>
                            </div>
                            <div class="form-group">
                                <label for="your_pass"><i class="zmdi zmdi-lock"></i></label>
                                <input type="password" name="password" id="your_pass" placeholder="Password"/>
                            </div>
                            <div class="form-group form-button">
                                <input type="submit" name="signin" id="signin" class="form-submit" value="Log in"/>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </section>
    </div>
    <!-- JS -->
	<script src="Template/Login/vendor/jquery/jquery.min.js"></script>
	<script src="Template/Login/js/main.js"></script>
</body>
</html>