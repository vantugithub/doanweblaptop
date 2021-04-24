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

@WebServlet("/admin/unlockproduct")
public class UpdateStatusOfProduct extends HttpServlet {
	
	private ProductDAO dao;
	
	private static final long serialVersionUID = 1L;
	
    public UpdateStatusOfProduct() {
        super();
    }

	@SuppressWarnings("static-access")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		String status = request.getParameter("status");
		long id = Long.parseLong(idStr);
		Connection conn = DBConnection.creatConnection();
		System.out.println(status);
		if(status.equals("true"))
		{
			dao.updateStatusOfProduct(conn, id, false);
		}
		else 
		{
			dao.updateStatusOfProduct(conn, id, true);
		}
		
		response.sendRedirect(request.getContextPath()+"/admin/detailproduct?id="+idStr);
	}


}
