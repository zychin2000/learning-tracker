<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width">
  <title>Learn To Seek Truth| Dashboard</title>
  <link rel="stylesheet" href="./css/style.css">
  <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>

<body>
  <header>
    <div class="container">
      <div id="branding">
        <h1><span class="highlight">Learn</span>ToSeekTruth</h1>
  </header>

  <!-- Sidebar -->
  <div class="w3-sidebar w3-light-grey w3-bar-block" style="width:25%">
    <h3 class="w3-bar-item">MENU</h3>
    <a href="../profile.html" class="w3-bar-item w3-button">PROFILE</a>
    <a href="#" class="w3-bar-item w3-button">LEARN</a>
    <div class="w3-dropdown-hover">
      <button class="w3-button">NOTES</button>
      <div class="w3-dropdown-content w3-bar-block w3-card-4">
        <a href="#" class="w3-bar-item w3-button">ADD</a>
        <a href="#" class="w3-bar-item w3-button">EDIT</a>
        <a href="#" class="w3-bar-item w3-button">SERACH</a>
        <a href="#" class="w3-bar-item w3-button">DETELE</a>
      </div>
    </div>
  </div>

  <!-- Page Content -->
  <div style="margin-left:25%">

    <div class="w3-container w3-black">
      <h1>Dashboard Page</h1>
    </div>
    <div class="w3-container">
    		<h3 class="text-center">List of Notes</h3>
			<hr>
			<div class="container text-middle">

				<a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add
					New Note</a>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>PrincipleID</th>
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
							<td><c:out value="${note.principle_id}" /></td>
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
      <h2>Note title</h2>
      <p>This is for attibute name from database</p>
      <p>This is notes data from database.This is notes data from database.This is
        notes data from database.This is notes data from database.This is notes data
        from database.This is notes data from database.This is notes data from database.
        This is notes data from database.This is notes data from database.This is notes
        data from database.This is notes data from database.</p>
    </div>
  </div>

  <footer>
    <p>LearnToSeekTruth, Copyright &copy; 2020</p>
  </footer>
</body>

</html>
