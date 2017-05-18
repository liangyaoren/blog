<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div style="clear: both;padding: 50px 0;font-size: 14px;">
	<p>powered by <a href="http://www.notejava.com">notejava</a></p>
</div>
<script type="text/javascript" src="${base}/static/js/jquery-3.1.0.min.js"></script>
<script type="text/javascript">
	function gotoPage(pageNo,pageSize){
		var cndName = $('#cndName').val();
		var cndValue = $('#cndValue').val();
		var data = {};
		data[cndName]=cndValue;
		data['pageNo']=pageNo;
		data['pageSize']=pageSize;
		$('#list').load('${base}/index #list ul',data);
		scrollTo(0,0);
	}
	
	function gotoPageByTypeId(pageNo,pageSize,typeId){
		$('#cndName').val('typeId');
		$('#cndValue').val(typeId);
		gotoPage(pageNo,pageSize);
	}
	
	function gotoPageByDate(pageNo,pageSize,createTime){
		$('#cndName').val('createTime');
		$('#cndValue').val(createTime);
		gotoPage(pageNo,pageSize);
	}
</script>