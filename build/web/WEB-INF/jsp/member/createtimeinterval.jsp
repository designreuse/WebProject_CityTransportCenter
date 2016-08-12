<%-- 
    Document   : createtimeinterval
    Created on : 19.12.2015, 16:30:34
    Author     : Dmytro Deinichenko
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="usertag" uri="/WEB-INF/tlds/usertag_library" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:choose>
    <c:when test="${not empty sessionScope.locale}">
        <fmt:setLocale value="${sessionScope.locale}" />
    </c:when>
    <c:otherwise>
        <fmt:setLocale value="en-EN" />
    </c:otherwise>
</c:choose>
<fmt:setBundle basename="i18n.messages" />

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>CityTransport Marshroutes</title>
        <link rel="shortcut icon" href="/WebApp/styles/pics/logo.png"> 

        <usertag:cssinc/>

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>
    <body>
        <div class="navbar navbar-default navbar-fixed-top" role="navigation">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#" style="font-size: 32px;"><fmt:message key="title.general"/> >> <fmt:message key="crint"/></a>
                </div>
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <li class="active"><a href="/WebApp/"><button type="button" class="btn btn  btn-primary"><fmt:message key="home"/></button></a></li>
                        <li><a>
                                <c:choose>
                                    <c:when test="${empty sessionScope.user}">
                                        <form name="loginForm" method="POST" action="controller">
                                            <input type="hidden" name="command" value="cLogin"/>
                                            <input type="submit" class="btn btn  btn-success" value="<fmt:message key="login"/>">
                                        </form>
                                    </c:when>
                                    <c:otherwise>
                                        <form name="logoutForm" method="POST" action="controller">
                                            <input type="hidden" name="command" value="cLogout"/>
                                            <input type="submit" class="btn btn  btn-warning" value="${sessionScope.user.userName} - Logout">
                                        </form>
                                    </c:otherwise>
                                </c:choose>
                            </a>
                        </li>
                    </ul>
                    <c:if test="${not empty messageResult}">
                        <div class="alert alert-success alert-dismissable col-md-5" style="position:absolute; left:400px; top:37px; z-index:0">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                            <strong><fmt:message key="opsucc"/>: </strong><c:out value="${messageResult}"/>
                        </div>
                    </c:if>

                    <c:if test="${not empty messageError}">
                        <div class="alert alert-danger alert-dismissable col-md-5" style="position:absolute; left:400px; top:37px; z-index:0">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                            <strong><fmt:message key="opfail"/>: </strong><c:out value="${messageError}"/>
                        </div>
                    </c:if>
                </div><!--/.nav-collapse -->
            </div>
        </div>

        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-3 col-md-2 sidebar">
                    <ul class="nav nav-sidebar" style="margin-top: 100px">
                        <li class="active"><a>
                                <form name="routesForm" method="POST" action="controller">
                                    <input type="hidden" name="command" value="cRoutes"/>
                                    <input type="submit" class="btn btn-default" value="<fmt:message key="routes"/>" style="width: 150px;">
                                </form>
                            </a></li>
                        <li><a>
                                <form name="vehiclesForm" method="POST" action="controller">
                                    <input type="hidden" name="command" value="cVehicles"/>
                                    <input type="submit" class="btn btn-default" value="<fmt:message key="transport"/>" style="width: 150px;">
                                </form>
                            </a></li>
                        <li><a>
                                <form name="timemapsForm" method="POST" action="controller">
                                    <input type="hidden" name="command" value="cTimeMaps"/>
                                    <input type="submit" class="btn btn-default" value="<fmt:message key="intervals"/>" style="width: 150px;">
                                </form>
                            </a></li>
                        <li><a>
                                <form name="exportForm" method="POST" action="controller">
                                    <input type="hidden" name="command" value=""/>
                                    <input type="submit" class="btn btn-default" value="<fmt:message key="export"/>" style="width: 150px;">
                                </form>
                            </a></li>
                    </ul>
                </div>

                <div class="col-sm-8 col-md-9" style="margin-top: 80px">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <div class="text-muted bootstrap-admin-box-title">
                                        <strong><fmt:message key="crnewint"/>:</strong>
                                    </div>

                                </div>
                                <div class="bootstrap-admin-panel-content" style="height: 400px;">
                                    <br>
                                    <br>
                                    <form class="form-horizontal" role="form" method="POST" action="controller">
                                        <input type="hidden" name="command" value="cCreateTimeInterval"/>
                                        <div class="form-group">
                                            <label for="direction" class="col-sm-2 control-label"><fmt:message key="direct"/>:</label>
                                            <div class="col-sm-9">
                                                <select class="form-control" name="direction">
                                                    <option><fmt:message key="straight"/></option>
                                                    <option><fmt:message key="back"/></option>
                                                    <option><fmt:message key="circle"/></option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="from" class="col-sm-2 control-label"><fmt:message key="from"/>:</label>
                                            <div class="col-sm-9">
                                                <input type="text" class="form-control" name="from" placeholder="" required>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="to" class="col-sm-2 control-label"><fmt:message key="to"/>:</label>
                                            <div class="col-sm-9">
                                                <input type="text" class="form-control" name="to" placeholder="" required>
                                            </div>
                                        </div>
                                        <div class="form-group" id="intervaltime">
                                            <label for="interval" class="col-sm-2 control-label"><fmt:message key="interval"/>:</label>
                                            <div class="col-sm-9">
                                                <input type="text" class="form-control" name="interval" required placeholder="00:00:00" pattern="\d{2}:[0-5]{1}[0-9]{1}:[0-5]{1}[0-9]{1}" title="Please enter time value in format HH:MM:SS" required>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="toroute" class="col-sm-2 control-label"><fmt:message key="asstoroute"/>:</label>
                                            <div class="col-sm-9">
                                                <select class="form-control" name="toroute">
                                                    <c:forEach var="route" items="${routeslist}">
                                                        <option><p><c:out value="#${route.idmarshroutes}"/>  ---  <c:out value="${route.routename}"/>  ---  <c:out value="${route.description}"/></p></option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-sm-offset-2 col-sm-7">
                                                <button type="submit" class="btn btn-success"><fmt:message key="crint"/></button>
                                            </div>
                                        </div>
                                    </form>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <footer class="footer">
            <div class="container">
                <p class="pull-right text-muted"><a href="#"><fmt:message key="btt"/></a></p>
                <p class="text-muted">&copy; 2016 MainCompany, Inc. &middot; <a href="#"><fmt:message key="priv"/></a> &middot; <a href="#"><fmt:message key="terms"/></a></p>
            </div>
        </footer>

        <usertag:scriptinc/>
        
    </body>
</html>

