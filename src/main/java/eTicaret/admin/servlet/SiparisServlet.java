package eTicaret.admin.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eTicaret.admin.dao.SiparisDao;
import eTicaret.admin.model.Siparis;
import eTicaret.admin.model.SiparisForm;
import eTicaret.admin.util.AuthUtil;
import eTicaret.admin.util.NavbarUtil;
import eTicaret.configuration.DatabaseConfiguration;

@WebServlet("/admin/order/*")
public class SiparisServlet extends HttpServlet {

	private static final long serialVersionUID = 3028406323098544000L;

	private SiparisDao siparisDao;
	private Connection conn;

	@Override
	public void init() throws ServletException {
		try {
			conn = DatabaseConfiguration.getConnection();
			siparisDao = new SiparisDao(conn);
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
		List<Siparis> siparisler = siparisDao.list();
		req.setAttribute("siparisler", siparisler);
		req.getRequestDispatcher("/admin/siparis/siparisler.jsp").forward(req, resp);
	}

	private void search(HttpServletRequest req, HttpServletResponse resp)
			throws SQLException, IOException, ServletException {
		String sorgu = req.getParameter("sorgu");
		List<Siparis> siparisler = siparisDao.search(sorgu);
		req.setAttribute("siparisler", siparisler);
		req.getRequestDispatcher("/admin/siparis/siparisler.jsp").forward(req, resp);
	}

	private void showCreateForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SiparisForm formData = siparisDao.getForm();
		req.setAttribute("siparisForm", formData);
		
		req.getRequestDispatcher("/admin/siparis/siparisform.jsp").forward(req, resp);
	}

	private void showEditForm(HttpServletRequest req, HttpServletResponse resp)
			throws SQLException, ServletException, IOException {
		
		SiparisForm formData = siparisDao.getForm();
		req.setAttribute("siparisForm", formData);
		
		int id = Integer.parseInt(req.getParameter("siparisId"));
		Siparis eskiSiparis = siparisDao.read(id);
		req.setAttribute("siparis", eskiSiparis);
		
		req.getRequestDispatcher("/admin/siparis/siparisform.jsp").forward(req, resp);
	}

	private void insert(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
//		int id = Integer.parseInt(req.getParameter("siparisId"));
		int urunId = Integer.parseInt(req.getParameter("urunId"));
		int kullaniciId = Integer.parseInt(req.getParameter("kullanici_id"));
		int odemeId = Integer.parseInt(req.getParameter("odemeId"));
		int adet = Integer.parseInt(req.getParameter("adet"));
		String siparisTarihString = req.getParameter("siparisTarih");

		try {
			Date siparisTarih = Date.valueOf(siparisTarihString);

			Siparis yeniSiparis = new Siparis(0, urunId, kullaniciId, odemeId, adet, siparisTarih);
			siparisDao.create(yeniSiparis);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("[SiparisServlet][create] Error: " + e.getMessage());
		} finally {
			resp.sendRedirect("list");
		}
	}

	private void update(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
		int siparisId = Integer.parseInt(req.getParameter("siparisId"));
		int urunId = Integer.parseInt(req.getParameter("urunId"));
		int kullaniciId = Integer.parseInt(req.getParameter("kullanici_id"));
		int odemeId = Integer.parseInt(req.getParameter("odemeId"));
		int adet = Integer.parseInt(req.getParameter("adet"));
		String siparisTarihString = req.getParameter("siparisTarih");

		try {
			Date siparisTarih = Date.valueOf(siparisTarihString);

			Siparis siparis = new Siparis(siparisId, urunId, kullaniciId, odemeId, adet, siparisTarih);
			siparisDao.update(siparis);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("[SiparisServlet][update] Error: " + e.getMessage());
		} finally {
			resp.sendRedirect("list");
		}
	}

	private void delete(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
		int id = Integer.parseInt(req.getParameter("siparisId"));
		siparisDao.delete(id);
		resp.sendRedirect("list");
	}
}
