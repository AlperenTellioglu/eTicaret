<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="eTicaret.SepetItem" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sepet</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        /* Opsiyonel olarak ekstra stil ekleyebilirsiniz */
        body {
            padding: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Sepet</h2>
        <%
            HttpSession sess = request.getSession();
            List<SepetItem> cart = (List<SepetItem>) sess.getAttribute("cart");
            double total = 0;
            if (cart != null && !cart.isEmpty()) {
        %>
                <table>
                    <tr><th>Ürün Adı</th><th>Fiyat</th><th>Adet</th><th>Toplam</th></tr>
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
                </table>
                <p>Toplam: <%= total %> TL</p>
                <form action='odeme.jsp' method='post'><input type='submit' value='Satın Al'></form>
        <% } else { %>
                <p>Sepetiniz boş.</p>
        <% } %>
        <a class="btn btn-primary" href="index.jsp">Alışverişe Devam Et</a>
    </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
