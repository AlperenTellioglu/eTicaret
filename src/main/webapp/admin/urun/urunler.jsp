<%@page import="java.util.ArrayList"%>
<%@page import="eTicaret.admin.model.Urun"%>
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

<title>Ürünler - Admin Paneli</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/admin.css">
</head>
<body class="admin-body">
	<%@ include file="/admin/navbar.jsp"%>

	<div class="container pt-4">
		<h1 class="display-6">Ürünler</h1>
		<hr class="mb-4">

		<div class="row mb-4">
			<div class="col">
				<a class="btn btn-secondary" href="/eTicaret/admin/dashboard">Geri</a>
			</div>
						<div class="col">
				<div class="input-group mb-3">
					<input type="text" class="form-control"
						placeholder="Ürün ID, Ürün Adı, Kategori Adı"
						aria-label="Recipient's username" aria-describedby="basic-addon2">
					<div class="input-group-append">
						<button class="btn btn-outline-secondary" type="button">Ara</button>
					</div>
				</div>
			</div>
			<div class="col text-end">
				<a class="btn btn-primary" href="/eTicaret/admin/product/add">Ürün
					Ekle</a>
			</div>
		</div>

		<table class="table">
			<thead>
				<tr>
					<th scope="col">ID</th>
					<th scope="col">Ad</th>
					<th scope="col">Açıklama</th>
					<th scope="col">Fiyat</th>
					<th scope="col">Stok Miktarı</th>
					<th scope="col">Kategori</th>
					<th scope="col" class="text-end" style="width: 15%;"></th>
				</tr>
			</thead>
			<tbody>
				<%
				Object obj = request.getAttribute("urunler");
				if (obj instanceof ArrayList<?>) {
					List<?> objeler = (List<?>) obj;

					for (Object obje : objeler) {
						if (obje instanceof Urun) {
					Urun urun = (Urun) obje;
				%>
				<tr>
					<th scope="row" class="align-middle"><%=urun.getId()%></th>
					<td class="align-middle"><%=urun.getAd()%></td>
					<td class="align-middle"><%=urun.getAciklama()%></td>
					<td class="align-middle"><%=urun.getFiyat()%></td>
					<td class="align-middle"><%=urun.getStokMiktar()%></td>
					<td class="align-middle"><%=urun.getKategoriId()%></td>
					<td class="text-end">
						<form action="/eTicaret/admin/product/update" method="get"
							class="d-inline">
							<button type="submit" class="btn btn-warning edit-button">
								<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"
									fill="currentColor">
                            <path
										d="M3 21h18v2H3v-2zm3.89-2.49l2.29-.57 12.32-12.33a2.51 2.51 0 0 0 0-3.54l-.56-.56a2.51 2.51 0 0 0-3.54 0L5.59 14.33l-.57 2.29a1.25 1.25 0 0 0 1.87 1.87zm2.3-3.09L18.11 3.89a.75.75 0 0 1 1.06 0l.56.56a.75.75 0 0 1 0 1.06L7.81 15.11l-1.13.28.28-1.13z" />
                        </svg>
							</button>
							<input type="hidden" name="urunId"
								value="<%=urun.getId()%>">
						</form>
						<form action="/eTicaret/admin/product/" method="post"
							class="d-inline">
							<button type="submit" class="btn btn-danger delete-button">
								<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"
									fill="currentColor">
                            <path
										d="M16 9v10H8V9h8m-1.5-6h-5L9 4H5v2h14V4h-4l-.5-1zM18 8H6v12a2 2 0 0 0 2 2h8a2 2 0 0 0 2-2V8z" />
                        </svg>
							</button>
							<input type="hidden" name="action" value="delete"> <input
								type="hidden" name="urunId" value="<%=urun.getId()%>">

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