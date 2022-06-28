<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html lang="en">
<head>

    <title>Authors</title>
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
                        <form class="col-5">
                            <div class="row">
                                <div class="col-8">
                                    <select class="form-control" name="type" id="type" onchange="unlockIdGroup()">
                                        <option value="authors">Authors</option>
                                        <option value="genres">Genres</option>
                                        <option value="publishers">Publishers</option>
                                    </select>
                                </div>
                                <div class="col-4">
                                    <button type="submit" class="btn btn-outline-primary">Select</button>
                                </div>
                            </div>
                        </form>

                    </div>
                    <hr>

                    <c:if test="${type == 'authors' or type == 'genres' or type == 'publishers' }">
                        <div class="row">
                            <h1>${fn:toUpperCase(type)}</h1>
                        </div>
                        <div class="row">
                            <div class="col-5">
                                <div class="info">
                                    <table class="table table-hover">
                                        <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Name</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${requestScope['list']}" var="item">
                                            <tr>
                                                <td>${item.getId()}</td>
                                                <td>${item.getName()}</td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <div class="col-7">
                                <div class="book-form">
                                    <form method="post">
                                        <div class="row">
                                            <div class="col-4">
                                                <label for="action" class="form-label">Action</label>
                                                <select class="form-control" name="action" id="action" onchange="unlockIdGroup()">
                                                    <option value="add">Add New</option>
                                                    <option value="edit">Edit</option>
                                                </select>
                                            </div>
                                            <div class="col-8" style="display: none" id="idGroup">
<%--                                                <form>--%>
<%--                                                    <label for="search" class="form-label">Name</label>--%>
<%--                                                    <div class="row">--%>
<%--                                                        <div class="col-8">--%>
<%--                                                            <input type="search" class="form-control" placeholder="Enter name" id="search" name="search">--%>
<%--                                                        </div>--%>
<%--                                                        <div class="col-4">--%>
<%--                                                            <button type="submit" class="btn btn-outline-primary">Search</button>--%>
<%--                                                        </div>--%>
<%--                                                    </div>--%>
<%--                                                    <div class="row">--%>
<%--                                                        <div class="col-12">--%>
<%--                                                            <select class="form-control" name="id" id="id">--%>
<%--                                                                <c:forEach items="${requestScope['searchList']}" var="item">--%>
<%--                                                                    <option value="${item.getId()}">${item.getName()}</option>--%>
<%--                                                                </c:forEach>--%>
<%--                                                            </select>--%>
<%--                                                        </div>--%>
<%--                                                    </div>--%>
<%--                                                </form>--%>

                                                <label for="id" class="form-label">Name</label>
                                                <select class="form-control" name="id" id="id">
                                                    <c:forEach items="${requestScope['list']}" var="item">
                                                        <option value="${item.getId()}">${item.getName()}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-12">
                                                <label for="name" class="form-label">New Name *</label>
                                                <input type="text" class="form-control" id="name" name="name" required>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <button type="submit" class="btn btn-outline-primary">Save</button>
                                        </div>
                                    </form>
                                </div>

                            </div>
                        </div>
                    </c:if>
                    <div class="footer">
                        <c:if test="${!requestScope['success'].isEmpty()}">
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

