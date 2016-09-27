<%@ page language="java" pageEncoding="utf-8" session="true" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ru">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Meloman Admin</title>

    <!-- Bootstrap Core CSS -->
    <link href="../vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- MetisMenu CSS -->
    <link href="../vendor/metisMenu/metisMenu.min.css" rel="stylesheet">
    <!-- DataTables CSS -->
    <link href="../vendor/datatables-plugins/dataTables.bootstrap.css" rel="stylesheet">
    <!-- DataTables Responsive CSS -->
    <link href="../vendor/datatables-responsive/dataTables.responsive.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="../dist/css/sb-admin-2.css" rel="stylesheet">
    <!-- Custom Fonts -->
    <link href="../vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body>

<div id="wrapper">

    <!-- Navigation -->
    <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
        <div class="navbar-header">
            <a class="navbar-brand" href="/admin-page">Meloman Admin</a>
        </div>
        <!-- /.navbar-header -->

        <ul class="nav navbar-top-links navbar-right">
            <li>
                admin
            </li>
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                    <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
                </a>
                <ul class="dropdown-menu dropdown-user">
                    <li><a href="/pages/login.jsp"><i class="fa fa-sign-out fa-fw"></i>Logout</a>
                    </li>
                </ul>
                <!-- /.dropdown-user -->
            </li>
            <!-- /.dropdown -->
        </ul>
        <!-- /.navbar-top-links -->

        <div class="navbar-default sidebar" role="navigation">
            <div class="sidebar-nav navbar-collapse">
                <ul class="nav" id="side-menu">
                    <li>
                        <a href="/admin-page"><i class="fa fa-info-circle" aria-hidden="true"></i> Info</a>
                    </li>
                    <li>
                        <a href="/admin-panel"><i class="fa fa-tachometer" aria-hidden="true"></i></i> Control panel</a>
                    </li>
                </ul>
            </div>
            <!-- /.sidebar-collapse -->
        </div>
        <!-- /.navbar-static-side -->
    </nav>

    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Edit</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <i class="fa fa-pencil-square-o"></i> Edit user
                    </div>
                    <div class="panel-body">
                        <form class="form-horizontal" action="/admin-edit" method="post" role="form">
                            <div class="row">
                                <div class="col-xs-6">
                                    <div class="panel-heading">
                                        <h4>Personal information</h4>
                                        <hr>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">Username</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" id="username" name="login"
                                                   placeholder="user100" value="${user.login}">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="password" class="col-sm-2 control-label">Password</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" id="password" name="password"
                                                   placeholder="12345" value="${user.password}">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="firstname" class="col-sm-2 control-label">First Name</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" id="Firstname" name="firstName"
                                                   placeholder="Andrew" value="${user.firstName}">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="lastname" class="col-sm-2 control-label">Last Name</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" id="lastname" name="lastName"
                                                   placeholder="Kovalski" value="${user.lastName}">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="age" class="col-sm-2 control-label">Age</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" id="age" name="age"
                                                   placeholder="25" value="${user.age}">
                                        </div>
                                    </div>
                                    <br>
                                    <div class="panel-heading">
                                        <h4>Access right</h4>
                                        <hr>
                                    </div>
                                    <div class="form-group">
                                        <label for="country" class="col-sm-2 control-label">User Type</label>
                                        <div class="col-sm-10">
                                            <div class="radio">
                                                <c:forEach var="roles" items="${roles}">
                                                    <c:choose>
                                                        <c:when test="${roleUser.id == roles.id}">
                                                            <label>
                                                                <input type="radio" name="userRole"
                                                                       value="${roles.id}" checked> ${roles.roleName}
                                                            </label>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <label>
                                                                <input type="radio" name="userRole"
                                                                       value="${roles.id}"> ${roles.roleName}
                                                            </label>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:forEach>
                                            </div>
                                        </div>
                                    </div>
                                    <br>
                                </div>
                                <div class="col-xs-6">
                                    <div class="panel-heading">
                                        <h4>Address</h4>
                                        <hr>
                                    </div>
                                    <div class="form-group">
                                        <label for="country" class="col-sm-2 control-label">Country</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" id="country" name="country"
                                                   placeholder="Ukraine" value="${user.getAddress().country}">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="street" class="col-sm-2 control-label">Street</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" id="street" name="street"
                                                   placeholder="63 Kolomenskaya Street"
                                                   value="${user.getAddress().street}">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="zipCode" class="col-sm-2 control-label">ZIP Code</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" id="zipCode" name="zipCode"
                                                   placeholder="123456" value="${user.getAddress().zipCode}">
                                        </div>
                                    </div>
                                    <br>
                                    <div class="panel-heading">
                                        <h4>Favorite music</h4>
                                        <hr>
                                    </div>
                                    <div class="form-group">
                                        <label for="street" class="col-sm-2 control-label">Choose</label>
                                        <div class="col-sm-10">
                                            <div class="checkbox">
                                                <c:forEach var="musicTypes" items="${musicTypes}">
                                                    <c:set var="count" value="0"/>
                                                    <c:forEach var="musicTypesUser" items="${musicTypesUser}">
                                                        <c:choose>
                                                            <c:when test="${musicTypesUser.id == musicTypes.id}">
                                                                <label>
                                                                    <input type="checkbox" name="listMusics"
                                                                           value="${musicTypes.musicTypeName}" checked>
                                                                        ${musicTypes.musicTypeName}
                                                                </label>
                                                                <c:set var="count" value="1"/>
                                                            </c:when>
                                                        </c:choose>
                                                    </c:forEach>
                                                    <c:if test="${count==0}">
                                                        <label>
                                                            <input type="checkbox" name="listMusics"
                                                                   value="${musicTypes.musicTypeName}">
                                                                ${musicTypes.musicTypeName}
                                                        </label>
                                                    </c:if>
                                                </c:forEach>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-offset-11 col-sm-1">
                                        <button type="submit" class="btn btn-primary" name="add">
                                            <i class="fa fa-floppy-o" aria-hidden="true"></i> Save
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="panel-footer">
                    </div>
                </div>
                <!-- /.col-lg-12 -->
            </div>
        </div>
        <!-- /.row -->
    </div>
    <!-- /#page-wrapper -->
</div>
<!-- /#wrapper -->

<!-- jQuery -->
<script src="../vendor/jquery/jquery.min.js"></script>
<!-- Bootstrap Core JavaScript -->
<script src="../vendor/bootstrap/js/bootstrap.min.js"></script>
<!-- Metis Menu Plugin JavaScript -->
<script src="../vendor/metisMenu/metisMenu.min.js"></script>
<!-- DataTables JavaScript -->
<script src="../vendor/datatables/js/jquery.dataTables.min.js"></script>
<script src="../vendor/datatables-plugins/dataTables.bootstrap.min.js"></script>
<script src="../vendor/datatables-responsive/dataTables.responsive.js"></script>
<!-- Custom Theme JavaScript -->
<script src="../dist/js/sb-admin-2.js"></script>

</body>
</html>