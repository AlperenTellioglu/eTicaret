package eTicaret;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import eTicaret.configuration.DatabaseConfiguration;

/**
 * Servlet implementation class SepetServlet
 */
@WebServlet("/cart")
public class SepetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Boolean isLoggedIn = (Boolean) session.getAttribute("isLoggedIn");

        if (isLoggedIn != null && isLoggedIn) {
            int productId = Integer.parseInt(request.getParameter("productId"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            List<SepetItem> cart = (List<SepetItem>) session.getAttribute("cart");
            if (cart == null) {
                cart = new ArrayList<>();
            }

            boolean productExists = false;
            for (SepetItem item : cart) {
                if (item.getProductId() == productId) {
                    item.setQuantity(item.getQuantity() + quantity);
                    productExists = true;
                    break;
                }
            }

            if (!productExists) {
                Connection connection = null;
                PreparedStatement preparedStatement = null;
                ResultSet resultSet = null;
                try {
                    connection = DatabaseConfiguration.getConnection();
                    String sql = "SELECT urunId, urunAdi, urunFiyati FROM urunler WHERE urunId = ?";
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setInt(1, productId);
                    resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()) {
                        String name = resultSet.getString("urunAdi");
                        double price = resultSet.getDouble("urunFiyati");
                        cart.add(new SepetItem(productId, name, price, quantity));
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
            }

            session.setAttribute("cart", cart);
            response.sendRedirect("sepet.jsp");
        } else {
            response.sendRedirect("login.jsp");
        }
    }


}
