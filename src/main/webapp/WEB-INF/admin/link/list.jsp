<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>友情链接列表</title>
<!-- this page specific styles -->
<link rel="stylesheet" href="${base}/static/css/compiled/form-showcase.css" type="text/css" media="screen" />
<link rel="stylesheet" href="${base}/static/css/compiled/gallery.css" type="text/css" media="screen" />
<%@ include file="/WEB-INF/admin/common.jspf" %>
</head>
<body>
<!-- main container -->
<div class="content content-inner">
	<div id="pad-wrapper" class="table-wrapper">
        <div class="row header">
            <h3>友情链接列表</h3>
            <button type="button" style="float: right; margin-right: 30px;" class="btn btn-primary" data-toggle="modal" data-target="#myModal" onclick="add()">添加</button>
        </div>
        <div id="list" class="row">
            <div class="col-md-12">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th class="col-md-1">
                                <span class="line"></span>名称
                            </th>
                            <th class="col-md-2">
                                <span class="line"></span>url
                            </th>
                            <th class="col-md-2">
                                <span class="line"></span>排序号
                            </th>
                            <th class="col-md-2">
                                <span class="line"></span>操作
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="link" items="${obj.data.links}" varStatus="status">
	                     <tr>
	                         <td><a href="#myModal" data-toggle="modal" onclick="info(${link.id})">${link.name}</a></td>
	                         <td>${link.url}</td>
	                         <td>${link.orderNo}</td>
	                         <td>
	                         	<div class="actions">
	                          		<a href="#myModal" data-toggle="modal" onclick="info(${link.id})">修改</a>
	                          		<a onclick="deletelink(${link.id})" href="javascript:void(0)">删除</a>
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

<!-- 模态框 -->
<div class="modal fade" id="myModal" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title">添加友链</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-md-12">
						<form id="form">
							<table style="width: 100%;border-collapse:separate; border-spacing:10px;">
								<tr>
									<td class="col-md-2">
										<label>友链名</label>
									</td>
									<td class="col-md-10">
										<input id="linkName" type="text" class="form-control" name="linkName">
										<input id = "linkId" type="hidden" name="id">
									</td>
								</tr>
								
								<tr>
									<td class="col-md-2">
										<label>友链url</label>
									</td>
									<td class="col-md-10">
										<input id="linkUrl" type="text" class="form-control" name="linkUrl">
									</td>
								</tr>
								
								<tr>
									<td class="col-md-2">
										<label>排序号</label>
									</td>
									<td class="col-md-10">
										<input id="orderNo" type="text" class="form-control" name="orderNo">
									</td>
								</tr>
							</table>
						</form>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<a class="btn btn-danger" onclick="save()">保存</a>
				<button id="btn_close" type="button" class="btn btn-default" data-dismiss="modal">取消</button>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="${base}/static/js/jquery-1.10.2.js"></script>
<script src="${base}/static/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${base}/static/plug/layer-v2.4/layer.js"></script>
<script type="text/javascript">
	function gotoPage(pageNo,pageSize){
		var url = '${base}/admin/link/list?pageNo='+pageNo+'&pageSize='+pageSize;
		$('#list').load(url+' #list div:first');
	}
	
	function save(){
		$('#myModal').modal('hide');
		$.ajax({
			type:'post',
			url:'${base}/admin/link/save',
			async:false,
			dataType:'json',
			data:$('#form').serializeArray(),
			success:function(result){
				if(result.code == 0){
					layer.msg("保存成功",{icon: 1,time:1000},function(){
						gotoPage(1,10);
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
	
	function deletelink(id){
		layer.confirm("确定要删除？", {
			btn: ['确定','取消']
		}, function(){
			$.ajax({
				type:'post',
				url:'${base}/admin/link/delete',
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
	
	function info(id){
		$.ajax({
			type:'post',
			url:'${base}/admin/link/info',
			dataType:'json',
			data:{'id':id},
			success:function(result){
				if(result.code == 0){
					$('#linkName').val(result.data.name);
					$('#linkUrl').val(result.data.url);
					$('#orderNo').val(result.data.orderNo);
					$('#linkId').val(result.data.id);
				}else{
					
				}
			},
			error: function(result){
				layer.msg("请求出错！",{time:0,btn: ['确定']});
			}
		})
	}
	
	function add(){
		$('#linkName').val('');
		$('#linkUrl').val('');
		$('#orderNo').val('');
		$('#linkId').val('');
	}
</script>
</body>
</html>