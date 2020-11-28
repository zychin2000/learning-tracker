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
			<h1>Insert PIC Note</h1>
			<div class="tab-content">
				<div class="tab-pane active" id="home">
					<hr>
					<c:if test="${error != null}">
						<div class="alert alert-warning" role="alert">${error}</div>
					</c:if>


					<form class="form"
						action="${pageContext.request.contextPath}/dashboard/insertpic"
						method="post" enctype="multipart/form-data">
						
						<input type="hidden" name="note_type" value="picture">
						
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
							<label>Note Image Type</label> <input type="text"
								value="<c:out value='${note.image_type}' />"
								class="form-control" name="image_type">
						</fieldset>


						<fieldset class="form-group">
							<label>Note Size</label> <input type="text"
								value="<c:out value='${note.size}' />" class="form-control"
								name="size">
						</fieldset>


						<!-- 	<fieldset class="form-group">
							<label>Note Photo</label> <input type="file"
								 class="form-control" name="photo">
						</fieldset> -->
						<tr>
							<td>Note Photo:</td>
							<td><input type="file" name="photo" size="50" /></td>
						</tr>


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
