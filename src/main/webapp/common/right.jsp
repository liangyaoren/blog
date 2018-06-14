<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="col-md-3 col-md-offset-1">

    <div class="blog_classify">
        <div class="row">
            <div class="col-md-12">
                <form action="${base}/search" onsubmit="return checkForm()">
                    <div>
                        <input type="text" class="form-control" id="q" name="q" placeholder="输入想搜索的内容..." value="${obj.data.q}">
                        <input type="hidden" name="pageNo" value="1">
                        <%--<input type="submit" class="btn btn-default"/>--%>
                    </div>
                </form>
            </div>
        </div>

        <div class="row">
            <div class="col-md-12">
                <p>热门文章</p>
            </div>
        </div>

        <c:forEach items="${hotBlog}" var="blog">
            <div class="row" style="margin-top: 10px;">
                <div class="col-md-12">
                    <a style="color: #757575;text-decoration: underline" href="${base}/blog/${blog.id}">${blog.title}&nbsp;(${blog.clicks}+阅读)</a>
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
                    <fmt:formatDate value="${blog.createTime}" var="createTime" pattern="yyyy-MM-dd HH:mm:ss" />
                    <a style="color: #757575;text-decoration: underline" href="${base}/blog/${blog.id}">${blog.title}&nbsp;(${createTime})</a>
                </div>
            </div>
        </c:forEach>
    </div>

    <div class="blog_classify">
        <div class="row">
            <div class="col-md-12">
                <p>最新评论</p>
            </div>
        </div>

        <c:forEach items="${newComments}" var="comment">
            <div class="row" style="margin-top: 10px;">
                <div class="col-md-12">
                    <fmt:formatDate value="${comment.createTime}" var="createTime" pattern="yyyy-MM-dd HH:mm:ss" />
                    <a style="color: #757575;text-decoration: underline" href="${base}/blog/${comment.blogId}">${comment.content}</a>--@${comment.nickname} 发表于${createTime}
                </div>
            </div>
        </c:forEach>
    </div>

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

        <c:forEach items="${createTimeList}" var="createTime">
            <div class="row" style="margin-top: 10px;">
                <div class="col-md-12">
                    <a href="${base}/index?createTime=${createTime.createTime}">${createTime.createTime}(${createTime.blogCount})</a>
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
                    <a href="${link.url}">${link.name}</a>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
