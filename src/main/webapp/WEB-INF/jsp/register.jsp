<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="header.jsp"/>
</head>

<%--<body onload='document.f.username.focus();'>--%>

<div class="container">
    <form:form modelAttribute="user" class="form-signin" method="POST" action="/register">
        <h2 class="form-signin-heading">Регистрация пользователя</h2>
        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>
        <form:label path="username">Логин:</form:label>
        <form:input path="username" class="form-control" placeholder="Минимум 3, без спецсимволов"/>
        <form:label path="password">Пароль:</form:label>
        <form:input path="password" class="form-control" placeholder="Минимум 5 силволов"/>
        <form:label path="firstName">Имя:</form:label>
        <form:input path="firstName" class="form-control" placeholder="Минимум 5 силволов"/>
        <form:label path="secondName">Фамилия:</form:label>
        <form:input path="secondName" class="form-control" placeholder="Минимум 5 силволов"/>
        <form:label path="lastName">Отчество:</form:label>
        <form:input path="lastName" class="form-control" placeholder="Минимум 5 силволов"/>
        <button class="btn btn-lg  btn-block" type="submit">Зарегистрироваться</button>
        <button type="button" class="btn" onclick="location.href='/'">Назад</button>

        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <form:input class="hidden" path="enabled" disabled="true"/>
    </form:form>
</div>

</body>
</html>
