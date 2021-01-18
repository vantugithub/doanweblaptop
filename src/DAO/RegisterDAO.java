package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import BEAN.MyUser;
import Service.DecodeAndDecrypt;

public class RegisterDAO {

	public static boolean checkRegister(Connection conn,MyUser myUser) {
		String sql = "INSERT INTO users(username,password,fullname,address,phone,hash,active) VALUES(?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, myUser.getUsername());
			ps.setString(2, DecodeAndDecrypt.hashPassword(myUser.getPassword()));
			ps.setString(3, myUser.getFullName());
			ps.setString(4, myUser.getAddress());
			ps.setString(5, myUser.getPhone());
			ps.setString(6, myUser.getHash());
			ps.setInt(7, 0);
			if(ps.executeUpdate()!=0) {
				ps.close();
				return true;
			}
			ps.close();
		}
		catch (Exception e) 
		{
			System.out.println(e.toString());
		}
		return false;
	}

	public static boolean checkUserExist(Connection conn,String username) {
		String sql = "SELECT iduser FROM users WHERE username = ?  ";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			return rs.next();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return false;
	}

	public static boolean checkStatusOfUser(Connection conn,String username) {

		String sql = "SELECT iduser FROM users WHERE username = ? AND active != ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setInt(2, 0);
			ResultSet rs = ps.executeQuery();
			return rs.next();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return false;
	}

	public static boolean checkStatus0OfUser(Connection conn,String key1 , String key2) {
		String sql = "SELECT iduser FROM users WHERE username = ? AND hash = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, key1);
			ps.setString(2,key2);
			ResultSet rs = ps.executeQuery();
			return rs.next();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return false;
	}

	public static boolean updateStatusUser(Connection conn,String username) {
		String sql = "UPDATE users SET active = ? WHERE username = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, 1);
			ps.setString(2, username);
			if(ps.executeUpdate()!=0) {
				ps.close();
				return true;
			}
			ps.close();
			return false;
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return false;
	}

	public static boolean updateHashOfUsername(Connection conn,String hash,String username) {
		String sql = "UPDATE users SET hash = ? WHERE username = ? ";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, hash);
			ps.setString(2, username);
			if(ps.executeUpdate()!=0) {
				return true;
			}
			return false;
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return false;
	}

	public static int getIdUser(Connection conn,String username) {
		String sql = "SELECT iduser FROM users WHERE username = ?";
		int idUser = -1;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				idUser = rs.getInt("iduser");
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return idUser;
	}

	public static void insertRole(Connection conn,int idUser) {
		String sql = "INSERT INTO roles_users(user,role) VALUES(?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,idUser);
			ps.setInt(2,2);
			ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}
