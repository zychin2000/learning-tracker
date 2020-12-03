<%--@elvariable id="base64pic" type="java.lang.String"--%>
<%--@elvariable id="note" type="sjsu.cs157a.model.PictureNote"--%>
<%--@elvariable id="learningPrinciples" type="java.util.Set<sjsu.cs157a.model.LearningPrinciple>"--%>
<%--
  Created by IntelliJ IDEA.
  User: Zheng Yao
  Date: 11/27/2020
  Time: 10:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
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
<!-- Inline Code -->
    <%----%>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <style>
        /*Settings for editor.js*/
        .ce-block__content,
        .ce-toolbar__content {
            max-width: 1200px;
        }

        .shadowContainer {
            box-shadow: 10px 14px 5px 0px rgba(0, 0, 0, 0.24);
            -webkit-box-shadow: 10px 14px 5px 0px rgba(0, 0, 0, 0.24);
            -moz-box-shadow: 10px 14px 5px 0px rgba(0, 0, 0, 0.24);
        }

    </style>
</head>

<body>
<jsp:include page="components/dashboardHeader.jsp"/>
<div class="row">
    <jsp:include page="components/dashboardSidebar.jsp"/>
    <div class="col-md-6">

        <h1 style="align-self: center">${note.getTitle()}</h1>
        <img src="data:image/${note.image_type};base64,${base64pic}" />
    </div>
    <div class=col-md-4">
<%--        <button class="btn btn-secondary btn-lg" type="button" id="saveButton" style="margin: 1em">Re upload</button>--%>
        <div class=container>

            <jsp:include page="components/learningPrinciples.jsp">
                <jsp:param name="learningPrinciples" value="${learningPrinciples}"/>
                <jsp:param name="note" value="${note}"/>
            </jsp:include>
        </div>
    </div>

    <jsp:include page="components/comments.jsp">
        <jsp:param name="comments" value="${comments}"/>
    </jsp:include>

    <script>

        /**
         * Saving button
         */
        const saveButton = document.getElementById('saveButton');

        /**
         * Toggle read-only button
         */
        const toggleReadOnlyButton = document.getElementById('toggleReadOnlyButton');
        const readOnlyIndicator = document.getElementById('readonly-state');

        /**
         * Saving example
         */
        saveButton.addEventListener('click', save);

        /**
         * Toggle read-only example
         */
        toggleReadOnlyButton.addEventListener('click', async () => {
            const readOnlyState = await editor.readOnly.toggle();

            readOnlyIndicator.textContent = readOnlyState ? 'On' : 'Off';
        });
    </script>
</div>

</body>
</html>
