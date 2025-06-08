package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {

	/*
	 * Declaration of the private static Connection object that will make our
	 * connection to the database. Declaração do objeto do tipo private static
	 * Connection que fará nossa conexão com o banco de dados.
	 * 
	 * "Connection" will be the jdbc database connection object. It starts with
	 * null. "Connection" será o objeto de conexão com o banco de dados do jdbc. Se
	 * inicia nulo.
	 * 
	 */
	private static Connection conn = null;

	/*
	 * Aqui será o método que fará a conexão com o banco de dados se for nulo. Here
	 * will be the method that will connect to the database if it is null.
	 */

	public static Connection getConnection() {
		if (conn == null) { // if conn = null.
			try {
				Properties props = loadProperties();
				String url = props.getProperty("dburl");
				conn = DriverManager.getConnection(url, props);
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
		return conn;
	}

	public static void closeConnection() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}

	/*
	 * method to load the data that is inside the db.properties file método para
	 * carregar os dados que estão dentro do arquivo db.properties
	 */
	private static Properties loadProperties() {

		try (FileInputStream fs = new FileInputStream("db.properties")) {
			Properties props = new Properties();
			props.load(fs);
			return props;
		} catch (IOException e) {
			throw new DbException(e.getMessage());
		}
	}

	public static void closeStatement(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
	
	public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
}
