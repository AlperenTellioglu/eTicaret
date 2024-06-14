package eTicaret.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/admin/products", "/admin/product/add", "/admin/product/update"})
public class AdminProductServlet extends HttpServlet {

	private static final long serialVersionUID = 5607346776180865194L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/admin/product/adminproducts.jsp").forward(req, resp);
	}
}
