package View;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import Database.SQLiteJDBC;
import Application.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * RegisterScreenController.java
 * Purpose: This class inserts users into the users table in the database.
 * 
 * @author Jakob during sprint 4
 * @version 1.0
 *
 */

public class RegisterScreenController implements ControlledScreen, Initializable {
	
	@FXML
	private MainController main;
	@FXML
	private TextField RegName;
	@FXML
	private TextField RegPass;
	@FXML
	private Label InvalidLabel;

    public void setScreenParent(MainController screenParent){
		main = screenParent;
	}
    
    /**
     * Handles button action for going back the previous page
     * @param event
     */
	@FXML
	private void goToOverview(ActionEvent event){
	    RegName.clear();
	    RegPass.clear();
	    InvalidLabel.setText("");
		main.setScreen(App.HotelOverviewID);
	}
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	System.out.println("Register initialized");
    }    
    
    /**
     * Uses the information in the text boxes of the register form and sends and inserts the info into db
     * @param event
     * @throws IOException
     */
    @FXML
    private void handleRegistrationAction(ActionEvent event) throws IOException{
    	if(RegName.getText().equals("") || RegPass.getText().equals("")){
    	    RegName.clear();
    	    RegPass.clear();
    		InvalidLabel.setText("Username or Password field cannot be empty");
    	}else{
	String Username = RegName.getText();
	String Password = RegPass.getText();
	SQLiteJDBC.InsertUser(Username, Password);
    RegName.clear();
    RegPass.clear();
    InvalidLabel.setText("");
	main.setScreen(App.HotelOverviewID);
    }
    }
public RegisterScreenController() {
		
		
	}

}
