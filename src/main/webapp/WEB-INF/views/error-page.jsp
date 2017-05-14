<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<t:layout>
    <div class="alert alert-danger">
        <h1>${status} ${reasonPhrase}</h1>
        <h2>${message}</h2>
    </div>
</t:layout>
