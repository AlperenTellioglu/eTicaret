<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.*" %>
<%@ page import="eTicaret.configuration.DatabaseConfiguration" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Profil</title>
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
        <h2>Siparişlerim</h2>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Ürün Adı</th>
                    <th>Adet</th>
                    <th>Ödeme Yöntemi</th>
                    <th>Sipariş Tarihi</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    int userId = (int) request.getSession().getAttribute("kullaniciId");
                    Connection connection = null;
                    PreparedStatement preparedStatement = null;
                    ResultSet resultSet = null;
                    try {
                        connection = DatabaseConfiguration.getConnection();
                        String sql = "SELECT urunler.urunAdi, siparisler.adet, odemeler.odemeTuru, siparisler.siparisTarihi FROM siparisler " +
                                    "INNER JOIN urunler ON siparisler.urunId = urunler.urunId " +
                                    "INNER JOIN odemeler ON siparisler.odemeId = odemeler.odemeId " +
                                    "WHERE siparisler.kullanici_id = ? ORDER BY siparisler.siparisTarihi DESC LIMIT 10"; // Son 10 siparişi al
                        preparedStatement = connection.prepareStatement(sql);
                        preparedStatement.setInt(1, userId);
                        resultSet = preparedStatement.executeQuery();
                        while (resultSet.next()) {
                            String urunAdi = resultSet.getString("urunAdi");
                            int adet = resultSet.getInt("adet");
                            String odemeTuru = resultSet.getString("odemeTuru");
                            String siparisTarihi = resultSet.getString("siparisTarihi");
                %>
                            <tr>
                                <td><%= urunAdi %></td>
                                <td><%= adet %></td>
                                <td><%= odemeTuru %></td>
                                <td><%= siparisTarihi %></td>
                            </tr>
                <% 
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
                        if (connection != null) {
                            try {
                                connection.close();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                %>
            </tbody>
        </table>
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
