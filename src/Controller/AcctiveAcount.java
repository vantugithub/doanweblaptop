package Controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DAO.RegisterDAO;

@WebServlet("/AcctiveAcount")
public class AcctiveAcount extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AcctiveAcount() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String key1 = request.getParameter("key1");
			String key2 = request.getParameter("key2");
			Connection conn = DB.DBConnection.creatConnection();
			if(RegisterDAO.checkStatusOfUser(conn,key1)) {
				conn.close();
				RequestDispatcher rd = request.getRequestDispatcher("View/Exist.jsp");
				rd.forward(request, response);
			}
			else {
				if(RegisterDAO.checkStatus0OfUser(conn, key1, key2)) {
					if(RegisterDAO.updateStatusUser(conn, key1)) {
						int idUser = RegisterDAO.getIdUser(conn, key1);
						RegisterDAO.insertRole(conn, idUser);
						conn.close();
						request.setAttribute("mess", "Registration was successful");
						RequestDispatcher rd = request.getRequestDispatcher("Login");
						rd.forward(request, response);
					}
					else {
						conn.close();
						RequestDispatcher rd = request.getRequestDispatcher("View/Faild.jsp");
						rd.forward(request, response);
					}
				}
				else {
					conn.close();
					RequestDispatcher rd = request.getRequestDispatcher("View/Faild.jsp");
					rd.forward(request, response);
				}
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
