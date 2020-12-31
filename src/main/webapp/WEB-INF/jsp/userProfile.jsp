<%--@elvariable id="error" type="java.lang.String"--%>
<%--@elvariable id="user" type="sjsu.cs157a.models.User"--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Edit Profile</title>
    <link rel="stylesheet" href="../css/style.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
          crossorigin="anonymous">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>
<jsp:include page="components/dashboardHeader.jsp"/>
    <div class="row">
        <jsp:include page="components/dashboardSidebar.jsp"/>
        <div class="col-md-9 ml-sm-auto col-lg-10 px-4">
            <h1>Edit Profile</h1>
            <div class="tab-content">
                <div class="tab-pane active" id="home">
                    <hr>
                    <c:if test="${error != null}"><div class="alert alert-warning" role="alert">
                        ${error}
                    </div></c:if>
    
                    <c:if test="${param.status.equals(\"success\")}"> <div class="alert alert-success" role="alert">
                    Changes Successful!
                </div></c:if>

                    <form class="form" action="${pageContext.request.contextPath}/dashboard/profile" method="post">
                        <div class="form-group">

                            <div class="col-xs-6">
                                <label for="first_name"><h4>First name</h4></label>
                                <input type="text" class="form-control" name="firstName" value="${user.firstName}" title="enter your first name if any.">
                            </div>
                        </div>
                        <div class="form-group">

                            <div class="col-xs-6">
                                <label for="last_name"><h4>Last name</h4></label>
                                <input type="text" class="form-control" name="lastName" value=${user.lastName} title="enter your last name if any.">
                            </div>
                        </div>

                        <div class="form-group">

                            <div class="col-xs-6">
                                <label for="phone"><h4>Phone</h4></label>
                                <input type="text" class="form-control" name="phone" value="${user.phoneNo}" title="enter your phone number if any.">
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-xs-6">
                                <label for="email"><h4>Email</h4></label>
                                <input type="email" class="form-control" name="email" value="${user.email}" title="enter your email.">
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-xs-6">
                                <label for="password"><h4>Change Password</h4></label>
                                <input type="password" class="form-control" name="password" title="Password">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-xs-6">
                                <label for="password2"><h4>Confirm Password</h4></label>
                                <input type="password" class="form-control" name="confirmPassword"  title="enter your password2.">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-xs-12">
                                <br>
                                <button class="btn btn-lg btn-success" type="submit"><i class="glyphicon glyphicon-ok-sign"></i> Save</button>
                                <button class="btn btn-lg" type="reset"><i class="glyphicon glyphicon-repeat"></i> Reset</button>
                            </div>
                        </div>
                    </form>

                    <hr>

                </div>
                <!--/tab-pane-->
                <div class="tab-pane" id="messages">

                    <h2></h2>

                    <hr>
                    <form class="form" action="##" method="post" id="registrationForm">
                        <div class="form-group">

                            <div class="col-xs-6">
                                <label for="first_name"><h4>First name</h4></label>
                                <input type="text" class="form-control" name="first_name" id="first_name" placeholder="first name" title="enter your first name if any.">
                            </div>
                        </div>

                    </form>

                </div><!--/tab-pane-->

            </div><!--/tab-pane-->
        </div><!--/tab-content-->

    </div><!--/col-9-->
</body>
</html>
