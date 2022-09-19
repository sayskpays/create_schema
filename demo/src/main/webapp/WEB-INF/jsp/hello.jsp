<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page errorPage = "/error.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    <title>Create Schema</title>
    <style>
        .wrap {
            width: 800px;
            margin: 0 auto;
        }
    </style>
</head>
<body>
    <div class="wrap">
        <form>
            <div class="form-group">
                <h3 for="exampleFormControlTextarea1">Output Data</h3>
                <hr>
                <h4 for="exampleFormControlTextarea1">컬럼 총 갯수 : ${count}</h4>
                <br>
                <div class="textarea-group">
                    <h4>스키마 생성 적용 부분</h4>
                <textarea class="form-control" id="exampleFormControlTextarea1" rows="15"><c:forEach var="xml" items="${xml_data}">${xml}</c:forEach></textarea>
                    <h4>송신 적용 부분</h4>
                <textarea class="form-control" id="exampleFormControlTextarea1" rows="15">${column_data}</textarea>
                <textarea class="form-control" id="exampleFormControlTextarea1" rows="15"><c:forEach var="record" items="${recordList}">${record}</c:forEach></textarea>
                <textarea class="form-control" id="exampleFormControlTextarea1" rows="15"><c:forEach var="tmp" items="${selectList}"><c:forEach var="select" items="${tmp}">${select}</c:forEach></c:forEach></textarea>
                    <h4>수신 적용 부분</h4>
                <textarea class="form-control" id="exampleFormControlTextarea1" rows="15"><c:forEach var="parameter" items="${parameterList}">${parameter}</c:forEach></textarea>
                <textarea class="form-control" id="exampleFormControlTextarea1" rows="15">${question}</textarea>

                </div>   
            </div>
            <button type="button" class="btn btn-outline-primary" onclick="location.href='index.html'">Home</button>
        </form>
    </div>
</body>
</html>







