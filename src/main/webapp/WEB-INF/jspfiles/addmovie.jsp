<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<body>
<form action="insertMovie" method="post">
MovieName:<input type="text" name="mname"/><br><br>
DirectorName:<input type="text" name="director"/><br><br>
Language:<select name="lang">
<option value="Tamil">Tamil</option>
<option value="English">English</option>
<option value="Hindi">Hindi</option>
</select><br><br>
Relesed Date:<input type="date" name="rdate"/><br><br>
<input type="submit" value="AddMovie Here"/><br>
</form>
</body>
</html>