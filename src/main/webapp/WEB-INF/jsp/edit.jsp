<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="header.jsp"/>
</head>

<body>

<div class="container">
    <div class="row">
        <div class="col-xs-4 col-xs-offset-4">
            <h3>Редактировать контакт</h3>
            <c:url var="saveUrl" value="/edit/${contact.id}"/>
            <form:form modelAttribute="contact" method="POST" action="${saveUrl}">
                <table class="table">
                    <tr class="hidden">
                        <td><form:label path="id">Id:</form:label></td>
                        <td><form:input path="id" disabled="true"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="firstName">Имя:</form:label></td>
                        <td><form:input path="firstName"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="secondName">Фамилия:</form:label></td>
                        <td><form:input path="secondName"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="lastName">Отчество:</form:label></td>
                        <td><form:input path="lastName"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="phoneMobile">Моб.телефон:</form:label></td>
                        <td><form:input path="phoneMobile"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="phoneHome">Дом.телефон:</form:label></td>
                        <td><form:input path="phoneHome"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="address">Адрес:</form:label></td>
                        <td><form:input path="address"/></td>
                    </tr>
                    <tr>
                        <td><form:label path="email">E-mail:</form:label></td>
                        <td><form:input path="email"/></td>
                    </tr>
                </table>
                <button class="btn" onclick="location.href='/'">Назад</button>
                <input class="btn" type="submit" value="Сохранить"/>
            </form:form>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>

</body>
</html>
