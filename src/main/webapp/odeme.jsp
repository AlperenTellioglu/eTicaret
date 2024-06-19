<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.*" %>
<%@ page import="eTicaret.configuration.DatabaseConfiguration" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ödeme Yap</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        /* Opsiyonel olarak ekstra stil ekleyebilirsiniz */
        body {
            padding: 20px;
        }
    </style>
</head>
<body>
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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
