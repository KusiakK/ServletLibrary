<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Delete book</title>
    <jsp:include page="WEB-INF/headlinks.jspf"/>
</head>
<body class="bg-light h-100">
<jsp:include page="WEB-INF/header.jspf"/>
<div class="container d-flex h-100 m-auto">
    <div class="my-auto w-100">
        <div class="my-6 w-100">
            <p class="lead font-weight-light">Delete book</p>
            <hr class="my-4 bg-info opacity">
            <p class="font-weight-light">Are you sure you want to delete book?</p>
            <form class="w-100" action="deleteBook" method="post">
                <div class="h-100 w-100 mb-3">
                </div>
                <input type="hidden" name="book-id" value="${requestScope['book-id']}">
                <div class="btn-group" role="group">
                    <button type="submit" class="btn btn-info" name="deleteConfirmation" value="confirm">Delete</button>
                    <button type="submit" class="btn btn-info" name="deleteConfirmation" value="cancel">Cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>
<jsp:include page="WEB-INF/footer.jspf"/>
<jsp:include page="WEB-INF/srcjs.jspf"/>
</body>
</html>
