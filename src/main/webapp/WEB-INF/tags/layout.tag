<%@tag pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!doctype html>
<html>
<head>
    <title>Blog app</title>
    <link type="text/css" rel="stylesheet" href="/webjars/bootstrap/3.3.7-1/css/bootstrap.min.css">
    <link type="text/css" rel="stylesheet" href="/webjars/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="/css/main.css" type="text/css">
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
        <ul class="nav navbar-nav navbar-right">
            <sec:authorize access="hasRole('ROLE_USER')">
                <li class="<%=request.getRequestURI().equals("/WEB-INF/views/post/user-posts.jsp")?"active":""%>">
                    <a href="/my-posts">
                        <spring:message code="label.my.posts"/>
                    </a>
                </li>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <li class="<%=request.getRequestURI().equals("/WEB-INF/views/user/profile.jsp")?"active":""%>">
                    <spring:message code="label.profile" var="profile"/>
                    <a href="/my-profile" data-toggle="tooltip" title="${profile}">
                        <i class="fa fa-user"></i>
                        <sec:authentication property="principal.fullName"/>
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
                    <li><a href="?lang=en"><img src="/images/en.png" alt=""></a></li>
                    <li><a href="?lang=uk"><img src="/images/uk.png" alt=""></a></li>
                </ul>
            </li>
        </ul>
        <ul class="nav navbar-nav">
            <sec:authorize access="isAnonymous()">
                <li class="<%=request.getRequestURI().equals("/WEB-INF/views/user/login.jsp")?"active":""%>">
                    <a href="/login"><spring:message code="label.log.in"/></a>
                </li>
                <li class="<%=request.getRequestURI().equals("/WEB-INF/views/user/sign-up.jsp")?"active":""%>">
                    <a href="/sign-up"><spring:message code="label.sign.up"/></a>
                </li>
            </sec:authorize>
            <li class="<%=request.getRequestURI().equals("/WEB-INF/views/about.jsp")?"active":""%>">
                <a href="/about"><spring:message code="label.about"/></a>
            </li>
        </ul>
    </div>
</nav>

<div class="container">
    <jsp:doBody/>
</div>

<script src="/webjars/jquery/1.11.1/jquery.min.js"></script>
<script src="/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
<script src="/webjars/momentjs/2.18.1/min/moment.min.js"></script>
<script src="/webjars/tinymce/4.5.6/tinymce.min.js"></script>
<%--<script src="/webjars/jquery-file-upload/9.10.1/js/jquery.fileupload.js"></script>--%>

<script src="/js/utc-converter.js"></script>
<script src="/js/tiny-mce-config.js"></script>
</body>
</html>

