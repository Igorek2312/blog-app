<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<t:layout>
    <c:if test="${isCurrentUserOwner}">
        <a href="/create-post">
            <button class="btn btn-lg btn-primary">
                <spring:message code="label.new.post"/>
            </button>
        </a>
    </c:if>

    <c:forEach var="post" items="${posts.content}">
        <hr>
        <div class="col-sm-12">
            <a href="/posts/${post.id}">
                <h3>${post.title}</h3>
            </a>
        </div>
        <div class="col-sm-12">
            <span class="text-muted">
                <spring:message code="label.posted.at"/>
                <span class="utc-date-time">${post.dateTimePublished}</span>
            </span>
        </div>
    </c:forEach>

    <c:set value="${posts.totalPages-1}" var="maxPage"/>
    <c:if test="${maxPage>=0}">
        <div class="row">
            <div class="col-sm-2 col-centered">
                <ul class="pagination">
                    <li><a href="?page=0">&laquo;</a></li>
                    <c:forEach var="current" begin="0" end="${maxPage}">
                        <li><a href="?page=${current}">${current+1}</a></li>
                    </c:forEach>
                    <li><a href="?page=${maxPage}">&raquo;</a></li>
                </ul>
            </div>
        </div>
    </c:if>

</t:layout>
