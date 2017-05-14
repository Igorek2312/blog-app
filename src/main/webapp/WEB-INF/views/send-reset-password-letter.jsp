<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<t:layout>
    <div class="col-sm-6 col-sm-offset-3">
        <div class="panel panel-default">
            <div class="panel-body">
                <c:if test="${param.letter_sent!=null}">
                    <div class="alert alert-info">
                        <spring:message code="label.reset.password.letter.is.sent"/>
                    </div>
                </c:if>
                <form:form modelAttribute="sendResetPasswordLetterForm" action="/send-reset-password-letter"
                           method="post" class="form-horizontal" role="form" novalidate="true">
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

                    <div class="form-group">
                        <div class="col-sm-12">
                            <button type="submit" class="btn btn-primary">
                                <spring:message code="label.send.reset.password.letter"/>
                            </button>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</t:layout>
