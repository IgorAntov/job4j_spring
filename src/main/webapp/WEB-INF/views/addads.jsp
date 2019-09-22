<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
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
            title_el = $("#title");
            addesc_el = $("#addesc");
            if (title_el.val() == '') {
                alert("Please " + title_el.attr('placeholder')); return false;
            }
            if (addesc_el.val() == '') {
                alert("Please " + addesc_el.attr('placeholder')); return false;
            }
            return true;
        }
    </script>
</head>
<body>
<div class="jumbotron text-center">
    <h1>Ad Manager.</h1>
    <p>Add New Ads</p>
</div>
<div class="container">
    <div class="row">
        <div class="col-sm-4">
        </div>
        <div class="col-sm-4">
            <div class="panel panel-default">
                <div class="panel-body">
                    <form action='${pageContext.request.contextPath}/addads' method = "post" id="myForm" enctype="multipart/form-data">
                        <div class="form-group">
                            <label for="title">Title:</label>
                            <input type="text" class="form-control" id="title" placeholder="Enter title" name ="title">
                        </div>
                        <div class="form-group">
                            <label for="addesc">Desc:</label>
                            <textarea rows="3" class="form-control" id="addesc" placeholder="Enter addesc" name="addesc"></textarea>
                        </div>
                        <div class="form-group">
                            <label for="cost">Cost:</label>
                            <input type="text" class="form-control" id="cost" placeholder="Enter cost" name ="cost">
                        </div>
                        <div class="form-group">
                            <label for="mileage">Mileage:</label>
                            <input type="text" class="form-control" id="mileage" placeholder="Enter mileage" name ="mileage">
                        </div>
                        <div class="form-group">
                            <label for="power">Power:</label>
                            <input type="text" class="form-control" id="power" placeholder="Enter power" name ="power">
                        </div>
                        <!-- body section-->
                        <div class="form-group">
                            <label for="body">Body:</label>
                            <select class="form-control" id="body" name="body">
                                <c:forEach items="${body}" var="body">
                                    <option value="<c:out value="${body.id}"></c:out>"><c:out value="${body.name}"></c:out></option>
                                </c:forEach>
                            </select>
                        </div>
                        <!-- brand section-->
                        <div class="form-group">
                            <label for="brand">Brand:</label>
                            <select class="form-control" id="brand" name="brand">
                                <c:forEach items="${brand}" var="brand">
                                    <option value="<c:out value="${brand.id}"></c:out>"><c:out value="${brand.name}"></c:out></option>
                                </c:forEach>
                            </select>
                        </div>
                        <!-- drive section-->
                        <div class="form-group">
                            <label for="drive">Drive:</label>
                            <select class="form-control" id="drive" name="drive">
                                <c:forEach items="${drive}" var="drive">
                                    <option value="<c:out value="${drive.id}"></c:out>"><c:out value="${drive.name}"></c:out></option>
                                </c:forEach>
                            </select>
                        </div>
                        <!-- engine section-->
                        <div class="form-group">
                            <label for="engine">Engine:</label>
                            <select class="form-control" id="engine" name="engine">
                                <c:forEach items="${engine}" var="engine">
                                    <option value="<c:out value="${engine.id}"></c:out>"><c:out value="${engine.name}"></c:out></option>
                                </c:forEach>
                            </select>
                        </div>
                        <!-- transmission section-->
                        <div class="form-group">
                            <label for="transmission">Transmission:</label>
                            <select class="form-control" id="transmission" name="transmission">
                                <c:forEach items="${transmission}" var="transmission">
                                    <option value="<c:out value="${transmission.id}"></c:out>"><c:out value="${transmission.name}"></c:out></option>
                                </c:forEach>
                            </select>
                        </div>
                        <!-- wheel section-->
                        <div class="form-group">
                            <label for="wheel">Brand:</label>
                            <select class="form-control" id="wheel" name="wheel">
                                <c:forEach items="${wheel}" var="wheel">
                                    <option value="<c:out value="${wheel.id}"></c:out>"><c:out value="${wheel.name}"></c:out></option>
                                </c:forEach>
                            </select>
                        </div>
                        <!-- files upload-->
                        <div class="container-fluid">
                                <div class="form-group">
                                    <label for="file1">Select file to upload (*.png)</label>
                                    <input type="file" class="form-control-file" id="file1" name="file1">
                                </div>
                            <div class="form-group">
                                <label for="file2">Select file to upload (*.png)</label>
                                <input type="file" class="form-control-file" id="file2" name="file2">
                            </div>
                        </div>
                        <!-- end files upload-->
                        <button type="submit" class="btn btn-default" onclick="validate()">Save</button>
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