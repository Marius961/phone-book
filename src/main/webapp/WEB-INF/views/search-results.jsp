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
            <form:form modelAttribute="searchedObject" action="/search-employee" method="post" class="form-inline my-2 my-lg-0">
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
    <c:forEach var="employee" items="${results}">
        <div class="table-div-info">
            <span class="table-cell-div-info">${employee.fullName}</span>
            <span class="table-cell-div-info">${employee.position.name}<br>(${employee.department.name})</span>
            <span class="table-cell-div-info">${employee.mobileNumber}</span>
            <span class="table-cell-div-info">${employee.ledlineNumber}</span>
            <div class="table-cell-div-info">
                <form:form action="/edit-employee" modelAttribute="employee" cssClass="icon-div" id="editForm${employee.id}">
                    <form:hidden path="id" value="${employee.id}"/>
                    <form:hidden path="departmentId" value="${employee.departmentId}"/>
                    <form:hidden path="ledlineNumber" value="${employee.ledlineNumber}"/>
                    <form:hidden path="mobileNumber" value="${employee.mobileNumber}"/>
                    <form:hidden path="positionId" value="${employee.positionId}"/>
                    <form:hidden path="fullName" value="${employee.fullName}"/>
                    <img onclick="submit(${employee.id})" src="<%=request.getContextPath()%>/resources/images/editIMG.png" class="icon">
                </form:form>
                <div class="icon-div">
                    <img src="<%=request.getContextPath()%>/resources/images/delete2IMG.png" class="icon" onclick="location.href='/delete-employee/${employee.id}'">
                </div>
            </div>
        </div>
    </c:forEach>
</main>
</body>
</html>
