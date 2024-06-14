package eTicaret.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eTicaret.configuration.DatabaseConfiguration;

@WebServlet(urlPatterns = { "/admin/users", "/admin/user/add", "/admin/user/update" })
public class AdminUserServlet extends HttpServlet {

	private static final long serialVersionUID = -1112806690048086911L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();
		System.out.println("action: " + action);
		if (action == null) {
			action = "/";
		}

		switch (action) {
		case "/add":
			req.getRequestDispatcher("/admin/user/adminproducts.jsp").forward(req, resp);
			break;
		case "/update":
			req.getRequestDispatcher("/admin/user/adminproducts.jsp").forward(req, resp);
			break;
		default:
			List<Kullanici> kullanicilar = null;
			try {
				kullanicilar = GetListUsers();
				req.setAttribute("kullanicilar", kullanicilar);				
			} catch (Exception e) {
				System.out.println("Hata");
			}finally {				
				req.getRequestDispatcher("/admin/user/adminusers.jsp").forward(req, resp);
			}
			break;
		}

	}

	private List<Kullanici> GetListUsers() throws SQLException {
		List<Kullanici> kullanicilar = new ArrayList<Kullanici>();

		Statement statement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DatabaseConfiguration.getConnection();
			statement = connection.createStatement();
			String sql = "SELECT * FROM kullanicilar";
			resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				int id = resultSet.getInt("kullanici_id");
				String ad = resultSet.getString("ad");
				String soyad = resultSet.getString("soyad");
				String email = resultSet.getString("email");
				String password = resultSet.getString("password");
				kullanicilar.add(new Kullanici(id, ad, soyad, email, password));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Kaynakları kapat
			if (resultSet != null)
				try {
					resultSet.close();
				} catch (SQLException ignore) {
				}
			if (statement != null)
				try {
					statement.close();
				} catch (SQLException ignore) {
				}
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException ignore) {
				}
		}
		return kullanicilar;
	}
}
