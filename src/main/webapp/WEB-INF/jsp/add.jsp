<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>User</title>
    <%--<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link media="all" rel="stylesheet" href="/css/style.css" type="text/css"/>
    <link rel="icon" type="image/png" href="/img/icon.png"/>
    <link rel="apple-touch-icon" href="/img/icon.png"/>
    <script type="text/javascript" src="/js/jquery-1.12.3.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <script src="/js/feedback.js"></script>--%>
    <jsp:include page="header.jsp"/>
</head>

<body>

<div class="container">
    <div class="row">
        <div class="col-xs-4 col-xs-offset-4">
            <h3>Добавить контакт</h3>
            <c:url var="saveUrl" value="/add/${contact.id}"/>
            <form:form modelAttribute="contact" method="POST" action="/add">
                <table class="table">
                    <tr class="hidden">
                        <td><form:label path="id">Id:</form:label></td>
                        <td><form:input path="id" disabled="true"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="firstName">Имя:</form:label></td>
                        <td><form:input path="firstName" placeholder="Минимум 5 силволов"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="secondName">Фамилия:</form:label></td>
                        <td><form:input path="secondName" placeholder="Минимум 5 силволов"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="lastName">Отчество:</form:label></td>
                        <td><form:input path="lastName" placeholder="Минимум 5 силволов"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="phoneMobile">Моб.телефон:</form:label></td>
                        <td><form:input path="phoneMobile" placeholder="+380(66)1234567"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="phoneHome">Дом.телефон:</form:label></td>
                        <td><form:input path="phoneHome" placeholder="Не обязательно"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="address">Адрес:</form:label></td>
                        <td><form:input path="address" placeholder="Не обязательно"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="email">E-mail:</form:label></td>
                        <td><form:input path="email" placeholder="Не обязательно"/></td>
                    </tr>
                </table>
                <button type="button" class="btn" onclick="location.href='/'">Назад</button>
                <input class="btn" type="submit" value="Сохранить"/>
                <%--<form:button type="button" id="addContact">Submit</form:button>--%>
            </form:form>

        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>

</body>
</html>

<%--<form>--%>
<%--<input type="text" id="firstName" name="firstName" placeholder="FirstName">--%>
<%--<input type="text" id="secondName" name="secondName" placeholder="SecondName">--%>
<%--<input type="text" id="lastName" name="lastName" placeholder="LastName">--%>
<%--<input type="text" id="phoneMobile" name="phoneMobile" placeholder="PhoneMobile">--%>
<%--<input type="text" id="phoneHome" name="phoneHome" placeholder="PhoneHome">--%>
<%--<input type="text" id="address" name="address" placeholder="Address">--%>
<%--<input type="text" id="email" name="email" placeholder="Email">--%>
<%--<button type="button" id="addContact">Submit</button>--%>

<%--</form>--%>