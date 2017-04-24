<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<c:if test="${empty obj}">
	<title>消息模板添加</title>
</c:if>
<c:if test="${not empty obj.data.blogId}">
	<title>消息模板修改</title>
</c:if>
<%@ include file="/WEB-INF/admin/common.jspf" %>
<!-- this page specific styles -->
<link rel="stylesheet" href="${base}/static/css/compiled/form-showcase.css" type="text/css" media="screen" />
<link rel="stylesheet" href="${base}/static/css/compiled/gallery.css" type="text/css" media="screen" />
</head>
<body>
<!-- main container -->
	<div class="content content-inner">
		<div id="pad-wrapper" class="form-page">
			<div class="row header">
				<c:if test="${empty obj.data.blog.id}">
					<h3>写博客</h3>
				</c:if>
				<c:if test="${not empty obj.data.blog.id}">
					<h3>修改博客</h3>
				</c:if>
			</div>
			<div class="row form-wrapper">
				<!-- left column -->
				<div class="col-md-12 column">
					<form id="form" action="/blog/add" method="post">
					
					<input type="hidden" name="id" value="${obj.data.blog.id}">
					<div class="field-box">
						<label>标题</label>
						<div class="col-md-8">
							<input name="title"
								class="form-control inline-input" type="text" value="${obj.data.blog.title}" />
						</div>
					</div>
					
					<div class="field-box">
						<label>类型</label>
						<div class="col-md-8">
							<select name="typeId" class="form-control">
								<c:forEach var="blogType" items="${obj.data.blogTypes}">
									<option value="${blogType.id}">${blogType.typeName}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					
					<div class="field-box">
						<label>内容</label>
						<div class="col-md-8">
							//隐藏的id是  val_content  调用文本数据
							<textarea id="val_content" style="display:none;" >${obj.data.blog.content}</textarea>
							<!-- 加载编辑器的容器 -->
							<script id="container" name="content" type="text/plain" style="height: 500px;width: 100%">
								
							</script>
						</div>
					</div>
					<input id="summary" type="hidden" name="summary">
					<input id="contentNoTag" type="hidden" name="contentNoTag">
					<div class="field-box">
						<label>关键字</label>
						<div class="col-md-8">
							<input name="keyWord"
								class="form-control inline-input" type="text" value="${obj.data.blog.keyWord}" />
						</div>
					</div>
				</form>
				</div>
			</div>
			<div class="row" style="text-align: center; margin-bottom: 60px">
				<a class="btn btn-default" href="${base}/admin/blog/list">返　回</a>
				<a class="btn btn-primary" onclick="save()">保　存</a>
			</div>
		</div>
	</div>
<script type="text/javascript" src="${base}/static/js/jquery-1.10.2.js"></script> 
<script src="${base}/static/js/bootstrap.min.js"></script>
<!-- layer -->
<script src="${base}/static/plug/layer-v2.4/layer.js" type="text/javascript"></script>
<script type="text/javascript">
	function save() {
		var contentNoTag = UE.getEditor('container').getContentTxt();
		var summary = contentNoTag.substring(0,155);
		$('#summary').val(summary);
		$('#contentNoTag').val(contentNoTag);
		$.ajax({
			type:'post',
			url:'${base}/admin/blog/save',
			async:false,
			dataType:'json',
			data:$('#form').serializeArray(),
			success:function(result){
				if(result.code == 0){
					layer.msg("保存成功",{icon: 1,time:1000},function(){
						window.location.href='${base}/admin/blog/list';
					});
				}else{
					layer.msg(result.errorMsg,{icon:2,time:2000});
				}
			},
			error: function(result){
				layer.msg("请求出错！",{icon:2,time:2000});
			}
		}) 
	}
</script>

<!-- 配置文件 -->
<script type="text/javascript" src="${base}/static/ueditor/ueditor.config.js"></script>
<!-- 编辑器源码文件 -->
<script type="text/javascript" src="${base}/static/ueditor/ueditor.all.js"></script>
<!-- 实例化编辑器 -->
<script type="text/javascript">
    var ue = UE.getEditor('container');
    ue.ready(function() {
        //设置编辑器的内容
        var content = $('#val_content').val();
        ue.setContent(content);
    });
</script>
</body>
</html>