package application;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.DbException;

public class Program {

	public static void main(String[] args) {

		Connection conn = null;
		Statement st = null;

		try {
			conn = DB.getConnection();
			conn.setAutoCommit(false); // does not confirm operations automatically

			st = conn.createStatement();

			int rows1 = st.executeUpdate("UPDATE seller SET BaseSAlary = 800 " + "WHERE DepartmentId = 3");
			int rows2 = st.executeUpdate("UPDATE seller SET BaseSAlary = 900 " + "WHERE DepartmentId = 4");

			conn.commit(); // confirmation of operation without errors

			System.out.println("rows1" + rows1);
			System.out.println("rows2" + rows2);

		} catch (SQLException e) {
			
			try {
				conn.rollback();
				throw new DbException("Transation rolled back! Caused by: " + e.getMessage());
			} catch (SQLException e1) {
				throw new DbException("Error trying to rollback! Caused by: " + e.getMessage());
			}

		} finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}
}
