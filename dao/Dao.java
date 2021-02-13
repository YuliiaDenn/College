package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Dao {

	private final String URL = "jdbc:mysql://localhost:3306/college?serverTimezone=Europe/Kiev";
	private final String PASSWORD = "root";
	private final String USER = "root";

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	Connection getConnection() {
		Connection connection = null;

		try {
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
}
