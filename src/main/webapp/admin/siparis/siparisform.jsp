<%@page import="eTicaret.admin.model.Siparis"%>
<%@page import="eTicaret.admin.model.Odeme"%>
<%@page import="eTicaret.admin.model.Urun"%>
<%@page import="eTicaret.admin.model.SiparisForm"%>
<%@page import="eTicaret.admin.model.Kullanici"%>
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

<title>${siparis == null ? 'Yeni Sipariş Ekle' : 'Sipariş Düzenle'}
	- Admin Paneli</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/admin.css">
</head>
<body class="admin-body">
	<%@ include file="/admin/navbar.jsp"%>
	<div class="container pt-4">
		<h1 class="display-6">${siparis == null ? 'Yeni Sipariş Ekle' : 'Sipariş Düzenle'}</h1>
		<hr class="mb-4">

		<form action="/eTicaret/admin/order/" method="post">
			<input type="hidden" name="action"
				value="${siparis == null ? 'insert' : 'edit'}"> <input
				type="hidden" name="siparisId" value="${siparis.getId()}">
			<div class="form-group">
				<label for="kullanici_id">Kullanıcı</label> <select
					class="form-select" id="kullanici_id" name="kullanici_id">
					<%
					SiparisForm siparisForm = (SiparisForm) request.getAttribute("siparisForm");
					Siparis siparis = (Siparis) request.getAttribute("siparis");
						
					for (Kullanici k : siparisForm.getKullanicilar()) {
						boolean isSelected = false;
						if (siparis != null) {
							isSelected = k.getId() == siparis.getKullaniciId();
						}
					%>
					<option value="<%=k.getId()%>" <%=isSelected ? "selected" : ""%>>[<%=k.getId()%>]
						<%=k.getAd()%>
						<%=k.getSoyad()%>
					</option>
					<%
					}
					%>
				</select>
			</div>
			<div class="form-group">
				<label for="urunId">Urun</label> <select class="form-select"
					id="urunId" name="urunId">
					<%
					for (Urun urun : siparisForm.getUrunler()) {
						boolean isSelected = false;
						if (siparis != null) {
							isSelected = urun.getId() == siparis.getUrunId();
						}
					%>
					<option value="<%=urun.getId()%>" <%=isSelected ? "selected" : ""%>>[<%=urun.getId()%>]
						<%=urun.getAd()%></option>
					<%
					}
					%>
				</select>
			</div>
			<div class="form-group">
				<label for="odemeId">Odeme</label> <select class="form-select"
					id="odemeId" name="odemeId">
					<%
					for (Odeme odeme : siparisForm.getOdemeler()) {
						boolean isSelected = false;
						if (siparis != null) {
							isSelected = odeme.getId() == siparis.getOdemeId();
						}
					%>
					<option value="<%=odeme.getId()%>" <%=isSelected ? "selected" : ""%>>[<%=odeme.getId()%>]
						<%=odeme.getOdemeTuru()%></option>
					<%
					}
					%>
				</select>
			</div>
			<div class="form-group">
				<label for="adet">Adet</label> <input type="number" name="adet"
					class="form-control" value="${siparis.getAdet()}">
			</div>
			<div class="form-group">
				<label for="siparisTarih">Sipariş Tarihi</label> <input type="date"
					name="siparisTarih" class="form-control"
					value="${siparis.getSiparisTarih()}">
			</div>

			<div class="row my-5">
				<div class="col">
					<a class="btn btn-secondary" href="/eTicaret/admin/order/list">Geri</a>
				</div>
				<div class="col text-end">
					<button type="submit" class="btn btn-primary">${siparis == null ? 'Kaydet' : 'Güncelle'}</button>
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