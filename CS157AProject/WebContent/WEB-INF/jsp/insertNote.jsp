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
	<div class="w3-container w3-center"  >
		<jsp:include page="components/dashboardSidebar.jsp" />
		<h1>Welcome, </h1>
		<h2>Please choose what type note do you want to add? </h2>
		
		 <div class="w3-dropdown-hover">
		<button class="w3-button w3-black">Note Type</button>
		      <div class="w3-dropdown-content w3-bar-block w3-card-4">
		      <a href="${pageContext.request.contextPath}/dashboard/insertdoc" class="w3-bar-item w3-button">Doc</a>
			   <a href="#" class="w3-bar-item w3-button">Pic</a>
		      
		      </div>
		
		 </div>
	
	
		
		
		  
	<!-- 	<form>
			<select name="item">
				<option value="/dashboard/insert/doc">docu</option>
				<option value="insertPic.jsp">Pic</option>
			</select> <input type="submit" value="Submit">
		</form> -->
		
		
		
	</div>
	

</body>

</html>