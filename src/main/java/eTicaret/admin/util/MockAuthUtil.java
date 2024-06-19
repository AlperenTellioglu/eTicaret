package eTicaret.admin.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class MockAuthUtil {
	public static boolean isAdmin(HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		return session != null && session.getAttribute("isAdmin") !=null && (Boolean) session.getAttribute("isAdmin") == true;
	}

	public static boolean isLoggedIn(HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		return session != null && session.getAttribute("isLoggedIn") !=null && (Boolean) session.getAttribute("isLoggedIn") == true;
	}
}
