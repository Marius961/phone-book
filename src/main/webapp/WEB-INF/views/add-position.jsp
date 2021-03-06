<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <c:if test="${position.id != '0'}">
        <title>Редагувати посаду</title>
    </c:if>
    <c:if test="${position.id == '0'}">
        <title>Додати посаду</title>
    </c:if>
    <c:if test="${position.id != '0'}">
    <link rel="icon" href="<%=request.getContextPath()%>/resources/images/edit.ico" type="images/x-icon">
    </c:if>
    <c:if test="${position.id == '0'}">
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
            <a class="navbar-brand" href="<c:url value="/"/>">На головну</a>
            <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
            </ul>
        </div>
    </nav>

</header>
<main>
    <div class="form-header">Посада</div>
    <form:form action="/process-position" modelAttribute="position"  class="form-box">
        <div class="form-group">
            <form:label path="name" for="departmentName">Назва посади</form:label>
            <form:input path="name" type="text" class="form-control" id="departmentName" value="${position.name}"/>
            <small class="form-text text-muted">Введіть назву посади</small>
            <form:errors path="name" cssClass="error-message"/>
            <form:hidden path="id" value="${position.id}"/>
        </div>
        <button type="submit" class="btn btn-primary" style="background-color: #28a745;">Зберегти</button>
    </form:form>
</main>
</body>
</html>