package View;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import Application.App;
import Classes.Hotel;
import Classes.Reviews;
import Database.ReviewJDBC;

public class ViewAllReviews implements Initializable, ControlledScreen {


	private MainController main;
	@FXML
	private TableView<Reviews> ReviewsTable = new TableView<Reviews>();
	@FXML
	private TableColumn<Reviews, String> ReviewsNameColumn = new TableColumn<Reviews,String>();
	@FXML
	private TextArea InfoLabel;
	@FXML
	private Button CancelButton;
	@FXML
	private Label DateLabel;
	@FXML
	private Label UserLabel;
	@FXML
	private Label HotelNameLabel;
	@FXML 
	private Label StarsLabel;
	@FXML 
	ArrayList<Reviews> database = ReviewJDBC.RetrieveOneReview("SCANDIC");
	@FXML
	private ObservableList<Reviews> obslist = FXCollections.observableArrayList(database);

	public static IntegerProperty updateOutput;
	public Hotel ViewHotel;
	public static ObjectProperty<Hotel> hotelreview;
	private String hotelname;


	public void setScreenParent(MainController screenParent){
		main = screenParent;
	}
	@FXML
	private void goToCheckReview(ActionEvent event){

		Reviews Reviewz = ReviewsTable.getSelectionModel().getSelectedItem();

		if (Reviewz != null) {

			App.appViewReview.set(Reviewz);

			main.setScreen(App.HotelViewReviewUserId);


		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(App.getPrimaryStage());
			alert.setTitle("No User chosen");
			alert.setHeaderText("No User Selected");
			alert.setContentText("Please select a User.");
			alert.showAndWait();

		}

	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		hotelreview = new SimpleObjectProperty<Hotel>(new Hotel());
		hotelreview.addListener(new ChangeListener<Hotel>() {

			@Override
			public void changed(ObservableValue<? extends Hotel> observable, Hotel oldValue,
					Hotel newValue) {
				ViewHotel = hotelreview.get();
				hotelname = ViewHotel.getHotelName();
				database = ReviewJDBC.RetrieveOneReview(hotelname);
				obslist = FXCollections.observableArrayList(database);
				ReviewsTable.setItems(obslist);
			}
		});

		if(!obslist.isEmpty()){
			updateOutput = new SimpleIntegerProperty(1);
			updateOutput.addListener(new ChangeListener<Number>() {
				@Override
				public void changed(ObservableValue<? extends Number> observable, Number oldValue,
						Number newValue) {
					setList();
					System.out.println("View Reviews Updated");
				}
			});
			ReviewsTable.setItems(obslist);
			ReviewsNameColumn.setCellValueFactory(cellData -> cellData.getValue().HotelUserProperty());
			showReviewDetails(null);
			ReviewsTable.getSelectionModel().selectedItemProperty().addListener(
					(observable, oldValue, newValue) -> showReviewDetails(newValue));
			System.out.println("HotelReviews initialized with content");

		}else{
			System.out.println("HotelReviews initialized without content");
		}
	}

	@FXML
	private void Cancel(ActionEvent event){
		main.setScreen(App.HotelOutputID);
	}

	@FXML public void setList(){
		database = ReviewJDBC.RetrieveOneReview(hotelname);
		obslist = FXCollections.observableList(database);
		ReviewsTable.setItems(obslist);

	}
	private void showReviewDetails(Reviews Review) {
		if (Review != null) {

			HotelNameLabel.setText(Review.getRHotelName());
			StarsLabel.setText(Integer.toString(Review.getStars()));
			UserLabel.setText(Review.getUser());
			DateLabel.setText(Review.getReviewDate());
			InfoLabel.setText(Review.getHotelInfo());

		}else{
			HotelNameLabel.setText("");
			StarsLabel.setText(Integer.toString(0));
			UserLabel.setText("");
			DateLabel.setText("");
			InfoLabel.setText("");

		}
	}


}

