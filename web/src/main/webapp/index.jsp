<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>Web Library</title>
    <jsp:include page="WEB-INF/headlinks.jspf"/>
</head>
<body class="bg-light h-100">
<jsp:include page="WEB-INF/header.jspf"/>
<div class="container d-flex h-100 m-auto">
    <div class="my-auto w-100">
        <div class="my-6 w-100">
            <h1 class="display-4">Welcome to Web Library!</h1>
            <jsp:include page="WEB-INF/notifications.jspf"/>
            <%--<p class="lead font-weight-light">Welcome to the Web Library!</p>--%>
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
