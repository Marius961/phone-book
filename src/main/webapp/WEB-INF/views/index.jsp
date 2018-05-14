<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Головна</title>
    <link rel="icon" href="<%=request.getContextPath()%>/resources/images/favicon.ico" type="images/x-icon">
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
            <a class="navbar-brand" href="#">Головна</a>
            <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/info"/> ">Посади та відділи<span class="sr-only">(current)</span></a>
                </li>
            </ul>
            <form:form modelAttribute="searchedObject" action="/search-employee" method="post" class="form-inline my-2 my-lg-0">
                <form:input path="objectName" class="form-control mr-sm-2" type="search" placeholder="Пошук" aria-label="Search"/>
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Знайти</button>
            </form:form>
        </div>
    </nav>
</header>
<main>
    <c:forEach var="department" items="${departments}" varStatus="status">
        <div>
            <div class="table-div dep-block" onclick="showHide(${department.id})" id="label${department.id}">
                <div class="table-cell-div">${department.name}
                    <small>(${department.emploeeCount})</small>
                    <div class="icon-div-2">
                        <img src="<%=request.getContextPath()%>/resources/images/arrowIMG.png" class="icon-2" id="icon${department.id}">
                    </div>
                </div>
            </div>
            <div id="department${department.id}" style="display: none">
                <c:if test="${empty department.employeeList}">
                    <div class="table-div-info">
                        <span class="table-cell-div-info">Працівники у цьому відділі відсутні</span>
                        <span class="table-cell-div" onclick="location.href='/add-employee/${department.id}'" style="cursor: pointer"><span><img src="<%=request.getContextPath()%>/resources/images/addIMG.png" style="margin-right:  3%; width: 6.5%">Додати працівника</span></span>
                    </div>
                </c:if>
                <c:if test="${not empty department.employeeList}">
                    <div class="table-div">
                        <span class="table-cell-div">Повне ім'я</span>
                        <span class="table-cell-div">Посада</span>
                        <span class="table-cell-div">Мобільний телефон</span>
                        <span class="table-cell-div">Домашній телефон</span>
                        <span class="table-cell-div" onclick="location.href='/add-employee/${department.id}'" style="cursor: pointer"><span><img src="<%=request.getContextPath()%>/resources/images/addIMG.png" style="margin-right:  3%; width: 6.5%">Додати працівника</span></span>
                    </div>
                    <c:forEach var="employee" items="${department.employeeList}">
                        <div class="table-div-info">
                            <span class="table-cell-div-info">${employee.fullName}</span>
                            <span class="table-cell-div-info">${employee.position.name}</span>
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
                </c:if>
            </div>
        </div>
    </c:forEach>
</main>

<script src="<%=request.getContextPath()%>/resources/js/main.js"></script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>
