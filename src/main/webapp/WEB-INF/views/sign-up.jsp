<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<t:layout>
    <div class="row">
        <div class="col-sm-6 col-lg-offset-3">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <spring:message code="label.fill.in.fields"/>
                </div>
                <div class="panel-body">
                    <form:form modelAttribute="user" action="/sign-up" method="post" role="form"
                               class="form-horizontal" novalidate="">
                        <spring:bind path="username">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <div class="col-sm-12">
                                    <spring:message code="label.username" var="username"/>
                                    <form:input path="username" type="text" class="form-control"
                                                placeholder="${username}"/>
                                </div>
                                <div class="col-sm-12">
                                    <form:errors path="username" cssClass="text-danger"/>
                                </div>
                            </div>
                        </spring:bind>
                        <spring:bind path="email">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <div class="col-sm-12">
                                    <form:input path="email" type="email" class="form-control" placeholder="Email"/>
                                </div>
                                <div class="col-sm-12">
                                    <form:errors path="email" cssClass="text-danger"/>
                                </div>
                            </div>
                        </spring:bind>
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
                                    <form:input path="confirmedPassword" type="password" class="form-control"
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
                        <hr>
                        <spring:bind path="firstName">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <div class="col-sm-12">
                                    <spring:message code="label.first.name" var="firstName"/>
                                    <form:input path="firstName" type="text" class="form-control"
                                                placeholder="${firstName}"/>
                                </div>
                                <div class="col-sm-12">
                                    <form:errors path="firstName" cssClass="text-danger"/>
                                </div>
                            </div>
                        </spring:bind>
                        <spring:bind path="lastName">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <div class="col-sm-12">
                                    <spring:message code="label.last.name" var="lastName"/>
                                    <form:input path="lastName" type="text" class="form-control"
                                                placeholder="${lastName}"/>
                                </div>
                                <div class="col-sm-12">
                                    <form:errors path="lastName" cssClass="text-danger"/>
                                </div>
                            </div>
                        </spring:bind>
                        <button type="submit" class="btn btn-primary">
                            <spring:message code="label.sign.up"/>
                        </button>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</t:layout>

