package eTicaret.admin.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eTicaret.admin.util.NavbarUtil;

@WebServlet(urlPatterns = {"/admin/","/admin/dashboard"})
public class DashboardServlet extends HttpServlet {

	private static final long serialVersionUID = 7237020241140011353L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		NavbarUtil.setLoggedInUsername(req);
		
		req.getRequestDispatcher("/admin/dashboard.jsp").forward(req, resp);
	}
}
