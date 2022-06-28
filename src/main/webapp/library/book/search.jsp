<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>

    <title>Document</title>
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
                            <h1>Search</h1>
                        </div>
                        <div class="col-6">
                            <form>
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                        <select class="form-control" name="field_name" id="field_name" onchange="blockInputSearch()">
                                            <option value="name">Name</option>
                                            <option value="author">Author</option>
                                            <option value="genre">Genre</option>
                                            <option value="publisher">Publisher</option>
                                            <option value="disable">Disable</option>
                                        </select>
                                    </div>
                                    <input type="search" class="form-control" name="search" id="search" placeholder="use ' ' to search exactly element. Ex: 'a'">
                                    <button type="submit" class="btn btn-primary">Search</button>
                                </div>
                            </form>
                        </div>

                        <div class="col-4">
                            <a href="/books?action=show&page_index=1">
                                <button type="button" class="btn btn-danger">Show List of Book</button>
                            </a>
                        </div>
                    </div>
                    <hr>
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
                                <th>Active</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${requestScope['bookDTOList']}" var="item">
                                <tr onclick="window.location.assign('/books?action=select&id=${item.getId()}');">
                                    <td>
                                            ${item.getId()}
                                    </td>
                                    <td>
                                            ${item.getName()}
                                    </td>
                                    <td>
                                            ${item.getAuthor()}
                                    </td>
                                    <td>
                                            ${item.getGenre()}
                                    </td>
                                    <td>
                                            ${item.getPublisher()}
                                    </td>
                                    <td>
                                            ${item.getAvailable()}
                                    </td>
                                    <td>
                                            ${item.isActive()}
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
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
