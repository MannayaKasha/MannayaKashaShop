<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
 
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link href="resources/css/bootstrap.min.css" rel="stylesheet">
    <title>Product</title>
     
</head>
<body>
 
   
<jsp:include page="../tags/_header.jsp"></jsp:include>
  
   <c:if test="${not empty errorMessage }">
         <div class="error-message">
             ${errorMessage}
         </div>
       </c:if>

<form:form modelAttribute="productForm" method="POST"> <%--enctype="multipart/form-data"--%>
    <div class="container text-center">
        <h3 style="margin-top: 70px;">Manage Product</h3>
        <hr>
        <div class="form-group">
            <label class="control-label col-md-3">Code</label>
            <div class="col-md-7">
                                  <c:if test="${not empty productForm.code}">
                                       <hidden path="code"/>
                                       ${productForm.code}
                                  </c:if>
                                  <c:if test="${empty productForm.code}">
                                       <%--<form:input path="code"/>--%>
                <input type="text" class="form-control" name="code" value="${productForm.code}"/>
                                       <hidden path="newProduct"/>
                                  </c:if>
                <form:errors path="code" class="error-message"/>
            </div>
        </div>
        <br>
        <br>
        <br>
        <br>
        <div class="form-group">
            <label class="control-label col-md-3">Name</label>
            <div class="col-md-7">
                <input type="text" class="form-control" name="name" value="${productForm.name}"/>
                <form:errors path="name" class="error-message"/>
            </div>
        </div>
        <br>
        <br>
        <br>
        <div class="form-group">
            <label class="control-label col-md-3">Price</label>
            <div class="col-md-7">
                <input type="text" class="form-control" name="price" value="${productForm.price}"/>
                <form:errors path="price" class="error-message"/>
            </div>
        </div>
        <br>
        <br>
        <div class="form-group">
            <label class="control-label col-md-3">Novelty</label>
            <div class="col-md-7">
                <c:if test="${productForm.novelty == true}">
                    <input type="radio" class="col-sm-1" name="novelty" value="true" checked/>
                    <div class="col-sm-1">Yes</div>
                    <input type="radio" class="col-sm-1" name="novelty" value="false"/>
                    <div class="col-sm-1">No</div>
                </c:if>
                <c:if test="${productForm.novelty == false}">
                    <input type="radio" class="col-sm-1" name="novelty" value="true"/>
                    <div class="col-sm-1">Yes</div>
                    <input type="radio" class="col-sm-1" name="novelty" value="false" checked/>
                    <div class="col-sm-1">No</div>
                </c:if>
            </div>
        </div>
        <br>
        <br>
        <div class="form-group">
            <label class="control-label col-md-3">Category</label>
            <div class="col-md-7">
                <input type="text" class="form-control" name="category.name" value="${productForm.category.name}"/>
            </div>
        </div>
        <br>
        <br>
        <div class="form-group">
            <label class="control-label col-md-3">Image</label>
            <div class="col-md-7">
                <img src="${pageContext.request.contextPath}/productImage?code=${productForm.code}" width="100"/>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-md-3">Upload Image</label>
            <div class="col-md-7">
                <input type="file" path="fileData"/>
            </div>
        </div>
        <div class="form-group">
            <div class="col-md-7">
                <br>
                <input type="submit" class="btn btn-success" value="Submit"/>
                <input type="reset" class="btn btn-info" value="Reset"/>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </div>
        </div>
    </div>
</form:form>
<script src="resources/js/jquery-1.11.1.min.js"></script>
<script src="resources/js/bootstrap.min.js"></script>

<jsp:include page="../tags/_footer.jsp"/>
 
</body>
</html>