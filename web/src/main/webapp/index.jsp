<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>Web Library</title>
    <jsp:include page="src/main/webapp/WEB-INF/headlinks.jspf"/>
</head>
<body class="bg-light h-100">
<jsp:include page="src/main/webapp/WEB-INF/header.jspf"/>
<div class="container d-flex h-100 m-auto">
    <div class="my-auto">
        <div class="my-6 w-100">

            <c:if test="${not empty requestScope.errorHead}">
                <div class="alert alert-danger">
                    <strong>${requestScope.errorHead}</strong>
                    <c:if test="${not empty requestScope.error}">
                        ${requestScope.error}
                    </c:if>
                </div>
            </c:if>

            <h1 class="display-4">Welcome to the Web Library!</h1>
            <%--<p class="lead font-weight-light">Welcome to the Web Library!</p>--%>
            <hr class="my-4 bg-info opacity">
            <p class="font-weight-light">Here you can browse our books and borrow them.</p>
            <a class="btn btn-info btn-lg" href="browse.jsp" role="button">Browse</a>
        </div>
    </div>
</div>
<jsp:include page="src/main/webapp/WEB-INF/footer.jspf"/>
<jsp:include page="src/main/webapp/WEB-INF/srcjs.jspf"/>
</body>
</html>
