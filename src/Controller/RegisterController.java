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

import BEAN.MyUser;
import DAO.EmailUtility;
import DAO.RegisterDAO;
import Service.DecodeAndDecrypt;

@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public RegisterController() {
		super();
	}

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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getCharacterEncoding()==null) {
			request.setCharacterEncoding("UTF-8");
		}
		Connection conn = DB.DBConnection.creatConnection();
		String recipient = request.getParameter("email");
		if(RegisterDAO.checkUserExist(conn, recipient)) {
			try {
				conn.close();
				RequestDispatcher rd = request.getRequestDispatcher("View/Register.jsp");
				request.setAttribute("mess", "Tai khoan da ton tai");
				rd.forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
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
				MyUser myUser = new MyUser();
				myUser.setFullName(request.getParameter("fullname"));
				myUser.setUsername(recipient);
				myUser.setAddress(request.getParameter("address"));
				myUser.setHash(DecodeAndDecrypt.hashPassword(codeStr));
				myUser.setPhone(request.getParameter("phone"));
				myUser.setPassword(request.getParameter("password"));
				if(RegisterDAO.checkRegister(conn, myUser))
				{
					conn.close();
					request.setAttribute("username", recipient);
					RequestDispatcher rd =request.getRequestDispatcher("View/Wait.jsp");
					rd.forward(request, response);
				}
				else 
				{
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
