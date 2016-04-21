<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="header.jsp"/>
</head>

<%--<body onload='document.f.username.focus();'>--%>

<div class="container">
    <form class="form-signin" action="/login" method="post">
        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>
        <c:if test="${not empty msg}">
            <div class="msg">${msg}</div>
        </c:if>
        <h2 class="form-signin-heading">Войдите или <a href="/register">Зарегистрируйтесь</a></h2>
        <input type="text" name="username" id="inputEmail" class="form-control" placeholder="Логин" required autofocus
               data-toggle="tooltip" data-placement="right" title="Ваш логин">
        <input type="password" name="password" id="inputPassword" class="form-control" placeholder="Пароль" required
               data-toggle="tooltip" data-placement="right" title="Ваш пароль">
        <button class="btn btn-lg  btn-block" type="submit">Войти</button>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
</div>

<script>
    $(function () {
        $('[data-toggle="tooltip"]').tooltip({
//            placement: "bottom",
            trigger: "hover",
            'container': 'body'
        })

    })
</script>
</body>
</html>
