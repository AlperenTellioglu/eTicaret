<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
<title>Register</title>
</head>
<body>
	<h2>Register</h2>
	<form action="register" method="post">
		Ad: <input type="text" name="ad" required><br>
		Soyad: <input type="text" name="soyad" required><br>
		Email: <input type="text" name="email" required><br>
		Sifre: <input type="password" name="password" required><br>
		<input type="submit" value="Register">
	</form>
</body>
</html>