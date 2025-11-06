<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<body>
<form action="updatemoviedetails" method="post">
MovieName:<input type="text" name="mname" value="${movie.moviename}" /><br><br>
DirectorName:<input type="text" name="director" value="${movie.director}" /><br><br>
Language:<select name="lang" value="${movie.language}">
<option value="Tamil">Tamil</option>
<option value="English">English</option>
<option value="Hindi">Hindi</option>
</select><br><br>
Relesed Date:<input type="date" name="rdate" value="${movie.relesedate}" /><br><br>
<input type="hidden" name="movieid" value="${movie.movieid}" />
<input type="submit" value="updatemovied Here"/><br>
</form>
</body>
</html>