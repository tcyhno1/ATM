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
    <link rel="icon" type="image/png" href="assets/i/favicon.png">
    <link rel="apple-touch-icon-precomposed" href="assets/i/app-icon72x72@2x.png">
    <meta name="apple-mobile-web-app-title" content="Amaze UI" />
    <link rel="stylesheet" href="assets/css/amazeui.min.css" />
    <link rel="stylesheet" href="assets/css/admin.css">
    <link rel="stylesheet" href="assets/css/app.css">

    <script src="/js/jquery-3.3.1.js"></script>
    <script src="/js/login.js"></script>
</head>

<body data-type="login">

<div class="am-g myapp-login">
    <div class="myapp-login-logo-block  tpl-login-max">
        <div class="myapp-login-logo-text">
            <div class="myapp-login-logo-text">
                YuWucong<span> ATM</span> <i class="am-icon-skyatlas"></i>

            </div>
        </div>

        <div class="login-font">
            <i>Log In </i> or <span> Sign Up</span>
        </div>
        <div class="login-font">
            <i> </i>
        </div>
        <div class="am-u-sm-10 login-am-center">
            <form  action="/user/login.do" method="post" class="am-form" onsubmit="return validate()">
                <fieldset>
                    <div class="am-form-group">
                        <input id="loginName" name="loginName"  type="text" class="" id="doc-ipt-email-1" placeholder="请输入卡号108">
                    </div>
                    <div class="am-form-group">
                        <input id="password" name="password"   type="text" class="" id="doc-ipt-pwd-1" placeholder="请输入密码1234">
                    </div>
                    <p><button type="submit" class="am-btn am-btn-default">登录</button></p>
                    <p><button type="reset" class="am-btn am-btn-default">重置</button></p>
                    <p><button type="button" class="am-btn am-btn-default"><a href="http://182.61.59.59:8081/resume/resume.html" target="_blank">
                        我的简历
                    </a></button></p>
                </fieldset>
            </form>
        </div>
    </div>

</div>




<script src="assets/js/jquery.min.js"></script>
<script src="assets/js/amazeui.min.js"></script>
<script src="assets/js/app.js"></script>
</body>

</html>