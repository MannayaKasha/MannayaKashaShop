<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<html>
<title>Mannaya Kasha Shop</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/resources/css/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
    .w3-sidenav a {
        font-family: "Roboto", sans-serif
    }

    body, h1, h2, h3, h4, h5, h6, .w3-wide {
        font-family: "Montserrat", sans-serif;
    }
</style>
<body class="w3-content" style="max-width:1200px">

<!-- Sidenav/menu -->
<nav class="w3-sidenav w3-white w3-collapse w3-top" style="z-index:3;width:250px" id="mySidenav">
    <div class="w3-container w3-padding-16">
        <i onclick="w3_close()" class="fa fa-remove w3-hide-large w3-closebtn w3-hover-text-red"></i>
        <h3 class="w3-wide"><b>LOGO</b></h3>
    </div>
    <form method="POST" modelAttribute="searchForm" action="${pageContext.request.contextPath}/">
                    <span class="form-group">
                        <input type="text" placeholder="Product name" class="form-control" name="name"
                               value="${cartLineInfo.productInfo.name}"/>
                    </span>
        <button type="submit" class="btn"><i class="fa fa-search"></i></button>
    </form>
    <div class="w3-padding-64 w3-large w3-text-grey" style="font-weight:bold">
        <a href="${pageContext.request.contextPath}/?category=shirts">Shirts</a>
        <a href="${pageContext.request.contextPath}/?category=jeans">Jeans</a>
        <a href="${pageContext.request.contextPath}/?category=shoes">Shoes</a>
    </div>
</nav>

<!-- Top menu on small screens -->
<header class="w3-container w3-top w3-hide-large w3-black w3-xlarge w3-padding-24">
    <span class="w3-left w3-wide">LOGO</span>
    <a href="javascript:void(0)" class="w3-right w3-opennav" onclick="w3_open()"><i class="fa fa-bars"></i></a>
</header>

<!-- Overlay effect when opening sidenav on small screens -->
<div class="w3-overlay w3-hide-large" onclick="w3_close()" style="cursor:pointer" title="close side menu"
     id="myOverlay"></div>

<!-- !PAGE CONTENT! -->
<div class="w3-main" style="margin-left:250px">

    <!-- Push down content on small screens -->
    <div class="w3-hide-large" style="margin-top:83px"></div>

    <!-- Top header -->
    <header class="w3-container w3-xlarge">
        <p class="w3-left">Jeans</p>
        <p class="w3-right">
            <c:if test="${pageContext.request.userPrincipal.name != null}">
                <span class="navbar-brand">
                    Hello, <a
                        href="${pageContext.request.contextPath}/accountInfo">${pageContext.request.userPrincipal.name}</a>
                    <a href="${pageContext.request.contextPath}/logout">
                        <i class="fa fa-sign-out" aria-hidden="true"></i> Logout
                    </a>
                </span>
            </c:if>
            <c:if test="${pageContext.request.userPrincipal.name == null}">
                <a href="${pageContext.request.contextPath}/login">Log in</a>
                <a href="${pageContext.request.contextPath}/login">Sign in</a>
            </c:if>
            <a href="${pageContext.request.contextPath}/shoppingCart">
                <i class="fa fa-shopping-cart w3-margin-right">
                    <span class="w3-badge w3-right w3-small w3-green">${cartForm.cartLines.size()}</span>
                </i>
            </a>
        </p>
    </header>

    <!-- Image header -->
    <div class="w3-display-container w3-container">
        <img src="../../resources/images/jeans.jpg" alt="Jeans" style="width:100%">
        <div class="w3-display-topleft w3-padding-xxlarge w3-text-white">
            <h1 class="w3-jumbo w3-hide-small">New arrivals</h1>
            <h1 class="w3-hide-large w3-hide-medium">New arrivals</h1>
            <h1 class="w3-hide-small">COLLECTION 2016</h1>
            <p><a href="#jeans" class="w3-btn w3-padding-large w3-large">SHOP NOW</a></p>
        </div>
    </div>


    <!-- Product grid -->
    <div class="w3-row"> <%-- w3-grayscale --%>
        <c:forEach items="${paginationProducts.list}" var="prodInfo">
            <div class="w3-col l3 s 6">
                <div class="w3-container">
                    <div class="w3-display-container">
                        <img src="${pageContext.request.contextPath}/productImage?code=${prodInfo.code}"
                             style="width:100%">
                        <c:if test="${prodInfo.novelty}">
                            <span class="w3-tag w3-display-topleft">New</span>
                        </c:if>
                        <div class="w3-display-middle w3-display-hover">
                            <a href="${pageContext.request.contextPath}/buyProduct?code=${prodInfo.code}">
                                <input type="button" class="w3-button w3-red" value="Buy now">
                            </a>
                        </div>
                        <div>Code: ${prodInfo.code}</div>
                        <div>Name: ${prodInfo.name}</div>
                        <div>Price: <fmt:formatNumber value="${prodInfo.price}" type="currency"/></div>
                        <div>Category: ${prodInfo.category.name}</div>
                        <security:authorize access="hasRole('ROLE_MANAGER')">
                            <div><a style="color:red;"
                                    href="${pageContext.request.contextPath}/product?code=${prodInfo.code}">Edit
                                Product</a></div>
                        </security:authorize>
                        <br>
                        <br>
                    </div>
                </div>
            </div>
        </c:forEach>
        <br>
        <c:if test="${paginationProducts.totalPages > 1}">
            <div class="page-navigator">
                <c:forEach items="${paginationProducts.navigationPages}" var="page">
                    <c:if test="${page != -1 }">
                        <a href="/?page=${page}" class="nav-item">
                                ${page}
                        </a>
                    </c:if>
                    <c:if test="${page == -1 }">
                        <span class="nav-item"> ... </span>
                    </c:if>
                </c:forEach>

            </div>
        </c:if>
    </div>

    <!-- Footer -->
    <footer class="w3-padding-64 w3-light-grey w3-small w3-center" id="footer">
        <div class="w3-row-padding">
            <div class="w3-col s4">
                <h4>Contact</h4>
                <p>Questions? Go ahead.</p>
                <form action="form.asp" target="_blank">
                    <p><input class="w3-input w3-border" type="text" placeholder="Name" name="Name" required></p>
                    <p><input class="w3-input w3-border" type="text" placeholder="Email" name="Email" required></p>
                    <p><input class="w3-input w3-border" type="text" placeholder="Subject" name="Subject" required></p>
                    <p><input class="w3-input w3-border" type="text" placeholder="Message" name="Message" required></p>
                    <button type="submit" class="w3-btn-block w3-padding w3-black">Send</button>
                </form>
            </div>

            <div class="w3-col s4">
                <h4>About</h4>
                <p><a href="#">About us</a></p>
                <p><a href="#">Support</a></p>
                <p><a href="#">Shipment</a></p>
                <p><a href="#">Payment</a></p>
                <p><a href="#">Gift card</a></p>
                <p><a href="#">Return</a></p>
                <p><a href="#">Help</a></p>
            </div>

            <div class="w3-col s4 w3-justify">
                <h4>Store</h4>
                <p><i class="fa fa-fw fa-map-marker"></i>Mannaya Kasha Shop</p>
                <p><i class="fa fa-fw fa-phone"></i> 364-536-9034</p>
                <p><i class="fa fa-fw fa-envelope"></i> mannayakasha@gmail.com</p>
                <br>
                <i class="fa fa-facebook-official w3-xlarge w3-hover-text-indigo"></i>
                <i class="fa fa-instagram w3-xlarge w3-hover-text-purple"></i>
                <i class="fa fa-twitter w3-xlarge w3-hover-text-light-blue"></i>
                <i class="fa fa-pinterest w3-xlarge w3-hover-text-red"></i>
                <i class="fa fa-flickr w3-xlarge w3-hover-text-blue"></i>
            </div>
        </div>
        <br>
        <br>
        <center>&copy;2017 Mannaya Kasha Shop. All rights reserved.</center>
    </footer>

    <!-- End page content -->
</div>
</body>
</html>
