package Classes;
import javafx.beans.property.StringProperty;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Hotel.java
 * Purpose: Class contains the hotel.
 * 
 * @author Johan during sprint 1/2
 * @version 1.0
 *
 */
public class Hotel {
	private final IntegerProperty HID;
	private final StringProperty HotelName;
	private final IntegerProperty Stars;
	private final DoubleProperty Price;
	private final IntegerProperty Popularity;
	private final DoubleProperty Distance;
	private final BooleanProperty Breakfast;
	private final BooleanProperty Bar;
	private final BooleanProperty Gym;
	private final BooleanProperty Pets;
	private final BooleanProperty Pool;
	private final StringProperty Picture;

	/**
	 * This method is used to create an empty hotel.
	 */
	public Hotel() {
		this.Picture = new SimpleStringProperty();
		this.HotelName = new SimpleStringProperty();
		this.Stars = new SimpleIntegerProperty();
		this.Popularity = new SimpleIntegerProperty();
		this.Distance = new SimpleDoubleProperty();
		this.Breakfast = new SimpleBooleanProperty();
		this.Bar = new SimpleBooleanProperty();
		this.Gym = new SimpleBooleanProperty();
		this.Pets = new SimpleBooleanProperty();
		this.Pool = new SimpleBooleanProperty();
		this.HID = new SimpleIntegerProperty();
		this.Price = new SimpleDoubleProperty();
	}


	/**
	 * This method is used to create a new hotel with parameters.
	 * 
	 * @param Picture
	 * @param HID
	 * @param HotelName
	 * @param Price
	 * @param Stars
	 * @param Popularity
	 * @param Distance
	 * @param Breakfast
	 * @param Bar
	 * @param Gym
	 * @param Pets
	 * @param Pool
	 */
	public Hotel(String Picture, int HID, String HotelName, double Price, int Stars, int Popularity, double Distance, boolean Breakfast, Boolean Bar, Boolean Gym, Boolean Pets, Boolean Pool) {
		this.Picture = new SimpleStringProperty();
		this.HotelName = new SimpleStringProperty(HotelName);
		this.Stars = new SimpleIntegerProperty(Stars);
		this.Popularity = new SimpleIntegerProperty(Popularity);
		this.Distance = new SimpleDoubleProperty(Distance);
		this.Breakfast = new SimpleBooleanProperty(Breakfast);
		this.Bar = new SimpleBooleanProperty(Bar);
		this.Gym = new SimpleBooleanProperty(Gym);
		this.Pets = new SimpleBooleanProperty(Pets);
		this.Pool = new SimpleBooleanProperty(Pool);
		this.HID = new SimpleIntegerProperty(HID);
		this.Price = new SimpleDoubleProperty(Price);
	}


	/**
	 * This method gets the hotels picture.
	 * @return String with picture url.
	 */
	public String getHotelPicture() {
		return Picture.get();
	}
	
	/**
	 * This method sets the picture of the hotel.
	 * @param HotelName
	 */
	public void setHotelPicture(String HotelName) {
		this.Picture.set(HotelName);
	}
	
	/**
	 * This method gets the property of the hotels picture.
	 * @return StringProperty containing the hotels picture url.
	 */
	public StringProperty HotelPictureProperty() {
		return Picture;
	}
	
	/**
	 * This method gets the hotels name.
	 * @return String with hotels name.
	 */
	public String getHotelName() {
		return HotelName.get();
	}
	
	/**
	 * This method sets the name of the hotel.
	 * @param HotelName
	 */
	public void setHotelName(String HotelName) {
		this.HotelName.set(HotelName);
	}
	
	/**
	 * This method gets the property of the hotels name.
	 * @return StringProperty containing hotels name.
	 */
	public StringProperty HotelNameProperty() {
		return HotelName;
	}
	
	/**
	 * This method gets the hotels Id.
	 * @return integer containing hotels Id.
	 */
	public int getHotelId() {
		return HID.get();
	}
	
	/**
	 * This method sets the Id of the hotel
	 * @param HotelName
	 */
	public void setHotelId(int HotelName) {
		this.HID.set(HotelName);
	}
	
	/**
	 * This method gets the property of the hotels Id.
	 * @return IntegerProperty containing hotels Id.
	 */
	public IntegerProperty HotelIdProperty() {
		return HID;
	}
	
	/**
	 * This method gets the hotels price.
	 * @return Double containing hotels price.
	 */
	public double getHotelPrice() {
		return Price.get();
	}
	
	/**
	 * This method sets the price of the hotel.
	 * @param HotelName
	 */
	public void setHotelPrice(double HotelName) {
		this.Price.set(HotelName);
	}
	
	/**
	 * This method gets the property of the hotels price.
	 * @return DooubleProperty containing hotels price.
	 */
	public DoubleProperty HotelPriceProperty() {
		return Price;
	}

	/**
	 * This method gets the hotels stars.
	 * @return Integer containing the hotels stars.
	 */
	public int getStars() {
		return Stars.get();
	}
	
	/**
	 * This method sets the stars of the hotel.
	 * @param Stars
	 */
	public void setStars(int Stars) {
		this.Stars.set(Stars);
	}
	
	/**
	 * This method gets the property of the hotels stars.
	 * @return IntegerProperty containing hotels stars.
	 */
	public IntegerProperty StarsProperty() {
		return Stars;
	}

	/**
	 * This method gets the hotels popularity.
	 * @return Integer containing the hotels popularity.
	 */
	public int getPopularity() {
		return Popularity.get();
	}
	
	/**
	 * This method sets the popularity of the hotel.
	 * @param Popularity
	 */
	public void setPopularity(int Popularity) {
		this.Popularity.set(Popularity);
	}
	
	/**
	 * This method gets the property of the hotels popularity.
	 * @return IntegerProperty containing hotels popularity.
	 */
	public IntegerProperty StarsPopularity() {
		return Popularity;
	}

	/**
	 * This method gets the distance of the hotel.
	 * @return Double containing the hotels distance.
	 */
	public double getDistance() {
		return Distance.get();
	}
	
	/**
	 * This method sets the distance of the hotel.
	 * @param Distance
	 */
	public void setDistance(double Distance) {
		this.Distance.set(Distance);
	}
	
	/**
	 * This method gets the property of the hotels distance.
	 * @return DoubleProperty containing the hotels distance.
	 */
	public DoubleProperty DistanceProperty() {
		return Distance;
	}

	/**
	 * This method gets the hotels breakfast status.
	 * @return Boolean containing the hotels breakfast status.
	 */
	public boolean getBreakfast() {
		return Breakfast.get();
	}
	
	/**
	 * This method sets the hotels breakfast status.
	 * @param Breakfast
	 */
	public void setBreakfast(boolean Breakfast) {
		this.Breakfast.set(Breakfast);
	}
	
	/**
	 * This method gets the property of the hotels breakfast status.
	 * @return BooleanProperty containing the hotels breakfast status.
	 */
	public BooleanProperty BreakfastProperty() {
		return Breakfast;
	}

	/**
	 * This method gets the hotels bar status.
	 * @return Boolean containing the hotels bar status.
	 */
	public boolean getBar() {
		return Bar.get();
	}
	
	/**
	 * This method sets the hotels bar status.
	 * @param Bar
	 */
	public void setBar(boolean Bar) {
		this.Bar.set(Bar);
	}
	
	/**
	 * This method gets the property of the hotels bar status.
	 * @return BooleanProperty containing the hotels bar status.
	 */
	public BooleanProperty BarProperty() {
		return Bar;
	}

	/**
	 * This method gets the hotels gym status.
	 * @return Boolean containing the hotels gym status.
	 */
	public boolean getGym() {
		return Gym.get();
	}
	
	/**
	 * This method sets the hotels gym status.
	 * @param Gym
	 */
	public void setGym(boolean Gym) {
		this.Gym.set(Gym);
	}
	
	/**
	 * This method gets the property of the hotels gym status.
	 * @return BooleanProperty containing the hotels gym status.
	 */
	public BooleanProperty GymProperty() {
		return Gym;
	}

	/**
	 * This method contains the hotels pets status.
	 * @return Boolean containing the hotels pets status.
	 */
	public boolean getPets() {
		return Pets.get();
	}
	
	/**
	 * This method sets the hotels pets status.
	 * @param Pets
	 */
	public void setPets(boolean Pets) {
		this.Pets.set(Pets);
	}
	
	/**
	 * This method gets the property of the hotls pets status.
	 * @return BooleanProperty containing the hotels pets status.
	 */
	public BooleanProperty PetsProperty() {
		return Pets;
	}

	/**
	 * This method gets the hotels pool status.
	 * @return Boolean containing the hotels pool status.
	 */
	public boolean getPool() {
		return Pool.get();
	}
	
	/**
	 * This method sets the hotels pool status.
	 * @param Pool
	 */
	public void setPool(boolean Pool) {
		this.Pool.set(Pool);
	}
	
	/**
	 * This method gets the property of the hotels pool status.
	 * @return BooleanProperty containing the hotels pool status.
	 */
	public BooleanProperty PoolProperty() {
		return Pool;
	}
}


