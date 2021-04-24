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

@WebServlet("/admin/CreateProduct")
public class CreateProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static ProductDAO productDao;
	
    public CreateProduct() {
        super();
    }

	@SuppressWarnings("static-access")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = DBConnection.creatConnection();
		String nameOfProduct = request.getParameter("nameOfProduct");
		String idOfCatalogStr = request.getParameter("item");
		Product product = new Product();
		product.setName(nameOfProduct);
		String mess = "";
		String path = "";
		long LAST_INSERT_ID = -1;
		
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");  
		String strDate = formatter.format(date);  
		formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");  
		strDate = formatter.format(date);
		
		MyUser myUser =(MyUser) SessionUtil.getInstance().getValue(request, "USERMODEL");
		product.setDateCreated(strDate);
		product.setUserCreated(myUser.getUsername());
		try {
			LAST_INSERT_ID = productDao.createProduct(conn, product, Long.parseLong(idOfCatalogStr));
			if(LAST_INSERT_ID>=1)
			{
				mess = "Created Success";
			} else mess = "Created Failded";
		}
		catch (Exception e) {
			mess = e.toString();
		}
		if(LAST_INSERT_ID>0) 
		{
			path += request.getContextPath()+"/admin/detailproduct?id="+String.valueOf(LAST_INSERT_ID);
		}
		else {
			path += request.getContextPath()+"/admin/products?page=1";
		}
		request.setAttribute("mess", mess);
		response.sendRedirect(path);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
