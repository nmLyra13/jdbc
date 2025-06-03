package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import db.DB;

public class Program {

	public static void main(String[] args) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = DB.getConnection(); // conecta com o banco de dados.
			/*
			 * prepareStatement expects as an argument a string that will be the sql command.
			 * prepareStatement espera como argumento uma string que será o comando sql.
			 */
			st = conn.prepareStatement("INSERT INTO seller "
					+ "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
					+ "VALUES "
					+ "(?, ?, ?, ?, ?)"
					+ Statement.RETURN_GENERATED_KEYS);
			st.setString(1, "Nelivaldo Lyra");
			st.setString(2, "nmlyra13@gmail.com");
			st.setDate(3, new java.sql.Date(sdf.parse("13/05/1978").getTime()));
			st.setDouble(4,  9000.0);
			st.setInt(5, 4);
			
			int rowsAffected = st.executeUpdate();
			System.out.println("Done! Rows affected: " + rowsAffected);
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}

	private static void printStackTrace() {
		
	}

	private static SimpleDateFormat SimpleDateFormat(String string) {
		return null;
	}
}
