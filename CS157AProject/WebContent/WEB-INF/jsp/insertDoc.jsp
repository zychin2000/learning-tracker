<%--@elvariable id="error" type="java.lang.String"--%>
<%--@elvariable id="user" type="sjsu.cs157a.models.User"--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
<title>Edit Profile</title>
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
			<h1>Insert Doc Note</h1>
			<div class="tab-content">
				<div class="tab-pane active" id="home">
					<hr>
					<c:if test="${error != null}">
						<div class="alert alert-warning" role="alert">${error}</div>
					</c:if>


					<form class="form"
						action="${pageContext.request.contextPath}/dashboard/insertdoc"
						method="post">
						
						<input type="hidden" name="note_type" value="docu">
						
						<fieldset class="form-group">
							<label>Class ID</label> <input type="text"
								value="<c:out value='${note.class_id}' />" class="form-control"
								name="class_id">
						</fieldset>

						<fieldset class="form-group">
							<label>Note title</label> <input type="text"
								value="<c:out value='${note.title}' />" class="form-control"
								name="title">
						</fieldset>

						<fieldset class="form-group">
							<label>Note Description</label> <input type="text"
								value="<c:out value='${note.content}' />" class="form-control"
								name="content">
						</fieldset>

						<fieldset class="form-group">
							<label>Note Font</label> <input type="text"
								value="<c:out value='${note.text_font}' />" class="form-control"
								name="text_font">
						</fieldset>

						<fieldset class="form-group">
							<label>Note Type</label> <input type="text"
								value="<c:out value='${note.file_type}' />" class="form-control"
								name="file_type">
						</fieldset>

						<fieldset class="form-group">
							<label>Enter JSON</label> <input type="text"
								value="<c:out value='${note.docContent}' />"
								class="form-control" name="docContent">
						</fieldset>
						<button type="submit" class="btn btn-success">Save</button>

					</form>

				</div>
				<!--/tab-pane-->

			</div>
			<!--/tab-pane-->
		</div>
		<!--/tab-content-->

	</div>
	<!--/col-9-->
</body>
</html>