<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width">
<title>Learn To Seek Truth| Dashboard</title>
<link rel="stylesheet" href="../css/style.css">


<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>

<style>
h3, h4, h5, h6 {
	font-family: sans-serif;
}
</style>

<body>
	<jsp:include page="../WEB-INF/jsp/components/dashboardHeader.jsp" />

	<div class="row">
		<jsp:include page="../WEB-INF/jsp/components/dashboardSidebar.jsp" />
		<div class="col-md-9 ml-sm-auto col-lg-10 px-4">

			<!-- Page content -->
			<div class="w3-content" style="max-width: 1100px">



				<img class="w3-image" src="../img/study3.png"
					alt="Hamburger Catering" width="1200" height="800">

				<div class="w3-display-bottomleft w3-padding-large w3-opacity">
				</div>

				<hr>

				<!-- Menu Section -->
				<div class="w3-row w3-padding-64" id="menu">
					<div class="w3-col l6 w3-padding-large">
						<h1 class="w3-center">Class Category</h1>
						<br>
						<h4>CS157A</h4>
						<p class="w3-text-grey">Introduction to Database Management
							Systems</p>
						<br>

						<h4>CS146</h4>
						<p class="w3-text-grey">Data Structures and Algorithms</p>
						<br>

						<h4>CS149</h4>
						<p class="w3-text-grey">Operating Systems</p>
						<br>

						<h4>CS151</h4>
						<p class="w3-text-grey">Object-Oriented Design</p>
						<br>

						<h4>CMPE 165</h4>
						<p class="w3-text-grey">Software Engineering Process
							Management</p>
						<br>

						<div class="w3-panel w3-leftbar w3-sand">
							<p class="w3-xxlarge w3-serif">
								<i>"Wait, there is more..."</i>
							</p>
							<a href="${pageContext.request.contextPath}/dashboard/notelist"
								class="w3-button w3-black">Go to Note Page</a>
						</div>
					</div>

					<div class="w3-col l6 w3-padding-large">
						<img src="../img/study4.png"
							class="w3-round w3-image w3-opacity-min" alt="Menu"
							style="width: 100%">
					</div>
				</div>

				<hr>

			</div>
		</div>


	</div>

	<div class="container">
		<footer>
			<p>LearnToSeekTruth, Copyright &copy; 2020</p>
		</footer>

	</div>
</body>

</html>