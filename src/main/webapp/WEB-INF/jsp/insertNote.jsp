<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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

<body>
	<jsp:include page="components/dashboardHeader.jsp" />
	<div class="row">
		<jsp:include page="components/dashboardSidebar.jsp" />
		<div class="col-md-9 ml-sm-auto col-lg-10 px-4">
			<h1 class="text-center">Welcome to LearnToSeekTruth</h1>
			<h3 class="text-center">Please choose what type note do you want
				to add?</h3>

			<div class="text-center">
				<div class="w3-dropdown-hover">
					<button class="w3-button w3-black">Note Type</button>
					<div class="w3-dropdown-content w3-bar-block w3-card-4">
						<a href="${pageContext.request.contextPath}/dashboard/insertdoc"
							class="w3-bar-item w3-button">Doc</a> <a
							href="${pageContext.request.contextPath}/dashboard/insertpic"
							class="w3-bar-item w3-button">PIC</a>

					</div>

				</div>

			</div>

		</div>

	</div>

</body>

</html>