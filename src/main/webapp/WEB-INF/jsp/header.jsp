<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css" media="screen">
    <link href="/css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <link href="/css/media.css" rel="stylesheet" type="text/css" media="all">

    <script src="/js/jquery-1.12.3.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <script src="/js/main.js"></script>


</head>
<body>
<div class="col-lg-10 col-lg-offset-1 col-md-10 col-md-offset-1 col-xs-12 col-xs-offset-0">
    <div class="size">
        <a href="/">
            <nav class="navbar navbar-inverse">
                <div class="row">
                </div>
            </nav>
        </a>
    </div>
</div>

<div class="container">
    <div class="navbar-right">
        <c:if test="${not empty user.id}">
            <p>
                <span class="glyphicon glyphicon-user"></span>
                    ${user.firstName}&nbsp;${user.secondName}&nbsp;${user.lastName}&nbsp;&nbsp;|&nbsp;
            <a href="/logout" style="font-weight: bold">Log Out</a>
            </p>
        </c:if>
    </div>
<hr>
</div>

</body>
</html>
