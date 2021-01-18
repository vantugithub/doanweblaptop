package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BEAN.MyUser;
import DAO.RegisterDAO;
@WebServlet("/ControllerConfirm")
public class ConfirmController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ConfirmController() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {

			if(request.getCharacterEncoding()==null) {
				request.setCharacterEncoding("UTF-8");
			}
			HttpSession httpSession = request.getSession(false);
			String codeSession = (String)httpSession.getAttribute("codeSession");
			String codeConfirm = request.getParameter("codeConfirm");
			if(codeSession.equals(codeConfirm)) {
				Connection conn = DB.DBConnection.creatConnection();
				String username = (String)httpSession.getAttribute("username");
				String password = (String)httpSession.getAttribute("password");
				String address = (String)httpSession.getAttribute("address");
				String fullname = (String)httpSession.getAttribute("fullname");
				MyUser myUser = new MyUser();
				myUser.setAddress(address);
				myUser.setFullName(fullname);
				myUser.setPassword(password);
				myUser.setUsername(username);
				HttpSession session = request.getSession(false);
				session.removeAttribute("codeSession");
				session.removeAttribute("username");
				session.removeAttribute("password");
				session.removeAttribute("address");
				session.removeAttribute("fullname");
				session.invalidate();
				if(RegisterDAO.checkRegister(conn, myUser)) {
					conn.close();
					RequestDispatcher rd = request.getRequestDispatcher("View/Success.jsp");
					rd.forward(request, response);
				}
				else {
					conn.close();
					RequestDispatcher rd = request.getRequestDispatcher("View/Faild.jsp");
					rd.forward(request, response);
				}
			}
			else {
				RequestDispatcher rd = request.getRequestDispatcher("View/Result.jsp");
				rd.forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
