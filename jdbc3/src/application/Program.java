package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
					+ "(?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1, "Carmelita Alves");
			st.setString(2, "carmem1805@gmail.com");
			st.setDate(3, new java.sql.Date(sdf.parse("07/07/1979").getTime()));
			st.setDouble(4,  7000.0);
			st.setInt(5, 3);
			
			int rowsAffected = st.executeUpdate();
			// System.out.println("Done! Rows affected: " + rowsAffected);
			
			if (rowsAffected > 0) {
				 ResultSet rs = st.getGeneratedKeys();
				 // Warning: The st.getGeneratedKeys() function returns a resultSet.
				 while (rs.next()) {
					 int id = rs.getInt(1); // returns the value of the first column.
					 System.out.println("Done! Id = " + id);
				 }

			} else {
				 System.out.println("No rown affected!");
			}
			
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

	private static void If(boolean b) {
		// TODO Auto-generated method stub

	}

	private static void printStackTrace() {

	}

	private static SimpleDateFormat SimpleDateFormat(String string) {
		return null;
	}
}
