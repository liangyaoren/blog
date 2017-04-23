<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人博客首页</title>
<link rel="stylesheet" type="text/css" href="${base}/static/css/my.css">
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
			<ul class="blog_list">
				<c:forEach items="${obj.data.blogs}" var="blog">
					<li>
						<c:if test="${!empty blog.imgUrl}">
							<div class="blogImg fl" style="margin-right: 10px;">
								<a href="${base}/blog/${blog.id}">
									<img alt="" src="${base}${blog.imgUrl}" width="215" height="144">
								</a>
							</div>
						</c:if>
						<div class="blog">
							<h3><a href="${base}/blog/${blog.id}">${blog.title}</a></h3>
							<div class="blog_count">
								<%-- <span>博主：${blogger.userName}&nbsp;&nbsp; </span> --%>
								<span>分类：${blog.typeName}&nbsp;&nbsp;</span>
								<span>阅读：${blog.clickHit}&nbsp;&nbsp;</span>
								<span>发布时间：${blog.releaseDate}</span>
							</div>
							<p>
								${blog.summary}......
							</p>
						</div>
					</li>
				</c:forEach>
			</ul>
			
			<ul class="pager">
				${obj.data.pageBar}
			</ul>
		</div>
		<%@include file="/common/backup/right.jsp" %>
		<%@include file="/common/backup/foot.jsp" %>
	</div>
</body>
</html>