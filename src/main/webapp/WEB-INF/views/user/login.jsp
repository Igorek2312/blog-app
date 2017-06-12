<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<t:layout>
    <div class="row">
        <div class="col-sm-6 text-center col-sm-offset-3">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">
                        <spring:message code="label.log.in.with.username.and.password"/>
                    </h3>
                </div>
                <div class="panel-body">
                    <c:if test="${param.logout!=null}">
                        <div class="alert alert-info">
                            <spring:message code="label.logged.out"/>
                        </div>
                    </c:if>
                    <c:if test="${param.letter_sent!=null}">
                        <div class="alert alert-info">
                            <spring:message code="label.letter.sent"/>
                        </div>
                    </c:if>
                    <c:if test="${param.signed_up!=null}">
                        <div class="alert alert-info">
                            <spring:message code="label.signed.up"/>
                        </div>
                    </c:if>
                    <c:if test="${param.password_reset!=null}">
                        <div class="alert alert-info">
                            <spring:message code="label.password.is.reset"/>
                        </div>
                    </c:if>
                    <c:if test="${param.error!=null}">
                        <div class="alert alert-danger">
                            <spring:message code="label.wrong.username.or.password"/>
                        </div>
                    </c:if>
                    <form:form action="/j_spring_security_check" method="post" class="form-horizontal">
                        <div class="form-group">
                            <div class="col-sm-12">
                                <spring:message code="label.username" var="username"/>
                                <input name="username" type="text" class="form-control"
                                       placeholder="${username}">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-12">
                                <spring:message code="label.password" var="password"/>
                                <input name="password" type="password" class="form-control"
                                       placeholder="${password}">
                            </div>
                        </div>
                    <div class="col-sm-12">
                        <div class="text-center">
                            <a href="/send-reset-password-letter">
                                <spring:message code="label.forgot.password"/>
                            </a>
                        </div>
                    </div>
                        <button type="submit" class="btn btn-primary">
                            <spring:message code="label.log.in"/>
                        </button>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</t:layout>


