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
    <form:form commandName="user" modelAttribute="user" class="form-signin" method="POST" action="/register">
        <h2 class="form-signin-heading">Регистрация пользователя</h2>
        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>
        <form:input id="inputReg" path="username" class="form-control" data-toggle="tooltip"
                    placeholder="Логин" data-placement="right" title="Минимум 3, без спецсимволов"/>
        <form:errors path="username" cssStyle="color: #ff0000;"/>

        <form:input id="inputReg" path="password" class="form-control" data-toggle="tooltip"
                    placeholder="Пароль" data-placement="right" title="Минимум 5 символов"/>
        <form:errors path="password" cssStyle="color: #ff0000;"/>

        <form:input id="inputReg" path="firstName" class="form-control" data-toggle="tooltip"
                    placeholder="Имя" data-placement="right" title="Минимум 5 символов"/>
        <form:errors path="firstName" cssStyle="color: #ff0000;"/>

        <form:input id="inputReg" path="secondName" class="form-control" data-toggle="tooltip"
                    placeholder="Фамилия" data-placement="right" title="Минимум 5 символов"/>
        <form:errors path="secondName" cssStyle="color: #ff0000;"/>

        <form:input id="inputReg" path="lastName" class="form-control" data-toggle="tooltip"
                    placeholder="Отчество" data-placement="right" title="Минимум 5 символов"/>
        <form:errors path="lastName" cssStyle="color: #ff0000;"/>



        <button class="btn btn-lg  btn-block" type="submit">Зарегистрироваться</button>
        <br>
        <button type="button" class="btn" onclick="location.href='/'">Назад</button>

        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <form:input class="hidden" path="enabled" disabled="true"/>
    </form:form>
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
