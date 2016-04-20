<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>User</title>
    <jsp:include page="header.jsp"/>
</head>

<body>
<%--modelAttribute="contact"--%>
<div class="container">
    <div class="row">
        <div class="col-xs-4 col-xs-offset-4">
            <h3>Добавить контакт</h3>
            <form:form class="form-horizontal" commandName="contact" modelAttribute="contact" method="POST"
                       action="/add">
                <table class="table">
                    <tr class="hidden">
                        <td><form:label path="id">Id:</form:label></td>
                        <td>
                            <form:input path="id" disabled="true"/>
                            <form:errors path="id" cssStyle="color: #ff0000;"/>
                        </td>
                    </tr>
                    <tr>
                        <td><form:label path="firstName">Имя:</form:label></td>
                        <td>
                            <form:input path="firstName" data-toggle="tooltip"
                                        data-placement="right" title="Минимум 4 силволов"/><br>
                            <form:errors path="firstName" cssStyle="color: #ff0000;"/>
                        </td>
                    </tr>
                    <tr>
                        <td><form:label path="secondName">Фамилия:</form:label></td>
                        <td>
                            <form:input path="secondName" data-toggle="tooltip"
                                        data-placement="right" title="Минимум 4 силволов"/><br>
                            <form:errors path="secondName" cssStyle="color: #ff0000;"/>
                        </td>
                    </tr>
                    <tr>
                        <td><form:label path="lastName">Отчество:</form:label></td>
                        <td>
                            <form:input path="lastName" data-toggle="tooltip"
                                        data-placement="right" title="Минимум 4 силволов"/><br>
                            <form:errors path="lastName" cssStyle="color: #ff0000;"/>
                        </td>
                    </tr>
                    <tr>
                        <td><form:label path="phoneMobile">Моб.телефон:</form:label></td>
                        <td>
                            <form:input path="phoneMobile" data-toggle="tooltip"
                                        data-placement="right" title="Пример: +380(66)1234567"/><br>
                            <form:errors path="phoneMobile" cssStyle="color: #ff0000;"/>
                        </td>
                    </tr>
                    <tr>
                        <td><form:label path="phoneHome">Дом.телефон:</form:label></td>
                        <td>
                            <form:input path="phoneHome" data-toggle="tooltip"
                                        data-placement="right" title="Не обязательно"/><br>
                            <form:errors path="phoneHome" cssStyle="color: #ff0000;"/>
                        </td>
                    </tr>
                    <tr>
                        <td><form:label path="address">Адрес:</form:label></td>
                        <td>
                            <form:input path="address" data-toggle="tooltip"
                                        data-placement="right" title="Не обязательно"/><br>
                            <form:errors path="address" cssStyle="color: #ff0000;"/>
                        </td>
                    </tr>
                    <tr>
                        <td><form:label path="email">E-mail:</form:label></td>
                        <td>
                            <form:input path="email" data-toggle="tooltip"
                                        data-placement="right" title="Не обязательно"/><br>
                            <form:errors path="email" cssStyle="color: #ff0000;"/>
                        </td>
                    </tr>
                </table>
                <div id="bottom-btn-div">
                    <button type="button" class="btn" onclick="location.href='/'">Назад</button>
                    <input class="btn" type="submit" value="Сохранить"/>
                </div>
                <%--<form:button type="button" id="addContact">Submit</form:button>--%>
            </form:form>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>
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
