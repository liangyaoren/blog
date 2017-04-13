<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>博主信息修改</title>
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
				<h3>修改博主信息</h3>
			</div>
			<div class="row form-wrapper">
				<!-- left column -->
				<div class="col-md-12 column">
					<form id="form" action="/blogger/add" method="post">
					
					<input type="hidden" name="id" value="${obj.data.id}">
					<div class="field-box">
						<label>用户名</label>
						<div class="col-md-8">
							<input name="userName"
								class="form-control inline-input" type="text" value="${obj.data.userName}" />
						</div>
					</div>
					
					<div class="field-box">
						<label>昵称</label>
						<div class="col-md-8">
							<input name="nickName"
								class="form-control inline-input" type="text" value="${obj.data.nickName}" />
						</div>
					</div>
					
					<div class="field-box">
						<label>头像</label>
						<div class="col-md-8">
							<img id="imgUrl" alt="头像" src="${base}/static/userImages/${obj.data.imageName}">
							<br>
							<br>
							<input id="userImage" type="file" onchange="upload()">
							<input id="imageName" type="hidden" name="imageName" value="${obj.data.imageName}">
						</div>
					</div>
					
					<div class="field-box">
						<label>个性签名</label>
						<div class="col-md-8">
							<input name="sign"
								class="form-control inline-input" type="text" value="${obj.data.sign}" />
						</div>
					</div>
					
					<div class="field-box">
						<label>个人简介</label>
						<div class="col-md-8">
							<!-- 加载编辑器的容器 -->
							<script id="container" name="proFile" type="text/plain" style="height: 500px;width: 100%">
								
							</script>
						</div>
					</div>
				</form>
				</div>
			</div>
			<div class="row" style="text-align: center; margin-bottom: 60px">
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
		$.ajax({
			type:'post',
			url:'${base}/admin/blogger/save',
			async:false,
			dataType:'json',
			data:$('#form').serializeArray(),
			success:function(result){
				if(result.code == 0){
					layer.msg("修改成功",{icon: 1,time:1000},function(){
						window.location.href='${base}/admin/blogger/info';
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
	
	function upload(){
		var filepath = $('#userImage').val();
		var fileArr=filepath.split("//");
		var fileNameArr=fileArr[fileArr.length-1].toLowerCase().split(".");
		var bool=false;
		var ZHArr=new Array('gif','jpg','png','bmp','jpeg');
		for(var i=0;i<ZHArr.length;i++){
			if(ZHArr[i]==fileNameArr[1]){
				bool=true;
				break;
			}
		}
		if(bool==false){
			alert("只能上传图片类型！");
			return;
		}
		var formData = new FormData();
		formData.append('userImage', $('#userImage')[0].files[0]);
		
		$.ajax({
			url:'${base}/admin/file/upload',
			type: 'POST',
			cache: false,
		    data: formData,
		    processData: false,
		    contentType: false,
		    success: function (result) { 
		    	if(result.code==0){
		    		$('#imageName').val(result.data);
		    		$("#imgUrl").attr('src','${base}/static/userImages/'+result.data);
		    		layer.msg('上传成功',{icon:1,time:1000});
		    	}else{
		    		layer.msg('上传失败',{icon:2,time:2000});
		    	}
	        },  
	        error: function (result) {  
		    	layer.msg('请求出错',{icon:2,time:2000});
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
        ue.setContent('${obj.data.proFile}');
    });
</script>
</body>
</html>