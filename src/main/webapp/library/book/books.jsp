<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>

    <title>Books</title>
    <%@include file="/library/layout/head.jsp"%>
    <style>
        table thead {
            background-color: #005cbf;
            color: white;
        }
        table tbody {
            background-color: white;
            color: black;
        }
    </style>

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
                        <div class="col-2">
                            <h1>Books</h1>
                        </div>
                        <div class="col-6">
                            <form>
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                        <select class="form-control" name="field_name" id="field_name" onchange="blockInputSearch()">
                                            <option value="b.name">Name</option>
                                            <option value="a.name">Author</option>
                                            <option value="g.name">Genre</option>
                                            <option value="p.name">Publisher</option>
                                            <option value="disable">Disable</option>
                                        </select>
                                    </div>
                                    <input type="search" class="form-control" name="search" id="search" placeholder="search">
                                    <button type="submit" class="btn btn-primary">Search</button>
                                </div>
                                <div>
                                    <p>use ' ' to search exactly element (Ex: 'a')</p>
                                    <p>or All caps (no spaces inside) to search by first characters (Ex: AB, 'AB',...)</p>
                                </div>
                            </form>
                        </div>

                        <div class="col-4">
                            <a href="/books?action=add">
                                <button type="button" class="btn btn-danger">Add Book</button>
                            </a>
                        </div>
                    </div>
                    <hr>
                    <c:if test="${requestScope['errors'] == null}">
                        <div class="row">
                            <table class="table table-hover">
                                <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Name</th>
                                    <th>Author</th>
                                    <th>Genre</th>
                                    <th>Publisher</th>
                                    <th>Available</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${requestScope['bookDTOList']}" var="item" begin="${(pageIndex -1) * 10}" end="${pageIndex * 10 - 1}">
                                    <tr onclick="window.location.assign('/books?action=select&id=${item.getId()}');">
                                        <td>${item.getId()}</td>
                                        <td>${item.getName()}</td>
                                        <td>${item.getAuthor()}</td>
                                        <td>${item.getGenre()}</td>
                                        <td>${item.getPublisher()}</td>
                                        <td>${item.getAvailable()}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <div class="row"></div>

                        <nav aria-label="Page navigation example">
                            <ul class="pagination justify-content-end">
                                <c:choose>
                                    <c:when test="${pageIndex == 1}">
                                        <li class="page-item disabled">
                                            <a class="page-link" href="/books?action=show&page_index=${pageIndex - 1}">Previous</a>
                                        </li>
                                    </c:when>
                                    <c:otherwise>
                                        <li class="page-item">
                                            <a class="page-link" href="/books?action=show&page_index=${pageIndex - 1}">Previous</a>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                                <c:forEach begin="1" end="${maxPageIndex}" var="i">
                                    <c:choose>
                                        <c:when test="${pageIndex == i}">
                                            <li class="page-item disabled">
                                                <a class="page-link" href="/books?action=show&page_index=${i}">${i}</a>
                                            </li>
                                        </c:when>
                                        <c:otherwise>
                                            <li class="page-item">
                                                <a class="page-link" href="/books?action=show&page_index=${i}">${i}</a>
                                            </li>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                                <c:choose>
                                    <c:when test="${pageIndex == maxPageIndex}">
                                        <li class="page-item disabled">
                                            <a class="page-link" href="/books?action=show&page_index=${pageIndex + 1}">Next</a>
                                        </li>
                                    </c:when>
                                    <c:otherwise>
                                        <li class="page-item">
                                            <a class="page-link" href="/books?action=show&page_index=${pageIndex + 1}">Next</a>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                            </ul>
                        </nav>
                    </c:if>

                    <c:if test="${!requestScope['errors'].isEmpty()}">
                        <ul class="error">
                            <c:forEach items="${requestScope['errors']}" var="item">
                                <li>${item}</li>
                            </c:forEach>
                        </ul>
                    </c:if>
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

