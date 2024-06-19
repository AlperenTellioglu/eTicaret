<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.*" %>
<%@ page import="eTicaret.configuration.DatabaseConfiguration" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Profil</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            padding: 20px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Siparişlerim</h2>
        <table class="table">
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
</body>
</html>
