<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

        <button class="btn btn-primary">
            <spring:message code="label.save"/>
        </button>
    </form:form>
</t:layout>
