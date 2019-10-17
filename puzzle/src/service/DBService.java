package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBService {
	public Connection getConnection() throws Exception {
		Class.forName("org.mariadb.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/puzzle","root","java1234");
		return conn;
	}
	public static void close(ResultSet rs, Statement stmt, Connection conn) {
		
		if( rs != null) {
			try {rs.close();}	catch(SQLException e) {e.printStackTrace();}
		}
		if( stmt != null) {
			try {stmt.close();}	catch(SQLException e) {e.printStackTrace();}
		}
		if( conn != null) {
			try {conn.close();}	catch(SQLException e) {e.printStackTrace();}
		}
	}
}
