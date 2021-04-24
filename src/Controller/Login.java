package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BEAN.MyUser;
import BEAN.Role;
import DAO.LoginDAO;
import SessionUtils.SessionUtil;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public Login() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("View/Login.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getCharacterEncoding()==null) 
		{
			request.setCharacterEncoding("UTF-8");
		}
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		MyUser myUser2 = new MyUser();
		myUser2.setUsername(username);
		myUser2.setPassword(password);
		Map<String, String> mess = new HashMap<String, String>();
		if(username == null || username.isEmpty()) {
			mess.put("username", "Please enter username");
		}
		if(password == null || password.isEmpty()) {
			mess.put("password","Please enter password");
		}
		if(mess.isEmpty()) {
			Connection conn = DB.DBConnection.creatConnection();
			MyUser myUser = LoginDAO.checkUsernameAndPasswordOfUser(conn, myUser2);
			if(myUser.getId()!=0) {
				String nameRole = LoginDAO.roleOfUser(conn, myUser2.getUsername());
				Role role  = new Role();
				role.setRoleName(nameRole);
				myUser.setRole(role);
				
				SessionUtil.getInstance().putValue(request, "USERMODEL" , myUser);
				
				if(nameRole.equals("ROLE_ADMIN")) {
					response.sendRedirect(request.getContextPath()+"/admin");
				}
				else if(nameRole.equals("ROLE_EMPLOYEE")) {
					response.sendRedirect(request.getContextPath()+"/employee");
				}
				else if(nameRole.equals("ROLE_USER")) {
					response.sendRedirect(request.getContextPath()+"/");
				}
				else {
					response.sendRedirect(request.getContextPath()+"/Login");
				}
			}
			else {
				response.sendRedirect(request.getContextPath()+"/Login");
			}
		}
		else {
			response.sendRedirect(request.getContextPath()+"/Login");
		}
	}
}
