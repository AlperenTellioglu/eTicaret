<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="eTicaret.SepetItem" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sepet</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
          crossorigin="anonymous">
    <style>
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
        <h2>Sepet</h2>
        <%
            HttpSession sess = request.getSession();
            List<SepetItem> cart = (List<SepetItem>) sess.getAttribute("cart");
            double total = 0;
            if (cart != null && !cart.isEmpty()) {
        %>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Ürün Adı</th>
                            <th>Fiyat</th>
                            <th>Adet</th>
                            <th>Toplam</th>
                        </tr>
                    </thead>
                    <tbody>
                    <% for (SepetItem item : cart) {
                            double itemTotal = item.getPrice() * item.getQuantity();
                            total += itemTotal;
                    %>
                        <tr>
                            <td><%= item.getName() %></td>
                            <td><%= item.getPrice() %> TL</td>
                            <td><%= item.getQuantity() %></td>
                            <td><%= itemTotal %> TL</td>
                        </tr>
                    <% } %>
                    </tbody>
                </table>
                <p class="font-weight-bold">Toplam: <%= total %> TL</p>
                <form action='odeme.jsp' method='post'>
                    <button type='submit' class='btn btn-success'>Satın Al</button>
                </form>
        <% } else { %>
                <p>Sepetiniz boş.</p>
        <% } %>
        <a class="btn btn-primary" href="index.jsp">Alışverişe Devam Et</a>
    </div>
    
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"
            integrity="sha384-rfW4BRygSYmo+JHI5VXyjsO4/vAB5UUbkdyoQYSnJSgy1hH0e3ccEaGMpVThX9VT"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
            integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8sh/jj5Bkz630Qf8/0aOr4mR5Vi+BiCfoL2jRn"
            crossorigin="anonymous"></script>
</body>
</html>
