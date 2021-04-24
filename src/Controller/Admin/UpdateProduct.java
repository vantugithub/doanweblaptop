package Controller.Admin;

import java.io.IOException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BEAN.MyUser;
import BEAN.Product;
import DAO.ProductDAO;
import DB.DBConnection;
import SessionUtils.SessionUtil;

@WebServlet("/admin/updateproductbyid")
public class UpdateProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private ProductDAO dao;
	
    public UpdateProduct() 
    {
        super();
    }

	@SuppressWarnings("static-access")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String idOfProductStr = request.getParameter("id");
		Long idOfProduct  = Long.parseLong(idOfProductStr);
		String name = request.getParameter("name");
		String priceStr = request.getParameter("price");
		Long price = Long.parseLong(priceStr);
		String warrantyStr = request.getParameter("warranty");
		int warranty = Integer.parseInt(warrantyStr);
		String promotionPriceStr = request.getParameter("promotionPrice");
		Long promotionPrice = Long.parseLong(promotionPriceStr);
		
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");  
		String strDate = formatter.format(date);  
		formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");  
		strDate = formatter.format(date);
		
		MyUser myUser =(MyUser) SessionUtil.getInstance().getValue(request, "USERMODEL");
		
		Product product = new Product();
		product.setName(name);
		product.setPrice(price);
		product.setPromotionPrice(promotionPrice);
		product.setDateModified(strDate);
		product.setUserModified(myUser.getUsername());
		product.setId(idOfProduct);
		product.setWarranty(warranty);
		Connection conn = DBConnection.creatConnection();
		dao.updateProductById(conn, product);
		
		response.sendRedirect(request.getContextPath()+"/admin/detailproduct?id="+idOfProduct);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
