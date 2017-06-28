<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<t:layout>
    <h2>
        <spring:message code="label.posts.of.author" arguments="${user.fullName}"/>
        <c:if test="${isCurrentUserOwner}">
            <a href="/create-post">
                <button class="btn btn-lg btn-primary">
                    <spring:message code="label.new.post"/>
                </button>
            </a>
        </c:if>
    </h2>
    <hr>

    <jsp:include page="posts.jsp"/>
</t:layout>
