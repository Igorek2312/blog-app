<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<t:layout>
    <div class="col-sm-5">
        <label for="profile-image" class="thumbnail">
            <img src="${user.imageUrl}" alt="no image">
        </label>
        <input id="profile-image" style="display:none;" type="file"/>
    </div>
    <div class="col-sm-5">
        <form:form modelAttribute="user" action="/update-email" method="post" cssClass="form-horizontal">
            <spring:bind path="email">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <div class="col-sm-3">
                        Email:
                    </div>
                    <div class="col-sm-9">
                        <form:input path="email" type="text" class="form-control"/>
                    </div>
                    <div class="col-sm-12">
                        <form:errors path="email" cssClass="text-danger"/>
                    </div>
                </div>
            </spring:bind>
            <button class="btn btn-primary">
                <spring:message code="label.save"/>
            </button>
        </form:form>
        <hr>
        <form:form modelAttribute="user" action="/update-profile" method="post" cssClass="form-horizontal">
            <spring:bind path="firstName">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <div class="col-sm-3">
                        <spring:message code="label.first.name"/>
                    </div>
                    <div class="col-sm-9">
                        <form:input path="firstName" type="text" class="form-control"/>
                    </div>
                    <div class="col-sm-12">
                        <form:errors path="firstName" cssClass="text-danger"/>
                    </div>
                </div>
            </spring:bind>
            <spring:bind path="lastName">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <div class="col-sm-3">
                        <spring:message code="label.last.name"/>
                    </div>
                    <div class="col-sm-9">
                        <form:input path="lastName" type="text" class="form-control"/>
                    </div>
                    <div class="col-sm-12">
                        <form:errors path="lastName" cssClass="text-danger"/>
                    </div>
                </div>
            </spring:bind>

            <button class="btn btn-primary">
                <spring:message code="label.save"/>
            </button>
        </form:form>
    </div>
</t:layout>
