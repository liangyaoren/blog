<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/head.jsp" %>
<div class="col-md-8">

    <c:forEach items="${obj.data.blogs}" var="blog">
        <div class="row blog_list">
            <div class="col-md-12">
                <div class="row">
                    <div class="col-md-12">
                        <a href="${base}/blog/${blog.id}">${blog.title}</a>
                    </div>
                </div>
                <div class="row" style="margin-top: 10px;">
                    <div class="col-md-12">
                        <p>类别:${blog.typeName}&emsp;阅读:${blog.clickHit}&emsp;发布时间:${blog.releaseDate}</p>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <p>${blog.summary}......</p>
                    </div>
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
                            <a href="${base}/index?pageNo=${obj.data.pageBar.currentPage-1}" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                    </c:if>


                    <c:forEach var="no" begin="${obj.data.pageBar.beginIndex}" end="${obj.data.pageBar.endIndex}">
                        <li <c:if test="${no==obj.data.pageBar.currentPage}">class="active"</c:if> ><a href="${base}/index?pageNo=${no}">${no}</a></li>
                    </c:forEach>

                    <c:if test="${obj.data.pageBar.endIndex > obj.data.pageBar.currentPage}">
                        <li>
                            <a href="${base}/index?pageNo=${obj.data.pageBar.currentPage+1}" aria-label="Next">
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
