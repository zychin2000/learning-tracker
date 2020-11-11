<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width">
    <title>Learn To Seek Truth | Note</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <link rel="stylesheet" href="./css/style.css">
  </head>
</head>
<body>

	<header>
		<div class="container">
        <div id="branding">
          <h1><span class="highlight">Learn</span> ToSeekTruth</h1>   
      </div>
	</header>
	 
  </div>


	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<h3 class="text-center">List of Notes</h3>
			<hr>
			<div class="container text-middle">

				<a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add
					New Note</a>
			</div>
			
			<table border = "1" width = "100%" class="table table-bordered">
				<thead>
					<tr>
						<th>NoteID</th>
						<th>Title</th>
						<th>Content</th>
						<th>Actions</th>
			
					</tr>
				</thead>
				<tbody>
					<!--   for (Todo todo: todos) {  -->
					<c:forEach var="note" items="${listNote}">

						<tr>
							<td><c:out value="${note.note_id}" /></td>
							<td><c:out value="${note.title}" /></td>
							<td><c:out value="${note.content}" /></td>
							<td><a href="edit?id=<c:out value='${note.note_id}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="delete?id=<c:out value='${note.note_id}' />">Delete</a></td>
						</tr>
					</c:forEach>
					<!-- } -->
				</tbody>

			</table>
		</div>
	</div>
</body>
</html>
