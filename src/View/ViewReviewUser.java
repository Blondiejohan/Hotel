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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import Application.App;
import Classes.Reviews;
import Database.ReviewJDBC;

/**
 * ViewReviewUser.java
 * Purpose: Class contains the view reviews by user functionality.
 * 
 * @author Adin during sprint 3/4
 * @version 1.0
 *
 */

public class ViewReviewUser implements Initializable, ControlledScreen {


	private MainController main;
	@FXML
	private TableView<Reviews> ReviewsTable = new TableView<Reviews>();
	@FXML
	private TableColumn<Reviews, String> ReviewsHotelColumn = new TableColumn<Reviews,String>();
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
	ArrayList<Reviews> database = ReviewJDBC.RetrieveReviews();
	@FXML
	private ObservableList<Reviews> obslist = FXCollections.observableArrayList(database);

	public static IntegerProperty updateOutput;
	
	private String hoteluser;
	public Reviews ViewHotel;
	public static ObjectProperty<Reviews> hotelreview;

	public void setScreenParent(MainController screenParent){
		main = screenParent;
	}
	/*
	 * Initializes screen with content from sql query databas.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		hotelreview = new SimpleObjectProperty<Reviews>(new Reviews());
		hotelreview.addListener(new ChangeListener<Reviews>() {

			@Override
			public void changed(ObservableValue<? extends Reviews> observable, Reviews oldValue,
					Reviews newValue) {
				ViewHotel = hotelreview.get();
				hoteluser = ViewHotel.getUser();
				database = ReviewJDBC.RetrieveUserReviews(hoteluser);
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
					System.out.println("User Reviews Updated");

				}
			});

			ReviewsTable.setItems(obslist);
			ReviewsHotelColumn.setCellValueFactory(cellData -> cellData.getValue().RHotelNameProperty());
 
				showReviewDetails(null);
				ReviewsTable.getSelectionModel().selectedItemProperty().addListener(
						(observable, oldValue, newValue) -> showReviewDetails(newValue));
				System.out.println("HotelUser initialized with content");

			}else{
				System.out.println("HotelUser initialized without content");
			}
		}

	/*
	 * returns to all reviews
	 */
	@FXML
	private void Cancel(ActionEvent event){
		main.setScreen(App.HotelViewAllReviewId);
	}
	/*
	 * updates list with the previous chosen user in the view all reviews page.
	 */
	@FXML public void setList(){
		database = ReviewJDBC.RetrieveUserReviews(hoteluser);
		obslist = FXCollections.observableList(database);
		ReviewsTable.setItems(obslist);

	}
	/*
	 * shows a review and all diffrent aspects in the database
	 */
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

