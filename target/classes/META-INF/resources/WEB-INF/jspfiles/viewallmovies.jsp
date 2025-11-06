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
<c:forEach items="${movies}" var="s">
<tr>
<td>${s.moviename}</td>
<td>${s.director}</td>
<td>${s.relesedate}</td>
<td>${s.language}</td>
<td><a href="bookmovies?id=${s.movieid}"> bookmovie </a> 
</tr>
</c:forEach>
</table>
</body>
</html>