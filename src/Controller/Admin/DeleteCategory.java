package Controller.Admin;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.CategoryDAO;
import DB.DBConnection;


@WebServlet("/admin/deletecategory")
public class DeleteCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CategoryDAO categoryDAO;
	
    public DeleteCategory() {
        super();
    }


	@SuppressWarnings("static-access")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn  = DBConnection.creatConnection();
		String idOfCategory = request.getParameter("id");
		int id = Integer.parseInt(idOfCategory);
		int check = categoryDAO.deleteCategoryById(conn, id);
		if(check==0) 
		{
			request.setAttribute("mess", "Delete Failed");
		}
		else request.setAttribute("mess", "Delete Success");
		response.sendRedirect(request.getContextPath()+"/admin/categorys");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
