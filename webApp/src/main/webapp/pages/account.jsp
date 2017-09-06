<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Spring Security</title>

    <!-- Bootstrap core CSS -->
    <link href="<c:url value="/pages/css/bootstrap.css" />" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="<c:url value="/pages/css/signin.css" />" rel="stylesheet">

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
</head>

<body>
    <div class="container" style="width: 500px;">
        <form:form method="post"  modelAttribute="user">
            <h2 class="form-signin-heading">Ваши данные:</h2>
            <font color="red">
               <form:errors path="*" cssClass="error"/>
            </font>
            <p> Логин</p> <p><form:input type="text" path="email" disabled="true"/></p>
            <p> Фамилия</p> <p> <form:input type="text" path="lastName"/></p>
            <p> Имя </p> <p><form:input type="text" path="firstName"/></p>
            <button class="btn btn-lg btn-success" name="change" type="submit">Сохранить изменения</button>
            <p/>
            <p><button class="btn btn-lg btn-danger" name="delete" type="submit">Удалить аккаунт</button></p>
        </form:form>
        <p/>
        <p><a class="btn btn-lg btn-primary" href="<c:url value="/" />" role="button">Главная страница</a></p>
    </div>

</body>
</html>