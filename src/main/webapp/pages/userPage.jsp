<%@ page language="java" pageEncoding="utf-8" session="true" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ru">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Meloman</title>

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

<div id="wrapper">

    <!-- Navigation -->
    <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
        <div class="navbar-header">
            <a class="navbar-brand" href="/meloman">Meloman</a>
        </div>
        <!-- /.navbar-header -->

        <ul class="nav navbar-top-links navbar-right">
            <!-- /.dropdown -->
            <li>
                <c:out value="${user.firstName}" default="Unknown" escapeXml="true" />
                user
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
                        <a href="/meloman"><i class="fa fa-info-circle" aria-hidden="true"></i> Info</a>
                    </li>
                </ul>
            </div>
            <!-- /.sidebar-collapse -->
        </div>
        <!-- /.navbar-static-side -->
    </nav>

    <!-- Page Content -->
    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Тестовое задание</h1>
            </div>
        </div>
        <!-- /.row -->
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        О задании
                    </div>
                    <div class="panel-body">
                        <div class="col-lg-6">
                            <h3>Тема JSP Database Access с применением паттерна DAO</h3>
                            <p>Реализовать <strong>DAO</strong> для каждой из сущностей, в которых должны находится
                                <strong>CRUD</strong>
                                операции (создать,
                                выбрать все сущности, выбор сущности по id, редактировать сущность, удалить
                                сущность).
                            </p>
                            <p>Добавить web с возможностью входа под этими ролями. </p>
                            <p>Существуют 4 сущности:</p>
                            <ul>
                                <li>User;</li>
                                <li>Address;</li>
                                <li>Role;</li>
                                <li>MusicType.</li>
                            </ul>
                            <p>Таблицы и связи между таблицами:</p>
                            <ul>
                                <li>Role : User(1:M);</li>
                                <li>User : Address(1:1);</li>
                                <li>User : MusicType (M:M).</li>
                            </ul>
                            <p>Таблицы <strong>Role</strong> и <strong>MusicType</strong> заполнены: (<strong>USER,
                                MANDATOR, ADMIN</strong>) и (<strong>RAP, ROCK …</strong>).</p>
                            <blockquote>
                                <p>Не использовать фреймворки Spring и Hibernate</p>
                            </blockquote>
                        </div>
                        <!-- /.col-lg-6 -->
                        <div class="col-lg-6">
                            <img class="img-responsive" alt="Диаграмма классов MySQL" src="/img/mloman_db.png">
                        </div>
                        <!-- /.col-lg-6 -->
                    </div>
                    <!-- /.panel-body -->
                    <div class="panel-footer">
                    </div>
                </div>
                <!-- /.panel .panel-primary -->
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        Обзор программы
                    </div>
                    <div class="panel-body">
                        <div class="embed-responsive embed-responsive-16by9">
                            <iframe class="embed-responsive-item" src="//www.youtube.com/embed/ePbKGoIGAXY"></iframe>
                        </div>
                    </div>
                    <!-- /.panel-body -->
                    <div class="panel-footer">
                    </div>
                </div>
                <!-- /.panel .panel-green -->
            </div>
            <!-- /.col-lg-12 -->
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
<!-- Custom Theme JavaScript -->
<script src="../dist/js/sb-admin-2.js"></script>

</body>
</html>
