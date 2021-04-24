package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BEAN.Category;

public class CategoryDAO {
	public static List<Category> getAllOfCategory(Connection conn)
	{
		List<Category> list = new ArrayList<Category>();
		String sql = " SELECT * FROM catalogs";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Category vc  = new Category();
				vc.setId(rs.getLong("Id"));
				vc.setName(rs.getString("Name"));
				list.add(vc);
			}
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static int deleteCategoryById(Connection conn,long id) {
		String sql = "DELETE FROM catalogs WHERE Id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(1, id);
			ps.executeUpdate();
			ps.close();
			return 1;
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return 0;
	}
	
}
