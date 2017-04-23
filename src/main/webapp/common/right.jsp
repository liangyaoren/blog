<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="col-md-3 col-md-offset-1">

    <div class="blog_classify">
        <div class="row">
            <div class="col-md-12">
                <p>热门文章</p>
            </div>
        </div>

        <c:forEach items="${hotBlog}" var="blog">
            <div class="row" style="margin-top: 10px;">
                <div class="col-md-12">
                    <a style="color: #757575;text-decoration: underline" href="${base}/blog/${blog.id}">${blog.title}&nbsp;(${blog.clickHit}+阅读)</a>
                </div>
            </div>
        </c:forEach>

    </div>


    <div class="blog_classify">
        <div class="row">
            <div class="col-md-12">
                <p>近期文章</p>
            </div>
        </div>

        <c:forEach items="${newBlog}" var="blog">
            <div class="row" style="margin-top: 10px;">
                <div class="col-md-12">
                    <fmt:formatDate value="${blog.releaseDate}" var="releaseDate" pattern="yyyy-MM-dd HH:mm:ss" />
                    <a style="color: #757575;text-decoration: underline" href="${base}/blog/${blog.id}">${blog.title}&nbsp;(${releaseDate})</a>
                </div>
            </div>
        </c:forEach>
    </div>

    <%--<div class="blog_classify">
        <div class="row">
            <div class="col-md-12">
                <p>近期评论</p>
            </div>
        </div>

        <div class="row">
            <div class="col-md-12">
                <a href="#">nginx反向代理到多台tomcat配置</a>
            </div>
        </div>
    </div>--%>

    <div class="blog_classify">
        <div class="row">
            <div class="col-md-12">
                <p>分类目录</p>
            </div>
        </div>

        <c:forEach items="${typeNameList}" var="type">
            <div class="row" style="margin-top: 10px;">
                <div class="col-md-12">
                    <a href="${base}/index?typeId=${type.id}">${type.typeName}(${type.blogCount})</a>
                </div>
            </div>
        </c:forEach>
    </div>

    <div class="blog_classify">
        <div class="row">
            <div class="col-md-12">
                <p>日期归档</p>
            </div>
        </div>

        <c:forEach items="${releaseDateList}" var="releaseDate">
            <div class="row" style="margin-top: 10px;">
                <div class="col-md-12">
                    <a href="${base}/index?releaseDate=${releaseDate.releaseDate}">${releaseDate.releaseDate}(${releaseDate.blogCount})</a>
                </div>
            </div>
        </c:forEach>
    </div>

    <div class="blog_classify">
        <div class="row">
            <div class="col-md-12">
                <p>友情链接</p>
            </div>
        </div>

        <c:forEach items="${linkList}" var="link">
            <div class="row" style="margin-top: 10px;">
                <div class="col-md-12">
                    <a href="${link.linkUrl}">${link.linkName}</a>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
