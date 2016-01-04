package Database;

import java.sql.*;

public class DBConnection {


	/**
	 * Constructor. Loads the driver to be used.
	 */
	public DBConnection() {
		try {
			System.out.println("Loading driver...");
			
			Class.forName("org.sqlite.JDBC");
			System.out.println("Driver loaded...");
		} catch (ClassNotFoundException ex) {
			
			System.out.println("\n" + "Could not load driver..." + "\n");
			System.out.println(ex);
		}

	}

	/**
	 * Returns a connection to the database.
	 * 
	 * @return Connection
	 */
	public  Connection getConn() {

		Connection conn = null;

		try {
			// get a connection
			conn = DriverManager.getConnection("jdbc:sqlite:test.sqlite");
		} catch (SQLException e) {
			// handle errors for JDBC here
			System.out.println("\n" + "Could not connect..." + "\n");
			System.out.println(e);
		}

		return conn;

	}

}
