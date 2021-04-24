package Controller.Admin;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import DAO.ProductDAO;
import DB.DBConnection;


@WebServlet("/admin/UploadAvatarProduct")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50) // 50MB
public class UploadAvatarProduct extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	private ProductDAO dao;

    public UploadAvatarProduct() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	@SuppressWarnings("static-access")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idOfProductStr = request.getParameter("idOfProductt");
		try {
			   Connection conn = DBConnection.creatConnection();
			   System.out.println(idOfProductStr);
			   long id = Long.parseLong(idOfProductStr);
	           String appPath = request.getServletContext().getRealPath("/image");
	           System.out.println(appPath);
	           for (Part part : request.getParts()) {
	        	   String fileName = extractFileName(part);
	        	   System.out.println(fileName);
	               if (fileName != null && fileName.length() > 0) {
	            	   dao.updateImageOfProduct(conn, id, fileName);
	                   String filePath = appPath + File.separator + fileName;
	                   System.out.println("Write attachment to file: " + filePath);
	                   part.write(filePath);
	               }
	           }
	  
	           response.sendRedirect(request.getContextPath()+"/admin/detailproduct?id="+idOfProductStr);
	       } catch (Exception e) {
	           e.printStackTrace();
	           request.setAttribute("errorMessage", "Error: " + e.getMessage());
	           response.sendRedirect(request.getContextPath()+"/admin/detailproduct?id="+idOfProductStr);
	       }
	   }
	
	private String extractFileName(Part part) {
	       String contentDisp = part.getHeader("content-disposition");
	       String[] items = contentDisp.split(";");
	       for (String s : items) {
	           if (s.trim().startsWith("filename")) {
	               String clientFileName = s.substring(s.indexOf("=") + 2, s.length() - 1);
	               clientFileName = clientFileName.replace("\\", "/");
	               int i = clientFileName.lastIndexOf('/');
	               return clientFileName.substring(i + 1);
	           }
	       }
	       return null;
	   }
		
	}
