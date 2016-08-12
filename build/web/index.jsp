<%-- 
    Document   : index
    Created on : 14.11.2015, 21:56:58
    Author     : Dmytro Deinichenko
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="usertag" uri="/WEB-INF/tlds/usertag_library" %>
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
    
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title><fmt:message key="title.general"/></title>
        <link rel="shortcut icon" href="/WebApp/styles/pics/logo.png"> 

        <usertag:cssinc/>

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
    
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
                    <a class="navbar-brand" href="/WebApp/" style="font-size: 32px;"><fmt:message key="title.general"/></a>
                </div>

                <div class="collapse navbar-collapse">
                    <div class="btn-group" style="position:absolute; left: 850px; top: 15px;">
                        <button class="btn btn-default btn-sm dropdown-toggle" type="button" data-toggle="dropdown" style="position:absolute; top:20; width:85px;">
                            <fmt:message key="lang"/> <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu">
                            <li><a><img src="./styles/pics/flags/Ukraine.png" height="20" width="20">
                                    <form name="setLangForm" method="POST" action="controller" style="display: inline-block">
                                        <input type="hidden" name="command" value="cSetLang"/>
                                        <input type="hidden" name="backcommand" value=""/>
                                        <input type="hidden" name="locale" value="uk_UA"/>
                                        <input type="submit" class="btn btn-default btn-xs" value="<fmt:message key="ua"/>">
                                    </form>
                                </a></li>
                            <li><a><img src="./styles/pics/flags/United-Kingdom.png" height="20" width="20">
                                    <form name="setLangForm" method="POST" action="controller" style="display: inline-block">
                                        <input type="hidden" name="command" value="cSetLang"/>
                                        <input type="hidden" name="backcommand" value=""/>
                                        <input type="hidden" name="locale" value="en_EN"/>
                                        <input type="submit" class="btn btn-default btn-xs" value="<fmt:message key="eng"/>">
                                    </form>
                                </a></li>
                        </ul>
                    </div>
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
                                            <input type="submit" class="btn btn  btn-warning" value="${sessionScope.user.userName} - <fmt:message key="logout"/>">
                                        </form>
                                    </c:otherwise>
                                </c:choose>
                            </a>
                        </li>
                            
                    </ul>
                </div><!--/.nav-collapse -->
            </div>
        </div>

        <div class="bs-example" style="padding-top: 88px; margin-left: 30px; margin-right: 30px;">
            <div class="jumbotron" style="background: url(/WebApp/styles/pics/small/title2_tn.jpg)">
                <h1 style="color: #ffffff; margin-left: 30px;"><fmt:message key="title.carousel"/></h1>
                <p>...</p>
                <p><a class="btn btn-primary btn-lg" style="padding-top: 10px; margin-left: 40px;"data-toggle="modal" data-target="#about"><fmt:message key="about"/></a></p>
            </div>
        </div>

        <div class="row">
            <div class="col-lg-3 col-md-3" style="padding-top: 10px; margin-left: 100px;">
                <img class="img-circle img-responsive" src="./styles/pics/small/routes.jpg" alt="Generic placeholder image">
                <h2><fmt:message key="routes"/></h2>
                <p><fmt:message key="descr.routes"/></p>
                <p><a> 
                        <form name="routesForm" method="POST" action="controller">
                            <input type="hidden" name="command" value="cRoutes"/>
                            <input type="submit" class="btn btn-default" value="<fmt:message key="go"/> &raquo;">
                        </form>
                    </a></p>
            </div><!-- /.col-lg-4 -->
            <div class="col-lg-3 col-md-3" style="padding-top: 10px; margin-left: 80px;">
                <img class="img-circle img-responsive" src="./styles/pics/small/vehicles.jpg" alt="Generic placeholder image">
                <h2><fmt:message key="transport"/></h2>
                <p><fmt:message key="descr.vehicles"/></p>
                <p><a> 
                        <form name="vehiclesForm" method="POST" action="controller">
                            <input type="hidden" name="command" value="cVehicles"/>
                            <input type="submit" class="btn btn-default" value="<fmt:message key="go"/> &raquo;">
                        </form>
                    </a></p>
            </div><!-- /.col-lg-4 -->
            <div class="col-lg-3 col-md-3" style="padding-top: 10px; margin-left: 80px;">
                <img class="img-circle img-responsive" src="./styles/pics/small/commandcenter.jpg" alt="Generic placeholder image">
                <h2><fmt:message key="intervals"/></h2>
                <p><fmt:message key="descr.intervals"/></p>
                <p><a> 
                        <form name="TimeMapsForm" method="POST" action="controller">
                            <input type="hidden" name="command" value="cTimeMaps"/>
                            <input type="submit" class="btn btn-default" value="<fmt:message key="go"/> &raquo;">
                        </form>
                    </a></p>
            </div><!-- /.col-lg-4 -->
        </div><!-- /.row -->

        <!-- Modal -->
        <div class="modal fade" id="about" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">
                            <span aria-hidden="true" class="glyphicon glyphicon-remove"></span>
                            <span class="sr-only"><fmt:message key="close"/></span>
                        </button>
                        <h3 class="modal-title" id="myModalLabel"><fmt:message key="general.info"/></h3>
                    </div>
                    <div class="modal-body">
                        <strong><h4><fmt:message key="aboutmessage"/></h4></strong>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal"><fmt:message key="close"/></button>
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
