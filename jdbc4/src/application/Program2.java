package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;

public class Program2 {

	public static void main(String[] args) {

		Connection conn = null;
		PreparedStatement st = null;

		try {
			conn = DB.getConnection();
			st = conn.prepareStatement(
					"insert into department (Name) values ('D1'), ('D2')",
					Statement.RETURN_GENERATED_KEYS);
					
			int rowsAffected = st.executeUpdate();

			System.out.println("Done! Rows Affected: " + rowsAffected);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}
}
