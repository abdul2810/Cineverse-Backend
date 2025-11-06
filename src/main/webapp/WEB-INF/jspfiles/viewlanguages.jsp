<%@page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
   <%@page import="java.util.*" %>
   <html>
   <body>
 
<c:forEach items="${lang}" var="la">
<a href="viewmoviesbylang?lang=${la}"><c:out value="${la}" /> </a><br>

</c:forEach>
</body>
</html>