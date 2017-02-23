<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
 
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
     
     
</head>
<body>
 
 
   
<jsp:include page="../../tags/_header.jsp"></jsp:include>

<!-- Profile -->
<div class="w3-container w3-content" style="max-width:1400px;margin-top:80px">
    <!-- The Grid -->
    <div class="w3-row">
        <!-- Left Column -->
        <div class="w3-col m3">
            <br>
        </div>
        <!-- Middle Column -->
        <div class="w3-col m7">
            <div class="w3-card-2 w3-round w3-white">
                <div class="w3-container">
                    <h4 class="w3-center"><b>My Profile</b></h4>
                    <p class="w3-center"><img src="../../../resources/images/default_avatar_male.jpg" class="w3-circle"
                                              style="height:106px;width:106px" alt="Avatar"></p>
                    <hr>
                    <center>
                        <p>
                            <b>${pageContext.request.userPrincipal.name}</b>
                        </p>
                        <p>
                            <i class="fa fa-home fa-fw w3-margin-right w3-text-theme"></i>
                            <c:forEach items="${userDetails.authorities}" var="auth">
                                <b>${auth.authority }</b>
                                    </c:forEach>
                        </p>
                    </center>
                </div>
            </div>
        </div>
    </div>
</div>
 
<jsp:include page="../../tags/_footer.jsp"/>
 
</body>
</html>