<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<header class="header-wrap">
	<div class="logo">
		<a href="http://notejava.com">
			<img alt="logo" src="${base}/static/images/logo.png" width="326px" height="60px">
		</a>
	</div>
	<div class="saying">
		梦想还是要有的，万一实现了呢
	</div>
</header>
<nav class="nav-wrap">
	<ul>
		<li class="active">
			<a href="${base}/index">首页</a>
		</li>
		<li>
			<a href="javascript:void(0)" onclick="aboutBlogger()">关于博主</a>
		</li>
		<li>
			<a href="javascript:void(0)" onclick="aboutSite()">关于本站</a>
		</li>
		<li>
			<a href="#">留言板</a>
		</li>
	</ul>
	<script type="text/javascript">
		function aboutBlogger(){
			$('#list').load('${base}/blogger/info #list div:first');
		}
		function aboutSite(){
			$('#list').load('${base}/common/aboutSite.html div:first');
		}
	</script>
</nav>
