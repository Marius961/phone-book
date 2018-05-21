<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <c:if test="${emploee.id != '0'}">
    <title>Редагувати працівника</title>
    </c:if>
    <c:if test="${emploee.id == '0'}">
    <title>Додати працівника</title>
    </c:if>
    <c:if test="${emploee.id != '0'}">
        <link rel="icon" href="<%=request.getContextPath()%>/resources/images/edit.ico" type="images/x-icon">
    </c:if>
    <c:if test="${emploee.id == '0'}">
        <link rel="icon" href="<%=request.getContextPath()%>/resources/images/add.ico" type="images/x-icon">
    </c:if>
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
            <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
                <li class="nav-item active">
                    <a class="nav-link" href="<c:url value="/"/>">На головну<span class="sr-only ">(current)</span></a>
                </li>
            </ul>
        </div>
    </nav>
</header>

<main>
    <div class="form-header"></div>
    <form:form action="/process-employee" method="post" modelAttribute="employee" cssClass="form-box">
        <div class="form-group">
            <form:label path="" for="fullName">ПІБ</form:label>
            <form:input path="fullName" type="text" class="form-control" id="fullName" value="${employee.fullName}"/>
            <small class="form-text text-muted">Введіть ім'я, прізвище, по батькові працівника</small>
            <form:errors path="fullName" cssClass="error-message"/>
        </div>
        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <label class="input-group-text" for="positionSelect">Посади</label>
            </div>
            <form:select path="positionId" class="custom-select" id="positionSelect">
                <form:option value="0" label="Виберіть посаду працівника"/>
                <c:forEach var="position" items="${positions}">
                    <form:option value="${position.id}" label="${position.name}"/>
                </c:forEach>
            </form:select>
        </div>
        <form:errors path="positionId" cssClass="error-message"/>
        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <label class="input-group-text" for="departmentSelect">Відділи</label>
            </div>
            <form:select path="departmentId" class="custom-select" id="departmentSelect">
                <form:option value="0" label="Виберіть відділ"/>
                <c:forEach var="department" items="${departments}">
                    <form:option value="${department.id}" label="${department.name}"/>
                </c:forEach>
            </form:select>
        </div>
        <form:errors path="departmentId" cssClass="error-message"/>
        <div class="form-group" >
            <form:label path="ledlineNumber" for="ledlineNum">Домашній телефон</form:label>
            <form:input path="ledlineNumber" type="tel" class="form-control" id="ledlineNum" value="${employee.ledlineNumber}" cssStyle="width: 80%"/>
            <small class="form-text text-muted">Введіть домашній телефон працівника</small>
            <form:errors path="ledlineNumber" cssClass="error-message"/>
        </div>
        <div class="form-group">
            <form:label path="mobileNumber" for="mobileNum">Мобільний телефон</form:label>
            <br>
            <div class="input-group-prepend" style="width: 7%; display: inline-block">
                <div class="input-group-text">+38</div>
            </div>
            <form:input path="mobileNumber" type="text" class="form-control" id="mobileNum" value="${employee.mobileNumber}" cssStyle="width: 73.5%; display: inline-block" />
            <small class="form-text text-muted">Введіть мобільний телефон працівника</small>
            <form:errors path="mobileNumber" cssClass="error-message"/>
        </div>
        <form:hidden path="id" value="${employee.id}"/>
        <button type="submit" class="btn btn-primary" style="background-color: #28a745">Зберегти</button>
    </form:form>
</main>
</body>
</html>
