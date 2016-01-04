package Application;
import Classes.Hotel;
import Classes.Reviews;
import Database.ReviewJDBC;
import Database.SQLiteJDBC;
import View.HotelEditController;
import View.HotelOutputController;
import View.HotelOverviewController;
import View.InsertReview;
import View.MainController;
import View.ViewAllReviews;
import View.ViewReviewUser;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * App.java
 * Purpose: Start the program.
 * 
 * @author Johan during sprint 1, content not created by Johan is stated elsewhere.
 * @version 1.0
 * 
 */

public class App extends Application{

	public static Stage primaryStage;
	public static String HotelOutputID = "HotelOutput";
	public static String HotelOutputFile = "HotelOutput.fxml";
	public static String HotelEditID = "HotelEdit";
	public static String HotelEditFile = "HotelEdit.fxml";
	public static String HotelOverviewID = "HotelOverview";
	public static String HotelOverviewFile = "HotelOverview.fxml";
	public static String LoginScreenID = "LoginScreen";
	public static String LoginScreenFile = "LoginScreen.fxml";
	public static String HotelInsertId = "InsertReview";
	public static String HotelInsertFile = "InsertReview.fxml";
	public static String HotelViewReviewUserId = "ViewReviewUser";
	public static String HotelViewReviewUser = "ViewReviewUser.fxml";
	public static String HotelViewAllReviewId = "ViewAllReviews";
	public static String HotelViewReviewsFile = "ViewAllReviews.fxml";
	public static String RegisterScreenID = "RegisterScreen";
	public static String RegisterScreenFile = "RegisterScreen.fxml";
	public static String HotelCompareId = "HotelCompare";
	public static String HotelCompareFile = "HotelCompare.fxml";
	public static Group root;
	public static MainController mainContainer;
	public static Scene scene;
	public static IntegerProperty update;
	public static Hotel sendHotel;
	public static ObjectProperty<Hotel> appHotel;
	public static Hotel ReviewHotel;
	public static ObjectProperty<Hotel> thisHotel;
	public static Hotel oneHotel;
	public static ObjectProperty<Hotel> appViewHotel;
	public static Reviews oneReview;
	public static ObjectProperty<Reviews> appViewReview;

	/**
	 * This method creates listeners for communication between controllers.
	 * It also loads all FXML pages, puts them in a group, puts the group in a scene
	 * and loads the scene into a stage and loads the stage.
	 * Created by Johan during sprint 1.
	 */
	@Override public void start(Stage primaryStage) throws Exception{

		@SuppressWarnings("unused")
		SQLiteJDBC hello = new SQLiteJDBC();

		@SuppressWarnings("unused")
		ReviewJDBC hello1 = new ReviewJDBC();

		update = new SimpleIntegerProperty(1);
		update.addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue,
					Number newValue) {

				int tmp = HotelOverviewController.updateOverview.get();
				tmp = tmp+1;
				HotelOverviewController.updateOverview.set(tmp);
				HotelOutputController.updateOutput.set(tmp);
			}
		});

		appHotel = new SimpleObjectProperty<Hotel>(null);
		appHotel.addListener(new ChangeListener<Hotel>() {
			@Override
			public void changed(ObservableValue<? extends Hotel> observable, Hotel oldValue,
					Hotel newValue) {
				sendHotel = appHotel.get();
				HotelEditController.hotelEdit.set(sendHotel);
				System.out.println("Hotel sent to Edit");
			}
		});

		
		thisHotel = new SimpleObjectProperty<Hotel>(null);
		thisHotel.addListener(new ChangeListener<Hotel>() {
			@Override
			public void changed(ObservableValue<? extends Hotel> observable, Hotel oldValue,
					Hotel newValue) {
				ReviewHotel = thisHotel.get();
				InsertReview.ReviewName.set(ReviewHotel);
				System.out.println("Hotel sent to Review");
			}
		});

		
		appViewHotel = new SimpleObjectProperty<Hotel>(null);
		appViewHotel.addListener(new ChangeListener<Hotel>() {
			@Override
			public void changed(ObservableValue<? extends Hotel> observable, Hotel oldValue,
					Hotel newValue) {
				oneHotel = appViewHotel.get();
				ViewAllReviews.hotelreview.set(oneHotel);
				System.out.println("Hotel sent to Review");
			}
		});

		
		appViewReview = new SimpleObjectProperty<Reviews>(null);
		appViewReview.addListener(new ChangeListener<Reviews>() {
			@Override
			public void changed(ObservableValue<? extends Reviews> observable, Reviews oldValue,
					Reviews newValue) {
				oneReview = appViewReview.get();
				ViewReviewUser.hotelreview.set(oneReview);
				System.out.println("Hotel sent to Review");
			}
		});

		mainContainer = new MainController();
		mainContainer.loadScreen(App.HotelOutputID,  App.HotelOutputFile);
		mainContainer.loadScreen(App.HotelOverviewID,  App.HotelOverviewFile);
		mainContainer.loadScreen(App.HotelEditID,  App.HotelEditFile);
		mainContainer.loadScreen(App.LoginScreenID,  App.LoginScreenFile);
		mainContainer.loadScreen(App.HotelInsertId,  App.HotelInsertFile);
		mainContainer.loadScreen(App.HotelViewReviewUserId,  App.HotelViewReviewUser);
		mainContainer.loadScreen(App.HotelViewAllReviewId,  App.HotelViewReviewsFile);
		mainContainer.loadScreen(App.HotelCompareId,  App.HotelCompareFile);
		mainContainer.loadScreen(App.RegisterScreenID,  App.RegisterScreenFile);

		// Created by Johan during sprint 5.
		primaryStage.widthProperty().addListener(new ChangeListener<Number>() {
			@Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
				if(primaryStage.getWidth() > 800){
					mainContainer.setPrefSize(primaryStage.getWidth(), primaryStage.getHeight()-30);
				}
			}
		});

		// Created by Johan during sprint 5.
		primaryStage.heightProperty().addListener(new ChangeListener<Number>() {
			@Override public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
				if(primaryStage.getWidth() > 600){
					mainContainer.setPrefSize(primaryStage.getWidth(), primaryStage.getHeight()-30);
				}
			}
		});

		mainContainer.setScreen(App.HotelOutputID);
		root = new Group();
		root.getChildren().addAll(mainContainer); 
		scene = new Scene(root);
		scene.getStylesheets().add("/View/dark.css");
		primaryStage.setMinHeight(830);
		primaryStage.setMinWidth(1280);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static Window getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}

	
}
