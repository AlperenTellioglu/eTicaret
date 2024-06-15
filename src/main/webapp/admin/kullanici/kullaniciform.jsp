<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Kullanıcı Formu</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/admin.css">
</head>
<body>
    <div class="container">
        <h2>${kullanici == null ? 'Yeni Kullanıcı Ekle' : 'Kullanıcı Düzenle'}</h2>
        <form action="/eTicaret/admin/user/" method="post">
        	<input type="hidden" name="action" value="${kullanici == null ? 'insert' : 'edit'}">
            <input type="hidden" name="kullanici_id" value="${kullanici.id}">
            <div class="form-group">
                <label for="firstName">Ad</label>
                <input type="text" name="ad" class="form-control" value="${kullanici.ad}">
            </div>
            <div class="form-group">
                <label for="lastName">Soyad</label>
                <input type="text" name="soyad" class="form-control" value="${kullanici.soyad}">
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" name="email" class="form-control" value="${kullanici.email}">
            </div>
            <div class="form-group">
                <label for="password">Şifre</label>
                <input type="password" name="password" class="form-control" value="${kullanici.password}">
            </div>
            <button type="submit" class="btn btn-primary">${kullanici == null ? 'Kaydet' : 'Güncelle'}</button>
        </form>
    </div>
</body>
</html>