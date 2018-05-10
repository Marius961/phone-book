<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/main.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
</head>
<body>
<header>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark nav-size">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
            <a class="navbar-brand" href="<c:url value="/"/>">На головну</a>
            <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/add-position"/>">Додати посаду<span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/add-department"/>">Додати відділ<span class="sr-only">(current)</span></a>
                </li>
            </ul>
        </div>
    </nav>

</header>
<main>
    <div style="width: 47%; display: inline-block; margin: 1%; float: left">
        <div class="table-div dep-block" onclick="showHide(1)" id="label1">
            <div class="table-cell-div">Посади
                <div class="icon-div-2">
                    <img src="<%=request.getContextPath()%>/resources/images/arrowIMG.png" class="icon-2" id="icon1">
                </div>
            </div>
        </div>
        <div id="department1" class="department-div" style="display: none">
            <c:forEach var="position" items="${positions}">
                <div class="table-div-info">
                    <span class="table-cell-div-info">${position.name}</span>
                    <div class="table-cell-div-info">
                        <div class="icon-div">
                            <img onclick="location.href='/edit-position/${position.id}'" src="<%=request.getContextPath()%>/resources/images/editIMG.png" class="icon">
                        </div>
                        <c:if test="${position.canDelete}">
                            <div class="icon-div">
                                <img onclick="location.href='/delete-position/${position.id}'" src="<%=request.getContextPath()%>/resources/images/delete2IMG.png" class="icon">
                            </div>
                        </c:if>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
    <div style="width: 47%; display: inline-block; margin: 1%; float: left">
        <div class="table-div dep-block" onclick="showHide(2)" id="label2">
            <div class="table-cell-div">Відділи
                <div class="icon-div-2">
                    <img src="<%=request.getContextPath()%>/resources/images/arrowIMG.png" class="icon-2" id="icon2">
                </div>
            </div>
        </div>
        <div id="department2" class="department-div" style="display: none">
            <c:forEach var="department" items="${departments}">
                <div class="table-div-info">
                    <span class="table-cell-div-info">${department.name}</span>
                    <div class="table-cell-div-info">
                        <div class="icon-div">
                            <img onclick="location.href='/edit-department/${department.id}'" src="<%=request.getContextPath()%>/resources/images/editIMG.png" class="icon">
                        </div>
                        <c:if test="${department.canDelete}">
                            <div class="icon-div">
                                <img onclick="location.href='/delete-department/${department.id}'" src="<%=request.getContextPath()%>/resources/images/delete2IMG.png" class="icon">
                            </div>
                        </c:if>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</main>

<script src="<%=request.getContextPath()%>/resources/js/main.js"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>