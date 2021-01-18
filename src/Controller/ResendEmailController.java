package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.EmailUtility;
import DAO.RegisterDAO;
import Service.DecodeAndDecrypt;

@WebServlet("/ResendEmail")
public class ResendEmailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String host;
	private String port;
	private String user;
	private String pass;

	public void init() {
		// reads SMTP server setting from web.xml file
		ServletContext context = getServletContext();
		host = context.getInitParameter("host");
		port = context.getInitParameter("port");
		user = context.getInitParameter("user");
		pass = context.getInitParameter("pass");
	}
	public ResendEmailController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Connection conn = DB.DBConnection.creatConnection();
		String recipient = request.getParameter("username");
		if(RegisterDAO.checkStatusOfUser(conn, recipient)) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			RequestDispatcher rd = request.getRequestDispatcher("View/Exist.jsp");
			rd.forward(request, response);
		}
		else {
			String subject = "";
			int code = Random.ran();
			String codeStr = String.valueOf(code);
			String mess = "http://localhost:8080/Laptop/AcctiveAcount?key1="+recipient+"&key2="+ 
					DecodeAndDecrypt.hashPassword(codeStr);
			try {
				EmailUtility.sendEmail(host, port, user, pass, recipient, subject,
						mess);
				if(RegisterDAO.updateHashOfUsername(conn, DecodeAndDecrypt.hashPassword(codeStr),recipient)) {
					conn.close();
					request.setAttribute("username",recipient );
					RequestDispatcher rd =request.getRequestDispatcher("View/Wait.jsp");
					rd.forward(request, response);
				}
				else {
					conn.close();
					RequestDispatcher rd = request.getRequestDispatcher("View/Faild.jsp");
					rd.forward(request, response);
				}
			}
			catch (SQLException ex) {
				ex.printStackTrace();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
