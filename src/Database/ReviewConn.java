package Database;

import java.sql.*;

/* Dbconnection, connection for smaller part of the database
* @author Adin during sprint 3
* @version 1.0
*/

public class ReviewConn {


	/**
	 * Constructor. Loads the driver to be used.
	 */
	public ReviewConn() {
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
	public Connection getConn() {

		Connection conn = null;

		try {
			// get a connection
			conn = DriverManager.getConnection("jdbc:sqlite:reviews.sqlite");
		} catch (SQLException e) {
			// handle errors for JDBC here
			System.out.println("\n" + "Could not connect..." + "\n");
			System.out.println(e);
		}

		return conn;

	}

}
