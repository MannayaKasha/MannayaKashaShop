<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
 
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="resources/css/bootstrap.min.css" rel="stylesheet">
     
    <title>Shopping Cart</title>
     
    <meta name="viewport" content="width=device-width, initial-scale=1">
     
</head>
<body>

   
<jsp:include page="../../tags/_header.jsp"></jsp:include>
  
   <fmt:setLocale value="en_US" scope="session"/>
 
<c:if test="${empty cartForm or empty cartForm.cartLines}">
      <center><h2 style="margin-top: 70px;">There is no items in Cart</h2>
      <a href="${pageContext.request.contextPath}/">Back to shopping</a></center>
</c:if>

<c:if test="${not empty cartForm and not empty cartForm.cartLines}">
    <form method="POST" modelAttribute="cartForm" action="${pageContext.request.contextPath}/shoppingCart">
        <div class="w3-row-padding" style="margin-top: 70px;">
            <div class="w3-col m12">
                <div class="w3-card-2 w3-round w3-white">
                    <div class="w3-container w3-padding">
                        <c:forEach items="${cartForm.cartLines}" var="cartLineInfo" varStatus="varStatus">
                            <table class="table table-responsive" style="width: 65%; margin: auto;">
                                <thead>
                                <tr>
                                    <th>Item</th>
                                    <th></th>
                                    <th>Price</th>
                                    <th>Quantity</th>
                                    <th>Subtotal</th>
                                    <th></th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td><img width="160" height="120"
                                             src="${pageContext.request.contextPath}/productImage?code=${cartLineInfo.productInfo.code}"
                                             alt=""></td>
                                    <td>
                                        <h4>Name: ${cartLineInfo.productInfo.name}</h4>
                                        <p><form:hidden
                                                path="cartForm.cartLines[${varStatus.index}].productInfo.code"/></p>
                                    </td>
                                    <td><fmt:formatNumber value="${cartLineInfo.productInfo.price}"
                                                          type="currency"/></td>
                                    <td><form:input
                                            path="cartForm.cartLines[${varStatus.index}].quantity"></form:input></td>
                                    <td><fmt:formatNumber value="${cartLineInfo.amount}" type="currency"/></td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/shoppingCartRemoveProduct?code=${cartLineInfo.productInfo.code}">
                                            <input type="button" class="btn btn-danger" value="Delete">
                                        </a>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </c:forEach>
                    </div>
                               
                    <div style="clear: both"></div>
                               <input class="btn btn-primary" type="submit" value="Update Quantity"/>
                               <a class="navi-item"
                                  href="${pageContext.request.contextPath}/shoppingCartCustomer"><input type="button"
                                                                                                        class="btn btn-success"
                                                                                                        value="Enter Customer Info"></a>
                               <a class="navi-item" href="${pageContext.request.contextPath}/"><input type="button"
                                                                                                      class="btn btn-info"
                                                                                                      value="Continue Buy"></a>
                    </div>
                </div>
            </div>
    </form>
</c:if>

<jsp:include page="../../tags/_footer.jsp"/>

</body>
</html>