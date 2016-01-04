package Database;

import java.sql.*;
import java.util.ArrayList;

import Classes.Reviews;

public class ReviewJDBC {

	public static ReviewConn ReviewConn;
	static Connection c = null;
	static Statement stmt = null;

	/**
	 * Simple constructor. Initializes the ReviewConnection class.
	 */
	public ReviewJDBC() {
		ReviewConn = new ReviewConn();
		System.out.println("Connected");
	}
	
	/*
	 * Insert a review to the database through a query
	 */
	public static void InsertReview(String HotelName, String Info, String User, Integer Stars){
		String correctname = HotelName.toUpperCase();
		String correctUser = User.toUpperCase();
		try{
			c = ReviewConn.getConn();

			stmt = c.createStatement();

			String sql = "INSERT INTO Reviews (HotelName, Info, User, Stars) "
					+ "VALUES('"+correctname+"' , '"+Info+"', '"+correctUser+"', '"+Stars+"'); ";  

			stmt.executeUpdate(sql);
			c.commit();
			c.close();
		}
		catch (SQLException ex) {
			// handle erros here
			System.out.println("\n" + "Review sent" + "\n");
		}
	}
	/*
	 * Retrieves all reviews
	 */
	public static ArrayList<Reviews> RetrieveReviews(){


		ArrayList<Reviews> list = new ArrayList<Reviews>();

		try{
			c = ReviewConn.getConn();
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Reviews;" );

			while (rs.next()) {
				// the array list holding the data from
				// the current row parsed in the result set
				Reviews databaseReview = new Reviews();

				// get data by column name
				String Info = rs.getString("Info");
				String name = rs.getString("HotelName");
				String Date = rs.getString("Date");
				String User = rs.getString("User");
				int Stars = rs.getInt("Stars");

				// add to data list
				databaseReview.setRHotelName(name);
				databaseReview.setReviewInfo(Info);
				databaseReview.setReviewDate(Date);
				databaseReview.setUser(User);
				databaseReview.setStars(Stars);

				// add to list to return
				list.add(databaseReview);

			}
		} // end while()

		catch (SQLException ex) {
			// handle erros here
			System.out.println("\n" + "Something went wrong " + "while executing query..." + "\n");
		}
		return list;

	}
	/*
	 * retrieves reviews by matching hotelname
	 */
	public static ArrayList<Reviews> RetrieveOneReview(String name1){

		String correctname = name1.toUpperCase();
		ArrayList<Reviews> list = new ArrayList<Reviews>();

		try{
			c = ReviewConn.getConn();
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Reviews WHERE HotelName == '"+correctname+"' ;" );

			while (rs.next()) {
				// the array list holding the data from
				// the current row parsed in the result set
				Reviews databaseReview = new Reviews();

				// get data by column name
				String Info = rs.getString("Info");
				String name = rs.getString("HotelName");
				String Date = rs.getString("Date");
				String User = rs.getString("User");
				int Stars = rs.getInt("Stars");

				// add to data list
				databaseReview.setRHotelName(name);
				databaseReview.setReviewInfo(Info);
				databaseReview.setReviewDate(Date);
				databaseReview.setUser(User);
				databaseReview.setStars(Stars);

				// add to list to return
				list.add(databaseReview);


			}
		} // end while()

		catch (SQLException ex) {
			// handle erros here
			System.out.println("\n" + "Something went wrong " + "while executing query..." + "\n");
		}

		return list;

	}
	/*
	 * Retrieve reviews by matching username
	 */
	public static ArrayList<Reviews> RetrieveUserReviews(String name1){

		String correctname = name1.toUpperCase();
		ArrayList<Reviews> list = new ArrayList<Reviews>();

		try{
			c = ReviewConn.getConn();
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Reviews WHERE User == '"+correctname+"' ;" );

			while (rs.next()) {
				// the array list holding the data from
				// the current row parsed in the result set
				Reviews databaseReview = new Reviews();

				// get data by column name
				String Info = rs.getString("Info");
				String name = rs.getString("HotelName");
				String Date = rs.getString("Date");
				String User = rs.getString("User");
				int Stars = rs.getInt("Stars");

				// add to data list
				databaseReview.setRHotelName(name);
				databaseReview.setReviewInfo(Info);
				databaseReview.setReviewDate(Date);
				databaseReview.setUser(User);
				databaseReview.setStars(Stars);

				// add to list to return
				list.add(databaseReview);

			}
		} // end while()

		catch (SQLException ex) {
			// handle erros here
			System.out.println("\n" + "Something went wrong " + "while executing query..." + "\n");
		}

		return list;

	}

}
