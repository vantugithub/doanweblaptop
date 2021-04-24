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

import BEAN.Product;
import DAO.ProductDAO;
import DB.DBConnection;

@WebServlet("/admin/detailproduct")
public class EditProduct extends HttpServlet {
	
	private ProductDAO dao;
	
	private static final long serialVersionUID = 1L;
    public EditProduct() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String idStr = request.getParameter("id");
		int idOfProduct = Integer.parseInt(idStr);
		Connection conn = DBConnection.creatConnection();
		@SuppressWarnings("static-access")
		List<Product> list  = dao.findProductById(conn, idOfProduct);
		request.setAttribute("list", list);
		RequestDispatcher rd = request.getRequestDispatcher("/View/Admin/detailofproduct.jsp");
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
