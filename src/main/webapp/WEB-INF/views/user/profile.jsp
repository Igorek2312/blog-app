<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<t:layout>
    <div class="col-sm-5">
        <div class="thumbnail">
            <img src="${user.imageUrl}" alt="no image">
        </div>
    </div>
    <div class="col-sm-7">
        <div class="thumbnail clearfix">
            <div class="col-sm-6 ">
                <span class="text-muted"><spring:message code="label.username"/>:</span>
            </div>
            <div class="col-sm-6">
                <span class="text-info">${user.username}</span>
            </div>
            <div class="col-sm-6 ">
                <span class="text-muted"><spring:message code="label.name"/>:</span>
            </div>
            <div class="col-sm-6">
                <span class="text-info">${user.fullName}</span>
            </div>
            <div class="col-sm-12">
                <hr>
                <a href="/users/${user.id}/posts">
                    <spring:message code="label.posts"/>
                </a>
                <c:if test="${isCurrentUserOwner}">
                    <a href="/edit-my-profile">
                        <button class="btn btn-primary pull-right">
                            <i class="fa fa-pencil-square-o"></i>
                        </button>
                    </a>
                </c:if>
            </div>
        </div>
    </div>
</t:layout>


