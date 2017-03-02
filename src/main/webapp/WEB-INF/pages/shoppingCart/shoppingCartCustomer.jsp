<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
 
 
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
     
    <title>Enter Customer Information</title>
     
</head>
<body>

   
<jsp:include page="../../tags/_header.jsp"></jsp:include>
 
<div class="w3-container w3-white w3-center" style="margin-top: 70px;">
       
    <form method="POST" modelAttribute="customerForm" action="${pageContext.request.contextPath}/shoppingCartCustomer">

        <div><form:input path="customerForm.name" style="width: 200px; margin: auto;" name="customerForm.name"
                         class="w3-input w3-border" type="text" placeholder="Name"></form:input></div>
        <div><form:errors path="customerForm.name" class="error-message"/></div>
        <br>
        <div><form:input path="customerForm.email" style="width: 200px; margin: auto;" name="customerForm.email"
                         class="w3-input w3-border" type="text" placeholder="Email"></form:input></div>
        <div><form:errors path="customerForm.email" class="error-message"/></div>
        <br>
        <div><form:input path="customerForm.phone" style="width: 200px; margin: auto;" name="customerForm.phone"
                         class="w3-input w3-border" type="text" placeholder="Phone"></form:input></div>
        <div><form:errors path="customerForm.phone" class="error-message"/></div>
        <br>
        <div><form:input path="customerForm.address" style="width: 200px; margin: auto;" name="customerForm.address"
                         class="w3-input w3-border" type="text" placeholder="Address"></form:input></div>
        <div><form:errors path="customerForm.address" class="error-message"/></div>
        <br>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <button value="Submit" type="submit" class="w3-btn w3-padding-large w3-red w3-margin-bottom">Submit</button>
        <button value="Reset" type="reset" class="w3-btn w3-padding-large w3-red w3-margin-bottom">Reset</button>

    </form>
</div>

<jsp:include page="../../tags/_footer.jsp"/>

</body>
</html>