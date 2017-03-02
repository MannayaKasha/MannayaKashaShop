<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
     
    <title>Login</title>
     
</head>
<body>

<jsp:include page="../tags/_header.jsp"></jsp:include>

<div class="w3-container w3-white w3-center" style="margin-top: 70px;">
    <h2>Log in</h2>
           <c:if test="${param.error == 'true'}">
               <div style="color: red; margin: 10px 0px;">Login Failed!!!<br> Reason : ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</div>
           </c:if>
    <form method="POST" action="${pageContext.request.contextPath}/j_spring_security_check">
        <div><input style="width: 200px; margin: auto;" name="userName" class="w3-input w3-border" type="text"
                    placeholder="Username"></div>
        <br>
        <div><input style="width: 200px; margin: auto" name="password" class="w3-input w3-border" type="password"
                    placeholder="Password"></div>
        <br>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <button value="Login" type="submit" class="w3-btn w3-padding-large w3-red w3-margin-bottom">Log in</button>
        <button value="Reset" type="reset" class="w3-btn w3-padding-large w3-red w3-margin-bottom">Reset</button>
    </form>
    <span class="error-message">${error}</span>
</div>

<jsp:include page="../tags/_footer.jsp"/>

</body>
</html>