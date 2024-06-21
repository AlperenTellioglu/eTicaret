<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.*" %>
<%@ page import="eTicaret.configuration.DatabaseConfiguration" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ödeme Yap</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
          integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
          crossorigin="anonymous">
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
        <h2>Ödeme Yöntemi Seçin</h2>
        <form action="checkout" method="post">
            <div class="form-group">
                <label for="paymentMethod">Ödeme Yöntemi:</label>
                <select class="form-control" name="paymentMethod" id="paymentMethod" required>
                    <%
                        Connection connection = null;
                        Statement statement = null;
                        ResultSet resultSet = null;
                        try {
                            connection = DatabaseConfiguration.getConnection();
                            statement = connection.createStatement();
                            resultSet = statement.executeQuery("SELECT odemeId, odemeTuru FROM odemeler");
                            while (resultSet.next()) {
                                int id = resultSet.getInt("odemeId");
                                String methodName = resultSet.getString("odemeTuru");
                                out.println("<option value='" + id + "'>" + methodName + "</option>");
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
                </select>
            </div>
            <br>
            <button type="submit" class="btn btn-primary">Ödemeyi Tamamla</button>
        </form>
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
