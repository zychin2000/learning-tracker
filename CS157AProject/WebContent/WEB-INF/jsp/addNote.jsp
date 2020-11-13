<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width">
<title>Learn To Seek Truth | Note</title>
<link rel="stylesheet" href="../css/style.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous">
</head>
<body>

	<header>
		<div class="container">
			<div id="branding">
				<h1>
					<span class="highlight">Learn</span> ToSeekTruth
				</h1>
			</div>
		</div>
	</header>

	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">

				<form action="<c:url value="/dashboard/insert"/>" method="post">

					<caption>
						<h2>
							<c:if test="${note != null}">
            			Edit Note
            		</c:if>
							<c:if test="${note == null}">
            			Add New Note
            		</c:if>
						</h2>
					</caption>



					<fieldset class="form-group">
						<label>Note title</label> <input type="text"
							value="<c:out value='${note.title}' />" class="form-control"
							name="title">
					</fieldset>

					<fieldset class="form-group">
						<label>Note Content</label> <input type="text"
							value="<c:out value='${note.content}' />" class="form-control"
							name="content">
					</fieldset>

					<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>