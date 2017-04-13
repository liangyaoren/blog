<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>博客列表页面</title>
<%@ include file="/WEB-INF/admin/common.jspf" %>
</head>
<body>
<!-- main container -->
<div class="content content-inner">
	<div id="pad-wrapper" class="table-wrapper">
        <div class="row header">
            <h3>博客列表</h3>
            <a style="float: right;margin-right: 30px;" class="btn btn-primary" href="${base}/admin/blog/info" role="button">添加</a>
        </div>
        <div id="list" class="row">
            <div class="col-md-12">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th class="col-md-1">
                                <span class="line"></span>标题
                            </th>
                            <th class="col-md-2">
                                <span class="line"></span>内容
                            </th>
                            <th class="col-md-2">
                                <span class="line"></span>操作
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="blog" items="${obj.data.blogs}" varStatus="status">
	                     <tr id="${blog.id}">
	                         <td><a href="${base}/admin/blog/info?id=${blog.id}">${blog.title}</a></td>
	                         <td>${blog.summary}</td>
	                         <td>
	                         	<div class="actions">
	                          		<a href="${base}/admin/blog/info?id=${blog.id}">修改</a>
	                          		<a onclick="deleteBlog(${blog.id})" href="javascript:void(0)">删除</a>
	                            </div>
	                         </td>
	                     </tr>
	                 </c:forEach>
                    </tbody>
                </table>
                
                <ul class="pagination pull-right">
                	${obj.data.pageBar}
                </ul>
            </div>                
        </div>
      </div>
    </div>
<script type="text/javascript" src="${base}/static/js/jquery-1.10.2.js"></script>
<script type="text/javascript" src="${base}/static/plug/layer-v2.4/layer.js"></script>
<script type="text/javascript">
	function gotoPage(pageNo,pageSize){
		var url = '${base}/admin/blog/list?pageNo='+pageNo+'&pageSize='+pageSize;
		$('#list').load(url+' #list div:first');
	}
	
	function deleteBlog(id){
		layer.confirm("确定要删除？", {
			btn: ['确定','取消']
		}, function(){
			$.ajax({
				type:'post',
				url:'${base}/admin/blog/delete',
				async:false,
				dataType:'json',
				data:{'id':id},
				success:function(result){
					if(result.code == 0){
						gotoPage(1,10);
						layer.msg("删除成功",{icon: 1});
					}else{
						layer.msg("删除失败",{icon: 2});
					}
				},
				error: function(result){
					layer.msg("请求出错！",{time:0,btn: ['确定']});
				}
			})
		},function(){return;});
	}
</script>
</body>
</html>