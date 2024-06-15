<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">

<title>Hello, world!</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/admin.css">
</head>
<body class="admin-body">
	<%@ include file="/admin/navbar.jsp"%>
	<div class="container pt-4">
		<h1 class="display-6">${kullanici == null ? 'Yeni Kullanıcı Ekle' : 'Kullanıcı Düzenle'}</h1>
		<hr class="mb-4">
		
		<div class="row mb-4">
			<div class="col">
				<a class="btn btn-secondary" href="/eTicaret/admin/dashboard">Geri</a>
			</div>
			<div class="col text-end">
				<a class="btn btn-primary" href="/eTicaret/admin/user/add">Kullanıcı
					Ekle</a>
			</div>
		</div>

		<form action="/eTicaret/admin/user/" method="post">
			<input type="hidden" name="action"
				value="${kullanici == null ? 'insert' : 'edit'}"> <input
				type="hidden" name="kullanici_id" value="${kullanici.id}">
			<div class="form-group">
				<label for="firstName">Ad</label> <input type="text" name="ad"
					class="form-control" value="${kullanici.ad}">
			</div>
			<div class="form-group">
				<label for="lastName">Soyad</label> <input type="text" name="soyad"
					class="form-control" value="${kullanici.soyad}">
			</div>
			<div class="form-group">
				<label for="email">Email</label> <input type="email" name="email"
					class="form-control" value="${kullanici.email}">
			</div>
			<div class="form-group">
				<label for="password">Şifre</label> <input type="password"
					name="password" class="form-control" value="${kullanici.password}">
			</div>
			<button type="submit" class="btn btn-primary">${kullanici == null ? 'Kaydet' : 'Güncelle'}</button>
		</form>
	</div>

	<!-- Optional JavaScript; choose one of the two! -->

	<!-- Option 1: Bootstrap Bundle with Popper -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>

	<!-- Option 2: Separate Popper and Bootstrap JS -->
	<!--
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
    -->
</body>
</html>