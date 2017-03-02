<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
     
    <title>Shopping Cart Confirmation</title>
    <link href="resources/css/bootstrap.min.css" rel="stylesheet">
     
</head>
<body>

   
<jsp:include page="../../tags/_header.jsp"></jsp:include>
 
  <fmt:setLocale value="en_US" scope="session"/>

<div class="w3-container w3-content" style="max-width:1400px;margin-top:80px">
    <!-- The Grid -->
    <div class="w3-row">
        <!-- Left Column -->
        <div class="w3-col m3">
            <div class="w3-card-2 w3-round w3-white">
                <div class="w3-container">
                    <h4 class="w3-center">Customer Information:</h4>
                    <p class="w3-center"><img src="../../../resources/images/default_avatar_male.jpg" class="w3-circle"
                                              style="height:106px;width:106px" alt="Avatar"></p>
                    <hr>
                    <p>
                        <i class="fa fa-pencil fa-fw w3-margin-right w3-text-theme"></i><b>Name: ${myCart.customerInfo.name}</b>
                    </p>
                    <p>
                        <i class="fa fa-pencil fa-fw w3-margin-right w3-text-theme"></i><b>Email: ${myCart.customerInfo.email}</b>
                    </p>
                    <p>
                        <i class="fa fa-pencil fa-fw w3-margin-right w3-text-theme"></i><b>Phone: ${myCart.customerInfo.phone}</b>
                    </p>
                    <p>
                        <i class="fa fa-pencil fa-fw w3-margin-right w3-text-theme"></i><b>Address: ${myCart.customerInfo.address}</b>
                    </p>
                </div>
            </div>
              
            <form method="POST" action="${pageContext.request.contextPath}/shoppingCartConfirmation">
                      <!-- Edit Cart -->
                      <a class="navi-item" href="${pageContext.request.contextPath}/shoppingCart">
                <input type="button" class="btn btn-info" value="Edit cart"/>
            </a>
                  <br>
                <br>
                      <!-- Edit Customer Info -->
                      <a class="navi-item" href="${pageContext.request.contextPath}/shoppingCartCustomer">
                <input type="button" class="btn btn-info" value="Edit Customer Info"/>
            </a>
                  <br>
                <br>
                      <!-- Send/Save -->
                      <input type="submit" value="Send" class="btn btn-success"/>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                  
            </form>
        </div>

        <!-- Middle Column -->
        <div class="w3-col m7">

            <div class="w3-row-padding">
                <div class="w3-col m12">
                    <div class="w3-card-2 w3-round w3-white">
                        <div class="w3-container w3-padding">
                             
                                  <c:forEach items="${myCart.cartLines}" var="cartLineInfo">
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
                                        <h4><b>Code:</b> ${cartLineInfo.productInfo.code}</h4>
                                        <h4><b>Name:</b> ${cartLineInfo.productInfo.name}</h4>
                                        <p><form:hidden
                                                path="cartForm.cartLines[${varStatus.index}].productInfo.code"/></p>
                                    </td>
                                    <td><h4><fmt:formatNumber value="${cartLineInfo.productInfo.price}"
                                                              type="currency"/></h4></td>
                                    <td>
                                        <h4>${cartLineInfo.quantity}</h4>
                                    </td>
                                    <td><h4><fmt:formatNumber value="${cartLineInfo.amount}" type="currency"/></h4></td>
                                </tr>
                                </tbody>
                            </table>
                                  </c:forEach>
                             
                              
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Right Column -->
        <div class="w3-col m2">
            <div class="w3-card-2 w3-round w3-white w3-center">
                <div class="w3-container">
                    <h3>Cart Summary:</h3>
                              
                    <li><b>Quantity: ${myCart.quantityTotal}</b></li>
                              
                    <li><b>Total:<span class="total"><fmt:formatNumber value="${myCart.amountTotal}"
                                                                       type="currency"/></span></b></li>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../../tags/_footer.jsp"/>

</body>
</html>