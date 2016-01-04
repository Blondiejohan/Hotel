package View;



import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Application.App;
import Classes.Hotel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import Database.*;

public class HotelCompareController implements Initializable, ControlledScreen {
	
	
	private MainController main;
	
	

	@FXML
	private TableView<Hotel> HotelComparisonTable1;
	@FXML
	private TableView<Hotel> HotelComparisonTable2;
	@FXML
	private Label FirstHotelLabel;
	@FXML
	private Label PriceLabel1;
	@FXML
	private Label StarsLabel1;
	@FXML
	private Label PopularityLabel1;
	@FXML
	private Label DistanceLabel1;
	@FXML
	private Label BreakfastLabel1;
	@FXML
	private Label BarLabel1;
	@FXML
	private Label GymLabel1;
	@FXML
	private Label PetsLabel1;
	@FXML
	private Label PoolLabel1;
	@FXML
	private TableColumn<Hotel, String> HotelColumn1;
	@FXML
	private TableColumn<Hotel, String> HotelColumn2;
	@FXML
	private Label SecondHotelLabel;
	@FXML
	private Label PriceLabel2;
	@FXML
	private Label StarsLabel2;
	@FXML
	private Label PopularityLabel2;
	@FXML
	private Label DistanceLabel2;
	@FXML
	private Label BreakfastLabel2;
	@FXML
	private Label BarLabel2;
	@FXML
	private Label GymLabel2;
	@FXML
	private Label PetsLabel2;
	@FXML
	private Label PoolLabel2;
	@FXML
	private ArrayList<Hotel> database = SQLiteJDBC.RetrieveHotels();
	@FXML
	private ObservableList<Hotel> outPutList =  FXCollections.observableArrayList(database);
	
	public void setScreenParent(MainController screenParent){
		main = screenParent;
	}
	//Goes back to the main screen when the back button is pressed
	@FXML
	private void goToBack(ActionEvent event){
		main.setScreen(App.HotelOutputID);

	}

	   
	
//Initalizes the controller class
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if(!database.isEmpty()){
		HotelComparisonTable1.setItems(outPutList);
		HotelComparisonTable2.setItems(outPutList);
		HotelColumn1.setCellValueFactory(cellData -> cellData.getValue().HotelNameProperty());
		showHotelDetails1(null);
		HotelColumn2.setCellValueFactory(cellData -> cellData.getValue().HotelNameProperty());
		showHotelDetails2(null);
		HotelComparisonTable1.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> showHotelDetails1(newValue));
		HotelComparisonTable2.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> showHotelDetails2(newValue));
		System.out.println("HotelOutput initialized with content");
		}else{
			System.out.println("HotelOutput initialized without content");
		}
	}
	//Fills the labels with info from the DB
	private void showHotelDetails1(Hotel Hotel) {
		if (Hotel != null) {

			
			FirstHotelLabel.setText(Hotel.getHotelName());
			StarsLabel1.setText(Integer.toString(Hotel.getStars()));
			PopularityLabel1.setText(Integer.toString(Hotel.getPopularity()));
			PriceLabel1.setText(Double.toString(Hotel.getHotelPrice()));
			DistanceLabel1.setText(Double.toString(Hotel.getDistance()));
			BreakfastLabel1.setText(Boolean.toString(Hotel.getBreakfast()));
			BarLabel1.setText(Boolean.toString(Hotel.getBar()));	 
			GymLabel1.setText(Boolean.toString(Hotel.getGym()));	
			PetsLabel1.setText(Boolean.toString(Hotel.getPets()));
			PoolLabel1.setText(Boolean.toString(Hotel.getPool()));
		}else{
//No hotel selected, to remove all text
			FirstHotelLabel.setText("");
			StarsLabel1.setText(Integer.toString(0));
			PopularityLabel1.setText(Integer.toString(0));
			PriceLabel1.setText(Double.toString(0));
			DistanceLabel1.setText(Double.toString(0));
			BreakfastLabel1.setText(Boolean.toString(false));
			BarLabel1.setText(Boolean.toString(false));	 
			GymLabel1.setText(Boolean.toString(false));	
			PetsLabel1.setText(Boolean.toString(false));
			PoolLabel1.setText(Boolean.toString(false));
		}
	}
	
	private void showHotelDetails2(Hotel Hotel) {
		if (Hotel != null) {

			
			SecondHotelLabel.setText(Hotel.getHotelName());
			StarsLabel2.setText(Integer.toString(Hotel.getStars()));
			PopularityLabel2.setText(Integer.toString(Hotel.getPopularity()));
			PriceLabel2.setText(Double.toString(Hotel.getHotelPrice()));
			DistanceLabel2.setText(Double.toString(Hotel.getDistance()));
			BreakfastLabel2.setText(Boolean.toString(Hotel.getBreakfast()));
			BarLabel2.setText(Boolean.toString(Hotel.getBar()));	 
			GymLabel2.setText(Boolean.toString(Hotel.getGym()));	
			PetsLabel2.setText(Boolean.toString(Hotel.getPets()));
			PoolLabel2.setText(Boolean.toString(Hotel.getPool()));
		}else{

			SecondHotelLabel.setText("");
			StarsLabel2.setText(Integer.toString(0));
			PopularityLabel2.setText(Integer.toString(0));
			PriceLabel2.setText(Double.toString(0));
			DistanceLabel2.setText(Double.toString(0));
			BreakfastLabel2.setText(Boolean.toString(false));
			BarLabel2.setText(Boolean.toString(false));	 
			GymLabel2.setText(Boolean.toString(false));	
			PetsLabel2.setText(Boolean.toString(false));
			PoolLabel2.setText(Boolean.toString(false));
		}
	}
	
	@FXML
	public void activate() {
		if(!outPutList.isEmpty()){
		HotelComparisonTable1.setItems(outPutList);
		HotelComparisonTable2.setItems(outPutList);
		HotelColumn1.setCellValueFactory(cellData -> cellData.getValue().HotelNameProperty());
		showHotelDetails2(null);
		HotelColumn2.setCellValueFactory(cellData -> cellData.getValue().HotelNameProperty());
		showHotelDetails2(null);
		HotelComparisonTable1.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> showHotelDetails1(newValue));
		HotelComparisonTable2.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> showHotelDetails2(newValue));
		System.out.println("HotelCompare initialized with content");
		}else{
			System.out.println("HotelCompare initialized without content");	
		}
	}

	public HotelCompareController() {
	}


}