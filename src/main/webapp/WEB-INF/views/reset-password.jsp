<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<t:layout>
    <div class="col-sm-6 col-sm-offset-3"></div>
    <div class="panel panel-default">
        <div class="panel-body">
            <form:form modelAttribute="resetPasswordForm" action="/reset-password" method="post" class="form-horizontal" role="form" novalidate="">
                <spring:bind path="password">
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <div class="col-sm-12">
                            <spring:message code="label.password" var="password"/>
                            <form:input path="password" type="password" class="form-control"
                                        placeholder="${password}"/>
                        </div>
                    </div>
                    <div class="form-group ${status.error ? 'has-error' : ''}">
                        <div class="col-sm-12">
                            <spring:message code="label.confirm.passwrod" var="confirmPassword"/>
                            <form:input path="confirmPassword" type="password" class="form-control"
                                        placeholder="${confirmPassword}"/>
                        </div>
                        <spring:hasBindErrors name="signUpForm">
                            <c:forEach var="error" items="${errors.globalErrors}">
                                <div class="col-sm-12">
                                    <span class="text-danger"><spring:message message="${error}"/></span>
                                </div>
                            </c:forEach>
                        </spring:hasBindErrors>
                        <div class="col-sm-12">
                            <form:errors path="password" cssClass="text-danger"/>
                        </div>
                    </div>
                </spring:bind>
                <form:input path="resetKey" value="${param.key}" type="hidden" class="form-control"/>
                <div class="form-group">
                    <div class="col-sm-12">
                        <button type="submit" class="btn btn-primary">Submit</button>
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</t:layout>