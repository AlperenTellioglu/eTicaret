<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.*" %>
<%@ page import="eTicaret.configuration.DatabaseConfiguration" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ödeme Yap</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
    <h2>Ödeme Yöntemi Seçin</h2>
    <form action="checkout" method="post">
        <label for="paymentMethod">Ödeme Yöntemi:</label>
        <select name="paymentMethod" id="paymentMethod" required>
            <%
                Connection connection = null;
                Statement statement = null;
                ResultSet resultSet = null;
                try {
                    connection = DatabaseConfiguration.getConnection();
                    statement = connection.createStatement();
                    resultSet = statement.executeQuery("SELECT id, method_name FROM payment_methods");
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String methodName = resultSet.getString("method_name");
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
        <br><br>
        <input type="submit" value="Ödemeyi Tamamla">
    </form>
</body>
</html>
