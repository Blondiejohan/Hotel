package Classes;

import javafx.beans.property.StringProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


public class Reviews {

	private final StringProperty HotelName;
	private final StringProperty User;
	private final StringProperty Date;
	private final StringProperty Info;
	private final IntegerProperty Stars;



	public Reviews() {
			this.HotelName = new SimpleStringProperty();
			this.Stars = new SimpleIntegerProperty();
			this.Date = new SimpleStringProperty();
			this.Info = new SimpleStringProperty();
			this.User = new SimpleStringProperty();
	}



	public Reviews(String HotelName, String Info, String Date, String User, int Stars) {
		this.HotelName = new SimpleStringProperty(HotelName);
		this.Info = new SimpleStringProperty(Info);
		this.Date = new SimpleStringProperty(Date);
		this.User = new SimpleStringProperty(User);
		this.Stars = new SimpleIntegerProperty(Stars);
	}


	//HotelName
	public String getRHotelName() {
		return HotelName.get();
	}

	public void setRHotelName(String HotelName) {
		this.HotelName.set(HotelName);
	}
	public StringProperty RHotelNameProperty() {
		return HotelName;
	}

	//Info
	public String getHotelInfo() {
		return Info.get();
	}

	public void setReviewInfo(String HotelName) {
		this.Info.set(HotelName);
	}

	public StringProperty InfoProperty() {
		return Info;
	}

	//Date
	public String getReviewDate() {
		return Date.get();
	}

	public void setReviewDate(String HotelName) {
		this.Date.set(HotelName);
	}

	public StringProperty ReviewInfoProperty() {
		return Date;
	}
	
	//User
	public String getUser() {
		return User.get();
	}

	public void setUser(String HotelName) {
		this.User.set(HotelName);
	}

	public StringProperty HotelUserProperty() {
		return User;
	}
	
	//Stars
	public int getStars() {
		return Stars.get();
	}

	public void setStars(int Stars) {
		this.Stars.set(Stars);
	}

	public IntegerProperty StarsProperty() {
		return Stars;
	}
}

