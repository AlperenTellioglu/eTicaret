package eTicaret.admin.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class NavbarUtil {
	
	public static void setLoggedInUsername(HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		if (session.getAttribute("fullName") != null) {
			String girisYapanIsim = (String) session.getAttribute("fullName");
			req.setAttribute("girisIsim", girisYapanIsim);
		}
	}
}
