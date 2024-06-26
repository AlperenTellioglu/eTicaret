<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
	<%
	String currentURI = request.getRequestURI();
	String girisIsim = "Giriş Yapılmadı!";
	if (request.getAttribute("girisIsim") != null) {
		girisIsim = (String) request.getAttribute("girisIsim");
	}
	%>

	<nav class="navbar navbar-expand-lg navbar-dark darker">
		<div class="container-fluid">
			<a class="navbar-brand" href="/eTicaret/admin/dashboard">Admin
				Paneli</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a
						class="nav-link <%=currentURI.endsWith("dashboard.jsp") ? "active" : ""%>"
						aria-current="page" href="/eTicaret/admin/dashboard">Anasayfa</a></li>
					<li class="nav-item"><a
						class="nav-link <%=currentURI.endsWith("urunler.jsp") ? "active" : ""%>"
						href="/eTicaret/admin/product/list">Ürünler</a></li>
					<li class="nav-item"><a
						class="nav-link <%=currentURI.endsWith("kategoriler.jsp") ? "active" : ""%>"
						href="/eTicaret/admin/category/list">Kategoriler</a></li>
					<li class="nav-item"><a
						class="nav-link <%=currentURI.endsWith("kullanicilar.jsp") ? "active" : ""%>"
						href="/eTicaret/admin/user/list">Kullanıcılar</a></li>
					<li class="nav-item"><a
						class="nav-link <%=currentURI.endsWith("siparisler.jsp") ? "active" : ""%>"
						href="/eTicaret/admin/order/list">Siparişler</a></li>
				</ul>
				<span class="text-white me-4"><%=girisIsim%></span>
				<form action="/eTicaret/logout">
					<button class="btn btn-outline-light" type="submit">Çıkış</button>
				</form>
			</div>
		</div>
	</nav>
</body>
</html>
