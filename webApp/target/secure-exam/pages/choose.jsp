<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
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
    <link href="<c:url value="/pages/css/jumbotron-narrow.css" />" rel="stylesheet">

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
</head>

<body>

<div class="container" style="width: 500px;">
    <h2 class="form-heading">Введите параметры поиска</h2>
        <form:form method="post"  modelAttribute="seans">
            <div class="form-group">
                <label class="control-label" >Фильм:</label>
                <form:select path="filmName" class="form-control">
                    <form:option value="">-- Select --</form:option>
                    <form:options items="${listOfFilmNames}"/>
                </form:select>
            </div>
            <div class="form-group">
                <label class="control-label" >Кинотеатр:</label>
                <form:select path="cinemaName" class="form-control">
                    <form:option value="">-- Select --</form:option>
                    <form:options items="${listOfCinemaNames}"/>
                </form:select>
            </div>
            <div class="form-group">
                <label class="control-label" >Дата:</label>
                <form:input class="form-control"  required="required" type="date" path="seansDate" id="seansDate"/>
            </div>
            <div class="form-group">
                <label class="control-label" >3D:</label>
                <form:checkbox path="threeD"/>
            </div>
            <button class="btn btn-lg btn-primary btn-block" name="find" value="Find" type="submit">Найти</button>
            <p/>
            <button class="btn btn-lg btn-success btn-block" type="submit" name="book" value="Book">Забронировать билеты</button>
            <font color="red">
                <form:errors path="seansId" cssClass="error"/>
            </font>
            <p/>
            <c:if test="${shortSeansNames.size() eq 0}">
                 <font color="red">
                    <p><output>Не найдено подходящих сеансов</output></p>
                 </font>
            </c:if>
            <c:forEach items="${shortSeansNames}" var="name">
                <p><output> ${name} </output></p>
                <c:forEach items="${listOfSeanses}" var="s">
                    <c:if test="${name eq s.toShortString()}">
                        <form:radiobutton path="seansId" value="${s.seansId}"/>${s.getStringTime()}
                    </c:if>
                </c:forEach>
            </c:forEach>
        </form:form>
</div>

<script>
    var c =document.getElementById("seansDate");
    var d= new Date();
    var dd = d.getDate();
    if (dd < 10) dd = "0" + dd;
    var mm = d.getMonth() + 1;
    if (mm < 10) mm = "0" + mm;
    var yyyy = d.getFullYear();
    c.min= yyyy+"-"+mm+"-"+dd;
</script>



</body>
</html>