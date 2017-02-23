<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
     
    <title>Shopping Cart Finalize</title>
     
</head>
<body>

   
<jsp:include page="../../tags/_header.jsp"></jsp:include>
  
       
<center style="margin-top: 70px;">
    <h2>Thank you for Order</h2>
    <h4>Your order number is: ${lastOrderedCart.orderNum}</h4>
</center>

<jsp:include page="../../tags/_footer.jsp"/>
 
</body>
</html>