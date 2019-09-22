<%--
  Created by IntelliJ IDEA.
  User: igor
  Date: 29.08.19
  Time: 5:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>My Page</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>
        function validate() {
            name_el = $("#name");
            password_el = $("#password");
            if (name_el.val() == '') {
                alert("Please " + name_el.attr('placeholder')); return false;
            }
            if (password_el.val() == '') {
                alert("Please " + password_el.attr('placeholder')); return false;
            }
            return true;
        }
    </script>
</head>
<body>
<div class="jumbotron text-center">
    <h1>Ad Manager.</h1>
    <p>Registration</p>
</div>

<div class="container">
    <div class="row">
        <div class="col-sm-4">
        </div>
        <div class="col-sm-4">
            <p class="bg-danger">
                <c:if test="${error != ''}">
                    <c:out value="${error}" ></c:out>
                </c:if>
            </p>

            <div class="panel panel-default">
                <div class="panel-body">
                    <form action='${pageContext.request.contextPath}/reg' style="width:300px" method = "post" id="myForm">
                        <div class="form-group">
                            <label for="name">User Name:</label>
                            <input type="text" class="form-control" id="name" placeholder="Enter name" name ="name">
                        </div>
                        <div class="form-group">
                            <label for="password">Password:</label>
                            <input type="password" class="form-control" id="password" placeholder="Enter password" name="password">
                        </div>
                        <button type="submit" class="btn btn-default" onclick="validate()">Add User</button>
                    </form>
                </div>
            </div>
        </div>
        <div class="col-sm-4">
        </div>
    </div>
</div>

</body>
</html>

