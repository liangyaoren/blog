<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="/common/head.jsp" %>
<style type="text/css">
    p img {
        max-width: 100%;
        max-height: 100%;
    }
</style>
<div class="col-md-8" style="margin-top: 25px;">
    <div class="row">
        <div class="col-md-12">
            <p style="text-align: center">${obj.data.blog.title}</p>
        </div>
    </div>

    <div class="row">
        <div class="col-md-12">
            <p style="text-align: center">
                类别:${obj.data.blog.typeName} &nbsp;阅读:${obj.data.blog.clicks}&nbsp;
                <fmt:formatDate value="${obj.data.blog.createTime}" var="createTime" pattern="yyyy-MM-dd HH:mm:ss" />
                发布时间:${createTime}
            </p>
        </div>
    </div>

    <div class="row">
        <div class="col-md-12">
            <p>${obj.data.blog.content}</p>
        </div>
    </div>

    <div class="row">
        <div class="col-md-12">
            <p>关键字:${obj.data.blog.keyWord}</p>
        </div>
    </div>

    <div class="row">
        <div class="col-md-12">
            <c:if test="${!empty obj.data.lastBlog}">
                <p>上一篇：<a href="${base}/blog/${obj.data.lastBlog.id}">${obj.data.lastBlog.title}</a></p>
            </c:if>
            <c:if test="${!empty obj.data.nextBlog}">
                <p>下一篇：<a href="${base}/blog/${obj.data.nextBlog.id}">${obj.data.nextBlog.title}</a></p>
            </c:if>
        </div>
    </div>

    <div id="SOHUCS" sid="${obj.data.blog.id}"></div>
    <script charset="utf-8" type="text/javascript" src="http://changyan.sohu.com/upload/changyan.js" ></script>
    <script type="text/javascript">
        window.changyan.api.config({
            appid: 'cysLtqfjS',
            conf: 'prod_b8d4997c289fc3ab019766dc015d2a83'
        });
    </script>
</div>
<%@include file="/common/right.jsp" %>
<%@include file="/common/foot.jsp" %>
