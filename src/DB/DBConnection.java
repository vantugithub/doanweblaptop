package DB;

import java.sql.*;

public class DBConnection {

	public static Connection creatConnection () {

		Connection conn = null;
		String url="jdbc:mysql://localhost:3306/lap";
		String username = "root";
		String password= "1111";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url,username,password);
		}
		catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return conn;
	}

}
