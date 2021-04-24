package Controller.Admin;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BEAN.Category;
import DAO.CategoryDAO;
import DB.DBConnection;

@WebServlet("/admin/categorys")
public class ListOfCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private CategoryDAO categoryDAO;
	
    public ListOfCategory() {
        super();
    }

	@SuppressWarnings("static-access")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = DBConnection.creatConnection();
		List<Category> list = categoryDAO.getAllOfCategory(conn);
		request.setAttribute("list", list);
		RequestDispatcher rd = request.getRequestDispatcher("/View/Admin/Categorys.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
