package eTicaret;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eTicaret.configuration.DatabaseConfiguration;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = -4342854810163655323L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DatabaseConfiguration.getConnection();
            String sql = "SELECT * FROM kullanicilar WHERE email = ? AND password = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Başarılı giriş durumunda session oluştur
            	String ad = resultSet.getString("ad");
            	String soyad = resultSet.getString("soyad");
            	int kullaniciId = resultSet.getInt("kullanici_id");
            	
                req.getSession().setAttribute("isLoggedIn", true);
                req.getSession().setAttribute("kullaniciId", kullaniciId);
                req.getSession().setAttribute("kullaniciAdi", ad +" "+ soyad);
                
                // Email içinde @ işaretinden önce admin varsa admin paneline yönlendir
                if (email.toLowerCase().startsWith("admin@")) {
                    resp.sendRedirect("admin/dashboard");
                    req.getSession().setAttribute("isAdmin", true);
                } else {
                    resp.sendRedirect("index.jsp");
                    req.getSession().setAttribute("isAdmin", false);
                }
            } else {
                resp.sendRedirect("login");
            }

            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
	
}
