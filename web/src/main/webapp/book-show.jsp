<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Show book</title>
    <jsp:include page="WEB-INF/headlinks.jspf"/>
</head>
<body class="bg-light h-100">
<jsp:include page="WEB-INF/header.jspf"/>
<div class="container d-flex h-100 m-auto">
    <div class="my-auto w-100">
        <div class="my-6 w-100">
            <p class="lead font-weight-light"></p>
            <jsp:include page="WEB-INF/notifications.jspf"/>
            <div class="row">
                <div class="col-md-3">
                    <ul class="list-group">
                        <li class="list-group-item">${requestScope.book.author.firstName} ${requestScope.book.author.lastName}</li>
                        <li class="list-group-item">${requestScope.book.category}</li>
                        <li class="list-group-item">${requestScope.book.isbn}</li>
                        <li class="list-group-item">${requestScope.book.pages}</li>
                        <li class="list-group-item">${requestScope.book.releaseDate}</li>
                    </ul>
                </div>
                <div class="col-md-9">
                    <h1 class="display-4">${requestScope.book.title}</h1>
                    <hr class="my-4 bg-info opacity">
                    <span class="page-item">${requestScope.book.summary}</span>
                </div>
            </div>
            <div class="row">
                <div class="btn-group" role="group" >
                    <a class="btn btn-info btn-lg" href="browse" role="button">< Back</a>
                    <c:choose>
                        <c:when test="${requestScope.book.borrowed == false}">
                                <a class="btn btn-info btn-lg" href="browse" role="button">Borrow</a>
                        </c:when>
                        <c:otherwise>
                                <a class="btn btn-warning btn-lg disabled" href="browse" role="button">Borrow</a>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="WEB-INF/footer.jspf"/>
<jsp:include page="WEB-INF/srcjs.jspf"/>
</body>
</html>
