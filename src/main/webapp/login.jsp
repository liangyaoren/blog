<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录--notejava</title>
<link rel="stylesheet" type="text/css" href="/static/css/bootstrap/bootstrap.css">
<link rel="stylesheet" type="text/css" href="/static/css/bootstrap/bootstrap-theme.min.css">
</head>
	<body>
		<div class="container" style="margin-top: 10%">
			<form class="form-horizontal" onsubmit="return login()">
				<div class="form-group">
					<div class="col-md-4 col-md-offset-4">
						<input type="userName" class="form-control" id="userName" placeholder="用户">
					</div>
				</div>
				<div class="form-group">
					<div class="col-md-4 col-md-offset-4">
						<input type="password" class="form-control" id="password" placeholder="密码">
					</div>
				</div>
				<div class="form-group">
					<div class="col-md-4 col-md-offset-4">
						<button style="width: 100%;" type="submit" class="btn btn-default">登录</button>
					</div>
				</div>
			</form>
		</div>
	</body>

<script src="/static/js/jquery-3.1.0.min.js"></script>
<script src="/static/js/bootstrap.min.js"></script>
<script type="text/javascript">
    function login(){
        var userName=$("#userName").val();
        var password=$("#password").val();
        if(userName==null||userName==""){
            $("#error").html("用户名不能为空！");
            return false;
        }
        if(password==null||password==""){
            $("#error").html("密码不能为空！");
            return false;
        }
        $.ajax({
            type:'post',
            url:'${base}/login',
            dataType:'json',
            data:{'userName':userName,'password':password},
            async:false,
            success:function(result){
                if(result.data){
                    window.location.href='${base}/admin/index';
                }else{
                    alert('用户或密码错误!');
                }
            }
        })

		return false;
    }

</script>
</html>


