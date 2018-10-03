<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Web Library</title>
    <link href="https://fonts.googleapis.com/css?family=Lato:100,300,400,700,900" rel="stylesheet">
    <link rel="stylesheet" href="webjars/bootstrap/4.1.3/css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/styles.css">
</head>
<body class="bg-light h-100">
<jsp:include page="WEB-INF/header.jspf"/>
<div class="container d-flex h-100 m-auto">
    <div class="my-auto w-100">
        <div class="my-6 w-100">
            <jsp:include page="WEB-INF/notifications.jspf"/>
            <c:choose>
                <c:when test="${not empty sessionScope.login}">
                    <h1 class="display-4">Hello ${sessionScope.login}!</h1>
                </c:when>
                <c:otherwise>
                    <h1 class="display-4">Hello there!</h1>
                </c:otherwise>
            </c:choose>
            <p class="lead">Welcome to my Web Library Project!</p>
            <hr class="my-4 bg-info opacity">
            <p class="font-weight-light">Here you can browse our books and borrow them.</p>
            <a class="btn btn-info btn-lg" href="browse" role="button">Browse</a>
        </div>
    </div>
</div>
<jsp:include page="WEB-INF/footer.jspf"/>
<jsp:include page="WEB-INF/srcjs.jspf"/>
</body>
</html>
