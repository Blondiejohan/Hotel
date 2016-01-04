package View;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import Application.App;
import Classes.Hotel;
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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import Database.*;

/**
 * HotelOutputController.java
 * Purpose: This is the controller for the main scene of this program.
 * 
 * @author Johan during sprint 2, content not created by Johan is stated elsewhere.
 * @version 1.0
 *
 */
public class HotelOutputController implements Initializable, ControlledScreen {

	@FXML private TableView<Hotel> HotelTable;
	@FXML private Image oneStar;
	@FXML private Image twoStar;
	@FXML private Image threeStar;
	@FXML private Image fourStar;
	@FXML private Image fiveStar;
	@FXML private Image noStar;
	@FXML private Image header;
	@FXML private ImageView starFrame;
	@FXML private ImageView popularityFrame;
	@FXML private Button refresh;
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
	@FXML private Image pic;
	@FXML private ImageView picFrame;
	@FXML private ArrayList<Hotel> databaseOut = SQLiteJDBC.RetrieveHotels();
	@FXML private CheckBox CheapestHotel;
	@FXML private ArrayList<Hotel> dbOrderPrice = SQLiteJDBC.OrderByPrice();
	@FXML private ObservableList<Hotel> CheapList = FXCollections.observableArrayList(dbOrderPrice);
	@FXML private CheckBox DistanceHotel;
	@FXML private ArrayList<Hotel> dbOrderDistance = SQLiteJDBC.OrderByDistance();
	@FXML private ObservableList<Hotel> DistanceList = FXCollections.observableArrayList(dbOrderDistance);
	@FXML private ObservableList<Hotel> obslist = FXCollections.observableList(databaseOut);
	@FXML private CheckBox checkfilter1;
	@FXML private CheckBox checkfilter2;
	@FXML private CheckBox checkfilter3;
	@FXML private CheckBox checkfilter4;
	@FXML private CheckBox checkfilter5;
	@FXML private CheckBox PopularityHotel;
	@FXML private TextField SearchField;
	@FXML private ArrayList<Hotel> dbSearchHotel = SQLiteJDBC.RetrieveHotels();
	@FXML private ObservableList<Hotel> SearchList = FXCollections.observableArrayList(dbSearchHotel);
	@FXML private ArrayList<Hotel> dbOrderPopular = SQLiteJDBC.OrderByPopularity();
	@FXML private ObservableList<Hotel> PopularList = FXCollections.observableArrayList(dbOrderPopular);
	@FXML private Slider PriceSlider;
	@FXML private ArrayList<Hotel> dbFilterAll =  SQLiteJDBC.RetrieveHotels();
	@FXML private ObservableList<Hotel> filterList = FXCollections.observableArrayList(dbFilterAll);
	@FXML private Slider DistanceSlider;
	@FXML private static ArrayList<String> filters = new ArrayList<String>();	
	private double tmp1;
	private double tmp2;
	private String temporarySearch = "";
	private MainController main;
	public static IntegerProperty updateOutput;
	
	/**
	 * This method sets the mainController as screenParent.
	 */
	public void setScreenParent(MainController screenParent){
		main = screenParent;
	}
	
	/**
	 * This method sets the current scene to the login FXML.
	 * @param event
	 */
	@FXML private void goToAdmin(ActionEvent event){
		main.setScreen(App.LoginScreenID);
	}
	
	@FXML private void goToReview(ActionEvent event){
		Hotel hotel = HotelTable.getSelectionModel().getSelectedItem();
		if (hotel != null) {
			App.thisHotel.set(hotel);
			main.setScreen(App.HotelInsertId);
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(App.getPrimaryStage());
			alert.setTitle("No Hotel chosen");
			alert.setHeaderText("No Hotel Selected");
			alert.setContentText("Please select a Hotel in the Label.");
			alert.showAndWait();
		}
	}
	
	@FXML private void goToCompare(ActionEvent event){
		main.setScreen(App.HotelCompareId);
	}
	
	
	@FXML private void countFilters(ActionEvent event){
		if(checkfilter1.isSelected()){
			filters.add(checkfilter1.getText());
		}
		if(checkfilter2.isSelected()){
			filters.add(checkfilter2.getText());
		}
		if(checkfilter3.isSelected()){
			filters.add(checkfilter3.getText());
		}
		if(checkfilter4.isSelected()){
			filters.add(checkfilter4.getText());
		}
		if(checkfilter5.isSelected()){
			filters.add(checkfilter5.getText());
		}
		System.out.println(filters.size());
	}
	
	@FXML private void filterAll(ActionEvent event){	
		tmp1 = PriceSlider.getValue();
		tmp2 = DistanceSlider.getValue();
		if(!filterList.isEmpty()&& filters.size()== 1){
				dbFilterAll = SQLiteJDBC.Filter1(filters.get(0),tmp1,tmp2);
				filterList = FXCollections.observableList(dbFilterAll);
				HotelTable.setItems(filterList);
				System.out.println("Filtaar");
				}
		else if(!filterList.isEmpty() && filters.size()== 3){
					dbFilterAll = SQLiteJDBC.Filter2(filters.get(0),filters.get(1),tmp1,tmp2);
					filterList = FXCollections.observableList(dbFilterAll);
					HotelTable.setItems(filterList);
					System.out.println("Filter initialized");
					}
		else if(filters.size()== 6 && !filterList.isEmpty()){
					dbFilterAll = SQLiteJDBC.Filter3(filters.get(0),filters.get(1),filters.get(2),tmp1,tmp2);
					filterList = FXCollections.observableList(dbFilterAll);
					HotelTable.setItems(filterList);
					System.out.println("Filter initialized");
			}
		else if(filters.size()== 10 && !filterList.isEmpty()){
					dbFilterAll = SQLiteJDBC.Filter4(filters.get(0),filters.get(1),filters.get(2),filters.get(3),tmp1,tmp2);
					filterList = FXCollections.observableList(dbFilterAll);
					HotelTable.setItems(filterList);
					System.out.println("Filter initialized");
					}
		else if(filters.size()== 15 && !filterList.isEmpty()){
					dbFilterAll = SQLiteJDBC.RetrieveHotels();
					filterList = FXCollections.observableList(dbFilterAll);
					HotelTable.setItems(filterList);
					System.out.println("Filter initialized");
					}
			}
	
	@FXML private void searchHotels(ActionEvent event){	
		temporarySearch = SearchField.getText();
		if(!SearchList.isEmpty()){
				dbSearchHotel = SQLiteJDBC.SearchHotels(temporarySearch);
				SearchList = FXCollections.observableList(dbSearchHotel);
				HotelTable.setItems(SearchList);
				System.out.println("Search initialized");
				}
			}
	
	@FXML private void ViewReviews(ActionEvent event){
		Hotel hotel = HotelTable.getSelectionModel().getSelectedItem();
		if (hotel != null) {
			App.appViewHotel.set(hotel);
			main.setScreen(App.HotelViewAllReviewId);
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
	 * This method fills the Output FXML with information when it is loaded.
	 * It contains a listener that makes the fxml update its information.
	 */
	@Override public void initialize(URL location, ResourceBundle resources) {
		if(!obslist.isEmpty()){
			updateOutput = new SimpleIntegerProperty(1);
			updateOutput.addListener(new ChangeListener<Number>() {
				@Override public void changed(ObservableValue<? extends Number> observable, Number oldValue,
						Number newValue) {
					setList();
					System.out.println("Output Updated");
				}
			});
			HotelTable.setItems(obslist);
			HotelNameColumn.setCellValueFactory(cellData -> cellData.getValue().HotelNameProperty());
			showHotelDetails(null);
			HotelTable.getSelectionModel().selectedItemProperty().addListener(
					(observable, oldValue, newValue) -> showHotelDetails(newValue));
			System.out.println("HotelOutput initialized with content");
		}else{
			System.out.println("HotelOutput initialized without content");
		}
	}

	@FXML private void CheapestHotel(ActionEvent event){
		if(CheapestHotel.isSelected()){	
			if(!CheapList.isEmpty()){
				dbOrderPrice = SQLiteJDBC.OrderByPrice();
				HotelTable.setItems(CheapList);
				HotelNameColumn.setCellValueFactory(cellData -> cellData.getValue().HotelNameProperty());
				showHotelDetails(null);
				HotelTable.getSelectionModel().selectedItemProperty().addListener(
						(observable, oldValue, newValue) -> showHotelDetails(newValue));
				System.out.println("CheapestHotel initialized with content");
			}else{
				System.out.println("CheapestHotel initialized without content");
			}
		}else{
			if(!databaseOut.isEmpty()){
				databaseOut = SQLiteJDBC.RetrieveHotels();
				HotelTable.setItems(obslist);
				HotelNameColumn.setCellValueFactory(cellData -> cellData.getValue().HotelNameProperty());
				showHotelDetails(null);
				HotelTable.getSelectionModel().selectedItemProperty().addListener(
						(observable, oldValue, newValue) -> showHotelDetails(newValue));
				System.out.println("HotelOutput initialized with content");
			}else{
				System.out.println("HotelOutput initialized without content");
			}
		}
	}
	
	@FXML private void PopularHotel(ActionEvent event){
		if(PopularityHotel.isSelected()){	
			if(!PopularList.isEmpty()){
				dbOrderPopular = SQLiteJDBC.OrderByPopularity();
				HotelTable.setItems(PopularList);
				HotelNameColumn.setCellValueFactory(cellData -> cellData.getValue().HotelNameProperty());
				showHotelDetails(null);
				HotelTable.getSelectionModel().selectedItemProperty().addListener(
						(observable, oldValue, newValue) -> showHotelDetails(newValue));
				System.out.println("PopularHotel initialized with content");
			}else{
				System.out.println("PopularHotel initialized without content");
			}
		}else{
			if(!databaseOut.isEmpty()){
				databaseOut = SQLiteJDBC.RetrieveHotels();
				HotelTable.setItems(obslist);
				HotelNameColumn.setCellValueFactory(cellData -> cellData.getValue().HotelNameProperty());
				showHotelDetails(null);
				HotelTable.getSelectionModel().selectedItemProperty().addListener(
						(observable, oldValue, newValue) -> showHotelDetails(newValue));
				System.out.println("HotelOutput initialized with content");
			}else{
				System.out.println("HotelOutput initialized without content");
			}
		}
	}

	@FXML private void DistanceHotel(ActionEvent event){
		if(DistanceHotel.isSelected()){	
			if(!DistanceList.isEmpty()){
				dbOrderDistance = SQLiteJDBC.OrderByDistance();
				HotelTable.setItems(DistanceList);
				HotelNameColumn.setCellValueFactory(cellData -> cellData.getValue().HotelNameProperty());
				showHotelDetails(null);
				HotelTable.getSelectionModel().selectedItemProperty().addListener(
						(observable, oldValue, newValue) -> showHotelDetails(newValue));
				System.out.println("DistanceHotel initialized with content");
			}else{
				System.out.println("DistanceHotel initialized without content");
			}
		}else{
			if(!databaseOut.isEmpty()){
				databaseOut = SQLiteJDBC.RetrieveHotels();
				HotelTable.setItems(obslist);
				HotelNameColumn.setCellValueFactory(cellData -> cellData.getValue().HotelNameProperty());
				showHotelDetails(null);
				HotelTable.getSelectionModel().selectedItemProperty().addListener(
						(observable, oldValue, newValue) -> showHotelDetails(newValue));
				System.out.println("HotelOutput initialized with content");
			}else{
				System.out.println("HotelOutput initialized without content");
			}
		}
	}
	
	/**
	 * This method updates the information shown with what's on the database.
	 */
	@FXML public void setList(){
		databaseOut = SQLiteJDBC.RetrieveHotels();
		obslist = FXCollections.observableList(databaseOut);
		HotelTable.setItems(obslist);
	}

	/**
	 * This method updates the information and images showed in the labels for a chosen hotel.
	 * @param Hotel
	 */
	private void showHotelDetails(Hotel Hotel) {
		if (Hotel != null) {
			HotelNameLabel.setText(Hotel.getHotelName());
			int tmp = Hotel.getStars();
			if(tmp==1){
				oneStar = new Image("file:oneStar.jpg");
				starFrame.setImage(oneStar);
			}else if(tmp==2){
				twoStar = new Image("file:twoStar.jpg");
				starFrame.setImage(twoStar);
			}else if(tmp==3){
				threeStar = new Image("file:threeStar.jpg");
				starFrame.setImage(threeStar);
			}else if(tmp==4){
				fourStar = new Image("file:fourStar.jpg");
				starFrame.setImage(fourStar);
			}else if(tmp==5){
				fiveStar = new Image("file:fiveStar.jpg");
				starFrame.setImage(fiveStar);
			}
			int tmp2 = Hotel.getPopularity();
			if(tmp2==1){
				oneStar = new Image("file:oneStar.jpg");
				popularityFrame.setImage(oneStar);
			}else if(tmp2==2){
				twoStar = new Image("file:twoStar.jpg");
				popularityFrame.setImage(twoStar);
			}else if(tmp2==3){
				threeStar = new Image("file:threeStar.jpg");
				popularityFrame.setImage(threeStar);
			}else if(tmp2==4){
				fourStar = new Image("file:fourStar.jpg");
				popularityFrame.setImage(fourStar);
			}else if(tmp2==5){
				fiveStar = new Image("file:fiveStar.jpg");
				popularityFrame.setImage(fiveStar);
			}
			PriceLabel.setText(Double.toString(Hotel.getHotelPrice()));
			DistanceLabel.setText(Double.toString(Hotel.getDistance()));
			if(Hotel.getBar()==true){
				BarLabel.setText("Yes");
			}else{
				BarLabel.setText("No");
			}
			if(Hotel.getGym()==true){
				GymLabel.setText("Yes");
			}else{
				GymLabel.setText("No");
			}
			if(Hotel.getPets()==true){
				PetsLabel.setText("Yes");
			}else{
				PetsLabel.setText("No");
			}
			if(Hotel.getPool()==true){
				PoolLabel.setText("Yes");
			}else{
				PoolLabel.setText("No");
			}
			if(Hotel.getBreakfast()==true){
				BreakfastLabel.setText("Yes");
			}else{
				BreakfastLabel.setText("No");
			}
			pic = new Image(Hotel.getHotelPicture());
			picFrame.setImage(pic);
		}else{
			HotelNameLabel.setText("");
			PriceLabel.setText(Double.toString(0));
			DistanceLabel.setText(Double.toString(0));
			BreakfastLabel.setText("");
			BarLabel.setText("");	 
			GymLabel.setText("");	
			PetsLabel.setText("");
			PoolLabel.setText("");
			pic = new Image("http://racers-republic.com/wp-content/themes/daily/images/default-thumb.gif");
			picFrame.setImage(pic);
			noStar = new Image("file:noStar.jpg");
			starFrame.setImage(noStar);
			popularityFrame.setImage(noStar);
		}
	}

	/**
	 * This method states that this class is the controller for output.
	 */
	public HotelOutputController() {
	}
}
