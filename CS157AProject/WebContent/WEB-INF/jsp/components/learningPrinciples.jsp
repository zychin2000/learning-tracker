<%--@elvariable id="learningPrinciples" type="java.util.java.util.Set<sjsu.cs157a.model.LearningPrinciple"--%>
<%--@elvariable id="note" type="sjsu.cs157a.model.Note"--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--
  Created by IntelliJ IDEA.
  User: Zheng Yao
  Date: 11/30/2020
  Time: 6:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container" style="display: block">
<c:forEach var="learningPrinciple" items="${learningPrinciples}">
    <c:choose>
        <c:when test="${learningPrinciple['class'].simpleName eq 'NoteLearningPrinciple' && learningPrinciple.getStatus() eq 'done'}">
            <div class="card text-white bg-secondary mb-3" style="width: 18rem; margin: 1em">
                <div class="card-body">
                    <h5 class="card-title">${learningPrinciple.getTitle()}</h5>
                    <p class="card-text">${learningPrinciple.getDescription()}</p>
                    <form action ="${pageContext.request.contextPath}/dashboard/note/updateLearningPrinciple" method="post" onsubmit="save()">
                        <input type="hidden" name="note_id" value=${note.note_id}>
                        <input type="hidden" name="principle_id" value="${learningPrinciple.principle_id}"/>
                        <input type="hidden" name="status" value="inProgress">
                        <input class="card-link" type="submit" value="Undo">
                    </form>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <div class="card" style="width: 18rem; margin: 1em">
                <div class="card-body">
                    <h5 class="card-title">${learningPrinciple.getTitle()}</h5>
                    <p class="card-text">${learningPrinciple.getDescription()}</p>
                    <form action ="${pageContext.request.contextPath}/dashboard/note/updateLearningPrinciple" method="post" onsubmit="save()">
                        <input type="hidden" name="note_id" value=${note.note_id}>
                        <input type="hidden" name="principle_id" value="${learningPrinciple.principle_id}"/>
                        <input type="hidden" name="status" value="done">
                        <input class="card-link" type="submit" value="I did it!">
                    </form>
                </div>
            </div>
        </c:otherwise>
    </c:choose>
</c:forEach>
<c:if test="${learningPrinciples.isEmpty()}">
    <h3>Congratulations! You have finished all the learning principle checks!</h3>
</c:if>

</div>