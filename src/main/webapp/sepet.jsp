<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="eTicaret.SepetItem" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sepet</title>
    <link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
    <h2>Sepet</h2>
    <%
        HttpSession sess = request.getSession();
        List<SepetItem> cart = (List<SepetItem>) sess.getAttribute("cart");
        double total = 0;
        if (cart != null && !cart.isEmpty()) {
            out.println("<table>");
            out.println("<tr><th>Ürün Adı</th><th>Fiyat</th><th>Adet</th><th>Toplam</th></tr>");
            for (SepetItem item : cart) {
                double itemTotal = item.getPrice() * item.getQuantity();
                total += itemTotal;
                out.println("<tr>");
                out.println("<td>" + item.getName() + "</td>");
                out.println("<td>" + item.getPrice() + " TL</td>");
                out.println("<td>" + item.getQuantity() + "</td>");
                out.println("<td>" + itemTotal + " TL</td>");
                out.println("</tr>");
            }
            out.println("</table>");
            out.println("<p>Toplam: " + total + " TL</p>");
            out.println("<form action='odeme.jsp' method='post'><input type='submit' value='Satın Al'></form>");
        } else {
            out.println("<p>Sepetiniz boş.</p>");
        }
    %>
    <a href="index.jsp">Alışverişe Devam Et</a>
</body>
</html>
