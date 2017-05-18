<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>管理后台</title>
<!-- bootstrap -->
<link href="${base}/static/css/bootstrap/bootstrap.min.css" rel="stylesheet" />
<link href="${base}/static/css/bootstrap/bootstrap-overrides.css" type="text/css" rel="stylesheet" />
<!-- global styles -->
<link rel="stylesheet" type="text/css" href="${base}/static/css/compiled/layout.css" />
<link rel="stylesheet" type="text/css" href="${base}/static/css/compiled/elements.css" />
<link rel="stylesheet" type="text/css" href="${base}/static/css/compiled/icons.css" />

<!-- libraries -->
<link href="${base}/static/css/lib/font-awesome.css" type="text/css" rel="stylesheet" />
<!-- this page specific styles -->
<link rel="stylesheet" href="${base}/static/css/compiled/index.css" type="text/css" media="screen" />

<!-- open sans font -->
<link href="${base}/static/css/openSansFont.css" rel="stylesheet" type="text/css" />
<!-- lato font -->
<link href="${base}/static/css/latoFont.css" rel="stylesheet" type="text/css" />

<!--[if lt IE 9]>
<script src="${base}/static/js/html5shiv.min.js"></script>
<![endif]-->
</head>
<body>
<!-- navbar -->
    <header class="navbar navbar-inverse" role="banner">
        <div class="navbar-header">
            <button class="navbar-toggle" type="button" data-toggle="collapse" id="menu-toggler">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="index.html">
                <img src="${base}/static/img/logo.png" alt="logo" />
            </a>
        </div>
        <ul class="nav navbar-nav pull-right hidden-xs">
            <li class="hidden-xs hidden-sm">
                <input class="search" type="text" />
            </li>
            <li class="notification-dropdown hidden-xs hidden-sm">
                <a href="#" class="trigger">
                    <i class="icon-warning-sign"></i>
                    <span class="count">8</span>
                </a>
                <div class="pop-dialog">
                    <div class="pointer right">
                        <div class="arrow"></div>
                        <div class="arrow_border"></div>
                    </div>
                    <div class="body">
                        <a href="#" class="close-icon"><i class="icon-remove-sign"></i></a>
                        <div class="notifications">
                            <h3>You have 6 new notifications</h3>
                            <a href="#" class="item">
                                <i class="icon-signin"></i> New user registration
                                <span class="time"><i class="icon-time"></i> 13 min.</span>
                            </a>
                            <a href="#" class="item">
                                <i class="icon-signin"></i> New user registration
                                <span class="time"><i class="icon-time"></i> 18 min.</span>
                            </a>
                            <a href="#" class="item">
                                <i class="icon-envelope-alt"></i> New message from Alejandra
                                <span class="time"><i class="icon-time"></i> 28 min.</span>
                            </a>
                            <a href="#" class="item">
                                <i class="icon-signin"></i> New user registration
                                <span class="time"><i class="icon-time"></i> 49 min.</span>
                            </a>
                            <a href="#" class="item">
                                <i class="icon-download-alt"></i> New order placed
                                <span class="time"><i class="icon-time"></i> 1 day.</span>
                            </a>
                            <div class="footer">
                                <a href="#" class="logout">View all notifications</a>
                            </div>
                        </div>
                    </div>
                </div>
            </li>
            <li class="notification-dropdown hidden-xs hidden-sm">
                <a href="#" class="trigger">
                    <i class="icon-envelope"></i>
                </a>
                <div class="pop-dialog">
                    <div class="pointer right">
                        <div class="arrow"></div>
                        <div class="arrow_border"></div>
                    </div>
                    <div class="body">
                        <a href="#" class="close-icon"><i class="icon-remove-sign"></i></a>
                        <div class="messages">
                            <a href="#" class="item">
                                <img src="${base}/static/img/contact-img.png" class="display" alt="user" />
                                <div class="name">Alejandra Galván</div>
                                <div class="msg">
                                    There are many variations of available, but the majority have suffered alterations.
                                </div>
                                <span class="time"><i class="icon-time"></i> 13 min.</span>
                            </a>
                            <a href="#" class="item">
                                <img src="${base}/static/img/contact-img2.png" class="display" alt="user" />
                                <div class="name">Alejandra Galván</div>
                                <div class="msg">
                                    There are many variations of available, have suffered alterations.
                                </div>
                                <span class="time"><i class="icon-time"></i> 26 min.</span>
                            </a>
                            <a href="#" class="item last">
                                <img src="${base}/static/img/contact-img.png" class="display" alt="user" />
                                <div class="name">Alejandra Galván</div>
                                <div class="msg">
                                    There are many variations of available, but the majority have suffered alterations.
                                </div>
                                <span class="time"><i class="icon-time"></i> 48 min.</span>
                            </a>
                            <div class="footer">
                                <a href="#" class="logout">View all messages</a>
                            </div>
                        </div>
                    </div>
                </div>
            </li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle hidden-xs hidden-sm" data-toggle="dropdown">
                  	  欢迎您：${user.userName}
                    <b class="caret"></b>
                </a>
                <ul class="dropdown-menu">
                    <li><a href="${base}/admin/blogger/info" target="resultFrame">个人信息</a></li>
                    <li><a href="#updateKeyword" data-toggle="modal" role="button">修改密码</a></li>
                    <li><a href="javascript:void(0)" onclick="flush()">刷新缓存</a></li>
                    <li><a href="${base}/logout">退出</a></li>
                </ul>
            </li>
            <li class="settings hidden-xs hidden-sm">
                <a href="personal-info.html" role="button">
                    <i class="icon-cog"></i>
                </a>
            </li>
            <li class="settings hidden-xs hidden-sm">
                <a href="signin.html" role="button">
                    <i class="icon-share-alt"></i>
                </a>
            </li>
        </ul>
    </header>
    <!-- end navbar -->
    
    <!-- 模态框 -->
<div class="modal fade" id="updateKeyword" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title">修改密码</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-md-12">
						<form id="form">
							<table style="width: 100%;border-collapse:separate; border-spacing:10px;">
								<tr>
									<td class="col-md-2">
										<label>用户名</label>
									</td>
									<td class="col-md-10">
										<input type="text" class="form-control" name="userName">
									</td>
								</tr>
								
								<tr>
									<td class="col-md-2">
										<label>原密码</label>
									</td>
									<td class="col-md-10">
										<input type="password" class="form-control" name="password">
									</td>
								</tr>
								
								<tr>
									<td class="col-md-2">
										<label>新密码</label>
									</td>
									<td class="col-md-10">
										<input type="password" class="form-control" name="newPassword">
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

    <!-- sidebar -->
    <div id="sidebar-nav">
        <ul id="dashboard-menu">
            <li class="active def-menu">
                <div id="pointer" class="pointer" style="z-index: 1;">
                    <div class="arrow"></div>
                    <div class="arrow_border"></div>
                </div>
                <a href="${base}/admin/home" target="resultFrame">
                    <i class="icon-home"></i>
                    <span>Home</span>
                </a>
            </li>
            
           	<li class="def-menu">
                <a href="${base}/admin/blog/list" target="resultFrame">
                    <i class="icon-edit"></i>
                    <span>博客管理</span>
                </a>
            </li>
            
           	<li class="def-menu">
                <a href="${base}/admin/blogType/list" target="resultFrame">
                    <i class="icon-tag"></i>
                    <span>类别管理</span>
                </a>
            </li>
            
            <li class="def-menu">
                <a href="${base}/admin/comments/list" target="resultFrame">
                    <i class="icon-comment"></i>
                    <span>评论管理</span>
                </a>
            </li>
            
           <li class="def-menu">
                <a href="${base}/admin/blogger/info" target="resultFrame">
                    <i class="icon-user"></i>
                    <span>个人信息</span>
                </a>
            </li>
            
            <li class="def-menu">
                <a href="${base}/admin/link/list" target="resultFrame">
                    <i class="icon-external-link"></i>
                    <span>友情链接</span>
                </a>
            </li>
        </ul>
    </div>
    <!-- end sidebar -->

	<!-- main container -->
    <div class="content">
        <div id="pad-wrapper" style="padding: 0; margin:0;">
			<iframe id="resultFrame" name="resultFrame" src="${base}/admin/home" style="width: 100%; /*min-height: 580px;*/border: 0px solid #000000; word-wrap: break-word;"></iframe>
        </div>
    </div>
    
<!-- scripts -->
<script src="${base}/static/js/jquery-1.10.2.js"></script>
<script src="${base}/static/js/bootstrap.min.js"></script>

<script src="${base}/static/js/jquery.slimscroll.min.js"></script>

<script src="${base}/static/js/index.js"></script>
<script type="text/javascript">
	function flush(){
		$.ajax({
			type:'post',
			dataType:'json',
			url:'${base}/admin/flush',
			success:function(result){
				if(result.code==0){
					alert('刷新成功');
				}else{
					alert('刷新失败');
				}
			},
			error:function(result){
				alert('请求出错');
			}
		})
	}
	
	function save(){
		$.ajax({
			type:'post',
			dataType:'json',
			url:'${base}/admin/modifyPwd',
			data:$('#form').serialize(),
			success:function(result){
				if(result.code==0){
					alert('修改成功');
					window.location.href='${base}/logout';
				}else{
					alert('修改失败');
				}
			},
			error:function(result){
				alert('请求出错');
			}
		})
	}
</script>
</body>
</html>