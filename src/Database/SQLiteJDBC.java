package Database;

	import java.sql.*;
	import java.util.ArrayList;

	import Classes.Hotel;

	 /*
	 * Dbconnection, connection for larger part of the database
	 * @author Adin during sprint 2
	 * @version 1.0
	 */
	
	public class SQLiteJDBC {

		public static DBConnection dbConn;
		static Connection c = null;
		static Statement stmt = null;
		public static ArrayList<Hotel> list1;
		/**
		 * Simple constructor. Initializes the DBConnection class.
		 */
		public SQLiteJDBC() {
			dbConn = new DBConnection();
			System.out.println("Connected");
		}
		/*
		 * Insert query for hotels.
		 */
	public static void InsertHotel(String Picture, String HotelName, double Price, int Stars, int Popularity, double Distance, int Breakfast, int Bar, int Gym, int Pets, int Pool){
		String correctname = HotelName.toUpperCase();
		try{
			c = dbConn.getConn();
			stmt = c.createStatement();

			String sql = "INSERT INTO Hotel (Picture, Name, Rating, Price, Distance, Pool, Popularity, Breakfast, Gym, Bar, Pets) "
					+ "VALUES('"+Picture+"','"+correctname+"' , '"+Stars+"', '"+Price+"', '"+Distance+"', '"+Pool+"'"
					+ ", '"+Popularity+"', '"+Breakfast+"', '"+Gym+"', '"+Bar+"', '"+Pets+"'); ";  

			stmt.executeUpdate(sql);
			c.commit();
			c.close();
		}
		catch (SQLException ex) {
			// handle erros here
			System.out.println("\n" + "Hotel added" + "\n");
		}
	}
	
	//Created by Jakob, creates an SQL statement that sends the input value of username and password
	// in order to add a user to the database when using the register function
	public static void InsertUser(String Username, String Password){

		try{
			c = dbConn.getConn();
			stmt = c.createStatement();

			String sql = "INSERT INTO Users (Username, Password) "
					+ "VALUES('"+Username+"' , '"+Password+"'); ";  

			stmt.executeUpdate(sql);
			c.commit();
			c.close();
		}
		catch (SQLException ex) {
			// handle erros here
			System.out.println("\n" + "User added" + "\n");
		}
	}

	/*
	 * Query for retrieving all hotels
	 */
	public static ArrayList<Hotel> RetrieveHotels(){


		ArrayList<Hotel> list = new ArrayList<Hotel>();

		try{
			c = dbConn.getConn();
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Hotel;" );

			while (rs.next()) {
				// the array list holding the data from
				// the current row parsed in the result set
				Hotel databaseHotel = new Hotel();

				// get data by column name
				int hid = rs.getInt("HID");
				int rating = rs.getInt("Rating");
				int popularity = rs.getInt("Popularity");
				String name = rs.getString("Name");
				String picture = rs.getString("Picture");
				double price = rs.getDouble("Price");
				double distance = rs.getDouble("Distance");
				boolean pool = rs.getBoolean("Pool");
				boolean gym = rs.getBoolean("Gym");
				boolean bar = rs.getBoolean("Bar");
				boolean pets = rs.getBoolean("Pets");
				boolean breakfast = rs.getBoolean("Breakfast");

				// add to data list
				databaseHotel.setHotelId(hid);
				databaseHotel.setHotelName(name);
				databaseHotel.setStars(rating);
				databaseHotel.setPopularity(popularity);
				databaseHotel.setHotelPrice(price);
				databaseHotel.setDistance(distance);
				databaseHotel.setPool(pool);
				databaseHotel.setBar(bar);
				databaseHotel.setPets(pets);
				databaseHotel.setBreakfast(breakfast);
				databaseHotel.setGym(gym);
				databaseHotel.setHotelPicture(picture);

				// add to list to return
				list.add(databaseHotel);


			}
		} // end while()

		catch (SQLException ex) {
			// handle erros here
			System.out.println("\n" + "Something went wrong " + "while executing query..." + "\n");
		}
		list1 = list;
		return list;

	}

	
	/*
	 * Query for retrieving one hotel with a specific name
	 */

		public ArrayList<Hotel> RetrieveOneHotel(String name1){

			String correctname = name1.toUpperCase();
			ArrayList<Hotel> list = new ArrayList<Hotel>();

			try{
				c = dbConn.getConn();
				stmt = c.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM Hotel WHERE Name == '"+correctname+"' ;" );

				while (rs.next()) {
					// the array list holding the data from
					// the current row parsed in the result set
					Hotel databaseHotel = new Hotel();

					// get data by column name
					int hid = rs.getInt("HID");
					int rating = rs.getInt("Rating");
					int popularity = rs.getInt("Popularity");
					String name = rs.getString("Name");
					String picture = rs.getString("Picture");
					double price = rs.getDouble("Price");
					double distance = rs.getDouble("Distance");
					boolean pool = rs.getBoolean("Pool");
					boolean gym = rs.getBoolean("Gym");
					boolean bar = rs.getBoolean("Bar");
					boolean pets = rs.getBoolean("Pets");
					boolean breakfast = rs.getBoolean("Breakfast");

					// add to data list
					databaseHotel.setHotelId(hid);
					databaseHotel.setHotelName(name);
					databaseHotel.setStars(rating);
					databaseHotel.setPopularity(popularity);
					databaseHotel.setHotelPrice(price);
					databaseHotel.setDistance(distance);
					databaseHotel.setPool(pool);
					databaseHotel.setBar(bar);
					databaseHotel.setPets(pets);
					databaseHotel.setBreakfast(breakfast);
					databaseHotel.setGym(gym);
					databaseHotel.setHotelPicture(picture);

					// add to list to return
					list.add(databaseHotel);
				}
			} // end while()

			catch (SQLException ex) {
				// handle erros here
				System.out.println("\n" + "Something went wrong " + "while executing query..." + "\n");
			}

			return list;

		}
		/*
		 * Query for getting two hotels and comparing them.
		 */
		public ArrayList<Hotel> CompareHotels(int hID1, int hID2){

			ArrayList<Hotel> list = new ArrayList<Hotel>();

			try{
				c = dbConn.getConn();
				stmt = c.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM Hotel Where HID IN ('"+hID1+"','"+hID2+"');");

				while (rs.next()) {
					// the array list holding the data from
					// the current row parsed in the result set
					Hotel databaseHotel = new Hotel();

					// get data by column name
					int hid = rs.getInt("HID");
					int rating = rs.getInt("Rating");
					int popularity = rs.getInt("Popularity");
					String name = rs.getString("Name");
					String picture = rs.getString("Picture");
					double price = rs.getDouble("Price");
					double distance = rs.getDouble("Distance");
					boolean pool = rs.getBoolean("Pool");
					boolean gym = rs.getBoolean("Gym");
					boolean bar = rs.getBoolean("Bar");
					boolean pets = rs.getBoolean("Pets");
					boolean breakfast = rs.getBoolean("Breakfast");

					// add to data list
					databaseHotel.setHotelId(hid);
					databaseHotel.setHotelName(name);
					databaseHotel.setStars(rating);
					databaseHotel.setPopularity(popularity);
					databaseHotel.setHotelPrice(price);
					databaseHotel.setDistance(distance);
					databaseHotel.setPool(pool);
					databaseHotel.setBar(bar);
					databaseHotel.setPets(pets);
					databaseHotel.setBreakfast(breakfast);
					databaseHotel.setGym(gym);
					databaseHotel.setHotelPicture(picture);

					// add to list to return
					list.add(databaseHotel);

				} // end while()
			}

			catch (SQLException ex) {
				// handle erros here
				System.out.println("\n" + "Something went wrong " + "while executing query..." + "\n");
			}
			return list;

		}
		
		/* 
		 * Query for ordering hotels by price ascending
		 */
		public static ArrayList<Hotel> OrderByPrice(){

			ArrayList<Hotel> list = new ArrayList<Hotel>();


			try{
				c = dbConn.getConn();
				stmt = c.createStatement();
				ResultSet rs = stmt.executeQuery( "SELECT * FROM Hotel ORDER BY Price ASC;");

				while (rs.next()) {
					// the array list holding the data from
					// the current row parsed in the result set
					Hotel databaseHotel = new Hotel();

					// get data by column name
					int hid = rs.getInt("HID");
					int rating = rs.getInt("Rating");
					int popularity = rs.getInt("Popularity");
					String name = rs.getString("Name");
					String picture = rs.getString("Picture");
					double price = rs.getDouble("Price");
					double distance = rs.getDouble("Distance");
					boolean pool = rs.getBoolean("Pool");
					boolean gym = rs.getBoolean("Gym");
					boolean bar = rs.getBoolean("Bar");
					boolean pets = rs.getBoolean("Pets");
					boolean breakfast = rs.getBoolean("Breakfast");

					// add to data list
					databaseHotel.setHotelId(hid);
					databaseHotel.setHotelName(name);
					databaseHotel.setStars(rating);
					databaseHotel.setPopularity(popularity);
					databaseHotel.setHotelPrice(price);
					databaseHotel.setDistance(distance);
					databaseHotel.setPool(pool);
					databaseHotel.setBar(bar);
					databaseHotel.setPets(pets);
					databaseHotel.setBreakfast(breakfast);
					databaseHotel.setGym(gym);
					databaseHotel.setHotelPicture(picture);

					// add to list to return
					list.add(databaseHotel);

				} // end while()
			}
			catch (SQLException ex) {
				// handle erros here
				System.out.println("\n" + "Something went wrong " + "while executing query..." + "\n");
			}

			return list;

		}
		/*
		 * Query for ordering by price ascending.
		 */
		public static ArrayList<Hotel> OrderByPriceChosen(int chosenprice){

			ArrayList<Hotel> list = new ArrayList<Hotel>();


			try{
				c = dbConn.getConn();
				stmt = c.createStatement();
				ResultSet rs = stmt.executeQuery( "SELECT * FROM Hotel ORDER BY Price DESC;");

				while (rs.next()) {
					// the array list holding the data from
					// the current row parsed in the result set
					Hotel databaseHotel = new Hotel();

					// get data by column name
					int hid = rs.getInt("HID");
					int rating = rs.getInt("Rating");
					int popularity = rs.getInt("Popularity");
					String name = rs.getString("Name");
					String picture = rs.getString("Picture");
					double price = rs.getDouble("Price");
					double distance = rs.getDouble("Distance");
					boolean pool = rs.getBoolean("Pool");
					boolean gym = rs.getBoolean("Gym");
					boolean bar = rs.getBoolean("Bar");
					boolean pets = rs.getBoolean("Pets");
					boolean breakfast = rs.getBoolean("Breakfast");

					// add to data list
					databaseHotel.setHotelId(hid);
					databaseHotel.setHotelName(name);
					databaseHotel.setStars(rating);
					databaseHotel.setPopularity(popularity);
					databaseHotel.setHotelPrice(price);
					databaseHotel.setDistance(distance);
					databaseHotel.setPool(pool);
					databaseHotel.setBar(bar);
					databaseHotel.setPets(pets);
					databaseHotel.setBreakfast(breakfast);
					databaseHotel.setGym(gym);
					databaseHotel.setHotelPicture(picture);

					// add to list to return
					list.add(databaseHotel);

				} // end while()
			}
			catch (SQLException ex) {
				// handle erros here
				System.out.println("\n" + "Something went wrong " + "while executing query..." + "\n");
			}

			return list;

		}
		/*
		 * Query for ordering hotels by distance
		 */
		public static ArrayList<Hotel> OrderByDistance() {

			ArrayList<Hotel> list = new ArrayList<Hotel>();

			try{
				c = dbConn.getConn();
				stmt = c.createStatement();
				ResultSet rs = stmt.executeQuery( "SELECT * FROM Hotel ORDER BY Distance ASC;");

				while (rs.next()) {
					// the array list holding the data from
					// the current row parsed in the result set
					Hotel databaseHotel = new Hotel();

					// get data by column name
					int hid = rs.getInt("HID");
					int rating = rs.getInt("Rating");
					int popularity = rs.getInt("Popularity");
					String name = rs.getString("Name");
					String picture = rs.getString("Picture");
					double price = rs.getDouble("Price");
					double distance = rs.getDouble("Distance");
					boolean pool = rs.getBoolean("Pool");
					boolean gym = rs.getBoolean("Gym");
					boolean bar = rs.getBoolean("Bar");
					boolean pets = rs.getBoolean("Pets");
					boolean breakfast = rs.getBoolean("Breakfast");

					// add to data list
					databaseHotel.setHotelId(hid);
					databaseHotel.setHotelName(name);
					databaseHotel.setStars(rating);
					databaseHotel.setPopularity(popularity);
					databaseHotel.setHotelPrice(price);
					databaseHotel.setDistance(distance);
					databaseHotel.setPool(pool);
					databaseHotel.setBar(bar);
					databaseHotel.setPets(pets);
					databaseHotel.setBreakfast(breakfast);
					databaseHotel.setGym(gym);
					databaseHotel.setHotelPicture(picture);

					// add to list to return
					list.add(databaseHotel);
				}
			} // end while()

			catch (SQLException ex) {
				// handle erros here
				System.out.println("\n" + "Something went wrong " + "while executing query..." + "\n");
			}

			return list;

		}
		/*
		 * Query for ordering by popularity
		 */
		public static ArrayList<Hotel> OrderByPopularity() {

			ArrayList<Hotel> list = new ArrayList<Hotel>();

			try{
				c = dbConn.getConn();
				stmt = c.createStatement();
				ResultSet rs = stmt.executeQuery( "SELECT * FROM Hotel ORDER BY Popularity DESC;");

				while (rs.next()) {
					// the array list holding the data from
					// the current row parsed in the result set
					Hotel databaseHotel = new Hotel();

					// get data by column name
					int hid = rs.getInt("HID");
					int rating = rs.getInt("Rating");
					int popularity = rs.getInt("Popularity");
					String name = rs.getString("Name");
					String picture = rs.getString("Picture");
					double price = rs.getDouble("Price");
					double distance = rs.getDouble("Distance");
					boolean pool = rs.getBoolean("Pool");
					boolean gym = rs.getBoolean("Gym");
					boolean bar = rs.getBoolean("Bar");
					boolean pets = rs.getBoolean("Pets");
					boolean breakfast = rs.getBoolean("Breakfast");

					// add to data list
					databaseHotel.setHotelId(hid);
					databaseHotel.setHotelName(name);
					databaseHotel.setStars(rating);
					databaseHotel.setPopularity(popularity);
					databaseHotel.setHotelPrice(price);
					databaseHotel.setDistance(distance);
					databaseHotel.setPool(pool);
					databaseHotel.setBar(bar);
					databaseHotel.setPets(pets);
					databaseHotel.setBreakfast(breakfast);
					databaseHotel.setGym(gym);
					databaseHotel.setHotelPicture(picture);

					// add to list to return
					list.add(databaseHotel);
				}
			} // end while()

			catch (SQLException ex) {
				// handle erros here
				System.out.println("\n" + "Something went wrong " + "while executing query..." + "\n");
			}

			return list;

		}
		
	
		/**Used with the sliders created by Jakob, prepares an SQL statement for the database
		*that takes an input of price/distance from slider and returns an arraylist of hotels containing
		* price/distance that is the same of above the input value.
		*/
		public static ArrayList<Hotel> Price(double checkthis){

			ArrayList<Hotel> list = new ArrayList<Hotel>();


			try{
				c = dbConn.getConn();
				stmt = c.createStatement();
				ResultSet rs = stmt.executeQuery( "SELECT * FROM Hotel WHERE Price >= '"+checkthis+"';");

				while (rs.next()) {
					// the array list holding the data from
					// the current row parsed in the result set
					Hotel databaseHotel = new Hotel();

					// get data by column name
					int hid = rs.getInt("HID");
					int rating = rs.getInt("Rating");
					int popularity = rs.getInt("Popularity");
					String name = rs.getString("Name");
					String picture = rs.getString("Picture");
					double price = rs.getDouble("Price");
					double distance = rs.getDouble("Distance");
					boolean pool = rs.getBoolean("Pool");
					boolean gym = rs.getBoolean("Gym");
					boolean bar = rs.getBoolean("Bar");
					boolean pets = rs.getBoolean("Pets");
					boolean breakfast = rs.getBoolean("Breakfast");

					// add to data list
					databaseHotel.setHotelId(hid);
					databaseHotel.setHotelName(name);
					databaseHotel.setStars(rating);
					databaseHotel.setPopularity(popularity);
					databaseHotel.setHotelPrice(price);
					databaseHotel.setDistance(distance);
					databaseHotel.setPool(pool);
					databaseHotel.setBar(bar);
					databaseHotel.setPets(pets);
					databaseHotel.setBreakfast(breakfast);
					databaseHotel.setGym(gym);
					databaseHotel.setHotelPicture(picture);

					// add to list to return
					list.add(databaseHotel);

				} // end while()
			}
			catch (SQLException ex) {
				// handle erros here
				System.out.println("\n" + "Something went wrong " + "while executing query..." + "\n");
			}

			return list;

		}
		/*
		 * Query for the filter when no checkboxes are checked
		 * Takes the price and distance slider in the query aswell
		 */
		public static ArrayList<Hotel> Filter0(double price1, double distance1){

			ArrayList<Hotel> list = new ArrayList<Hotel>();

			
			try{
				c = dbConn.getConn();
				stmt = c.createStatement();
				ResultSet rs = stmt.executeQuery( "SELECT * FROM Hotel WHERE Price >= '"+price1+"' AND Distance >= '"+distance1+"' ;")  ;
				while (rs.next()) {
					// the array list holding the data from
					// the current row parsed in the result set
					Hotel databaseHotel = new Hotel();

					// get data by column name
					int hid = rs.getInt("HID");
					int rating = rs.getInt("Rating");
					int popularity = rs.getInt("Popularity");
					String name = rs.getString("Name");
					String picture = rs.getString("Picture");
					double price = rs.getDouble("Price");
					double distance = rs.getDouble("Distance");
					boolean pool = rs.getBoolean("Pool");
					boolean gym = rs.getBoolean("Gym");
					boolean bar = rs.getBoolean("Bar");
					boolean pets = rs.getBoolean("Pets");
					boolean breakfast = rs.getBoolean("Breakfast");

					// add to data list
					databaseHotel.setHotelId(hid);
					databaseHotel.setHotelName(name);
					databaseHotel.setStars(rating);
					databaseHotel.setPopularity(popularity);
					databaseHotel.setHotelPrice(price);
					databaseHotel.setDistance(distance);
					databaseHotel.setPool(pool);
					databaseHotel.setBar(bar);
					databaseHotel.setPets(pets);
					databaseHotel.setBreakfast(breakfast);
					databaseHotel.setGym(gym);
					databaseHotel.setHotelPicture(picture);

					// add to list to return
					list.add(databaseHotel);

				} // end while()
			}
			catch (SQLException ex) {
				// handle erros here
				System.out.println("\n" + "Something went wrong " + "while executing query..." + "\n");
			}

			return list;

		}

		/*
		 * Query for the filtering when one checkboxes are checked
		 * Takes the price and distance slider in the query aswell
		 */
		public static ArrayList<Hotel> Filter1(String checkthis, double price1, double distance1){

			ArrayList<Hotel> list = new ArrayList<Hotel>();

			
			try{
				c = dbConn.getConn();
				stmt = c.createStatement();
				ResultSet rs = stmt.executeQuery( "SELECT * FROM Hotel WHERE "+checkthis+" == 1 AND Price >= '"+price1+"' AND Distance >= '"+distance1+"' ;")  ;
				while (rs.next()) {
					// the array list holding the data from
					// the current row parsed in the result set
					Hotel databaseHotel = new Hotel();

					// get data by column name
					int hid = rs.getInt("HID");
					int rating = rs.getInt("Rating");
					int popularity = rs.getInt("Popularity");
					String name = rs.getString("Name");
					String picture = rs.getString("Picture");
					double price = rs.getDouble("Price");
					double distance = rs.getDouble("Distance");
					boolean pool = rs.getBoolean("Pool");
					boolean gym = rs.getBoolean("Gym");
					boolean bar = rs.getBoolean("Bar");
					boolean pets = rs.getBoolean("Pets");
					boolean breakfast = rs.getBoolean("Breakfast");

					// add to data list
					databaseHotel.setHotelId(hid);
					databaseHotel.setHotelName(name);
					databaseHotel.setStars(rating);
					databaseHotel.setPopularity(popularity);
					databaseHotel.setHotelPrice(price);
					databaseHotel.setDistance(distance);
					databaseHotel.setPool(pool);
					databaseHotel.setBar(bar);
					databaseHotel.setPets(pets);
					databaseHotel.setBreakfast(breakfast);
					databaseHotel.setGym(gym);
					databaseHotel.setHotelPicture(picture);

					// add to list to return
					list.add(databaseHotel);

				} // end while()
			}
			catch (SQLException ex) {
				// handle erros here
				System.out.println("\n" + "Something went wrong " + "while executing query..." + "\n");
			}

			return list;

		}
		/*
		 * Query for the filtering when two checkboxes are checked
		 * Takes the price and distance slider in the query aswell
		 */
		public static ArrayList<Hotel> Filter2(String checkthis, String checkthis2, double price1, double distance1){

			ArrayList<Hotel> list = new ArrayList<Hotel>();


			try{
				c = dbConn.getConn();
				stmt = c.createStatement();
				ResultSet rs = stmt.executeQuery( "SELECT * FROM Hotel WHERE "+checkthis+" == 1 AND "+checkthis2+" == 1 AND Price >= '"+price1+"' AND Distance >= '"+distance1+"' ;");

				while (rs.next()) {
					// the array list holding the data from
					// the current row parsed in the result set
					Hotel databaseHotel = new Hotel();

					// get data by column name
					int hid = rs.getInt("HID");
					int rating = rs.getInt("Rating");
					int popularity = rs.getInt("Popularity");
					String name = rs.getString("Name");
					String picture = rs.getString("Picture");
					double price = rs.getDouble("Price");
					double distance = rs.getDouble("Distance");
					boolean pool = rs.getBoolean("Pool");
					boolean gym = rs.getBoolean("Gym");
					boolean bar = rs.getBoolean("Bar");
					boolean pets = rs.getBoolean("Pets");
					boolean breakfast = rs.getBoolean("Breakfast");

					// add to data list
					databaseHotel.setHotelId(hid);
					databaseHotel.setHotelName(name);
					databaseHotel.setStars(rating);
					databaseHotel.setPopularity(popularity);
					databaseHotel.setHotelPrice(price);
					databaseHotel.setDistance(distance);
					databaseHotel.setPool(pool);
					databaseHotel.setBar(bar);
					databaseHotel.setPets(pets);
					databaseHotel.setBreakfast(breakfast);
					databaseHotel.setGym(gym);
					databaseHotel.setHotelPicture(picture);

					// add to list to return
					list.add(databaseHotel);

				} // end while()
			}
			catch (SQLException ex) {
				// handle erros here
				System.out.println("\n" + "Something went wrong " + "while executing query..." + "\n");
			}

			return list;

		}
		/*
		 * Query for the filtering when three checkboxes are checked
		 * Takes the price and distance slider in the query aswell
		 */
		public static ArrayList<Hotel> Filter3(String checkthis, String checkthis2, String checkthis3, double price1, double distance1){

			ArrayList<Hotel> list = new ArrayList<Hotel>();


			try{
				c = dbConn.getConn();
				stmt = c.createStatement();
				ResultSet rs = stmt.executeQuery( "SELECT * FROM Hotel WHERE "+checkthis+" == 1 AND "+checkthis2+" == 1 AND "+checkthis3+" == 1 AND Price >= '"+price1+"' AND Distance >= '"+distance1+"' ;");

				while (rs.next()) {
					// the array list holding the data from
					// the current row parsed in the result set
					Hotel databaseHotel = new Hotel();

					// get data by column name
					int hid = rs.getInt("HID");
					int rating = rs.getInt("Rating");
					int popularity = rs.getInt("Popularity");
					String name = rs.getString("Name");
					String picture = rs.getString("Picture");
					double price = rs.getDouble("Price");
					double distance = rs.getDouble("Distance");
					boolean pool = rs.getBoolean("Pool");
					boolean gym = rs.getBoolean("Gym");
					boolean bar = rs.getBoolean("Bar");
					boolean pets = rs.getBoolean("Pets");
					boolean breakfast = rs.getBoolean("Breakfast");

					// add to data list
					databaseHotel.setHotelId(hid);
					databaseHotel.setHotelName(name);
					databaseHotel.setStars(rating);
					databaseHotel.setPopularity(popularity);
					databaseHotel.setHotelPrice(price);
					databaseHotel.setDistance(distance);
					databaseHotel.setPool(pool);
					databaseHotel.setBar(bar);
					databaseHotel.setPets(pets);
					databaseHotel.setBreakfast(breakfast);
					databaseHotel.setGym(gym);
					databaseHotel.setHotelPicture(picture);

					// add to list to return
					list.add(databaseHotel);

				} // end while()
			}
			catch (SQLException ex) {
				// handle erros here
				System.out.println("\n" + "Something went wrong " + "while executing query..." + "\n");
			}

			return list;

		}
		/*
		 * Query for the filtering when four checkboxes are checked
		 * Takes the price and distance slider in the query aswell
		 */
		public static ArrayList<Hotel> Filter4(String checkthis, String checkthis2, String checkthis3, String checkthis4, double price1, double distance1){

			ArrayList<Hotel> list = new ArrayList<Hotel>();


			try{
				c = dbConn.getConn();
				stmt = c.createStatement();
				ResultSet rs = stmt.executeQuery( "SELECT * FROM Hotel WHERE "+checkthis+" == 1 AND "+checkthis2+" == 1 AND "+checkthis3+" == 1 AND "+checkthis4+" == 1 AND Price >= '"+price1+"' AND Distance >= '"+distance1+"' ;");

				while (rs.next()) {
					// the array list holding the data from
					// the current row parsed in the result set
					Hotel databaseHotel = new Hotel();

					// get data by column name
					int hid = rs.getInt("HID");
					int rating = rs.getInt("Rating");
					int popularity = rs.getInt("Popularity");
					String name = rs.getString("Name");
					String picture = rs.getString("Picture");
					double price = rs.getDouble("Price");
					double distance = rs.getDouble("Distance");
					boolean pool = rs.getBoolean("Pool");
					boolean gym = rs.getBoolean("Gym");
					boolean bar = rs.getBoolean("Bar");
					boolean pets = rs.getBoolean("Pets");
					boolean breakfast = rs.getBoolean("Breakfast");

					// add to data list
					databaseHotel.setHotelId(hid);
					databaseHotel.setHotelName(name);
					databaseHotel.setStars(rating);
					databaseHotel.setPopularity(popularity);
					databaseHotel.setHotelPrice(price);
					databaseHotel.setDistance(distance);
					databaseHotel.setPool(pool);
					databaseHotel.setBar(bar);
					databaseHotel.setPets(pets);
					databaseHotel.setBreakfast(breakfast);
					databaseHotel.setGym(gym);
					databaseHotel.setHotelPicture(picture);

					// add to list to return
					list.add(databaseHotel);

				} // end while()
			}
			catch (SQLException ex) {
				// handle erros here
				System.out.println("\n" + "Something went wrong " + "while executing query..." + "\n");
			}

			return list;

		}
		/*
		 * Query for the filtering when five checkboxes are checked
		 * Takes the price and distance slider in the query aswell
		 */
		public static ArrayList<Hotel> Filter5(String checkthis, String checkthis2, String checkthis3, String checkthis4, String checkthis5, double price1, double distance1){

			ArrayList<Hotel> list = new ArrayList<Hotel>();


			try{
				c = dbConn.getConn();
				stmt = c.createStatement();
				ResultSet rs = stmt.executeQuery( "SELECT * FROM Hotel WHERE "+checkthis+" == 1 AND "+checkthis2+" == 1 AND "+checkthis5+" == 1 AND "+checkthis3+" == 1 AND "+checkthis4+" == 1 AND Price >= '"+price1+"' AND Distance >= '"+distance1+"' ;");

				while (rs.next()) {
					// the array list holding the data from
					// the current row parsed in the result set
					Hotel databaseHotel = new Hotel();

					// get data by column name
					int hid = rs.getInt("HID");
					int rating = rs.getInt("Rating");
					int popularity = rs.getInt("Popularity");
					String name = rs.getString("Name");
					String picture = rs.getString("Picture");
					double price = rs.getDouble("Price");
					double distance = rs.getDouble("Distance");
					boolean pool = rs.getBoolean("Pool");
					boolean gym = rs.getBoolean("Gym");
					boolean bar = rs.getBoolean("Bar");
					boolean pets = rs.getBoolean("Pets");
					boolean breakfast = rs.getBoolean("Breakfast");

					// add to data list
					databaseHotel.setHotelId(hid);
					databaseHotel.setHotelName(name);
					databaseHotel.setStars(rating);
					databaseHotel.setPopularity(popularity);
					databaseHotel.setHotelPrice(price);
					databaseHotel.setDistance(distance);
					databaseHotel.setPool(pool);
					databaseHotel.setBar(bar);
					databaseHotel.setPets(pets);
					databaseHotel.setBreakfast(breakfast);
					databaseHotel.setGym(gym);
					databaseHotel.setHotelPicture(picture);

					// add to list to return
					list.add(databaseHotel);

				} // end while()
			}
			catch (SQLException ex) {
				// handle erros here
				System.out.println("\n" + "Something went wrong " + "while executing query..." + "\n");
			}

			return list;

		}

		/*
		 * Query for searching hotels by matching names
		 */
		public static ArrayList<Hotel> SearchHotels(String Search) {

			ArrayList<Hotel> list = new ArrayList<Hotel>();

			String correctsearch = Search.toUpperCase();


			try{
				c = dbConn.getConn();
				stmt = c.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM Hotel Where Name LIKE ('"+correctsearch+"%');");


				while (rs.next()) {
					// the array list holding the data from
					// the current row parsed in the result set
					Hotel databaseHotel = new Hotel();

					// get data by column name
					int hid = rs.getInt("HID");
					int rating = rs.getInt("Rating");
					int popularity = rs.getInt("Popularity");
					String name = rs.getString("Name");
					String picture = rs.getString("Picture");
					double price = rs.getDouble("Price");
					double distance = rs.getDouble("Distance");
					boolean pool = rs.getBoolean("Pool");
					boolean gym = rs.getBoolean("Gym");
					boolean bar = rs.getBoolean("Bar");
					boolean pets = rs.getBoolean("Pets");
					boolean breakfast = rs.getBoolean("Breakfast");

					// add to data list
					databaseHotel.setHotelId(hid);
					databaseHotel.setHotelName(name);
					databaseHotel.setStars(rating);
					databaseHotel.setPopularity(popularity);
					databaseHotel.setHotelPrice(price);
					databaseHotel.setDistance(distance);
					databaseHotel.setPool(pool);
					databaseHotel.setBar(bar);
					databaseHotel.setPets(pets);
					databaseHotel.setBreakfast(breakfast);
					databaseHotel.setGym(gym);
					databaseHotel.setHotelPicture(picture);

					// add to list to return
					list.add(databaseHotel);
				}
			} // end while()

			catch (SQLException ex) {
				// handle erros here
				System.out.println("\n" + "Something went wrong " + "while executing query..." + "\n");
			}

			return list;

		}
		/*
		 * Made by Johan
		 * Deletes a chosen hotel
		 */
		public static void DeleteHotel(String tmp) throws SQLException {

			c = dbConn.getConn();
			stmt = c.createStatement();
			String command = "DELETE FROM Hotel WHERE Name = '"+tmp+"' ;";
			stmt.execute(command);


		}
	}

	