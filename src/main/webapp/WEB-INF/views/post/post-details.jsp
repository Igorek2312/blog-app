<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<t:layout>
    <div class="row">
        <div class="col-sm-12">
            <h1>${post.title}</h1>
                ${post.content}
        </div>
    </div>

    <c:if test="${isCurrentUserOwner}">
        <div class="row">
            <div class="col-sm-12">
                <c:if test="${!empty images}">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="row">
                                <c:forEach var="image" items="${images}">
                                    <div class="col-sm-3">
                                        <img src="${image.fileUrl}" alt="" style="width:100%"/>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </c:if>
                <hr>
                <a href="/edit-post/${post.id}">
                    <button class="btn btn-primary">
                        <i class="fa fa-pencil-square-o"></i>
                    </button>
                </a>
                <a href="/delete-post/${post.id}">
                    <button class="btn btn-danger">
                        <i class="fa fa-trash"></i>
                    </button>
                </a>
                <label for="attache-file" class="btn btn-primary">
                    <spring:message code="label.attach.file"/>
                </label>
                <form:form id="attache-file-form" action="/posts/${post.id}/attach-file" method="post"
                           enctype="multipart/form-data">
                    <input name="file" id="attache-file" style="display:none;" type="file"/>
                </form:form>
                <hr>
            </div>
        </div>
    </c:if>

    <c:if test="${!empty files}">
        <div class="panel panel-default">
            <div class="panel-body">
                <c:forEach var="file" items="${files}">
                    <a href="${file.fileUrl}">${file.fileUrl}</a>
                    <c:if test="${isCurrentUserOwner}">
                        <a href="/posts/${post.id}/delete-attached-file/${file.id}">
                            <button class="btn btn-danger">
                                <i class="fa fa-trash"></i>
                            </button>
                        </a>
                    </c:if>
                </c:forEach>
            </div>
        </div>
    </c:if>

    <sec:authorize access="hasRole('ROLE_USER')">
        <div class="row">
            <div class="col-sm-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <spring:message code="label.leave.comment"/>
                    </div>
                    <div class="panel-body">
                        <form:form modelAttribute="comment" action="/posts/${postId}/comments" method="post"
                                   cssClass="form-horizontal">
                            <spring:bind path="content">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <div class="col-sm-12">
                                        <spring:message code="label.content" var="content"/>
                                        <form:input path="content" type="text" class="form-control"
                                                    placeholder="content"/>
                                    </div>
                                    <div class="col-sm-12">
                                        <form:errors path="content" cssClass="text-danger"/>
                                    </div>
                                </div>
                            </spring:bind>
                            <button class="btn btn-primary">
                                <spring:message code="label.send"/>
                            </button>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </sec:authorize>

    <c:if test="${!empty comments.content}">
        <div class="row">
            <div class="col-sm-12">
                <h4><spring:message code="label.comments"/></h4>
                <c:forEach var="comment" items="${comments.content}">
                    <strong>${comment.content}</strong> <br>
                    <span class="text-muted">
                        <spring:message code="label.commented" arguments="${comment.user.fullName}"/>
                        <span class="utc-date-time">${comment.dateTimeCommented}</span>
                        <c:if test="${isCurrentUserOwner}">
                            <a href="/posts/${postId}//delete-comment/${comment.id}">
                            <button class="btn-danger">
                                <i class="fa fa-trash"></i>
                            </button>
                            </a>
                        </c:if>
                    </span>
                </c:forEach>
            </div>
        </div>
    </c:if>
</t:layout>
