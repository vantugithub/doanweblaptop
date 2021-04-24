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
import BEAN.Product;
import DAO.CategoryDAO;
import DAO.ProductDAO;
import DB.DBConnection;

@WebServlet("/admin/products")
public class ListOfProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ListOfProduct() {
        super();
    }
    private ProductDAO productDao;
    private CategoryDAO categoryDAO;
	@SuppressWarnings("static-access")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		Connection conn = DBConnection.creatConnection();
		int count = 5;
		String idPageStr = request.getParameter("page");
		int pageId = Integer.parseInt(idPageStr);
		if(pageId == 1 ) {

		}
		else {
			pageId = pageId - 1;
			pageId = pageId * count + 1;
		}
		
		int sumRow = productDao.countRowOfProduct(conn);
		
		int maxPageId;
		if((sumRow/count)%2==0) 
		{
			maxPageId = (sumRow/count);
		}
		else 
		{
			maxPageId = (sumRow/count)+1;
		}
		
		request.setAttribute("sum",sumRow);
		request.setAttribute("maxPageId", maxPageId);
		request.setAttribute("numberPage",Integer.parseInt(idPageStr));
		
		List<Product> list = productDao.paginationProducts(conn, pageId, count);
		List<Category> listt = categoryDAO.getAllOfCategory(conn);
		
		request.setAttribute("list", list);
		request.setAttribute("listt", listt);
		
		RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/View/Admin/Products.jsp");
		rd.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
	}

}
