<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Register</title>
    <jsp:include page="WEB-INF/headlinks.jspf"/>
</head>
<body class="bg-light h-100">
<jsp:include page="WEB-INF/header.jspf"/>
<div class="container d-flex h-100 m-auto">
    <div class="my-auto w-100">
        <div class="my-6 w-100">
            <jsp:include page="WEB-INF/notifications.jspf"/>
            <p class="lead font-weight-light">Register</p>
            <hr class="my-4 bg-info opacity">
            <form action="register" method="POST">
                <div class="row">
                    <div class="form-group col-md-6">
                        <label for="login">User name</label>
                        <input type="text" class="form-control" id="login" placeholder="Enter your user name"
                               name="login" required>
                    </div>
                    <div class="form-group col-md-6 ">
                        <label for="email">Email address</label>
                        <input type="email" class="form-control" id="email"
                               placeholder="Enter your email" name="email" required>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-md-6">
                        <label for="password">Password</label>
                        <input type="password" class="form-control" id="password" placeholder="Enter your password"
                               name="password" required>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="confirmPassword">Confirm password</label>
                        <input type="password" class="form-control" id="confirmPassword" placeholder="Confirm your password"
                               name="confirmPassword" required>
                    </div>
                </div>
                <button type="submit" class="btn btn-lg btn-info d-block">Submit</button>
            </form>
        </div>
    </div>
</div>
<jsp:include page="WEB-INF/footer.jspf"/>
<jsp:include page="WEB-INF/srcjs.jspf"/>
</body>
</html>
