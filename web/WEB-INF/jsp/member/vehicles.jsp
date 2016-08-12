<%-- 
    Document   : vehicles
    Created on : 13.12.2015, 17:34:25
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
        <title>CityTransport HomePage &raquo Vehicles</title>
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
                    <a class="navbar-brand" href="#" style="font-size: 32px;"><fmt:message key="title.general"/> >> <fmt:message key="transport"/></a>
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
                                        <input type="hidden" name="backcommand" value="cVehicles"/>
                                        <input type="hidden" name="locale" value="uk_UA"/>
                                        <input type="submit" class="btn btn-default btn-xs" value="<fmt:message key="ua"/>">
                                    </form>
                                </a></li>
                            <li><a><img src="./styles/pics/flags/United-Kingdom.png" height="20" width="20">
                                    <form name="setLangForm" method="POST" action="controller" style="display: inline-block">
                                        <input type="hidden" name="command" value="cSetLang"/>
                                        <input type="hidden" name="backcommand" value="cVehicles"/>
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
                                        <input type="hidden" name="command" value="cExportVehicles"/>
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
                                        <table class="table bootstrap-admin-table-with-actions">
                                            <thead>
                                                <tr>
                                                    <th style="width:6%;"><fmt:message key="id"/></th>
                                                    <th style="width:10%;"><fmt:message key="mark"/></th>
                                                    <th style="width:10%;"><fmt:message key="model"/></th>
                                                    <th style="width:10%;"><fmt:message key="type"/></th>
                                                    <th style="width:22%;"><fmt:message key="assto"/><br><fmt:message key="route"/></th>
                                                    <th style="width:15%;"><fmt:message key="status"/></th>
                                                    <th><fmt:message key="act"/></th>
                                                    <th>
                                                        <button class="btn btn-sm btn-success" align="right" data-toggle="modal" data-target="#newVehicle">
                                                            <i class="glyphicon glyphicon-ok-sign"></i><fmt:message key="newVeh"/>
                                                        </button>
                                                    </th>
                                                </tr>
                                            </thead>
                                        </table>
                                    </div>

                                </div>
                                <div class="bootstrap-admin-panel-content" style="height: 390px; overflow-y: scroll;">
                                    <table class="table table-hover bootstrap-admin-table-with-actions">
                                        <thead>
                                            <tr>
                                                <th style="width:8%;"></th>
                                                <th style="width:10%;"></th>
                                                <th style="width:10%;"></th>
                                                <th style="width:9%;"></th>
                                                <th style="width:22%;"></th>
                                                <th style="width:9%;"></th>
                                                <th></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="vehicle" items="${vehicleslist}">
                                                <tr>
                                                    <td align="center"><c:out value="${vehicle.vid}"/></td>
                                                    <td align="left"><c:out value="${vehicle.vmark}"/></td>
                                                    <td align="left"><c:out value="${vehicle.vmodel}"/></td>
                                                    <td align="left"><c:out value="${vehicle.vehiclestypesname}"/></td>
                                                    <td align="left"><c:out value="${vehicle.assignedtoname}"/></td> 
                                                    <td align="left"><c:out value="${vehicle.status}"/></td>
                                                    <td class="actions" align="right">
                                                        <div class="form-group">
                                                        <form class="form-horizontal" name="modvehicleForm" method="POST" action="controller" style="display: inline-block">
                                                            <input type="hidden" name="command" value="cModifyVehicleForm"/>
                                                            <input type="hidden" name="idvehicle" value="${vehicle.vid}"/>
                                                            <input type="hidden" name="markvehicle" value="${vehicle.vmark}"/>
                                                            <input type="hidden" name="modelvehicle" value="${vehicle.vmodel}"/>
                                                            <input type="hidden" name="typevehicle" value="${vehicle.vehiclestypesname}"/>
                                                            <input type="hidden" name="statusvehicle" value="${vehicle.status}"/>
                                                            <button class="btn btn-sm btn-primary" type="submit"><i class="glyphicon glyphicon-pencil"></i><fmt:message key="edit"/></button>
                                                        </form>

                                                        <form class="form-horizontal" name="assvehicleForm" method="POST" action="controller" style="display: inline-block">
                                                            <input type="hidden" name="command" value="cAssignVehicleForm"/>
                                                            <input type="hidden" name="idvehicle" value="${vehicle.vid}"/>
                                                            <input type="hidden" name="markvehicle" value="${vehicle.vmark}"/>
                                                            <input type="hidden" name="modelvehicle" value="${vehicle.vmodel}"/>
                                                            <input type="hidden" name="typevehicle" value="${vehicle.vehiclestypesname}"/>
                                                            <input type="hidden" name="statusvehicle" value="${vehicle.status}"/>
                                                            <button class="btn btn-sm btn-warning" type="submit"><i class="glyphicon glyphicon-link"></i><fmt:message key="assign"/></button>
                                                        </form>    
                                                            
                                                            
                                                        <form class="form-horizontal" name="delvehicleForm" method="POST" action="controller" style="display: inline-block">
                                                            <input type="hidden" name="command" value="cDeleteVehicle"/>
                                                            <input type="hidden" name="iddelvehicle" value="${vehicle.vid}"/>
                                                            <button class="btn btn-sm btn-danger" type="submit"><i class="glyphicon glyphicon-trash"></i><fmt:message key="del"/></button>
                                                        </form>
                                                        </div>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Modal -->
        <div class="modal fade" id="newVehicle" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">
                            <span aria-hidden="true" class="glyphicon glyphicon-remove"></span>
                            <span class="sr-only">Close</span>
                        </button>
                        <h4 class="modal-title" id="myModalLabel"><fmt:message key="crnewveh"/></h4>
                    </div>
                    <form class="form-horizontal" action="controller" method="post">
                        <div class="modal-body">
                            <input type="hidden" name="command" value="cCreateVehicle"/>
                            <label class="col-lg-2 control-label" for="mark"><fmt:message key="mark"/></label>
                            <div class="input-group col-lg-9">
                                <input type="text" name="mark" id="mark" class="form-control" value="">
                            </div>
                            <br>
                            <label class="col-lg-2 control-label" for="model"><fmt:message key="model"/></label>
                            <div class="input-group col-lg-9">
                                <input type="text" name="model" id="model" class="form-control" value="">                                
                            </div>
                            <br>
                            <label class="col-lg-2 control-label" for="status"><fmt:message key="status"/></label>
                            <div class="input-group col-lg-9">
                                <input type="text" name="status" id="status" class="form-control" value="">
                            </div>
                            <br>
                            <label class="col-lg-3 control-label" for="vehicletype"><fmt:message key="vehtype"/></label>
                            <div class="input-group col-lg-3">
                                <select class="form-control" name="vehicletype">
                                    <option><fmt:message key="bus"/></option>
                                    <option><fmt:message key="troll"/></option>
                                    <option><fmt:message key="tram"/></option>
                                </select>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <input type="submit" name="createvehicle" id="createroute" tabindex="1" class="btn btn-success" value="<fmt:message key="crnewveh"/>">
                            <button type="button" class="btn btn-default" data-dismiss="modal"><fmt:message key="close"/></button>
                        </div>
                    </form>
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
