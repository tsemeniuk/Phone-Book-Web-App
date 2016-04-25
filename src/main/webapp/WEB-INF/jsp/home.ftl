<!DOCTYPE html>
<html xmlns:="http://www.w3.org/1999/xhtml">
<head lang="en">
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<div class="container">
<div class="col-xs-6 col-xs-offset-0">
<#if loggedUser??><a href="/signOut" class="hre">SignOut</a>
<#else ><a href="/signIn" class="hre">SignIn</a>
</div>
</#if>
</div>
</body>
</html>
