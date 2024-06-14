package eTicaret;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eTicaret.configuration.DatabaseConfiguration;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

	private static final long serialVersionUID = -4722228995118068512L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("register.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String ad = req.getParameter("ad");
		String soyad = req.getParameter("soyad");
		String password = req.getParameter("password");

		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DatabaseConfiguration.getConnection();
			String sql = "INSERT INTO kullanicilar (ad, soyad, email, password) VALUES (?, ?, ?, ?)";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, ad);
			statement.setString(2, soyad);
			statement.setString(3, email);
			statement.setString(4, password);

			int result = statement.executeUpdate();
			conn.close();

			if (result > 0) {
				System.out.println("basarili kayıt");
				resp.sendRedirect("admin/dashboard");
			} else {
				System.out.println("basarisiz kayıt");
				resp.sendRedirect("register");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
