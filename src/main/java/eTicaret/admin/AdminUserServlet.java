package eTicaret.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/admin/users", "/admin/user/add", "/admin/user/update"})
public class AdminUserServlet extends HttpServlet {

	private static final long serialVersionUID = -1112806690048086911L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();
		System.out.println("action: "+ action);
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
			req.getRequestDispatcher("/admin/user/adminusers.jsp").forward(req, resp);
			break;
		}
		
	}
}
