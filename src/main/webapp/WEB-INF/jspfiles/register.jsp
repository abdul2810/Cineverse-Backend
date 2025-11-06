<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<html>
<head>
<style>
  .error {
      color: #ff0000;
  }
</style>
</head>
<body>
<form action="insertUserr" method="post">
	Email:<input type="email" name="email"/><br><br>
	Name:<input type="text" name="name"/> <form:errors path = "user.name" cssClass = "error" /><br><br>
	Age:<input type="text" name="age"/><form:errors path = "user.age" cssClass = "error" /> <br><br>
	gender:<select name="gender">
	<option value="male">male</option>
	<option value="Female">Female</option>
	<option value="others">others</option>
	</select><br><br>
	Location:<input type="text" name="location"/><br><br>
	Password:<input type="text" name="password"/><br><br>
	<input type="submit" value="Register Here"/><br>
</form>
</body>
</html>

