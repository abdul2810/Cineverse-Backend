<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
View Movies<br/>
<table border="1">
<tr>
<th>Movie Name</th>
<th>Director </th>
<th>Release Date</th>
<th>Language</th>
</tr>
<c:forEach items="${movies}" var="m">
<tr>
<td>${m.moviename}</td>
<td>${m.director}</td>
<td>${m.relesedate}</td>
<td>${m.language}</td>
<td><a href="updatemovie?id=${m.movieid}"> Update </a> | <a href="deletemovie?id=${m.movieid}"> Delete </a></td>
</tr>
</c:forEach>
</table>
</body>
</html>