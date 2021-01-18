package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Confirm")
public class Confirm extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Confirm() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession httpSession = request.getSession(false);
		String codeSession = (String)httpSession.getAttribute("codeSession");
		String codeConfirm = request.getParameter("codeConfirm");
		if(codeSession.equals(codeConfirm)) {
			httpSession.removeAttribute("codeSession");
			httpSession.invalidate();
			RequestDispatcher rd = request.getRequestDispatcher("View/Success.jsp");
			rd.forward(request, response);
		}
		else {
			RequestDispatcher rd = request.getRequestDispatcher("View/Faild.jsp");
			rd.forward(request, response);
		}
	}

}
