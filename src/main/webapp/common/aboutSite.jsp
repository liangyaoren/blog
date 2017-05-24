<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/head.jsp" %>
<div class="col-md-8" style="margin-top: 20px;">
	<div class="row">
		<div class="col-md-12">
			<p>本博客使用nutz框架开发</p>
		</div>
	</div>

	<div class="row">
		<div class="col-md-12">
			<p>前台页面使用boostrap栅格系统</p>
		</div>
	</div>

	<div class="row">
		<div class="col-md-12">
			<p>后台页面使用基于boostrap的detail admin后台管理模板</p>
		</div>
	</div>

	<div class="row">
		<div class="col-md-12">
			<p>后台博客新增使用ueditor插件支持单图、多图、截图上传</p>
		</div>
	</div>

	<div class="row">
		<div class="col-md-12">
			<p>使用mysql数据库，使用Druid连接池</p>
		</div>
	</div>

	<div class="row">
		<div class="col-md-12">
			<p>使用lucene全文检索技术做博客搜索</p>
		</div>
	</div>

	<div class="row">
		<div class="col-md-12">
			<p>使用ServletContent在项目启动时缓存一批不常变化的数据，提高性能</p>
		</div>
	</div>

	<div class="row">
		<div class="col-md-12">
			<p>项目部署在腾讯云，使用centos6.5操作系统</p>
		</div>
	</div>

	<div id="SOHUCS" sid="aboutSite"></div>
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