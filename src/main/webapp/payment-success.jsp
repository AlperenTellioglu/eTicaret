<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ödeme Başarılı</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        /* Opsiyonel olarak ekstra stil ekleyebilirsiniz */
        body {
            padding-top: 20px;
        }
    </style>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="index.jsp">eTicaret</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="index.jsp">Anasayfa</a>
                    </li>
                    <% HttpSession session2 = request.getSession(false);
                    Boolean isLoggedIn = (session2 != null && session2.getAttribute("isLoggedIn") != null) ? (Boolean) session2.getAttribute("isLoggedIn") : false;
                    if (isLoggedIn) { %>
                        <li class="nav-item">
                            <a class="nav-link" href="profile.jsp">Profilim</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="logout">Çıkış Yap</a>
                        </li>
                    <% } else { %>
                        <li class="nav-item">
                            <a class="nav-link" href="login.jsp">Giriş Yap</a>
                        </li>
                    <% } %>
                </ul>
            </div>
        </div>
    </nav>

    <div class="container">
        <h2>Ödeme Başarılı</h2>
        <p>Ödemeniz başarıyla gerçekleştirildi. </p>
        <a class="btn btn-primary" href="index.jsp">Anasayfaya Dön</a>
    </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
