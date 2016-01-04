package View;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import Application.*;
import Classes.*;
import Database.SQLiteJDBC;

/**
 * HotelOverviewController.java
 * Purpose: This is the controller for the admin page of the program.
 * 
 * @author Johan during sprint 1/2
 * @version 1.0
 *
 */
public class HotelOverviewController implements  Initializable, ControlledScreen{
	
	@FXML private TableView<Hotel> HotelTable;
	@FXML private TableColumn<Hotel, String> HotelNameColumn;
	@FXML private Label HotelNameLabel;
	@FXML private Label PriceLabel;
	@FXML private Label StarsLabel;
	@FXML private Label PopularityLabel;
	@FXML private Label DistanceLabel;
	@FXML private Label BreakfastLabel;
	@FXML private Label BarLabel;
	@FXML private Label GymLabel;
	@FXML private Label PetsLabel;
	@FXML private Label PoolLabel;
	@FXML private boolean okClicked = false;
	@FXML private ArrayList<Hotel> databaseOver = SQLiteJDBC.RetrieveHotels();
	@FXML public static Hotel HotelInOverview;
	public static IntegerProperty updateOverview;
	private ObservableList<Hotel> obslist = FXCollections.observableList(databaseOver);
	private MainController main;
	
	/**
	 * This method fills the overview fxml with content when it is loaded.
	 * It contains a listener that updates the content shown.
	 */
	@Override public void initialize(URL location, ResourceBundle resources) {
		if(!databaseOver.isEmpty()){
			updateOverview = new SimpleIntegerProperty(1);
			updateOverview.addListener(new ChangeListener<Number>() {
				@Override public void changed(ObservableValue<? extends Number> observable, Number oldValue,
						Number newValue) {
					setList();
					System.out.println("Overview Updated");
				}
			});
			HotelTable.setItems(obslist);
			HotelNameColumn.setCellValueFactory(cellData -> cellData.getValue().HotelNameProperty());
			showHotelDetails(null);
			HotelTable.getSelectionModel().selectedItemProperty().addListener(
					(observable, oldValue, newValue) -> showHotelDetails(newValue));
			System.out.println("HotelOverview initialized with content");
		}else{
			System.out.println("HotelOverview initialized without content");
		}
	}
	
	/**
	 * This method sets the screen parent.
	 */
	public void setScreenParent(MainController screenParent){
		main = screenParent;
	}

	/**
	 * This method sets the current scene to output.
	 * @param event
	 */
	@FXML private void goToOutput(ActionEvent event){
		main.setScreen(App.HotelOutputID);
	}

	@FXML private void goToRegister(ActionEvent event){
		main.setScreen(App.RegisterScreenID);
	}

	/**
	 * This method sets the information from a chosen hotel.
	 * @param Hotel
	 */
	private void showHotelDetails(Hotel Hotel) {
		if (Hotel != null) {
			HotelNameLabel.setText(Hotel.getHotelName());
			StarsLabel.setText(Integer.toString(Hotel.getStars()));
			PopularityLabel.setText(Integer.toString(Hotel.getPopularity()));
			PriceLabel.setText(Double.toString(Hotel.getHotelPrice()));
			DistanceLabel.setText(Double.toString(Hotel.getDistance()));
			BreakfastLabel.setText(Boolean.toString(Hotel.getBreakfast()));
			BarLabel.setText(Boolean.toString(Hotel.getBar()));	 
			GymLabel.setText(Boolean.toString(Hotel.getGym()));	
			PetsLabel.setText(Boolean.toString(Hotel.getPets()));
			PoolLabel.setText(Boolean.toString(Hotel.getPool()));
		}else{
			HotelNameLabel.setText("");
			StarsLabel.setText(Integer.toString(0));
			PopularityLabel.setText(Integer.toString(0));
			PriceLabel.setText(Double.toString(0));
			DistanceLabel.setText(Double.toString(0));
			BreakfastLabel.setText(Boolean.toString(false));
			BarLabel.setText(Boolean.toString(false));	 
			GymLabel.setText(Boolean.toString(false));	
			PetsLabel.setText(Boolean.toString(false));
			PoolLabel.setText(Boolean.toString(false));
		}
	}

	/**
	 * This method updates the fxml with list from the database.
	 */
	@FXML public void setList(){
		databaseOver = SQLiteJDBC.RetrieveHotels();
		obslist = FXCollections.observableList(databaseOver);
		HotelTable.setItems(obslist);
	}

	/**
	 * This method sets the current scene to edit.
	 * @param event
	 */
	@FXML private void handleNewHotel(ActionEvent event) {
		main.setScreen(App.HotelEditID);
	}

	/**
	 * This method deletes the hotel the user have selected.
	 * @param event
	 */
	@FXML private void GoToDeleteHotel(ActionEvent event) {
		int selectedIndex = HotelTable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			String tmp;
			tmp = HotelTable.getSelectionModel().getSelectedItem().getHotelName();
			try {
				SQLiteJDBC.DeleteHotel(tmp);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(App.getPrimaryStage());
			alert.setTitle("No Hotel chosen");
			alert.setHeaderText("No Hotel Selected");
			alert.setContentText("Please select a Hotel in the Label.");
			alert.showAndWait();
		}
		int tmp = App.update.get();
		tmp = tmp+1;
		App.update.set(tmp);
	}

	/**
	 * This method sets the current scene to edit.
	 * It also send the chosen hotel to edit so it can be previewed.
	 * @param event
	 */
	@FXML private void goToEditHotel(ActionEvent event){
		Hotel hotel = HotelTable.getSelectionModel().getSelectedItem();
		if (hotel != null) {
			App.appHotel.set(hotel);
			String tmp;
			tmp = HotelTable.getSelectionModel().getSelectedItem().getHotelName();
			try {
				SQLiteJDBC.DeleteHotel(tmp);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			main.setScreen(App.HotelEditID);
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(App.getPrimaryStage());
			alert.setTitle("No Hotel chosen");
			alert.setHeaderText("No Hotel Selected");
			alert.setContentText("Please select a Hotel in the Label.");
			alert.showAndWait();
		}
	}
	
	/**
	 * This method states that this class is the controller for overview.
	 */
	public HotelOverviewController() {
	}
}