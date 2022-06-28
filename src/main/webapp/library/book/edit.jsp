<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>

    <title>Document</title>
    <%@include file="/library/layout/head.jsp"%>

</head>
<body class="animsition">
<div class="page-wrapper">
    <!-- HEADER MOBILE-->
    <%@include file="/library/layout/header_mobile.jsp"%>


    <!-- MENU SIDEBAR-->
    <%@include file="/library/layout/sidebar.jsp"%>


    <!-- PAGE CONTAINER-->
    <div class="page-container">
        <!-- HEADER DESKTOP-->
        <%@include file="/library/layout/header_desktop.jsp"%>
        <!-- HEADER DESKTOP-->

        <!-- MAIN CONTENT-->
        <div class="main-content">
            <div class="section__content section__content--p30">
                <div class="container-fluid">

                    <div class="row">
                        <div class="col-6">
                            <h1>Edit book ${bookDTO.getId()}</h1>
                        </div>
                        <div class="col-6">
                            <a href="/books?action=show&page_index=1">
                                <button type="button" class="btn btn-danger">Book List</button>
                            </a>
                        </div>
                    </div>

                    <hr>
                    <c:if test="${requestScope['errorsId'] == null}">

                        <form method="post" class="book-form">
                            <h3>Update Book Information</h3>
                            <div class="row">
                                <div class="col-9">
                                    <label for="name" class="form-label">Previous Name: ${bookDTO.getName()}</label>
                                    <input type="text" class="form-control" id="name" name="name" required>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-3">
                                    <label for="authorId" class="form-label">Previous Author: ${bookDTO.getAuthor()}</label>
                                    <select class="form-control" name="authorId" id="authorId">
                                        <c:forEach items="${requestScope['authorList']}" var="item">
                                            <option value="${item.getId()}">${item.getName()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="col-3">
                                    <label for="genreId" class="form-label">Previous Genre: ${bookDTO.getGenre()}</label>
                                    <select class="form-control" name="genreId" id="genreId">
                                        <c:forEach items="${requestScope['genreList']}" var="item">
                                            <option value="${item.getId()}">${item.getName()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="col-3">
                                    <label for="publisherId" class="form-label">Previous Publisher: ${bookDTO.getPublisher()}</label>
                                    <select class="form-control" name="publisherId" id="publisherId">
                                        <c:forEach items="${requestScope['publisherList']}" var="item">
                                            <option value="${item.getId()}">${item.getName()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="row">
                                <button type="submit" class="btn btn-outline-primary">Save</button>
                            </div>
                        </form>


                        <form method="post" class="book-form">
                            <h3>Add more Book Quantity</h3>
                            <div class="row">
                                <div class="col-3">
                                    <label for="quantity" class="form-label">Previous Quantity: ${bookDTO.getQuantity()}</label>
                                    <input type="number" class="form-control" id="quantity" name="quantity" required>
                                </div>
                            </div>
                            <div class="row">
                                <button type="submit" class="btn btn-outline-primary">Save</button>
                            </div>

                        </form>
                    </c:if>

                    <div class="footer">
                        <c:if test="${requestScope['success'] != null}">
                            <ul class="success">
                                <li>${requestScope['success']}</li>
                            </ul>
                        </c:if>
                        <c:if test="${!requestScope['errors'].isEmpty()}">
                            <ul class="error">
                                <c:forEach items="${requestScope['errors']}" var="item">
                                    <li>${item}</li>
                                </c:forEach>
                            </ul>
                        </c:if>
                        <c:if test="${!requestScope['errorsId'].isEmpty()}">
                            <ul class="error">
                                <c:forEach items="${requestScope['errorsId']}" var="item">
                                    <li>${item}</li>
                                </c:forEach>
                            </ul>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
        <!-- END MAIN CONTENT-->
        <!-- END PAGE CONTAINER-->
    </div>

</div>
<!-- js -->
<%@include file="/library/layout/js.jsp"%>
</body>
</html>