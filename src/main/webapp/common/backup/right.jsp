<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="main_sign fl">
	<div class="sign_box">
		<div class="search_input">
			<form action="${base}/search" onsubmit="return checkForm()">
				<input id="q" name="q" type="text" placeholder="请输入你想搜索的内容" class="search_value">
				<input type="submit" class="search_btn" value="">
			</form>
		</div>
	</div>
	<script type="text/javascript">
		function checkForm(){
			var value = $('#q').val();
			value = value.trim();
			if(value==''){
				alert('请输入关键字!');
				return false;
			}
			return true;
		}
	</script>
	<div class="recommend_box">
		<img alt="" src="${base}/static/images/hot-recommend-img.png">
		<!-- <ul>
			<li>
				<a href="#">1.nohup让某个程序在后台运行</a>
			</li>
			<li>
				<a href="#">2.nohup让某个程序在后台运行</a>
			</li>
			<li>
				<a href="#">3.nohup让某个程序在后台运行</a>
			</li>
		</ul> -->
	</div>
	<div class="sign_box">
		<h3>按类别</h3>
		<hr style="height:5px;border:none;border-top:1px dashed gray;padding-bottom:  10px;" />
		<ul>
			<c:forEach items="${typeNameList}" var="type">
				<li>
					<a href="javascript:void(0)" onclick="gotoPageByTypeId(1,10,${type.id})">${type.name}(${type.blogCount})</a>
				</li>
			</c:forEach>
		</ul>
	</div>
	<input id="cndName" name="cndName" type="hidden">
	<input id="cndValue" name="cndValue" type="hidden">
	<div class="sign_box">
		<h3>按日期</h3>
		<hr style="height:5px;border:none;border-top:1px dashed gray;padding-bottom:  10px;" />
		<ul>
			<c:forEach items="${createTimeList}" var="createTime">
				<li>
					<a href="javascript:void(0)" onclick="gotoPageByDate(1,10,'${createTime.createTime}')">${createTime.createTime}(${createTime.blogCount})</a>
				</li>
			</c:forEach>
		</ul>
	</div>
	<div class="sign_box">
		<h3>友情链接</h3>
		<hr style="height:5px;border:none;border-top:1px dashed gray;padding-bottom:  10px;" />
		<ul>
			<c:forEach items="${linkList}" var="link">
				<li>
					<a href="${link.linkUrl}" target="_blank">${link.linkName}</a>
				</li>
			</c:forEach>
		</ul>
	</div>
</div>