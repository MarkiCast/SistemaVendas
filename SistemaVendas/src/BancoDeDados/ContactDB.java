package BancoDeDados;

import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;

public class ContactDB {
	public static void main (String [] args) throws SQLException{
		String jdbcURL = "jdbc:postgresql://localhost:5432/postgres";
		String username = "postgres";
		String password = "postgres";
		
		try {
			Connection connection = DriverManager.getConnection(jdbcURL, username, password);
			System.out.println("connected");
			
			connection.close();
			
		} catch (SQLException e) {
			System.out.println("error in connecting to PostgreSQL server");
			e.printStackTrace();
		}
	}
}