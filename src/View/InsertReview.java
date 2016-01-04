package View;

import java.net.URL;
import java.util.ResourceBundle;

import Application.App;
import Classes.Hotel;
import Database.ReviewJDBC;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
/**
 * InsertReview.java
 * Purpose: Class contains the Insert review functionality.
 * 
 * @author Adin during sprint 3/4
 * @version 1.0
 *
 */



public class InsertReview implements  Initializable, ControlledScreen  {


	@FXML
	private TextField HotelNameField;
	@FXML
	private TextField UserField;
	@FXML
	private TextArea InfoField;
	@FXML
	private CheckBox OneStarCheckBox;
	@FXML
	private CheckBox TwoStarCheckBox;
	@FXML
	private CheckBox ThreeStarCheckBox;
	@FXML
	private CheckBox FourStarCheckBox;
	@FXML
	private CheckBox FiveStarCheckBox;
	@FXML
	private Button insert;
	@FXML
	private Button CancelButton;

	@FXML
	private boolean okClicked = false;

	private MainController main;

	private int Stars = 0;	

	public Hotel ReviewHotel;

	public static ObjectProperty<Hotel> ReviewName;

	/*
	 * initializes the screen and resets all previous inputs to the reviews fields and checkboxes
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		resetHotel();
		ReviewName = new SimpleObjectProperty<Hotel>(new Hotel());
		ReviewName.addListener(new ChangeListener<Hotel>() {

			@Override
			public void changed(ObservableValue<? extends Hotel> observable, Hotel oldValue,
					Hotel newValue) {
				ReviewHotel = ReviewName.get();
				showHotelDetails();
				System.out.println("Hotel recieved in Reviews");
			}
		});

		System.out.println("HotelReview initialized");
	}


	public void setScreenParent(MainController screenParent){
		main = screenParent;
	}
	/*
	 * returns to former page
	 */
	@FXML
	private void Cancel(ActionEvent event){
		main.setScreen(App.HotelOutputID);
	}
	/* 
	 * method for reseting previous entered fields 
	 */
	public void resetHotel(){
		HotelNameField.setText("");
		UserField.setText("");
		InfoField.setText("");
		OneStarCheckBox.setSelected(false);
		TwoStarCheckBox.setSelected(false);
		ThreeStarCheckBox.setSelected(false);
		FourStarCheckBox.setSelected(false);
		FiveStarCheckBox.setSelected(false);
	}
	/*
	 * method for inserting a hotel in the database
	 */
	@FXML
	private void handleOk(ActionEvent event) {
		if (isInputValid()) {

			ReviewJDBC.InsertReview(HotelNameField.getText(), InfoField.getText(),
					UserField.getText(), Stars);
			System.out.println("hotelAdded");

			int tmp = App.update.get();
			tmp = tmp+1;
			App.update.set(tmp);
			resetHotel();
			main.setScreen(App.HotelOutputID);

		}
	}
	/*
	 * Changes the amount of stars of a reviews when a checkbox gets checked. Uncheckes all other
	 * checkboxes.
	 */
	@FXML
	private void OneStarChosen(ActionEvent event) {
		if(OneStarCheckBox.isSelected()==true){
			Stars = 1;
			ThreeStarCheckBox.setSelected(false);
			TwoStarCheckBox.setSelected(false);
			FourStarCheckBox.setSelected(false);
			FiveStarCheckBox.setSelected(false);
		}
	}
	/*
	 * Changes the amount of stars of a reviews when a checkbox gets checked. Uncheckes all other
	 * checkboxes.
	 */
	@FXML
	private void TwoStarChosen(ActionEvent event) {
		if(TwoStarCheckBox.isSelected()==true){
			Stars = 2;
			OneStarCheckBox.setSelected(false);
			ThreeStarCheckBox.setSelected(false);
			FourStarCheckBox.setSelected(false);
			FiveStarCheckBox.setSelected(false);
		}
	}
	/*
	 * Changes the amount of stars of a reviews when a checkbox gets checked. Uncheckes all other
	 * checkboxes.
	 */
	@FXML
	private void ThreeStarChosen(ActionEvent event) {
		if(ThreeStarCheckBox.isSelected()==true){
			Stars = 3;
			OneStarCheckBox.setSelected(false);
			TwoStarCheckBox.setSelected(false);
			FourStarCheckBox.setSelected(false);
			FiveStarCheckBox.setSelected(false);
		}
	}
	/*
	 * Changes the amount of stars of a reviews when a checkbox gets checked. Uncheckes all other
	 * checkboxes.
	 */
	@FXML
	private void FourStarChosen(ActionEvent event) {
		if(FourStarCheckBox.isSelected()==true){
			Stars = 4;
			OneStarCheckBox.setSelected(false);
			TwoStarCheckBox.setSelected(false);
			ThreeStarCheckBox.setSelected(false);
			FiveStarCheckBox.setSelected(false);
		}
	}
	/*
	 * Changes the amount of stars of a reviews when a checkbox gets checked. Uncheckes all other
	 * checkboxes.
	 */
	@FXML
	private void FiveStarChosen(ActionEvent event) {
		if(FiveStarCheckBox.isSelected()==true){
			Stars = 5;
			OneStarCheckBox.setSelected(false);
			TwoStarCheckBox.setSelected(false);
			FourStarCheckBox.setSelected(false);
			ThreeStarCheckBox.setSelected(false);
		}
	}


	/*
	 * Checks if all the fields are filled out and if a check box is checked, otherwise pops error message
	 */
	@FXML
	private boolean isInputValid() {
		String errorMessage = "";

		if (HotelNameField.getText() == null || HotelNameField.getText().length() == 0) {
			errorMessage += "No valid Hotel name \n"; 
		}
		if (InfoField.getText() == null || InfoField.getText().length() == 0) {
			errorMessage += "Not a correct amount of Text \n"; 
		}
		if (UserField.getText() == null || UserField.getText().length() == 0) {
			errorMessage += "Not a valid User \n"; 
		}
		if (OneStarCheckBox.isSelected() == false && TwoStarCheckBox.isSelected() == false 
				&& ThreeStarCheckBox.isSelected() == false && FourStarCheckBox.isSelected() == false 
				&& FiveStarCheckBox.isSelected() == false) {
			errorMessage += "Not valid rating \n"; 
		}


		if (errorMessage.length() == 0) {
			return true;

		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(App.getPrimaryStage());
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("Please correct invalid fields");
			alert.setContentText(errorMessage);
			alert.showAndWait();

			return false;
		}


	}
	public void showHotelDetails() {
		HotelNameField.setText(ReviewHotel.getHotelName());
	}

	public InsertReview() {
	}

}