<%@ page import="com.yuhao.entity.Student" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="utf-8" contentType="text/html;charset=utf-8" language="java" %>

<html>



<head>
    <meta http-equiv="content-type" content="text/html;charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>大猿国际银行</title>
    <meta name="description" content="这是一个 index 页面">
    <meta name="keywords" content="index">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link rel="icon" type="image/png" href="/assets/i/favicon.png">
    <link rel="apple-touch-icon-precomposed" href="/assets/i/app-icon72x72@2x.png">
    <meta name="apple-mobile-web-app-title" content="Amaze UI" />
    <link rel="stylesheet" href="/assets/css/amazeui.min.css" />
    <link rel="stylesheet" href="/assets/css/admin.css">
    <link rel="stylesheet" href="/assets/css/app.css">
    <script src="/js/jquery-3.3.1.js"></script>
    <script src="/js/draw.js"></script>
</head>

<body data-type="generalComponents">




<!--header-->
<c:import url="common/header.jsp"/>


<div class="tpl-page-container tpl-page-header-fixed">

    <!--menu-->
    <c:import url="common/menu.jsp"/>




    <div class="tpl-content-wrapper">
        <div class="tpl-content-page-title">
            操作成功
        </div>
        <ol class="am-breadcrumb">
            <li><a href="/user/home.do" class="am-icon-home">首页</a></li>
            <li><a href="#">会员业务</a></li>
            <li class="am-active">成功</li>
        </ol>
        <div class="tpl-portlet-components">
            <div class="portlet-title">
                <div class="caption font-green bold">
                    <span class="am-icon-code"></span> 您好${student.sname}:
                </div>



            </div>
            <div class="tpl-block ">

                <div class="am-g tpl-amazeui-form">


                    <div class="am-u-sm-12 am-u-md-9">
                        <%=request.getAttribute("msg") %>
                    </div>
                </div>
            </div>

        </div>










    </div>

</div>


<script src="/assets/js/jquery.min.js"></script>
<script src="/assets/js/amazeui.min.js"></script>
<script src="/assets/js/app.js"></script>
</body>

</html>