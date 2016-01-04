package View;
import java.net.URL;
import java.util.ResourceBundle;
import Application.App;
import Classes.Hotel;
import Database.SQLiteJDBC;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

/**
 * HotelEditController.java
 * Purpose: This class create and edit hotels.
 * 
 * @author Johan during sprint 1/2
 * @version 1.0
 *
 */
public class HotelEditController implements  Initializable, ControlledScreen {

	@FXML private TextField HotelPictureURL;
	@FXML private TextField HotelNameField;
	@FXML private TextField PriceField;
	@FXML private TextField StarsField;
	@FXML private TextField PopularityField;
	@FXML private TextField DistanceField;
	@FXML private CheckBox BreakfastCheckBox;
	@FXML private CheckBox BarCheckBox;
	@FXML private CheckBox GymCheckBox;
	@FXML private CheckBox PetsCheckBox;
	@FXML private CheckBox PoolCheckBox;
	@FXML private int PoolBoolean;
	@FXML private int BreakfastBoolean;
	@FXML private int GymBoolean;
	@FXML private int PetsBoolean;
	@FXML private int BarBoolean;
	@FXML private boolean okClicked = false;
	MainController main;
	public Hotel editHotel;
	public static ObjectProperty<Hotel> hotelEdit;

	/**
	 * This method fills the FXML page with information when it is loaded.
	 * It also contains a ObjectProperty with a listener so when the user want to edit
	 * a hotel the information from Overview gets updated and shown in the edit page.
	 */
	@Override public void initialize(URL location, ResourceBundle resources) {
		resetHotel();
		hotelEdit = new SimpleObjectProperty<Hotel>(new Hotel());
		hotelEdit.addListener(new ChangeListener<Hotel>() {
			@Override
			public void changed(ObservableValue<? extends Hotel> observable, Hotel oldValue,
					Hotel newValue) {
				editHotel = hotelEdit.get();
				showHotelDetails();
				System.out.println("Hotel recieved in  Edit");
			}
		});
		System.out.println("HotelEdit initialized");
	}

	/**
	 * This method sets the screen parent.
	 */
	public void setScreenParent(MainController screenParent){
		main = screenParent;
	}

	/**
	 * This method cancel the creating or editing of a hotel and goes back to the overview page. 
	 * @param event
	 */
	@FXML private void Canclel(ActionEvent event){
		main.setScreen(App.HotelOverviewID);
	}

	/**
	 * This method creates a hotel with the input from the different fields and checkboxes.
	 * It then brings you back to Overview.
	 * @param event
	 */
	@FXML private void handleOk(ActionEvent event) {
		if (isInputValid()) {
			SQLiteJDBC.InsertHotel(HotelPictureURL.getText(), HotelNameField.getText(), Double.parseDouble(PriceField.getText()), Integer.parseInt(StarsField.getText()), Integer.parseInt(PopularityField.getText()), Double.parseDouble(DistanceField.getText()), BreakfastBoolean, BarBoolean, GymBoolean, PetsBoolean, PoolBoolean);
			resetHotel();
			int tmp = App.update.get();
			tmp = tmp+1;
			App.update.set(tmp);
			main.setScreen(App.HotelOverviewID);
		}
	}

	/**
	 * This method checks if pool is chosen and changes the boolean into sql understandable code.
	 * @param event
	 */
	@FXML private void poolChosen(ActionEvent event) {
		if(PoolCheckBox.isSelected()==true){
			PoolBoolean = 1;
		}else{
			PoolBoolean = 0;
		}
	}

	/**
	 * This method checks if bar is chosen and changes the boolean into sql understandable code.
	 * @param event
	 */
	@FXML private void barChosen(ActionEvent event) {
		if(BarCheckBox.isSelected()==true){
			BarBoolean = 1;
		}else{
			BarBoolean = 0;
		}
	}

	/**
	 * This method checks if breakfast is chosen and changes the boolean into sql understandable code.
	 * @param event
	 */
	@FXML private void breakfastChosen(ActionEvent event) {
		if(BreakfastCheckBox.isSelected()==true){
			BreakfastBoolean = 1;
		}else{
			BreakfastBoolean = 0;
		}
	}

	/**
	 * This method checks if pets is chosen and changes the boolean into sql understandable code.
	 * @param event
	 */
	@FXML private void petsChosen(ActionEvent event) {
		if(PetsCheckBox.isSelected()==true){
			PetsBoolean = 1;
		}else{
			PetsBoolean = 0;
		}
	}

	/**
	 * This method checks if gym is chosen and changes the boolean into sql understandable code.
	 * @param event
	 */
	@FXML private void gymChosen(ActionEvent event) {
		if(GymCheckBox.isSelected()==true){
			GymBoolean = 1;
		}else{
			GymBoolean = 0;
		}
	}

	/**
	 * This method wipes the information shown on the edit FXML.
	 */
	public void resetHotel(){
		HotelNameField.setText("");
		PriceField.setText("");
		StarsField.setText("");
		PopularityField.setText("");
		DistanceField.setText("");
		BreakfastCheckBox.setSelected(false);
		BarCheckBox.setSelected(false);
		GymCheckBox.setSelected(false);
		PetsCheckBox.setSelected(false);
		PoolCheckBox.setSelected(false);
		HotelPictureURL.setText(String.valueOf(""));
	}

	/**
	 * This method shows the information of current a hotel on the edit FXML.
	 */
	public void showHotelDetails() {
		HotelPictureURL.setText(editHotel.getHotelPicture());
		HotelNameField.setText(editHotel.getHotelName());
		PriceField.setText(String.valueOf(editHotel.getHotelPrice()));
		StarsField.setText(String.valueOf(editHotel.getStars()));
		PopularityField.setText(String.valueOf(editHotel.getPopularity()));
		DistanceField.setText(String.valueOf(editHotel.getDistance()));
		BreakfastCheckBox.setText(String.valueOf(editHotel.getBreakfast()));
		BarCheckBox.setText(String.valueOf(editHotel.getBar()));
		GymCheckBox.setText(String.valueOf(editHotel.getGym()));
		PetsCheckBox.setText(String.valueOf(editHotel.getPets()));
		PoolCheckBox.setText(String.valueOf(editHotel.getPool()));
	}

	/**
	 * This method checks if the user have inputed information in the required fields.
	 * @return
	 */
	@FXML private boolean isInputValid() {
		String errorMessage = "";
		if (HotelPictureURL.getText() == null || HotelPictureURL.getText().length() == 0) {
			errorMessage += "Not a valid url \n";
		}
		if (HotelNameField.getText() == null || HotelNameField.getText().length() == 0) {
			errorMessage += "No valid Hotel name \n"; 
		}
		if (StarsField.getText() == null || StarsField.getText().length() == 0) {
			errorMessage += "Not a correct amount of stars \n"; 
		}	
		if (PopularityField.getText() == null || PopularityField.getText().length() == 0) {
			errorMessage += "Not a correct amount of popularity \n"; 
		}	
		if (DistanceField.getText() == null || DistanceField.getText().length() == 0) {
			errorMessage += "Not a valid distance \n"; 
		}
		if (PriceField.getText() == null || PriceField.getText().length() == 0) {
			errorMessage += "Not a valid Price \n"; 
		}
		if (errorMessage.length() == 0) {
			return true;
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(App.getPrimaryStage());
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("Please correct invalid fields");
			alert.setContentText(errorMessage);
			errorMessage="";
			alert.showAndWait();
			return false;
			
		}
	}

	/**
	 * This method states that this class is the controller for output.
	 */
	public HotelEditController() {
	}
}