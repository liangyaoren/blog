<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/head.jsp" %>
<div class="col-md-8" style="margin-top: 20px;">
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