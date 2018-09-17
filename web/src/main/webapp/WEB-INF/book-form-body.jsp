<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row justify-content-around">
    <div class="form-group col-md-6">
        <label for="bookTitle">Title</label>
        <input type="text" class="form-control" id="bookTitle" placeholder="Enter book title" name="bookTitle" value="${requestScope.book.title}">
    </div>
    <div class="form-group col-md-6">
        <label for="bookCategory">Category</label>
        <input type="text" class="form-control" id="bookCategory" placeholder="Enter category"
               name="bookCategory" value="${requestScope.book.category}">
    </div>
</div>
<div class="row justify-content-around">
    <div class="form-group col-md-6 ">
        <label for="isbn">ISBN</label>
        <input type="number" class="form-control" id="isbn" placeholder="Enter ISBN number" name="isbn" required
        value="${requestScope.book.isbn}">
    </div>
    <div class="form-group col-md-6">
        <label for="bookReleaseDate">Release date</label>
        <input type="date" class="form-control" id="bookReleaseDate" placeholder="Enter release date"
               name="bookReleaseDate" value="${requestScope.book.releaseDate}">
    </div>
</div>
<div class="row justify-content-around">
    <div class="form-group col-md-6">
        <label for="author">Author</label>
        <select name="author-id" id="author" class="custom-select">
            <option name="author-id" selected>Choose author</option>
            <c:forEach var="author" items="${requestScope.authors}">
                <option name="author-id" value="${author.authorID}">${author.firstName} ${author.lastName}</option>
            </c:forEach>
        </select>

    </div>
    <div class="form-group col-md-6">
        <label for="bookPages">Pages</label>
        <input type="number" class="form-control" id="bookPages" placeholder="Enter number of pages"
               name="bookPages" value="${requestScope.book.pages}">
    </div>
</div>
<div class="row justify-content-around">
    <div class="form-group col-md-12">
        <label for="bookSummary">Summary</label>
        <textarea class="form-control" id="bookSummary" placeholder="Enter summary" name="bookSummary">${requestScope.book.summary}</textarea>
    </div>
</div>
<button type="submit" class="btn btn-lg btn-info d-block">Submit</button>