<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
     
    <title>Access Denied</title>
     
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css">
     
</head>
<body>
 
 
   
<jsp:include page="../../tags/_header.jsp"></jsp:include>

<center>
    <h1 style="margin-top: 70px;">Access Denied!</h1>
      
       <h3 style="color:red;">Sorry, you can not access this page!</h3>
</center>

<jsp:include page="../../tags/_footer.jsp"></jsp:include>
 
</body>
</html>