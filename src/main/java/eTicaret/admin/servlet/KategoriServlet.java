package eTicaret.admin.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eTicaret.admin.dao.KategoriDao;
import eTicaret.admin.dao.SiparisDao;
import eTicaret.admin.model.Kategori;
import eTicaret.admin.model.Urun;
import eTicaret.admin.util.AuthUtil;
import eTicaret.admin.util.NavbarUtil;
import eTicaret.configuration.DatabaseConfiguration;

@WebServlet("/admin/category/*")
public class KategoriServlet extends HttpServlet {

	private static final long serialVersionUID = -9071732025782103106L;
	
	private Connection conn;
	private KategoriDao kategoriDao;

	@Override
	public void init() throws ServletException {
		try {
			conn = DatabaseConfiguration.getConnection();
			kategoriDao = new KategoriDao(conn);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("[SiparisServlet][init] Error: " + e.getMessage());
		}
	}

	@Override
	public void destroy() {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
			case "/search":
				search(req, resp);
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
		List<Kategori> kategoriler = kategoriDao.list();
		req.setAttribute("kategoriler", kategoriler);
		req.getRequestDispatcher("/admin/kategori/kategoriler.jsp").forward(req, resp);
	}

	private void search(HttpServletRequest req, HttpServletResponse resp)
			throws SQLException, IOException, ServletException {
		String sorgu = req.getParameter("sorgu");
		List<Kategori> kategoriler = kategoriDao.search(sorgu);
		req.setAttribute("kategoriler", kategoriler);
		req.getRequestDispatcher("/admin/kategori/kategoriler.jsp").forward(req, resp);
	}
	
	private void showCreateForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/admin/kategori/kategoriform.jsp").forward(req, resp);
	}

	private void showEditForm(HttpServletRequest req, HttpServletResponse resp)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("kategoriId"));
		Kategori eskiKategori = kategoriDao.read(id);
		req.setAttribute("kategori", eskiKategori);
		req.getRequestDispatcher("/admin/kategori/kategoriform.jsp").forward(req, resp);
	}

	private void insert(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
		String ad = req.getParameter("ad");
		Kategori yeniKategori = new Kategori(0, ad);
		kategoriDao.create(yeniKategori);
		resp.sendRedirect("list");
	}

	private void update(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
		System.out.println("update: pre");
		int id = Integer.parseInt(req.getParameter("kategoriId"));
		System.out.println("update: id=" +String.valueOf(id));
		String ad = req.getParameter("ad");

		Kategori kategori = new Kategori(id, ad);
		kategoriDao.update(kategori);
		resp.sendRedirect("list");
	}

	private void delete(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
		int id = Integer.parseInt(req.getParameter("kategoriId"));
		kategoriDao.delete(id);
		resp.sendRedirect("list");
	}
}
