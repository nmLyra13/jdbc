package application;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;

public class Program {

	public static void main(String[] args) {

		Connection conn = null;
		Statement st = null;

		try {
			conn = DB.getConnection();
			st = conn.createStatement();
			int rows1 = st.executeUpdate("UPDATE seller SET BaseSAlary = 4090 " 
			+ "WHERE DepartmentId = 3");
			int rows2 = st.executeUpdate("UPDATE seller SET BaseSAlary = 5090 " 
			+ "WHERE DepartmentId = 4");
			
			System.out.println("rows1" + rows1);
			System.out.println("rows2" + rows2);
			

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}
}
