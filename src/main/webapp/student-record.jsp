<%@ page import="com.learningprinciples.model.Student" %><%--
  Created by IntelliJ IDEA.
  User: Zheng Yao
  Date: 10/28/2020
  Time: 5:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student Record</title>
</head>
<body>
<%
    if (request.getAttribute("studentRecord") != null) {
        Student student = (Student) request.getAttribute("studentRecord");
%>

<h1>Student Record</h1>
<div>ID: <%= student.getId()%></div>
<div>First Name: <%= student.getFirstName()%></div>
<div>Last Name: <%= student.getLastName()%></div>

<%
} else {
%>

<h1>No student record found.</h1>

<% } %>
</body>
</html>
