<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
</head>

<body data-type="generalComponents">



<!--header-->
<c:import url="common/header.jsp"/>






<div class="tpl-page-container tpl-page-header-fixed">



    <!--menu-->
    <c:import url="common/menu.jsp"/>




    <div class="tpl-content-wrapper">
        <div class="tpl-content-page-title">
            流水表查询
        </div>
        <ol class="am-breadcrumb">
            <li><a href="/user/home.do" class="am-icon-home">首页</a></li>
            <%--<li><a href="#">Amaze UI CSS</a></li>--%>
            <li class="am-active">流水表</li>
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



                    <form id="form1" method="post">
                        日期
                        <input id="sTime" name="sTime" type="text" autocomplete="off" placeholder="请选择日期" value="${sTime}">
                        至
                        <input id="eTime" name="eTime" type="text" autocomplete="off" placeholder="请选择日期" value="${eTime}">
                        <input type="button" value="搜索" onclick="submitForm('/balance/streams.do')">
                    </form>
                </div>
                <div class="am-g">
                    <div class="am-u-sm-12">
                        <form class="am-form">
                            <table class="am-table am-table-striped am-table-hover table-main">
                                <thead>
                                <tr>
                                    <th class="table-check"><input type="checkbox" class="tpl-table-fz-check"></th>
                                    <th class="table-id">ID</th>
                                    <th class="table-id">卡号</th>
                                    <th class="table-type">类型</th>
                                    <th class="table-title">金额</th>
                                    <th class="table-date am-hide-sm-only">时间</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="stream" items="${streamList}" varStatus="i">
                                <tr>

                                    <td><input type="checkbox"></td>
                                    <td>${i.index+1}</td>
                                    <td>${stream.userSno}
                                    </td>
                                    <c:choose>
                                        <c:when test="${stream.type==1}"><td>取款</td></c:when>
                                        <c:when test="${stream.type==2}"><td>存款</td></c:when>
                                        <c:when test="${stream.type==3}"><td>转出</td></c:when>
                                        <c:otherwise><td>转入</td></c:otherwise>
                                    </c:choose>
                                    <td class="am-hide-sm-only">${stream.money}
                                    </td>
                                    <td class="am-hide-sm-only"><fmt:formatDate value="${stream.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                    </td>
                                </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            <div class="am-cf">

                                <div class="am-fr">
                                    <ul class="am-pagination tpl-pagination">

                                        <c:if test="${pageIndex le 1}">
                                            <li class="am-disabled"><a href="#">«</a></li>
                                        </c:if>
                                        <c:if test="${pageIndex gt 1}">
                                            <li><a href="javascript:void(0)" onclick="submitForm('/balance/streams.do?pageIndex=${pageIndex-1}')">«</a></li>
                                        </c:if>

                                        <c:forEach var="nav" items="${navigatepageNums}">
                                            <c:if test="${pageIndex eq nav}">
                                                <li class="am-active"><a href="#">${nav}</a></li>
                                            </c:if>
                                            <c:if test="${pageIndex ne nav}">
                                            <li><a href="javascript:void(0)" onclick="submitForm('/balance/streams.do?pageIndex=${nav}')">${nav}</a></li>
                                            </c:if>

                                        </c:forEach>

                                        <c:if test="${pageIndex ge totalPages}">
                                            <li class="am-disabled"><a href="#">»</a></li>
                                        </c:if>
                                        <c:if test="${pageIndex lt totalPages}">
                                            <li><a href="javascript:void(0)" onclick="submitForm('/balance/streams.do?pageIndex=${pageIndex+1}')">»</a></li>
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




<!--日期控件-->
<script type="text/javascript" src="/js/laydate/laydate.js"></script>
<script type="text/javascript" src="/js/vue.js"></script>

<script type="text/javascript">
    //执行一个laydate实例
    laydate.render({
        elem: '#eTime' //指定元素
    });
    laydate.render({
        elem: '#sTime' //指定元素
    });

    function submitForm(url) {
        if(!url){
            url = window.location.href;
        }
        form1.action = url;
        form1.submit();
    }

    var app =new Vue({
        el: '#streams_table',
        data:{
            streamList:[],
            message:'hi vue'
        }
    })


    $.ajax({
        type: 'post', //默认get
        url: "/balance/listStreams_ajax.do",
        async: true,   //是否异步（默认true：异步）
        dataType: "json",//定义服务器返回的数据类型
        success: function (data) {//data服务器返回的json字符串
            if (data.success) {
                // console.log(data.data.pageInfo.list)
                var streamList = data.data.pageInfo.list;
                app.streamList = streamList;
            } else {
                alert(data.errorMsg)
            }

        }
    });


</script>
</body>

</html>