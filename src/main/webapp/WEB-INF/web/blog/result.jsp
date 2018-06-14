<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/head.jsp" %>
<div class="col-md-8">

    <div class="row">
        <div class="col-md-12">
            搜索 ${obj.data.q} 的结果（总共搜索到 ${obj.data.count} 条记录）
        </div>
    </div>

    <c:forEach items="${obj.data.blogs}" var="blog">
        <div class="blog_list">
            <div class="row">
                <div class="col-md-12">
                    <a href="${base}/blog/${blog.id}">${blog.title}</a>
                </div>
            </div>

            <div class="row">
                <div class="col-md-12">
                    <p style="word-break: break-word">摘要：${blog.content}</p>
                </div>
            </div>

            <div class="row">
                <div class="col-md-12">
                    <a style="color: red" href="${base}/blog/${blog.id}">http://notejava.com/blog/${blog.id}</a>
                </div>
            </div>

            <div class="row">
                <div class="col-md-12">
                    <p>发布日期：${blog.createTimeStr}</p>
                </div>
            </div>
        </div>
    </c:forEach>

    <div class="row">
        <div class="col-md-12">
            <nav aria-label="Page navigation">
                <ul class="pagination">

                    <c:if test="${obj.data.pageBar.beginIndex < obj.data.pageBar.currentPage}">
                        <li>
                            <a href="${base}/search?q=${obj.data.q}&pageNo=${obj.data.pageBar.currentPage-1}" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                    </c:if>


                    <c:forEach var="no" begin="${obj.data.pageBar.beginIndex}" end="${obj.data.pageBar.endIndex}">
                        <li <c:if test="${no==obj.data.pageBar.currentPage}">class="active"</c:if> ><a href="${base}/search?q=${obj.data.q}&pageNo=${no}">${no}</a></li>
                    </c:forEach>

                    <c:if test="${obj.data.pageBar.endIndex > obj.data.pageBar.currentPage}">
                        <li>
                            <a href="${base}/search?q=${obj.data.q}&pageNo=${obj.data.pageBar.currentPage+1}" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                    </c:if>

                </ul>
            </nav>
        </div>
    </div>
</div>
<%@include file="/common/right.jsp" %>
<%@include file="/common/foot.jsp" %>
