<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<body>
<form action="bookingmoviedetails" method="post">

  <!-- Movie details -->
  <input type="hidden" name="movieid" value="${movies.movieid}" /><br><br>

  cusname:
  <input type="text" name="cusname" /><br><br>

  mobilenumber:
  <input type="text" name="mobilenumber" /><br><br>

  numberofticket:
  <input type="text" name="numberofticket" /><br><br>

  bookdate:
  <input type="date" name="bookdate" /><br><br>

  showdate:
  <input type="date" name="showdate" /><br><br>

  moviename:
  <input type="text" name="moviename" value="${movies.moviename}" /><br><br>

  <!-- Hidden booking ID (not used for new) -->
  <input type="hidden" name="bookingid" /><br><br>

  <!-- ✅ NEW: Pass user email to backend -->
  <input type="hidden" name="useremail" value="${sessionScope.user.email}" />

  <input type="submit" value="Book Movie Here" /><br>

</form>
</body>
</html>
