package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Service.DecodeAndDecrypt;
import BEAN.MyUser;

public class LoginDAO {

	public static boolean checkUser(Connection conn,MyUser myUser) {
		
		String sql= "SELECT iduser FROM users WHERE username = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, myUser.getUsername());
			ResultSet rs = ps.executeQuery();
			return rs.next();
		}
		catch (Exception e) {
			System.out.println(e.toString());
		}
		return false;
	}
	
	public static MyUser checkUsernameAndPasswordOfUser(Connection conn,MyUser myUser) {
		String sql = "SELECT * FROM users WHERE username = ? AND password = ? AND active = ?";
		MyUser myUser2 = new MyUser();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, myUser.getUsername());
			ps.setString(2, DecodeAndDecrypt.hashPassword(myUser.getPassword()));
			ps.setInt(3, 1);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				myUser2.setId(rs.getInt("iduser"));
				myUser2.setUsername(rs.getNString("username"));
				myUser2.setPassword(rs.getNString("password"));
				myUser2.setFullName(rs.getNString("fullname"));
				myUser2.setAddress(rs.getNString("address"));
				myUser2.setPhone(rs.getNString("phone"));
			}
			ps.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return myUser2;
	}
	
	public static String myUser(Connection conn,MyUser myUser1) {
		String sql = "SELECT password FROM users WHERE username = ?";
		String password = "";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, myUser1.getPassword());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				password = rs.getString("password");
			}
			ps.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return password;
	}
	
	public static String roleOfUser(Connection conn,String username) {
		String role = "";
		String sql = "SELECT roles.name FROM roles WHERE roles.idrole in "
				+ "(SELECT roles_users.role FROM roles_users WHERE roles_users.user in "
				+ "(SELECT users.iduser FROM users WHERE users.username = ?))";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				role = rs.getString("name");
			}
			ps.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return role;
	}
	
}
