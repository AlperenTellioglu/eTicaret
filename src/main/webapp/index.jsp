<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.*" %>
<%@ page import="eTicaret.configuration.DatabaseConfiguration" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ürünler</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="styles.css">
    <style>
        body {
            padding-top: 20px;
        }

        .product {
            border: 1px solid #dee2e6;
            border-radius: 5px;
            padding: 10px;
            margin-bottom: 20px;
        }

        .product h3 {
            margin-bottom: 10px;
        }

        .product p {
            margin-bottom: 5px;
        }

        .product input[type="number"] {
            width: 70px;
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
            <ul class="navbar-nav ms-auto">
                <li class="nav-item">
                    <a class="nav-link" href="index.jsp">Anasayfa</a>
                </li>
                <% HttpSession session2 = request.getSession(false);
                Boolean isLoggedIn = (session2 != null && session.getAttribute("isLoggedIn") != null) ? (Boolean) session2.getAttribute("isLoggedIn") : false;
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
    <h2>Ürünler</h2>
    <div class="row">
        <div class="col-md-4">
            <ul class="list-group">
                <% Connection connection = null;
                Statement statement = null;
                ResultSet resultSet = null;
                try {
                    connection = DatabaseConfiguration.getConnection();
                    statement = connection.createStatement();
                    resultSet = statement.executeQuery("SELECT kategoriId, kategoriAdi FROM kategoriler");
                    while (resultSet.next()) {
                        int id = resultSet.getInt("kategoriId");
                        String name = resultSet.getString("kategoriAdi");
                        out.println("<li class='list-group-item'><a href='index.jsp?categoryId=" + id + "'>" + name + "</a></li>");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    if (resultSet != null) {
                        try {
                            resultSet.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    if (statement != null) {
                        try {
                            statement.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    DatabaseConfiguration.closeConnection(connection);
                }
                %>
            </ul>
        </div>
        <div class="col-md-8">
                <% connection = null;
                PreparedStatement preparedStatement = null;
                resultSet = null;
                int categoryId = request.getParameter("categoryId") != null ? Integer.parseInt(request.getParameter("categoryId")) : -1;
                try {
                    connection = DatabaseConfiguration.getConnection();
                    String sql = "SELECT urunId, urunAdi, urunFiyati, urunStokMiktari, urunAciklamasi FROM urunler";
                    if (categoryId != -1) {
                        sql += " WHERE kategoriId = ?";
                        preparedStatement = connection.prepareStatement(sql);
                        preparedStatement.setInt(1, categoryId);
                    } else {
                        preparedStatement = connection.prepareStatement(sql);
                    }
                    resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        int id = resultSet.getInt("urunId");
                        String name = resultSet.getString("urunAdi");
                        double price = resultSet.getDouble("urunFiyati");
                        int stock = resultSet.getInt("urunStokMiktari");
                        String description = resultSet.getString("urunAciklamasi");
                        out.println("<form action=\"cart\" method=\"post\">");
                        out.println("<div class='product'>");
                        out.println("<h3>" + name + "</h3>");
                        out.println("<p>Fiyat: " + price + " TL</p>");
                        out.println("<p>Stok Miktarı: " + stock + "</p>");
                        out.println("<p>Açıklama: " + description + "</p>");
                        out.println("<input type='hidden' name='productId' value='" + id + "'>");
                        out.println("<label for='quantity'>Adet:</label>");
                        out.println("<input type='number' name='quantity' value='1' min='1'>");
                        out.println("<input type='submit' value='Sepete Ekle' class='btn btn-primary'>");
                        out.println("</div>");
                        out.println("</form>");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    if (resultSet != null) {
                        try {
                            resultSet.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    if (preparedStatement != null) {
                        try {
                            preparedStatement.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    DatabaseConfiguration.closeConnection(connection);
                }
                %>
            <a href="sepet.jsp" class="btn btn-secondary">Sepete Git</a>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>