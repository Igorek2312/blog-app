<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal fade" id="login-modal" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">
                    <spring:message code="label.log.in.with.username.and.password"/>
                </h4>
            </div>
            <div class="modal-body">
                <c:if test="${param.logged_out!=null}">
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
                <jsp:include page="social-sign-in.jsp"/>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

