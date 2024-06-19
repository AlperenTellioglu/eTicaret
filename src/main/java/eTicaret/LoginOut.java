package eTicaret;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/LoginOut")
public class LoginOut extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginOut() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        boolean isAdmin = false;
        if (request.getSession().getAttribute("isAdmin") != null) {
            isAdmin = (boolean) request.getSession().getAttribute("isAdmin");
        }

        
        request.getSession().invalidate();

        
        if (isAdmin) {
            response.sendRedirect("login");
        } else {
            response.sendRedirect("index");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
