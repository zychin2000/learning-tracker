<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width">
    <title>Learn To Seek Truth | Note</title>
    <link rel="stylesheet" href="../css/style.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
          crossorigin="anonymous">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>

<jsp:include page="components/dashboardHeader.jsp"/>

<div class="row">
    <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->
    <jsp:include page="components/dashboardSidebar.jsp"/>
    <div class="container">
        <h3 class="text-center">List of Notes</h3>

        <div class="container text-middle">

            <a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add
                New Note</a>
        </div>

        <div class="row">
            <c:forEach var="note" items="${listNote}">
                <div class="col-md-4">
                    <div class="card mb-4 shadow-sm">
                        <svg class="bd-placeholder-img card-img-top" width="100%" height="225"
                             xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false"
                             role="img" aria-label="Placeholder: Thumbnail"><title>Placeholder</title>
                            <rect width="100%" height="100%" fill="#55595c"/>
                            <a href="${pageContext.request.contextPath}/dashboard/note?id=${note.note_id}">
                            <text x="50%" y="50%" fill="#eceeef" dy=".3em">${note.title}</text>
                            </a>
                        </svg>
                        <div class="card-body">
                            <a href="${pageContext.request.contextPath}/dashboard/note?id=${note.note_id}">
                            <p class="card-text">${note.content}</p>
                            </a>
                            <div class="d-flex justify-content-between align-items-center">
                                <div class="btn-group">
                                    <a class="btn btn-sm btn-outline-secondary"
                                       href="edit?id=<c:out value='${note.note_id}' />">Edit</a>
                                    <a class="btn btn-sm btn-outline-secondary"
                                       href="delete?id=<c:out value='${note.note_id}' />">Delete</a>
                                </div>
                                    <%--                                <small class="text-muted">9 mins</small>--%>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
</body>
</html>
