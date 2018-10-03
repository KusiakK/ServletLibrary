<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Browse</title>
    <jsp:include page="WEB-INF/headlinks.jspf"/>
</head>
<body class="bg-light h-100">
<jsp:include page="WEB-INF/header.jspf"/>
<div class="container d-flex h-100 m-auto">
    <div class="my-auto w-100">
        <div class="my-6 w-100">
            <jsp:include page="WEB-INF/notifications.jspf"/>
            <p class="lead font-weight-light">Our Library</p>
            <hr class="my-4 bg-info opacity">
            <p class="font-weight-light">Here you can browse our books and borrow them.</p>
            <form class="w-100" action="bookOption" method="GET">
                <div class="h-100 w-100 mb-3">
                    <div class="table-responsive-xl">
                        <table class="table">
                            <thead>
                            <tr>
                                <th scope="col">Title</th>
                                <th scope="col">First name</th>
                                <th scope="col">Last name</th>
                                <th scope="col">ISBN</th>
                                <th scope="col">Category</th>
                                <th scope="col">Release Date</th>
                                <th scope="col">Borrower</th>
                                <th scope="col">#</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="book" items="${requestScope.books}">
                                <tr>
                                    <td>${book.title}</td>
                                    <td>${book.author.firstName}</td>
                                    <td>${book.author.lastName}</td>
                                    <td>${book.isbn}</td>
                                    <td>${book.category}</td>
                                    <td>${book.releaseDate}</td>
                                    <td></td>
                                    <td><input type="radio" name="book-id" value="${book.bookID}"/></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="btn-group" role="group">
                    <button type="submit" class="btn btn-info" name="actionType" value="add">Add</button>
                    <button type="submit" class="btn btn-info" name="actionType" value="edit">Edit</button>
                    <button type="submit" class="btn btn-info" name="actionType" value="delete">Delete</button>
                    <button type="submit" class="btn btn-info" name="actionType" value="show">Show</button>
                </div>
            </form>
        </div>
    </div>
</div>
<jsp:include page="WEB-INF/footer.jspf"/>
<jsp:include page="WEB-INF/srcjs.jspf"/>
</body>
</html>
