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
    <div class="container" style="width: 700px;">
        <h2 class="form-heading">Выберите места</h2>
        <form:form method="post"  modelAttribute="seans">
            <font color="red">
                <form:errors path="" cssClass="error"/>
            </font>
            <p><strong > Экран </strong></p>
            <c:forEach items="${rows}" var="r">
                <p><output>${r.intValue()} РЯД:    </output>

                <c:forEach items="${seats}" var="s">
                    <c:if test="${s.seatRow eq r.intValue()}">
                        <c:if test="${blockedSeats.contains(s.seatId)}">
                            <font color= "fuchsia">
                                <form:checkbox path="seatsForBook"  disabled="true" value="${s.seatId}"/>${s.seatNum}
                                <output>   </output>
                            </font>
                        </c:if>
                        <c:if test="${! blockedSeats.contains(s.seatId)}">
                            <font color= "green">
                                <form:checkbox path="seatsForBook"  value="${s.seatId}"/>${s.seatNum}
                                <output>   </output>
                            </font>
                        </c:if>
                    </c:if>
                </c:forEach>
                </p>
            </c:forEach>

            <font color="fuchsia">
                <p><output> места уже заняты </output></p>
            </font>
            <font color="green">
                <p><output> места свободны </output></p>
            </font>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Забронировать</button>
        </form:form>
    </div>
</body>
</html>