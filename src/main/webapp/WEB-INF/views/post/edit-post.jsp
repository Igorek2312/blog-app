<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<t:layout>
    <form:form modelAttribute="post" action="/edit-post/${post.id}" method="post" cssClass="form-horizontal">
        <spring:bind path="title">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <div class="col-sm-1">
                    <spring:message code="label.title"/>:
                </div>
                <div class="col-sm-4">
                    <form:input path="title" type="text" class="form-control"/>
                </div>
                <div class="col-sm-12">
                    <form:errors path="title" cssClass="text-danger"/>
                </div>
            </div>
        </spring:bind>
        <spring:bind path="content">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <div class="col-sm-12">
                    <spring:message code="label.content" var="content"/>
                    <form:textarea path="content" class="form-control content-textarea"
                                   placeholder="content"/>
                </div>
                <div class="col-sm-12">
                    <form:errors path="content" cssClass="text-danger"/>
                </div>
            </div>
        </spring:bind>
        <hr>
        <button class="btn btn-primary">
            <spring:message code="label.save"/>
        </button>
        <c:if test="${!empty images}">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <spring:message code="label.drag.and.drop.images"/>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <c:forEach var="image" items="${images}">
                            <div class="col-sm-3">
                                <img src="${image.fileUrl}" alt="" style="width:100%"/>
                                <a href="/posts/${post.id}/delete-attached-image/${image.id}">
                                    <button type="button" class="btn btn-danger"><i class="fa fa-trash"></i></button>
                                </a>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </c:if>
    </form:form>
</t:layout>
