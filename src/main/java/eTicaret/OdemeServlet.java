package eTicaret;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import eTicaret.configuration.DatabaseConfiguration;

/**
 * Servlet implementation class OdemeServlet
 */
@WebServlet("/checkout")
public class OdemeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int userId = (int) request.getSession().getAttribute("kullaniciId");
        int paymentId = Integer.parseInt(request.getParameter("paymentMethod"));
        String orderDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        List<SepetItem> cart = (List<SepetItem>) request.getSession().getAttribute("cart");

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DatabaseConfiguration.getConnection();
            for (SepetItem item : cart) {
                int productId = item.getProductId();
                int quantity = item.getQuantity();

                String sql = "INSERT INTO siparisler (kullanici_id, urunId, odemeId, adet, siparisTarihi) VALUES (?, ?, ?, ?, ?)";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, userId);
                preparedStatement.setInt(2, productId);
                preparedStatement.setInt(3, paymentId);
                preparedStatement.setInt(4, quantity);
                preparedStatement.setString(5, orderDate);
                preparedStatement.executeUpdate();
            }

            cart.clear();
            request.getSession().setAttribute("cart", cart);

            response.sendRedirect("payment-success.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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
    }

}
