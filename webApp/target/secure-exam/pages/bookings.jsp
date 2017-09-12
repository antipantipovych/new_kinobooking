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
    <div class="container" style="width: 700px;">
        <form:form method="post"  modelAttribute="booking">
            <h2 class="form-signin-heading">Ваши брони:</h2>
            <c:forEach items="${bookingList}" var="b">
                <p><output> Бронирование № ${b.bookingId} </output></p>
                <c:forEach items="${b.tickets}" var="t">
                    <p><output> Билет ${t.ticketId} , дата сеанса ${t.seans.seansDate}</output></p>
                    <p><output> Кинотеатр ${t.cinema.cinemaName}, Фильм ${t.film.filmName}</output></p>
                    <p><output> Зал ${t.hall.hallNum}, Ряд ${t.seat.seatRow}, Место ${t.seat.seatNum}</output></p>
                    <p>- - - - - - - - - </p>
                </c:forEach>
                <p><button class="btn btn-lg btn-danger" value="${b.bookingId}" name="delete" type="submit">Удалить Бронирование</button></p>
                <p>-----------------------------------------------------------</p>
            </c:forEach>
        </form:form>
    </div>
</body>