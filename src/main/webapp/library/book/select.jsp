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
                        <div class="col-8">
                            <h1>Book Information</h1>
                        </div>
                        <div class="col-3">
                            <a href="/books?action=show&page_index=1">
                                <button type="button" class="btn btn-danger">Show Book List</button>
                            </a>
                        </div>
                    </div>

                    <hr>
                    <c:if test="${requestScope['errors'] == null}">
                        <div class="container">
                            <div class="row">
                                <div class="col-7">
                                    <h2>${bookDTO.getName()}</h2>
                                </div>
                                <div class="col-4">
                                    <a href="/books?action=edit&id=${bookDTO.getId()}">
                                        <button type="button" class="btn btn-primary">
                                            <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">
                                                <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"></path>
                                                <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"></path>
                                            </svg>
                                            Edit
                                        </button>
                                    </a>
                                    <c:choose>
                                        <c:when test="${bookDTO.isActive() == true}">
                                            <a href="/books?action=disable&id=${bookDTO.getId()}">
                                                <button type="button" class="btn btn-outline-danger">
                                                    <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-archive-fill" viewBox="0 0 16 16">
                                                        <path d="M12.643 15C13.979 15 15 13.845 15 12.5V5H1v7.5C1 13.845 2.021 15 3.357 15h9.286zM5.5 7h5a.5.5 0 0 1 0 1h-5a.5.5 0 0 1 0-1zM.8 1a.8.8 0 0 0-.8.8V3a.8.8 0 0 0 .8.8h14.4A.8.8 0 0 0 16 3V1.8a.8.8 0 0 0-.8-.8H.8z"></path>
                                                    </svg>
                                                    Disable
                                                </button>
                                            </a>
                                        </c:when>
                                        <c:otherwise>
                                            <a href="/books?action=reActive&id=${bookDTO.getId()}">
                                                <button type="button" class="btn btn-success">
                                                    <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-unlock" viewBox="0 0 16 16">
                                                        <path d="M11 1a2 2 0 0 0-2 2v4a2 2 0 0 1 2 2v5a2 2 0 0 1-2 2H3a2 2 0 0 1-2-2V9a2 2 0 0 1 2-2h5V3a3 3 0 0 1 6 0v4a.5.5 0 0 1-1 0V3a2 2 0 0 0-2-2zM3 8a1 1 0 0 0-1 1v5a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V9a1 1 0 0 0-1-1H3z"/>
                                                    </svg>
                                                    Re-Active
                                                </button>
                                            </a>
                                        </c:otherwise>
                                    </c:choose>




                                </div>
                            </div>

                            <form class="book-form">
                                <div></div>
                                <div class="row">
                                    <div class="col-6">
                                        <label>Book Id: ${bookDTO.getId()}</label>
                                    </div>
                                    <div class="col-6">
                                        <label>Genre Name: ${bookDTO.getGenre()}</label>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-6">
                                        <label>Author Name: ${bookDTO.getAuthor()}</label>
                                    </div>
                                    <div class="col-6">
                                        <label>Publisher Name: ${bookDTO.getPublisher()}</label>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-6">
                                        <label>Quantity: ${bookDTO.getQuantity()}</label>
                                    </div>
                                    <div class="col-6">
                                        <label>Available: ${bookDTO.getAvailable()}</label>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-6">
                                        <c:choose>
                                            <c:when test="${bookDTO.isActive() == true}">
                                                <label style="color: green">Active: ${bookDTO.isActive()}</label>
                                            </c:when>
                                            <c:otherwise>
                                                <label style="color: red">Active: ${bookDTO.isActive()}</label>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>

                                </div>
                            </form>
                        </div>
                    </c:if>
                    <c:if test="${!requestScope['errors'].isEmpty()}">
                        <ul class="error">
                            <c:forEach items="${requestScope['errors']}" var="item">
                                <li>${item}</li>
                            </c:forEach>
                        </ul>
                    </c:if>
                    <c:if test="${requestScope['success'] != null}">
                        <ul class="success">
                            <li>${requestScope['success']}</li>
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



