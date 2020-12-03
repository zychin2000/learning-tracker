<%--
  Created by IntelliJ IDEA.
  User: Zheng Yao
  Date: 12/3/2020
  Time: 6:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--Comment snippet from https://bootsnipp.com/snippets/yPoe--%>
<div class="container">
    <div class="row">
        <div class="panel panel-default widget">
            <div class="panel-heading">
                <span class="glyphicon glyphicon-comment"></span>
                <h3 class="panel-title">
                    Comments</h3>
            </div>
            <div class="panel-body">
                <ul class="list-group">
                    <c:forEach items="${comments}" var="comment">
                        <li class="list-group-item">
                            <div class="row">
                                <div class="col-xs-10 col-md-11">
                                    <div>
                                        <b>${comment.title}</b>
                                        <div class="mic-info">
                                            By: <a href="#">${comment.user.firstName} ${comment.user.lastName}</a>
                                        </div>
                                    </div>
                                    <div class="comment-text">
                                            ${comment.content}
                                    </div>
                                </div>
                            </div>
                        </li>
                    </c:forEach>

                </ul>

            </div>
        </div>
    </div>
</div>
