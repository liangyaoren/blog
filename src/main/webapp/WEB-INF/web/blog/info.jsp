<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="/common/head.jsp" %>
<div class="col-md-8" style="margin-top: 25px;">
    <div class="row">
        <div class="col-md-12">
            <p style="text-align: center">${obj.data.blog.title}</p>
        </div>
    </div>

    <div class="row">
        <div class="col-md-12">
            <p style="text-align: center">
                类别:${obj.data.blog.typeName} &nbsp;阅读:${obj.data.blog.clicks}&nbsp;
                <fmt:formatDate value="${obj.data.blog.createTime}" var="createTime" pattern="yyyy-MM-dd HH:mm:ss" />
                发布时间:${createTime}
            </p>
        </div>
    </div>

    <div class="row">
        <div class="col-md-12">
            <p>${obj.data.blog.content}</p>
        </div>
    </div>
    
    <div class="bdsharebuttonbox" data-tag="share_1">
        <a class="bds_mshare" data-cmd="mshare"></a>
        <a class="bds_qzone" data-cmd="qzone" href="#"></a>
        <a class="bds_tsina" data-cmd="tsina"></a>
        <a class="bds_baidu" data-cmd="baidu"></a>
        <a class="bds_renren" data-cmd="renren"></a>
        <a class="bds_tqq" data-cmd="tqq"></a>
        <a class="bds_more" data-cmd="more">更多</a>
        <a class="bds_count" data-cmd="count"></a>
    </div>
    <script>
        window._bd_share_config = {
            common : {
                bdText : '自定义分享内容',
                bdDesc : '自定义分享摘要',
                bdUrl : '自定义分享url地址',
                bdPic : '自定义分享图片'
            },
            share : [{
                "bdSize" : 16
            }],
            slide : [{
                bdImg : 0,
                bdPos : "right",
                bdTop : 100
            }],
            image : [{
                viewType : 'list',
                viewPos : 'top',
                viewColor : 'black',
                viewSize : '16',
                viewList : ['qzone','tsina','huaban','tqq','renren']
            }],
            selectShare : [{
                "bdselectMiniList" : ['qzone','tqq','kaixin001','bdxc','tqf']
            }]
        }
        with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?cdnversion='+~(-new Date()/36e5)];
    </script>

    <div class="row">
        <div class="col-md-12">
            <p>关键字:${obj.data.blog.keyWord}</p>
        </div>
    </div>

    <div class="row">
        <div class="col-md-12">
            <c:if test="${!empty obj.data.lastBlog}">
                <p>上一篇：<a href="${base}/blog/${obj.data.lastBlog.id}">${obj.data.lastBlog.title}</a></p>
            </c:if>
            <c:if test="${!empty obj.data.nextBlog}">
                <p>下一篇：<a href="${base}/blog/${obj.data.nextBlog.id}">${obj.data.nextBlog.title}</a></p>
            </c:if>
        </div>
    </div>

    <div id="SOHUCS" sid="${obj.data.blog.id}"></div>
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
