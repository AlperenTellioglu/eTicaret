package eTicaret.admin.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

import eTicaret.admin.util.MockAuthUtil;
import eTicaret.admin.util.NavbarUtil;

@WebServlet(urlPatterns = {"/admin/","/admin/dashboard"})
public class DashboardServlet extends HttpServlet {

	private static final long serialVersionUID = 7237020241140011353L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		NavbarUtil.setLoggedInUsername(req);
		
//		HttpSession s= req.getSession();
//		Boolean a = (Boolean)s.getAttribute("isLoggedIn");
//		System.out.println("Is logged in:" + a);
		
		if (!MockAuthUtil.isLoggedIn(req)) {
			resp.sendRedirect("/eTicaret/login");
			return;
		}
		
		if (!MockAuthUtil.isAdmin()) {
			resp.sendRedirect("/eTicaret/");
			return;
        }
		
		req.getRequestDispatcher("/admin/dashboard.jsp").forward(req, resp);
	}
}
