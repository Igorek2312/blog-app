<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<hr>
<div class="text-center">
    <span><spring:message code="or.sign.in.with.social"/></span>
</div>
<hr>
<div class="row">

        <div class="col-sm-2 col-sm-offset-2">
            <form:form action="/signin/facebook" method="POST">
                <input type="hidden" name="scope" value="public_profile"/>
                <button type="submit" class="btn btn-primary btn-lg">
                    <i class="fa fa-facebook"></i>
                </button>
            </form:form>
        </div>
        <div class="col-sm-2">
            <form:form action="/signin/google" method="POST">
                <input type="hidden" name="scope"
                       value="email https://www.googleapis.com/auth/plus.login https://www.googleapis.com/auth/plus.me "/>
                <input type="hidden" name="request_visible_actions"
                       value="http://schemas.google.com/AddActivity http://schemas.google.com/BuyActivity http://schemas.google.com/CheckInActivity http://schemas.google.com/CommentActivity http://schemas.google.com/CreateActivity http://schemas.google.com/DiscoverActivity http://schemas.google.com/ListenActivity http://schemas.google.com/ReserveActivity http://schemas.google.com/ReviewActivity http://schemas.google.com/WantActivity"/>
                <input type="hidden" name="access_type" value="offline"/>
                <button type="submit" class="btn btn-danger btn-lg">
                    <i class="fa fa-google"></i>
                </button>
            </form:form>
        </div>
        <div class="col-sm-2">
            <form:form action="/signin/twitter" method="POST">
                <input type="hidden" name="scope" value="public_profile"/>
                <button type="submit" class="btn btn-info btn-lg">
                    <i class="fa fa-twitter"></i>
                </button>
            </form:form>
        </div>
        <div class="col-sm-2">
            <form:form action="/signin/linkedin" method="POST">
                <input type="hidden" name="scope" value="r_basicprofile"/>
                <button type="submit" class="btn btn-primary btn-lg">
                    <i class="fa fa-linkedin"></i>
                </button>
            </form:form>
        </div>
</div>
