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
<script>
    var filter = new Object();
    filter.foto = "";
    filter.brand = "";
    filter.period = "";

    function getList(filterList) {
        $.ajax({
            type: "POST",
            url: "/carslist",
            dataType: 'json',
            contentType: "application/json; charset=utf-8",
            cache: false,
            data: JSON.stringify(filterList),
            complete:  function (data) {
                var carslist = JSON.parse(data.responseText);
                var table = "";
                for (var i = 0; i != carslist.length; i++) {
                    table += "<tr><td><b><a href=/showads?id=" + carslist[i].id + ">" + carslist[i].title  + "</a></b></br>" +
                        "Author1: " + carslist[i].aduser.name + "</br>" +
                        "Cost: " + carslist[i].cost + "</br>" +
                        "Mileage: " + carslist[i].mileage + "</br>" +
                        "Power: " + carslist[i].power + "</br>" +
                        "Brand: " + carslist[i].brand.name + "</br>" +
                        "Engine: " + carslist[i].engine.name + "</br>" +
                        "Body: " + carslist[i].body.name + "</br>" +
                        "Drive: " + carslist[i].drive.name + "</br>" +
                        "Transmission: " + carslist[i].transmission.name + "</br>" +
                        "Wheel: " + carslist[i].wheel.name + "</br>" +
                        "Status: " + carslist[i].status + "</br>" +
                        "</td><td>";
                    if (carslist[i].imageSet.length > 0) table +=  "<img src=/image?fileName=" + carslist[i].imageSet[0].name + " width=\"250\" height=\"250\" class=\"img-thumbnail\" alt=\"Cinque Terre\">" + carslist[i].imageSet[0].name + "</img>";

                    table += "</td></tr>";
                }
                $('#table').find('tbody').empty().append(table);
            },
            error: function () {
                alert('error')
            }
        });
    }

    $(document).ready(function(){
        getList(filter);
    });

    function setStatus() {
        $('input[name="foto"]').change(function(){
            if($(this).is(":checked")){
                var id = $(this).val().toString();
                setFotoFilter(true);
                $('input[name="foto"]').events.cleanUp();
            }
            else if($(this).is(":not(:checked)")){
                var id = $(this).val().toString();
                setFotoFilter(false);
                $('input[name="foto"]').events.cleanUp();
            }
            alert("do");
        });
    }

    function setFotoFilter(status) {
        filter.foto = status;
        filter.brand = $('#brand').val();
        filter.period = $('#period').val();;
        getList(filter)
    }

    function setFilter() {
        filter.brand = $('#brand').val();
        filter.period = $('#period').val();
        getList(filter)
    }

</script>

<div class="jumbotron text-center">
    <h1> Welcome to the Ad Manager</h1>

    <p class="bg-danger">
        <c:if test="${error != ''}">
            <c:out value="${error}" ></c:out>
        </c:if>
    </p>

    <p>   <c:if test="${ empty sessionScope.user }">
        <a href="${pageContext.request.contextPath}/signin">Sign In</a><br>
    </c:if>
        <c:if test="${ not empty sessionScope.user }">
            <a href="${pageContext.request.contextPath}/signout">Sign out</a><br>
        </c:if>
    </p>
</div>

<div class="container">
    <h2>Ad List.</h2>
    <c:if test="${ not empty sessionScope.user }">
        <p>Welcome: <c:out value="${sessionScope.user}"></c:out></p>
        <b><a href="${pageContext.request.contextPath}/addads">Add new ads</a></b>
    </c:if>

    <!-- filter section-->
    <div class="panel panel-default">
        <div class="panel-body">
            <h2>Filter.</h2>

            <div class="checkbox">
                <label><input type="checkbox" value=""  id = "foto" name="foto" onclick="setStatus()" >Show with foto</label>
            </div>

            <div class="form-group">
                <label for="brand">Brand:</label>
                <select class="form-control" id="brand" name="brand" onclick="setFilter()">
                    <option value="">All</option>
                    <c:forEach items="${brand}" var="brand">
                        <option value="<c:out value="${brand.id}"></c:out>"><c:out value="${brand.name}"></c:out></option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group">
                <label for="Period">Period:</label>
                <select class="form-control" id="period" name="brand" onclick="setFilter()">
                    <option value="">All</option>
                    <option value="1">For last day</option>
                </select>
            </div>

        </div>
    </div>
    <!-- End filter section-->

    <table class="table table-bordered" id='table'>
        <thead>
        <tr>
            <th>Ads</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        </tbody>
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
