package eTicaret.admin.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eTicaret.admin.dao.KullaniciDao;
import eTicaret.admin.model.Kullanici;

@WebServlet(urlPatterns = { "/admin/user/list", "/admin/user/add", "/admin/user/update" })
public class KullaniciServlet extends HttpServlet {

	private static final long serialVersionUID = -1112806690048086911L;
	private KullaniciDao kullaniciDao;

	@Override
	public void init() throws ServletException {
		kullaniciDao = new KullaniciDao();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();
		System.out.println("action(get): " + action);
		if (action == null) {
			action = "/";
		}

		try {
			switch (action) {
			case "admin/user/add":
				req.getRequestDispatcher("/admin/kullanici/kullaniciekle.jsp").forward(req, resp);
				break;
			case "admin/user/update":
				req.getRequestDispatcher("/admin/kullanici/kullaniciduzenle.jsp").forward(req, resp);
				break;
			case "admin/user/list":
			default:
				list(req, resp);
				break;
			}
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();
		System.out.println("action(post): " + action);

		if (action == null) {
			action = "/";
		}

		switch (action) {
		case "admin/user/insert":
			break;
		case "admin/user/edit":
			break;
		case "admin/user/delete":
			break;
		}
	}

	private void list(HttpServletRequest req, HttpServletResponse resp)
			throws SQLException, IOException, ServletException {
		List<Kullanici> kullanicilar = kullaniciDao.list();
		req.setAttribute("kullanicilar", kullanicilar);
		req.getRequestDispatcher("/admin/kullanici/kullanicilar.jsp").forward(req, resp);
	}
}
