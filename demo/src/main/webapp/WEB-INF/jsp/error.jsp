<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    <title>Error</title>
    <style>
        *{
    transition: all 0.6s;
}

html {
    height: 100%;
}

body{
    font-family: 'Lato', sans-serif;
    color: #888;
    margin: 0;
}

#main{
    display: table;
    width: 100%;
    height: 100vh;
    text-align: center;
}

.fof{
	  display: table-cell;
	  vertical-align: middle;
}

.fof h1{
	  font-size: 50px;
	  display: inline-block;
	  padding-right: 12px;
	  animation: type .5s alternate infinite;
}

@keyframes type{
	  from{box-shadow: inset -3px 0px 0px #888;}
	  to{box-shadow: inset -3px 0px 0px transparent;}
}
    </style>
</head>
<body>
    <div id="main">
    	<div class="fof">
        		<h1>Error 500</h1>
                <h1>Please correctly insert data </h1>
    	</div>
</div>
</body>
</html>







