<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>User</title>
    <%--<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link media="all" rel="stylesheet" href="/css/style.css" type="text/css"/>
    <link rel="icon" type="image/png" href="/img/salesa.png"/>
    <link rel="apple-touch-icon" href="/img/salesa.png"/>
    <script type="text/javascript" src="/js/jquery-1.12.3.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <script src="/js/feedback.js"></script>--%>
    <jsp:include page="head-include.jsp"/>
</head>

<body>
<form>
    <input type="text" id="firstName" name="firstName" placeholder="FirstName">
    <input type="text" id="secondName" name="secondName" placeholder="SecondName">
    <input type="text" id="lastName" name="lastName" placeholder="LastName">
    <input type="text" id="phoneMobile" name="phoneMobile" placeholder="PhoneMobile">
    <input type="text" id="phoneHome" name="phoneHome" placeholder="PhoneHome">
    <input type="text" id="address" name="address" placeholder="Address">
    <input type="text" id="email" name="email" placeholder="Email">
    <button type="button" id="addContact">Submit</button>

</form>
</body>
</html>
