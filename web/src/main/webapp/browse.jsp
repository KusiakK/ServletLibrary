<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>Browse</title>
    <jsp:include page="/src/main/webapp/WEB-INF/headlinks.jspf"/>
</head>
<body class="bg-light h-100">
<jsp:include page="/src/main/webapp/WEB-INF/header.jspf"/>
<div class="container d-flex h-100 m-auto">
    <div class="my-auto w-100">
        <div class="my-6 w-100">
            <p class="lead font-weight-light">Our Library</p>
            <hr class="my-4 bg-info opacity">
            <p class="font-weight-light">Here you can browse our books and borrow them.</p>
            <form class="w-100" action="browse.jsp" method="POST">
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
                            <tr>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td><input type="radio" name="bookChoice"
                                           aria-label="Checkbox for following text input"></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td><input type="radio" name="bookChoice"
                                           aria-label="Checkbox for following text input"></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td><input type="radio" name="bookChoice"
                                           aria-label="Checkbox for following text input"></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td><input type="radio" name="bookChoice"
                                           aria-label="Checkbox for following text input"></td>
                            </tr>
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
<jsp:include page="/src/main/webapp/WEB-INF/footer.jspf"/>
<jsp:include page="/src/main/webapp/WEB-INF/srcjs.jspf"/>
</body>
</html>
