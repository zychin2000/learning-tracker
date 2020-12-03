<%--@elvariable id="error" type="String"--%>
<%--
  User: Zheng Yao
  Date: 11/9/2020
  Time: 11:43 AM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<title>Learn To Seek Truth | Login</title>
<link rel="stylesheet" href="./css/style.css">
</head>
<body>
	<header>
		<div class="container">
			<div id="branding">
				<h1>
					<span class="highlight">Learn</span> ToSeekTruth
				</h1>
			</div>
			<nav>
				<ul>
					<li><a href="index.html">Home</a></li>
					<li><a href="about.html">About</a></li>
					<li class="current"><a href="login.jsp">Login</a></li>
				</ul>
			</nav>
		</div>
	</header>


	<section id="main">
	<div class="dark">
		<div class="w3-container">

			<div class="w3-row ">
				<div class="w3-third w3-container"></div>

				<div class="w3-third w3-container">
					<aside id="main-col">
						<div class="dark">
							<h3>Register</h3>
							<div>
								<label>${error}</label>
							</div>
							<form method="post" action="register.jsp" class="quote">
								  <p>
								  
									<label>Email</label><br>
									
									 <input class="w3-input w3-border w3-round-large" type="email"
										placeholder="example@gmail.com" name="email">
								 <p>
								<div>
									<label>First Name</label><br> <input class="w3-input w3-border w3-round-large" type="text"
										placeholder="John" name="firstName">
								</div>
								<div>
									<label>Last Name</label><br> <input class="w3-input w3-border w3-round-large"
									type="text"
										placeholder="Doe" name="lastName">
								</div>
								<div>
									<label>Phone Number</label><br> <input class="w3-input w3-border w3-round-large" type="tel"
										placeholder="Phone Number" name="phone">
								</div>
								<div>
									<label>Password</label><br> <input class="w3-input w3-border w3-round-large" type="password"
										placeholder="Password" name="password">
								</div>
								<div>
									<label>Confirm Password</label><br> <input class="w3-input w3-border w3-round-large" type="password"
										placeholder="Confirm Password" name="confirmPassword">
								</div>
								<button class="button_1" type="submit">Register</button>

							</form>
						</div>
				</div>

				<div class="w3-third w3-container"></div>

				</aside>
			</div>
		</div>
		</div>

	</section>

	<footer>
		<p>LearnToSeekTruth, Copyright &copy; 2020</p>
	</footer>
</body>
</html>
