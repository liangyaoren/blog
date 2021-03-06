<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>${!empty obj.data.blog.title ? obj.data.blog.title:'Yaoren 的博客'}</title>
    <link rel="stylesheet" type="text/css" href="/static/css/bootstrap/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="/static/css/bootstrap/bootstrap-theme.min.css">
    <link rel="stylesheet" type="text/css" href="/static/css/notejava.css">
    <script src="/static/js/jquery-3.1.0.min.js"></script>
    <script src="/static/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">

    <%--<div class="row">
        <div class="col-md-12">
            <h3>Yaoren's Blog</h3>
        </div>
    </div>

    <div class="row">
        <div class="col-md-12">
            <h5>梦想总是要有的，万一实现了呢-----马云</h5>
        </div>
    </div>--%>

    <nav class="navbar navbar-default">
        <div class="container-fluid" style="background-color: #303643 !important">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="${base}/index" style="color: white">Yaoren 的博客</a>
            </div>

            <div class="collapse navbar-collapse navbar-right" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li><a href="${base}/index" style="color: white">首页</a></li>
                    <li><a href="${base}/blogger/info" style="color: white">关于博主</a></li>
                    <li><a href="${base}/blog/48" style="color: white">关于本站</a></li>
                    <li><a href="/common/messageBoard.jsp" style="color: white">留言板</a></li>
                    <%--<li><a href="${base}/blog/47" style="color: white">工具收藏</a></li>--%>
                    <li><a href="https://github.com/liangyaoren/blog" style="color: white">github地址</a></li>
                </ul>
                <%--<form class="navbar-form navbar-right" action="${base}/search" onsubmit="return checkForm()">
                    <div class="form-group">
                        <input type="text" class="form-control" id="q" name="q" placeholder="输入想搜索的内容...">
                    </div>
                    <input type="hidden" name="pageNo" value="1">
                    <input type="submit" class="btn btn-default"/>
                </form>--%>
            </div>
        </div>
    </nav>

    <div class="row">
