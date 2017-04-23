<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人博客首页</title>
<link rel="stylesheet" type="text/css" href="${base}/static/css/my.css">
<script type="text/javascript" src="${base}/static/js/jquery-3.1.0.min.js"></script>
<script>
var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "https://hm.baidu.com/hm.js?56081a73b92eac05d5417cb74f3d952f";
  var s = document.getElementsByTagName("script")[0]; 
  s.parentNode.insertBefore(hm, s);
})();
</script>

</head>
<body>
	<%@include file="/common/backup/head.jsp" %>
	<div class="main">
		<div id="list" class="main_content fl">
			<div class="blog_info">
				<h3 class="blog_title">${obj.data.blog.title}</h3>
				<div class="blog_sign">
					<fmt:formatDate value="${obj.data.blog.releaseDate}" var="releaseDate" pattern="yyyy-MM-dd HH:mm:ss" />
					<span>发布时间:${releaseDate}</span>&nbsp;&nbsp;
					<span>类别:${obj.data.blog.typeName}</span>&nbsp;&nbsp;
					<span>阅读:(${obj.data.blog.clickHit})</span>&nbsp;&nbsp;
					<span>评论:(${obj.data.blog.replyHit})</span>
				</div>
				<div class="blog_content">
					${obj.data.blog.content}
				</div>
				<div class="blog_keyWord">
					关键字：${obj.data.blog.keyWord}
				</div>
				<hr style="height:5px;border:none;border-top:1px dashed gray;padding-bottom:  10px;" />
				<c:if test="${!empty obj.data.lastBlog}">
					<div class="last_blog">
						上一篇：<a href="${base}/blog/${obj.data.lastBlog.id}">${obj.data.lastBlog.title}</a>
					</div>
				</c:if>
				<c:if test="${!empty obj.data.nextBlog}">
					<div class="next_blog">
						下一篇：<a href="${base}/blog/${obj.data.nextBlog.id}">${obj.data.nextBlog.title}</a>
					</div>
				</c:if>
			</div>
			<!--高速版-->
			<div id="SOHUCS" sid="${obj.data.blog.id}"></div>
			<script charset="utf-8" type="text/javascript" src="http://changyan.sohu.com/upload/changyan.js" ></script>
			<script type="text/javascript">
				window.changyan.api.config({
				appid: 'cysLtqfjS',
				conf: 'prod_b8d4997c289fc3ab019766dc015d2a83'
			});
			</script>
		</div>
		<%@include file="/common/backup/right.jsp" %>
		<%@include file="/common/backup/foot.jsp" %>
	</div>
	<script type="text/javascript">
		$(function(){
			var img = $('.blog_content img');
			for(var i=0;i<img.length;i++){
				$(img[i]).attr('src','${base}'+$(img[i]).attr('src'));
			}
		})
	</script>
</body>
</html>