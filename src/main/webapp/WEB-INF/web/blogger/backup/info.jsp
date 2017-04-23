<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>关于博主</title>
<link rel="stylesheet" type="text/css" href="${base}/static/css/my.css">
<script type="text/javascript" src="${base}/static/js/jquery-3.1.0.min.js"></script>
</head>
<body>
	<%@include file="/common/backup/head.jsp" %>
	<div class="main">
		<div id="list" class="main_content fl">
			<div class="aboutBlogger">
				${blogger.proFile}
			</div>
		</div>
		<%@include file="/common/backup/right.jsp" %>
		<%@include file="/common/backup/foot.jsp" %>
	</div>
</body>
</html>