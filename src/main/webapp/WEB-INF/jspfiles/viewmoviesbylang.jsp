<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
View Movies<br>
<table>
<tr>
<th>Movie Name</th>
<th>Director </th>
<th>Release Date</th>
<th>Language</th>
</tr>
<c:forEach items="${movies}" var="m">
<tr>
<td>${m.movieName}</td>
<td>${m.director}</td>
<td>${m.releaseDate}</td>
<td>${m.language}</td>
</tr>
</c:forEach>
</table>
</body>
</html>