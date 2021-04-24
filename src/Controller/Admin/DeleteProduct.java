package Controller.Admin;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ProductDAO;
import DB.DBConnection;
@WebServlet("/admin/deleteproduct")
public class DeleteProduct extends HttpServlet {
	private ProductDAO dao;
	private static final long serialVersionUID = 1L;
       
    public DeleteProduct() {
        super();
    }

	@SuppressWarnings("static-access")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		Connection conn = DBConnection.creatConnection();
		String mess = "";
		try {
			if(dao.deleteProductById(conn, Long.parseLong(idStr))==1) {
				mess+="Delete Success";
			}
			else {
				mess+="Delete failded";
			}
		}
		catch (Exception e) {
			mess+=e.toString();
		}
		request.setAttribute("mess", mess);
		response.sendRedirect(request.getContextPath()+"/admin/products?page=1");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
