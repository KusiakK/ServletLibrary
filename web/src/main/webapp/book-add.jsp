<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Add book</title>
    <jsp:include page="WEB-INF/headlinks.jspf"/>
</head>
<body class="bg-light h-100">
<jsp:include page="WEB-INF/header.jspf"/>
<div class="container d-flex h-100 m-auto">
    <div class="my-auto w-100">
        <div class="my-6 w-100">
            <jsp:include page="WEB-INF/notifications.jspf"/>
            <p class="lead font-weight-light">Add book</p>
            <hr class="my-4 bg-info opacity">
            <form action="addBook" method="POST">
                <jsp:include page="WEB-INF/book-form-body.jspf"/>
            </form>
        </div>
    </div>
</div>
<jsp:include page="WEB-INF/footer.jspf"/>
<jsp:include page="WEB-INF/srcjs.jspf"/>
</body>
</html>
