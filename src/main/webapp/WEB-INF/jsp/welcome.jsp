<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>--%>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Home</title>
    <jsp:include page="head-include.jsp"/>
</head>

<body>

Spring URL: ${springUrl} at
<br>
JSTL URL: ${url}
<br>
USER: IS ${user.firstName}<br>
<div class="container">
    <div class="row">
        <div class="col-xs-6 col-xs-offset-3">
            <table style="border: 1px solid black; width: auto;margin: auto">
                <thead>
                <td>id</td>
                <td>FirstName</td>
                <td>SecondName</td>
                <td>LastName</td>
                <td>PhoneMobile</td>
                <td>PhoneHome</td>
                <td>Address</td>
                <td>Email</td>
                </thead>
                <c:forEach items="${user.contacts}" var="contact">
                    <tr>
                        <td>${contact.id}</td>
                        <td> ${contact.firstName}</td>
                        <td> ${contact.secondName}</td>
                        <td> ${contact.lastName}</td>
                        <td> ${contact.phoneMobile}</td>
                        <td> ${contact.phoneHome}</td>
                        <td> ${contact.address} </td>
                        <td> ${contact.email} </td>
                    </tr>
                </c:forEach>
                <tr>
                    <button type="button"><a href="/add">Add Contact</a></button>
                </tr>
            </table>
        </div>
    </div>
</div>


<%--<c:forEach items="${categories}" var="category">--%>
<%--<c:choose>--%>
<%--<c:when test="${!empty category.children}">--%>
<%--<li class="dropdown-submenu">--%>
<%--<a tabindex="-1"--%>
<%--href="/category/${category.id}">${category.name}</a>--%>
<%--<ul class="dropdown-menu">--%>
<%--<c:forEach items="${category.children}" var="subCategory">--%>
<%--<li>--%>
<%--<a href="/category/${subCategory.id}">${subCategory.name}</a>--%>
<%--</li>--%>
<%--</c:forEach>--%>
<%--</ul>--%>
<%--</li>--%>
<%--</c:when>--%>
<%--<c:otherwise>--%>
<%--<li><a href="/category/${category.id}">${category.name}</a>--%>
<%--</li>--%>
<%--</c:otherwise>--%>
<%--</c:choose>--%>
<%--</c:forEach>--%>
</body>

</html>