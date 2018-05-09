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
            <form:form modelAttribute="searchedObject" action="/search-emploee" method="post" class="form-inline my-2 my-lg-0">
                <form:input path="objectName" class="form-control mr-sm-2" type="search" placeholder="Пошук" aria-label="Search"/>
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Знайти</button>
            </form:form>
        </div>
    </nav>

</header>
<main>
    <div class="table-div">
        <span class="table-cell-div">Повне ім'я</span>
        <span class="table-cell-div">Посада (відділ)</span>
        <span class="table-cell-div">Мобільний телефон</span>
        <span class="table-cell-div">Домашній телефон</span>
        <span class="table-cell-div-info"></span>
    </div>
    <c:forEach var="emploee" items="${results}">
        <div class="table-div-info">
            <span class="table-cell-div-info">${emploee.fullName}</span>
            <span class="table-cell-div-info">${emploee.position.name}<br>(${emploee.department.name})</span>
            <span class="table-cell-div-info">${emploee.mobileNumber}</span>
            <span class="table-cell-div-info">${emploee.ledlineNumber}</span>
            <div class="table-cell-div-info">
                <form:form action="/edit-emploee" modelAttribute="emploee" cssClass="icon-div" id="editForm${emploee.id}">
                    <form:hidden path="id" value="${emploee.id}"/>
                    <form:hidden path="departmentId" value="${emploee.departmentId}"/>
                    <form:hidden path="ledlineNumber" value="${emploee.ledlineNumber}"/>
                    <form:hidden path="mobileNumber" value="${emploee.mobileNumber}"/>
                    <form:hidden path="positionId" value="${emploee.positionId}"/>
                    <form:hidden path="fullName" value="${emploee.fullName}"/>
                    <img onclick="submit(${emploee.id})" src="<%=request.getContextPath()%>/resources/images/editIMG.png" class="icon">
                </form:form>
                <div class="icon-div">
                    <img src="<%=request.getContextPath()%>/resources/images/delete2IMG.png" class="icon" onclick="location.href='/delete-emploee/${emploee.id}'">
                </div>
            </div>
        </div>
    </c:forEach>
</main>
</body>
</html>
