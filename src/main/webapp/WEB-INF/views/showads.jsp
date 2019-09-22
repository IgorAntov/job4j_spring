<%--
  Created by IntelliJ IDEA.
  User: igor
  Date: 29.08.19
  Time: 4:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>My Page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="jumbotron text-center">
    <h1> Welcome to the Ad Manager</h1>
</div>

<div class="container">
    <h2>Ad Info.</h2>
    <b><a href="${pageContext.request.contextPath}/carslist">Ads List</a></b>
    <c:if test="${sessionScope.user == car.aduser.name && car.status == 'not sold'}">
        <div class="container-fluid">
            <form action='${pageContext.request.contextPath}/adsupdate' id="myForm" method = 'post'>
                <input type='hidden' name='id' value="<c:out value="${car.id}"></c:out>"></br>
                <button type="submit" class="btn btn-default">Set Status to Sold</button>
            </form>
        </div></br>
    </c:if>
    <table class="table table-bordered" id='table'>
        <thead>
        <tr>
            <th>Item</th>
            <th>Info</th>
        </tr>
        </thead>
        </tr>
        <tr>
            <td> Title </td><td> <c:out value="${car.title}"></c:out> </td>
        </tr>
        <tr>
            <td> Desc </td><td> <c:out value="${car.addesc}"></c:out> </td>
        </tr>
        <tr>
            <td> Cost </td><td> <c:out value="${car.cost}"></c:out> </td>
        </tr>
        <tr>
            <td> Mileage </td><td> <c:out value="${car.mileage}"></c:out> </td>
        </tr>
        <tr>
            <td> Power </td><td> <c:out value="${car.power}"></c:out> </td>
        </tr>
        <tr>
            <td> Body </td><td> <c:out value="${car.body.name}"></c:out> </td>
        </tr>
        <tr>
            <td> Brand </td><td> <c:out value="${car.brand.name}"></c:out> </td>
        </tr>
        <tr>
            <td> Drive </td><td> <c:out value="${car.drive.name}"></c:out> </td>
        </tr>
        <tr>
            <td> Engine </td><td> <c:out value="${car.engine.name}"></c:out> </td>
        </tr>
        <tr>
            <td> Transmission </td><td> <c:out value="${car.transmission.name}"></c:out> </td>
        </tr>
        <tr>
            <td> Wheel </td><td> <c:out value="${car.wheel.name}"></c:out> </td>
        </tr>

           <c:forEach items="${imageset}" var="file">
            <tr><td>Image:</td>
                <td>
                    <a href="${pageContext.request.contextPath}/image?fileName=<c:out value="${file.name}"></c:out>">Download Link: <c:out value="${file.name}"></c:out></a><br>
                    <img src="${pageContext.request.contextPath}/image?fileName=<c:out value="${file.name}"></c:out>" width="250" height="250" class="img-thumbnail" alt="Cinque Terre"><br>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<br>
<div class="container">
    <div class="row">
        <div class="col-sm-4">
            <c:if test="${sessionScope.role == 'All'}">
                <div class="container-fluid">
                    <form action='${pageContext.request.contextPath}/' id="myForm">
                        <input type='hidden' name='task' value="add"></br>
                        <button type="submit" class="btn btn-default">Add New Advertising</button>
                    </form>
                </div>
            </c:if>
        </div>
        <div class="col-sm-4">
            <%--            <h3>Column 2</h3>
                        <p>Lorem ipsum dolor..</p>--%>
        </div>
        <div class="col-sm-4">
            <%--            <h3>Column 3</h3>
                        <p>Lorem ipsum dolor..</p>--%>
        </div>
    </div>
</div><br>
</body>
</html>

