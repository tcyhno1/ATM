<%@ page pageEncoding="utf-8" contentType="text/html;charset=utf-8" language="java" %>

<div class="tpl-left-nav tpl-left-nav-hover">
    <div class="tpl-left-nav-title">
        功能列表
    </div>
    <div class="tpl-left-nav-list">
        <ul class="tpl-left-nav-menu">
            <li class="tpl-left-nav-item">
                <a href="/user/home.do" class="nav-link active">
                    <i class="am-icon-home"></i>
                    <span>首页</span>
                </a>
            </li>
            <li class="tpl-left-nav-item">
                <a href="/balance/streams.do" class="nav-link tpl-left-nav-link-list">
                    <i class="am-icon-bar-chart"></i>
                    <span>用户流水</span>
                </a>
            </li>

            <li class="tpl-left-nav-item">
                <a href="/balance/toRecharge.do" class="nav-link tpl-left-nav-link-list">
                    <i class="am-icon-bar-chart"></i>
                    <span>用户充值</span>
                </a>
            </li>

            <li class="tpl-left-nav-item">
                <a href="javascript:;" class="nav-link tpl-left-nav-link-list">
                    <i class="am-icon-table"></i>
                    <span>会员业务</span>
                    <i class="am-icon-angle-right tpl-left-nav-more-ico am-fr am-margin-right"></i>
                </a>
                <ul class="tpl-left-nav-sub-menu">
                    <li>
                        <a href="/balance/query.do">
                            <i class="am-icon-angle-right"></i>
                            <span>余额</span>
                            <%--<i class="am-icon-star tpl-left-nav-content-ico am-fr am-margin-right"></i>--%>
                        </a>

                        <a href="/balance/toDraw.do">
                            <i class="am-icon-angle-right"></i>
                            <span>取款</span>
                            <%--<i class="tpl-left-nav-content tpl-badge-success">--%>
                            <%--18--%>
                            <%--</i>--%>

                            <a href="/balance/toTrans.do">
                                <i class="am-icon-angle-right"></i>
                                <span>转账</span>
                                <%--<i class="tpl-left-nav-content tpl-badge-primary">--%>
                                <%--5--%>
                                <%--</i>--%>


                                <a href="/balance/toSave.do">
                                    <i class="am-icon-angle-right"></i>
                                    <span>存款</span>

                                </a>
                    </li>
                </ul>
            </li>

            <li class="tpl-left-nav-item">
                <a href="javascript:;" class="nav-link tpl-left-nav-link-list">
                    <i class="am-icon-wpforms"></i>
                    <span>会员管理</span>
                    <i class="am-icon-angle-right tpl-left-nav-more-ico am-fr am-margin-right tpl-left-nav-more-ico-rotate"></i>
                </a>
                <ul class="tpl-left-nav-sub-menu" style="display: block;">
                    <li>
                        <a href="/user/students.do">
                            <i class="am-icon-angle-right"></i>
                            <span>会员列表</span>
                            <i class="am-icon-star tpl-left-nav-content-ico am-fr am-margin-right"></i>
                        </a>
                    </li>
                </ul>
            </li>

            <li class="tpl-left-nav-item">
                <a href="/user/loginOut.do" class="nav-link tpl-left-nav-link-list">
                    <i class="am-icon-key"></i>
                    <span>登出</span>

                </a>
            </li>
        </ul>
    </div>
</div>