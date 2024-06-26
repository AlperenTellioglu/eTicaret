<%@page import="eTicaret.admin.model.UrunForm"%>
<%@page import="java.util.List"%>
<%@page import="eTicaret.admin.model.Urun"%>
<%@page import="eTicaret.admin.model.Kategori"%>
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

<title>${urun == null ? 'Yeni Ürün Ekle' : 'Ürün Düzenle'} - Admin Paneli</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/admin.css">
</head>
<body class="admin-body">
	<%@ include file="/admin/navbar.jsp"%>
	<div class="container pt-4">
		<h1 class="display-6">${urun == null ? 'Yeni Ürün Ekle' : 'Ürün Düzenle'}</h1>
		<hr class="mb-4">

		<form action="/eTicaret/admin/product/" method="post">
			<input type="hidden" name="action"
				value="${urun == null ? 'insert' : 'edit'}"> <input
				type="hidden" name="urunId" value="${urun.getId()}">
				<div class="form-group">
				<label for="ad">Ad</label> <input type="text" name="ad"
					class="form-control" value="${urun.getAd()}">
			</div>
			<div class="form-group">
				<label for="ad">Açıklama</label> <input type="text" name="aciklama"
					class="form-control" value="${urun.getAciklama()}">
			</div>
			<div class="form-group">
				<label for="soyad">Fiyat</label> <input type="text" name="fiyat"
					class="form-control" value="${urun.getFiyat()}">
			</div>
			<div class="form-group">
				<label for="email">Stok Miktarı</label> <input type="text" name="stokMiktar"
					class="form-control" value="${urun.getStokMiktar()}">
			</div>
			<div class="form-group">
				<label for="kategoriId">Kategori</label><select
					class="form-select" id="kategoriId" name="kategoriId">
					<%
					UrunForm urunForm = (UrunForm) request.getAttribute("urunForm");
					Urun urun = (Urun) request.getAttribute("urun");
						
					for (Kategori kategori : urunForm.getKategoriler()) {
						boolean isSelected = false;
						if (urun != null) {
							isSelected = kategori.getId() == urun.getKategoriId();
						}
					%>
					<option value="<%=kategori.getId()%>" <%=isSelected ? "selected" : ""%>>[<%=kategori.getId()%>]
						<%=kategori.getAd()%>
					</option>
					<%
					}
					%>
				</select>
			</div>

			<div class="row my-5">
				<div class="col">
					<a class="btn btn-secondary" href="/eTicaret/admin/product/list">Geri</a>
				</div>
				<div class="col text-end">
					<button type="submit" class="btn btn-primary">${urun == null ? 'Kaydet' : 'Güncelle'}</button>
				</div>
			</div>

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