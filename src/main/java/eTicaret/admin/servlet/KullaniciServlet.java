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
import eTicaret.admin.util.AuthUtil;
import eTicaret.admin.util.NavbarUtil;

@WebServlet("/admin/user/*")
public class KullaniciServlet extends HttpServlet {

	private static final long serialVersionUID = -1112806690048086911L;
	private KullaniciDao kullaniciDao;

	@Override
	public void init() throws ServletException {
		kullaniciDao = new KullaniciDao();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");

		NavbarUtil.setLoggedInUsername(req);
		
		if (!AuthUtil.isLoggedIn(req)) {
			resp.sendRedirect("/eTicaret/login");
			return;
		}
		
		if (!AuthUtil.isAdmin(req)) {
			resp.sendRedirect("/eTicaret/");
			return;
        }
		
		String action = req.getPathInfo();
		System.out.println("action(get): " + action);
		if (action == null) {
			action = "/";
		}

		try {
			switch (action) {
			case "/add":
				showCreateForm(req, resp);
				break;
			case "/update":
				showEditForm(req, resp);
				break;
			case "/list":
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
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		
		String action = req.getParameter("action");
		System.out.println("action(post): " + action);

		if (action == null) {
			action = "/";
		}

		try {
			switch (action) {
			case "insert":
				insert(req, resp);
				break;
			case "edit":
				update(req, resp);
				break;
			case "delete":
				delete(req, resp);
				break;
			}
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	private void list(HttpServletRequest req, HttpServletResponse resp)
			throws SQLException, IOException, ServletException {
		List<Kullanici> kullanicilar = kullaniciDao.list();
		req.setAttribute("kullanicilar", kullanicilar);
		req.getRequestDispatcher("/admin/kullanici/kullanicilar.jsp").forward(req, resp);
	}

	private void showCreateForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/admin/kullanici/kullaniciform.jsp").forward(req, resp);
	}

	private void showEditForm(HttpServletRequest req, HttpServletResponse resp)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("kullanici_id"));
		Kullanici eskiKullanici = kullaniciDao.read(id);
		req.setAttribute("kullanici", eskiKullanici);
		req.getRequestDispatcher("/admin/kullanici/kullaniciform.jsp").forward(req, resp);
	}

	private void insert(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
		String ad = req.getParameter("ad");
		String soyad = req.getParameter("soyad");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		Kullanici yeniKategori = new Kullanici(0, ad, soyad, email, password);
		kullaniciDao.create(yeniKategori);
		resp.sendRedirect("list");
	}

	private void update(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
		System.out.println("update: pre");
		int id = Integer.parseInt(req.getParameter("kullanici_id"));
		System.out.println("update: id=" +String.valueOf(id));
		String ad = req.getParameter("ad");
		String soyad = req.getParameter("soyad");
		String email = req.getParameter("email");
		String password = req.getParameter("password");

		Kullanici kullanici = new Kullanici(id, ad, soyad, email, password);
		kullaniciDao.update(kullanici);
		resp.sendRedirect("list");
	}

	private void delete(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
		int id = Integer.parseInt(req.getParameter("kullanici_id"));
		kullaniciDao.delete(id);
		resp.sendRedirect("list");
	}
}
