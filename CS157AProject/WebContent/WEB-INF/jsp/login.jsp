<%--@elvariable id="error" type="String"--%>
<!DOCTYPE html>
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
        <article id="main-col">
          <h1 class="page-title">What can you do? </h1>
          <ul id="services">
            <li>
              <h3>Note Upload</h3>
              <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus mi augue, viverra sit amet ultricies at, vulputate id lorem. Nulla facilisi.</p>
						  <p>Pricing: free</p>
            </li>
            <li>
              <h3>Note Maintenance</h3>
              <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus mi augue, viverra sit amet ultricies at, vulputate id lorem. Nulla facilisi.</p>
						  <p>Pricing: free</p>
            </li>
            <li>
              <h3>Learning Principles</h3>
              <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus mi augue, viverra sit amet ultricies at, vulputate id lorem. Nulla facilisi.</p>
						  <p>Pricing: free</p>
            </li>
          </ul>
        </article>

        <aside id="sidebar">
          <div class="dark">
            <h3>Sign In</h3>
            <div>
              <label>${error}</label>
            </div>
            <form method="post" action="login.jsp" class="quote">
  						<div>
  							<label>Email</label><br>
  							<input type="text" placeholder="example@gmail.com" name="email">
  						</div>
  						<div>
  							<label>Password</label><br>
  							<input type="password" placeholder="Password" name="password">
  						</div>
  						<button class="button_1" type="submit">Sign In</button>
              <div>
                <label>Don't have an account?</label>
                <button class="button_2" type="submit">Sign up?</button>
              </div>
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
