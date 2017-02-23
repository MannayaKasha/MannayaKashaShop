<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head><title><spring:message code="user.title"/></title></head>
<body>
<a href="user?mylocale=en">English </a> | <a href="user?mylocale=de">German </a>
<h3> <spring:message code="user.title"/></h3>
</body>
</html>