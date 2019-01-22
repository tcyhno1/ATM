<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page pageEncoding="utf-8" contentType="text/html;charset=utf-8" language="java" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html;charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>于武聪国际银行</title>
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
</head>

<body data-type="generalComponents">



<!--header-->
<c:import url="common/header.jsp"/>






<div class="tpl-page-container tpl-page-header-fixed">



    <!--menu-->
    <c:import url="common/menu.jsp"/>




    <div class="tpl-content-wrapper">
        <div class="tpl-content-page-title">
            用户列表查询
        </div>
        <ol class="am-breadcrumb">
            <li><a href="/user/home.do" class="am-icon-home">首页</a></li>
            <li><a href="#">会员管理</a></li>
            <li class="am-active">用户列表</li>
        </ol>
        <div class="tpl-portlet-components">
            <div class="portlet-title">
                <div class="caption font-green bold">
                    <span class="am-icon-code"></span>

                    您好${student.sname}:&nbsp;&nbsp;
                    -- 共${requestScope.totalPages}页--
                </div>



            </div>
            <div class="tpl-block">

                <div class="am-g">
                    <div class="am-u-sm-12">
                        <form class="am-form">
                            <table class="am-table am-table-striped am-table-hover table-main">
                                <thead>
                                <tr>
                                    <th class="table-check"><input type="checkbox" class="tpl-table-fz-check"></th>
                                    <td>卡号</td>
                                    <td>姓名</td>
                                    <td>性别</td>
                                    <td align="center">出生日期</td>
                                    <td>金额</td>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="student" items="${studenstList}">
                                <tr>

                                    <td><input type="checkbox"></td>
                                    <td>${student.sno}</td>
                                    <td>${student.sname}</td>
                                    <td>${student.ssex}</td>
                                    <%--<td class="am-hide-sm-only">${stream.money}</td>--%>
                                    <td class="am-hide-sm-only"><fmt:formatDate value="${student.sbirthday}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                    </td>
                                    <td>${student.balance}</td>

                                </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            <div class="am-cf">

                                <div class="am-fr">
                                    <ul class="am-pagination tpl-pagination">

                                        <!--分页按钮-->
                                        <c:if test="${pageIndex le 1}">
                                            <li class="am-disabled"><a href="#">«</a></li>
                                        </c:if>
                                        <c:if test="${pageIndex gt 1}">
                                            <li><a href="/user/students.do?pageIndex=${pageIndex-1}">«</a></li>
                                        </c:if>

                                        <c:forEach var="nav" items="${navigatepageNums}">
                                            <c:if test="${pageIndex eq nav}">
                                                <li class="am-active"><a href="#">${nav}</a></li>
                                            </c:if>
                                            <c:if test="${pageIndex ne nav}">
                                                <li><a href="/user/students.do?pageIndex=${nav}">${nav}</a></li>
                                            </c:if>
                                        </c:forEach>

                                        <c:if test="${pageIndex ge totalPages}">
                                            <li class="am-disabled"><a href="#">»</a></li>
                                        </c:if>
                                        <c:if test="${pageIndex lt totalPages}">
                                            <li><a href="/user/students.do?pageIndex=${pageIndex+1}">»</a></li>
                                        </c:if>
                                    </ul>
                                </div>
                            </div>
                            <hr>

                        </form>
                    </div>

                </div>
            </div>
            <div class="tpl-alert"></div>
        </div>










    </div>

</div>


<script src="/assets/js/jquery.min.js"></script>
<script src="/assets/js/amazeui.min.js"></script>
<script src="/assets/js/app.js"></script>



</body>

</html>