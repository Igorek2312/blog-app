<%@tag pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!doctype html>
<html>
<head>
    <title>Blog app</title>
    <link rel="stylesheet" href="css/main.css" type="text/css">
    <link type="text/css" rel="stylesheet" href="webjars/bootstrap/3.3.7-1/css/bootstrap.min.css">
    <link type="text/css" rel="stylesheet" href="webjars/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>

<nav class=" navbar navbar-default
    " role="navigation">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="/">Blog app</a>
    </div>

    <div class="collapse navbar-collapse navbar-ex1-collapse">
        <ul class="nav navbar-nav">
            <sec:authorize access="isAnonymous()">
                <li class="<%=request.getRequestURI().startsWith("/login")?"active":""%>">
                    <a href="/login"><spring:message code="label.log.in"/></a>
                </li>
                <li class="<%=request.getRequestURI().startsWith("/sign-up")?"active":""%>">
                    <a href="/sign-up"><spring:message code="label.sign.up"/></a>
                </li>
            </sec:authorize>
            <li class="<%=request.getRequestURI().startsWith("/about")?"active":""%>">
                <a href="/about"><spring:message code="label.about"/></a>
            </li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <sec:authorize access="isAuthenticated()">
                <li class="<%=request.getRequestURI().startsWith("/profile")?"active":""%>">
                    <spring:message code="label.profile" var="profile"/>
                    <a href="/profile" data-toggle="tooltip" title="${profile}">
                        <i class="fa fa-user"></i>
                        <sec:authentication property="principal.firstName"/>
                        <sec:authentication property="principal.lastName"/>
                    </a>
                </li>
                <li>
                    <a href="/logout"><spring:message code="label.log.out"/></a>
                </li>
            </sec:authorize>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    <c:set var="localeCode" value="${pageContext.response.locale}"/>
                    <c:choose>
                        <c:when test="${localeCode == 'uk'}">
                            <img src="/images/uk.png" alt="">
                        </c:when>
                        <c:otherwise>
                            <img src="/images/en.png" alt="">
                        </c:otherwise>
                    </c:choose>
                    <b class="caret"></b>
                </a>
                <ul class="dropdown-menu">
                    <li><a href="?lang=en"><img src="images/en.png" alt=""></a></li>
                    <li><a href="?lang=uk"><img src="images/uk.png" alt=""></a></li>
                </ul>
            </li>
        </ul>
    </div>
</nav>

<div class="container">
    <jsp:doBody/>
</div>

<script src="webjars/jquery/1.11.1/jquery.min.js"></script>
<script src="webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>

</body>
</html>

