package eTicaret.admin.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class NavbarUtil {
	
	public static void setLoggedInUsername(HttpServletRequest req) {
		
		HttpSession session = req.getSession(false);
		if (session != null && session.getAttribute("kullaniciAdi") != null) {
			String girisYapanIsim = (String) session.getAttribute("kullaniciAdi");
			req.setAttribute("girisIsim", girisYapanIsim);
		}
	}
}
