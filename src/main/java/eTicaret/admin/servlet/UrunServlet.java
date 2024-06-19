package eTicaret.admin.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eTicaret.admin.dao.UrunDao;
import eTicaret.admin.model.Urun;
import eTicaret.admin.util.MockAuthUtil;
import eTicaret.admin.util.NavbarUtil;

@WebServlet("/admin/product/*")
public class UrunServlet extends HttpServlet {

	private static final long serialVersionUID = 5607346776180865194L;

	private UrunDao urunDao;

	@Override
	public void init() throws ServletException {
		urunDao = new UrunDao();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		
		NavbarUtil.setLoggedInUsername(req);
		
		if (!MockAuthUtil.isLoggedIn(req)) {
			resp.sendRedirect("/eTicaret/login");
			return;
		}
		
		if (!MockAuthUtil.isAdmin(req)) {
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
		List<Urun> urunler = urunDao.list();
		req.setAttribute("urunler", urunler);
		req.getRequestDispatcher("/admin/urun/urunler.jsp").forward(req, resp);
	}

	private void showCreateForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/admin/urun/urunform.jsp").forward(req, resp);
	}

	private void showEditForm(HttpServletRequest req, HttpServletResponse resp)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("urunId"));
		Urun eskiUrun = urunDao.read(id);
		req.setAttribute("urun", eskiUrun);
		req.getRequestDispatcher("/admin/urun/urunform.jsp").forward(req, resp);
	}

	private void insert(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
		String ad = req.getParameter("ad");
		String aciklama = req.getParameter("aciklama");
		double fiyat = Double.parseDouble(req.getParameter("fiyat"));
		int stokMiktar = Integer.parseInt(req.getParameter("stokMiktar"));
		int kategoriId = Integer.parseInt(req.getParameter("kategoriId"));
		Urun yeniUrun = new Urun(0, ad, aciklama, fiyat, stokMiktar, kategoriId);
		urunDao.create(yeniUrun);
		resp.sendRedirect("list");
	}

	private void update(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
		System.out.println("update: pre");
		int id = Integer.parseInt(req.getParameter("urunId"));
		System.out.println("update: id=" +String.valueOf(id));
		String ad = req.getParameter("ad");
		String aciklama = req.getParameter("aciklama");
		double fiyat = Double.parseDouble(req.getParameter("fiyat"));
		int stokMiktar = Integer.parseInt(req.getParameter("stokMiktar"));
		int kategoriId = Integer.parseInt(req.getParameter("kategoriId"));

		Urun urun = new Urun(id, ad, aciklama, fiyat, stokMiktar, kategoriId);
		urunDao.update(urun);
		resp.sendRedirect("list");
	}

	private void delete(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
		int id = Integer.parseInt(req.getParameter("urunId"));
		urunDao.delete(id);
		resp.sendRedirect("list");
	}
}
