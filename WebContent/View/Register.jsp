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
	<div class="main">
		<div class="container">
			<div class="signup-content">
				<div class="signup-form">
					<h2 class="form-title">Sign up</h2>
					<h4 style="color: RED"><%=request.getAttribute("mess") != null ? request.getAttribute("mess") : ""%></h4>
					<form method="POST" class="register-form" id="register-form" action="RegisterController">
					
						<div class="form-group">
							<label for="name"><i
								class="zmdi zmdi-account material-icons-name"></i></label> <input
								type="text" name="fullname" id="name" placeholder="Your Full Name" required />
						</div>
						
						<div class="form-group">
							<label for="email"><i class="zmdi zmdi-email"></i></label> <input
								type="email" name="email" id="email" placeholder="Your Email" required/>
								<p id="Inv_email" class="invalid" style="color: red;"></p>
						</div>
						
						<div class="form-group">
							<label for="pass"><i class="zmdi zmdi-lock"></i></label> <input
								type="password" name="password" id="pass" placeholder="Password" required/>
						</div>
						
						<div class="form-group">
							<label for="add"><i class="zmdi zmdi-lock"></i></label> <input
								type="text" name="address" id="address" placeholder="Address" required/>
						</div>
						
						<div class="form-group">
							<label for="pass"><i class="zmdi zmdi-lock"></i></label> <input
								type="text" name="phone" id="pass" placeholder="Phone" required/>
						</div>
						
						<!-- <div class="form-group">
							<label for="re-pass"><i class="zmdi zmdi-lock-outline"></i></label>
							<input type="password" name="re_pass" id="re_pass"
								placeholder="Repeat your password" required/>
						</div> -->
						
						<div class="form-group form-button">
							<input type="submit" name="signup" id="signup"
								class="form-submit" value="Register" />
						</div>
					</form>
				</div>
				<div class="signup-image">
					<figure>
						<img src="Template/Login/images/signup-image.jpg"
							alt="sing up image">
					</figure>
				</div>
			</div>
		</div>
	</div>
	<!-- JS -->
	<script src="Template/Login/vendor/jquery/jquery.min.js"></script>
	<script src="Template/Login/js/main.js"></script>
</body>
</html>