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

<div class="container">
    <div class="panel-heading"></div>
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <i class="fa fa-user-plus"></i> New user registration
                </div>
                <div class="panel-body">
                    <form class="form-horizontal" action="/singUp" method="post" role="form">
                        <fieldset>
                            <div class="col-xs-6">
                                <div class="panel-heading">
                                    <h4>Personal information</h4>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-12">
                                        <input type="text" class="form-control" id="username" name="login"
                                               placeholder="Username">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-12">
                                        <input type="text" class="form-control" id="password" name="password"
                                               placeholder="Password">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-12">
                                        <input type="text" class="form-control" id="Firstname" name="firstName"
                                               placeholder="First Name">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-12">
                                        <input type="text" class="form-control" id="lastname" name="lastName"
                                               placeholder="Last Name">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-12">
                                        <input type="text" class="form-control" id="age" name="age"
                                               placeholder="Age">
                                    </div>
                                </div>
                                <br>
                            </div>
                            <div class="col-xs-6">
                                <div class="panel-heading">
                                    <h4>Address</h4>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-12">
                                        <input type="text" class="form-control" id="country" name="country"
                                               placeholder="Country">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-12">
                                        <input type="text" class="form-control" id="street" name="street"
                                               placeholder="Street">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-12">
                                        <input type="text" class="form-control" id="zipCode" name="zipCode"
                                               placeholder="ZIP Code 123456">
                                    </div>
                                </div>

                                <div class="panel-heading">
                                    <h4>Favorite music</h4>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-10">
                                        <div class="checkbox">
                                            <c:forEach var="musicTypes" items="${musicTypes}">
                                                <label>
                                                    <input type="checkbox" name="listMusics"
                                                           value="${musicTypes.musicTypeName}">
                                                        ${musicTypes.musicTypeName}
                                                </label>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div
                            </div>
                            <div class="col-xs-12  col-sm-offset-7">
                                <div class="form-group">
                                    <button type="submit" class="btn btn-success" name="add">
                                        <i class="fa fa-floppy-o" aria-hidden="true"></i> Sign Up
                                    </button>
                                </div>
                            </div>
                        </fieldset>
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
<!-- jQuery -->
<script src="../vendor/jquery/jquery.min.js"></script>
<!-- Bootstrap Core JavaScript -->
<script src="../vendor/bootstrap/js/bootstrap.min.js"></script>
<!-- Metis Menu Plugin JavaScript -->
<script src="../vendor/metisMenu/metisMenu.min.js"></script>
<!-- Custom Theme JavaScript -->
<script src="../dist/js/sb-admin-2.js"></script>

</body>
</html>
