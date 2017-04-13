<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人博客首页</title>
<link rel="stylesheet" type="text/css" href="${base}/static/css/my.css">
</head>
<body>
	<%@include file="/common/head.jsp" %>
	<div class="main">
		<div id="list" class="main_content fl">
			<div class="search_title">
				<p>
					搜索 ${obj.data.q} 的结果（总共搜索到 ${obj.data.count} 条记录）
				</p>
			</div>
		
			<ul class="blog_list">
			
				<c:forEach items="${obj.data.blogs}" var="blog">
					<li class="search_li">
						<div>
							<a href="${base}/blog/${blog.id}">${blog.title }</a>
						</div>
						<div>摘要：${blog.content}</div>
						<div>
							<a style="color: red" href="${base}/blog/${blog.id}">http://notejava.com/blog/${blog.id}</a>
						</div>
						<div>发布日期：${blog.releaseDateStr}</div>
					</li>
				</c:forEach>
			</ul>
			
			<ul class="pager">
				${obj.data.pageBar}
			</ul>
		</div>
		<script type="text/javascript">
			function nextPage(pageNo,pageSize){
				$('#list').load('${base}/search #list ul',{'q':'${obj.data.q}','pageNo':pageNo});
				scrollTo(0,0);
			}
		</script>
		<%@include file="/common/right.jsp" %>
		<%@include file="/common/foot.jsp" %>
	</div>
</body>
</html>