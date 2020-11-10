<%--@elvariable id="error" type="String"--%>
<%--
  User: Zheng Yao
  Date: 11/9/2020
  Time: 11:43 AM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width">
    <title>Learn To Seek Truth | Login</title>
    <link rel="stylesheet" href="./css/style.css">
</head>
<body>
<header>
    <div class="container">
        <div id="branding">
            <h1><span class="highlight">Learn</span> ToSeekTruth</h1>
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
    <div class="container">

        <aside id="main-col">
            <div class="dark">
                <h3>Register</h3>
                <div>
                    <label>${error}</label>
                </div>
                <form method="post" action="register.jsp" class="quote">
                    <div>
                        <label>Email</label><br>
                        <input type="email" placeholder="example@gmail.com" name="email">
                    </div>
                    <div>
                        <label>First Name</label><br>
                        <input type="text" placeholder="John" name="firstName">
                    </div>
                    <div>
                        <label>Last Name</label><br>
                        <input type="text" placeholder="Doe" name="lastName">
                    </div>
                    <div>
                        <label>Phone Number</label><br>
                        <input type="tel" placeholder="Phone Number" name="phone">
                    </div>
                    <div>
                        <label>Password</label><br>
                        <input type="password" placeholder="Password" name="password">
                    </div>
                    <div>
                        <label>Confirm Password</label><br>
                        <input type="password" placeholder="Confirm Password" name="confirmPassword">
                    </div>
                    <button class="button_1" type="submit">Register</button>

                </form>
            </div>
        </aside>
    </div>
</section>

<footer>
    <p>LearnToSeekTruth, Copyright &copy; 2020</p>
</footer>
</body>
</html>
