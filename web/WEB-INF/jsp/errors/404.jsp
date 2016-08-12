<%-- 
    Document   : 404
    Created on : 07.12.2015, 13:36:09
    Author     : Dmytro Deinichenko
--%>

<%@page isErrorPage="true" contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="/WebApp/styles/css/bootstrap.min.css">
  <link rel="stylesheet" href="/WebApp/styles/css/404.css">
  <title>City Transport: Error 404</title>
  <link rel="shortcut icon" href="/WebApp/styles/pics/logo.png">
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="error-template">
                <h1>
                    Oops!</h1>
                <h2>
                    404 Page Not Found</h2>
                <div class="error-details">
                    Sorry, an error has occured, Requested page not found!
                    <br />
                    Request that failed: ${pageContext.errorData.requestURI}
                    <br />
                    Status code: ${pageContext.errorData.statusCode}
                </div>
                <div class="error-actions">
                    <a href="/WebApp/"><button type="button" class="btn btn-primary btn-lg"><span class="glyphicon glyphicon-home"></span>Take Me Home</button></a>
                    <a href="/WebApp/"><button type="button" class="btn btn-default btn-lg"><span class="glyphicon glyphicon-envelope"></span> Contact Support </button></a>
                </div>
            </div>
        </div>
    </div>
</div>  
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="/WebApp/styles/js/bootstrap.min.js"></script>
</body>
</html>