<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<link rel="stylesheet" href="resources/css/w3.css">

<meta charset="utf-8">

<div class="w3-top">
    <ul class="w3-navbar w3-theme-d2 w3-left-align w3-large">
        <li><a href="/" class="w3-padding-large w3-theme-d4"><i class="fa fa-home w3-margin-right"></i>Mannaya Kasha
            Shop</a></li>
        <li class="w3-hide-small"><a href="${pageContext.request.contextPath}/shoppingCart"
                                     class="w3-padding-large w3-hover-white" title="Messages">My Cart</a></li>
        <li class="w3-hide-small w3-dropdown-hover">
            <security:authorize access="hasRole('ROLE_MANAGER')">
                <a href="#" class="w3-padding-large w3-hover-white" title="Notifications">Options</a>
                <div class="w3-dropdown-content w3-white w3-card-4">
                    <a href="${pageContext.request.contextPath}/product">Create Product</a>
                    <a href="${pageContext.request.contextPath}/orderList">Order List</a>
                </div>
            </security:authorize>
        </li>
    </ul>
</div>

