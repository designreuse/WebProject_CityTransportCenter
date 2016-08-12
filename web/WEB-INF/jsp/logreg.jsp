<%-- 
    Document   : logreg
    Created on : 21.11.2015, 20:44:48
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
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title><fmt:message key="title.login"/></title>
    <link rel="shortcut icon" href="/WebApp/styles/pics/logo.png">

    <usertag:cssinc/>
    <link href="./styles/css/styles.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
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
                    <a class="navbar-brand" href="#" style="font-size: 32px;"><fmt:message key="title.general"/> >> <fmt:message key="logreg"/></a>
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
                                        <input type="hidden" name="backcommand" value="cLogin"/>
                                        <input type="hidden" name="locale" value="uk_UA"/>
                                        <input type="submit" class="btn btn-default btn-xs" value="<fmt:message key="ua"/>">
                                    </form>
                                </a></li>
                            <li><a><img src="./styles/pics/flags/United-Kingdom.png" height="20" width="20">
                                    <form name="setLangForm" method="POST" action="controller" style="display: inline-block">
                                        <input type="hidden" name="command" value="cSetLang"/>
                                        <input type="hidden" name="backcommand" value="cLogin"/>
                                        <input type="hidden" name="locale" value="en_EN"/>
                                        <input type="submit" class="btn btn-default btn-xs" value="<fmt:message key="eng"/>">
                                    </form>
                                </a></li>
                        </ul>
                    </div>
                    <ul class="nav navbar-nav navbar-right">
                        <li class="active"><a href="/WebApp/"><button type="button" class="btn btn  btn-primary"><fmt:message key="home"/></button></a></li>
                        <li><a>
                                <form name="loginForm" method="POST" action="controller">
                                    <input type="hidden" name="command" value="cLogin"/>
                                    <input type="submit" class="btn btn  btn-success" value="<fmt:message key="login"/>">
                                </form>
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

    <div class="container">
      <div class="row">
      <div class="col-md-6 col-md-offset-3">
        <div class="panel panel-login">
          <div class="panel-heading">
            <div class="row">
              <div class="col-xs-6">
                <a href="#" class="active" id="login-form-link"><fmt:message key="log_in"/></a>
              </div>
              <div class="col-xs-6">
                <a href="#" id="register-form-link"><fmt:message key="reg"/></a>
              </div>
            </div>
            <hr>
          </div>
          <div class="panel-body">
            <div class="row">
              <div class="col-lg-12">
                <form id="login-form" action="controller" method="post" role="form" style="display: block;">
                  <input type="hidden" name="command" value="cCheckLogin"/>
                  <div class="form-group">
                    <input type="text" name="username" id="username" tabindex="1" class="form-control" placeholder="<fmt:message key="username"/>" value="">
                  </div>
                  <div class="form-group">
                    <input type="password" name="password" id="password" tabindex="2" class="form-control" placeholder="<fmt:message key="password"/>">
                  </div>
                  <div class="form-group text-center">
                    <input type="checkbox" tabindex="3" class="" name="remember" id="remember">
                    <label for="remember"><fmt:message key="remember"/></label>
                  </div>
                  <div class="form-group">
                    <div class="row">
                      <div class="col-sm-6 col-sm-offset-3">
                        <input type="submit" name="login-submit" id="login-submit" tabindex="4" class="form-control btn btn-login" value="<fmt:message key="log_in"/>">
                      </div>
                    </div>
                  </div>
                </form>
                <form id="register-form" action="controller" method="post" role="form" style="display: none;">
                  <input type="hidden" name="command" value="cCreateNewUser"/>
                  <div class="form-group">
                    <input type="text" name="username" id="username" tabindex="1" class="form-control" placeholder="<fmt:message key="username"/>" value="">
                  </div>
                  <div class="form-group">
                    <input type="email" name="email" id="email" tabindex="1" class="form-control" placeholder="<fmt:message key="email"/>" value="">
                  </div>
                  <div class="form-group">
                    <input type="password" name="password" id="password" tabindex="2" class="form-control" placeholder="<fmt:message key="password"/>">
                  </div>
                  <div class="form-group">
                    <input type="password" name="confirm-password" id="confirm-password" tabindex="2" class="form-control" placeholder="<fmt:message key="cpassword"/>">
                  </div>
                  <div class="form-group">
                    <div class="row">
                      <div class="col-sm-6 col-sm-offset-3">
                        <input type="submit" name="register-submit" id="register-submit" tabindex="4" class="form-control btn btn-register" value="<fmt:message key="regnow"/>">
                      </div>
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
    <script src="./styles/js/script.js"></script>

  </body>
</html>