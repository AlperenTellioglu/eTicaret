package eTicaret.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/admin/categories", "/admin/category/add", "/admin/category/update"})
public class AdminCategoryServlet extends HttpServlet {

	private static final long serialVersionUID = -9071732025782103106L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/admin/category/admincategories.jsp").forward(req, resp);
	}
}
