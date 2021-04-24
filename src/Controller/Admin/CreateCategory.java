package Controller.Admin;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ProductDAO;

@WebServlet("/admin/CreateCategory")
public class CreateCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ProductDAO dao;
	
    public CreateCategory() {
        super();
    }

	@SuppressWarnings("static-access")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nameOfCategory = request.getParameter("category");
		Connection conn = DB.DBConnection.creatConnection();
		dao.createCategory(conn, nameOfCategory);
		response.sendRedirect(request.getContextPath()+"/admin/categorys");
	}

}
