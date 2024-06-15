<%@page import="java.util.ArrayList"%>
<%@page import="eTicaret.admin.model.Kullanici"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!doctype html>
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
<style type="text/css">
.darker {
	background-color: #202022;
}

.darkest {
	background-color: #141415
}
</style>
</head>
<body class="darkest text-white">
	<header>
		<nav class="navbar navbar-expand-lg navbar-dark darker">
			<div class="container-fluid">
				<a class="navbar-brand" href="#">Dashboard</a>
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav me-auto mb-2 mb-lg-0">
						<li class="nav-item"><a class="nav-link" aria-current="page"
							href="/eTicaret/admin/dashboard">Anasayfa</a></li>
						<li class="nav-item"><a class="nav-link"
							href="/eTicaret/admin/product/list">Ürünler</a></li>
						<li class="nav-item"><a class="nav-link"
							href="/eTicaret/admin/category/list">Kategoriler</a></li>
						<li class="nav-item"><a class="nav-link active"
							href="/eTicaret/admin/user/list">Kullanıcılar</a></li>
					</ul>
					<a class="btn btn-outline-light" href="#">Çıkış</a>
				</div>
			</div>
		</nav>
	</header>

	<div class="container pt-4">
		<h1 class="display-6">Kullanıcılar</h1>
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

		<table class="table table-dark">
			<thead>
				<tr>
					<th scope="col">ID</th>
					<th scope="col">Ad</th>
					<th scope="col">Soyad</th>
					<th scope="col">Email</th>
					<th scope="col">Şifre</th>
					<th scope="col"></th>
				</tr>
			</thead>
			<tbody>
				<%
				Object obj = request.getAttribute("kullanicilar");
				if (obj instanceof ArrayList<?>) {
					List<?> objeler = (List<?>) obj;

					for (Object obje : objeler) {
						if (obje instanceof Kullanici) {
					Kullanici kullanici = (Kullanici) obje;
				%>
				<tr>
					<th scope="row"><%=kullanici.getId()%></th>
					<td><%=kullanici.getAd()%></td>
					<td><%=kullanici.getSoyad()%></td>
					<td><%=kullanici.getEmail()%></td>
					<td><%=kullanici.getPassword()%></td>
					<td>
						<form action="/eTicaret/admin/user/update" method="get"
							class="d-inline">
							<input type="hidden" name="kullanici_id"
								value="<%=kullanici.getId()%>"> <input type="submit"
								class="btn btn-warning" value="Düzenle">
						</form>
						<form action="/eTicaret/admin/user/"
							method="post" class="d-inline">
							<input type="hidden" name="action" value="delete"> <input
								type="hidden" name="kullanici_id" value="<%=kullanici.getId()%>">
							<input type="submit" class="btn btn-danger" value="Sil">
						</form>
					</td>
				</tr>
				<%
				}
				}
				}
				%>
			</tbody>
		</table>

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