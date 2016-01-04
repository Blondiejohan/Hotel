package View;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import Application.*;
import Database.SQLiteJDBC;

/**
 * LoginScreenController.java
 * Purpose: This class handles and verifies user information with database to authorize users
 * 
 * @author Jakob during sprint 2/3
 * @version 1.0
 *
 */

public class LoginScreenController implements ControlledScreen, Initializable {
   
	@FXML
	private MainController main;
    @FXML
    private Label label;
    @FXML
    private AnchorPane home_page;
    @FXML 
    private TextField username_box;
    @FXML 
    private TextField password_box;
    @FXML
    private Label invalid_label;
    @FXML
    private Button RegisterButton;
    @FXML
    private Button BackButton;
    
    public void setScreenParent(MainController screenParent){
		main = screenParent;
	}
    
    @Override
    public void initialize(URL location, ResourceBundle resources){
    	System.out.println("Login initialized");
    }   
    
    
    /**
     *  if the back button is clicked on this returns the user to previous page
     * @param event
     */
    @FXML
    private void handleBackAction(ActionEvent event){
    	main.setScreen(App.HotelOutputID);
    	invalid_label.setText("");
    }
    
    /**
     *  when login button is clicked this runs the check to test if user information is valid
     * @param event
     * @throws IOException
     */
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        
        


            /**
             *  runs isValidCredentials() if it returns true you get logged in otherwise error appears
             */
            if (isValidCredentials())
            {
            	invalid_label.setText("");
                username_box.clear();
                password_box.clear();
            	main.setScreen(App.HotelOverviewID);
            
            }
            else
            {
                username_box.clear();
                password_box.clear();
                invalid_label.setText("Invalid Username or Password"); 
            }
    }
    
    
    /**
     * Connects to the database and uses the information in the username and password field to check against db
     * 
     */
    private boolean isValidCredentials()
    {
    	/**
    	 * Default does not let in users
    	 */
        boolean let_in = false;
    
        /**
         * Opens connection
         */
        Connection c = null;
        Statement stmt = null;
        try {
            c = DriverManager.getConnection("jdbc:sqlite:test.sqlite");
            c.setAutoCommit(false);
            
            System.out.println("Login connected to Database");
            stmt = c.createStatement();
            
            /**
             * Prepares statement for database and executes query with database
             */
            ResultSet rs = stmt.executeQuery( "SELECT * FROM Users WHERE USERNAME= " + "'" + username_box.getText() + "'" 
            + " AND PASSWORD= " + "'" + password_box.getText() + "'");
            
            /**
             * Compares the results returned by the database if the information is accurate it changes let_in 
             * variable to true and the user is logged in
             */
            while ( rs.next() ) {
                 if (rs.getString("USERNAME") != null && rs.getString("PASSWORD") != null) { 
                     String  username = rs.getString("USERNAME");
                     System.out.println( "USERNAME = " + username );
                     String password = rs.getString("PASSWORD");
                     System.out.println("PASSWORD = " + password);
                     let_in = true;
                 }  
            }
            rs.close();
            stmt.close();
            c.close();
            } catch ( Exception e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                System.exit(0);
            }
            System.out.println("Login Successful");
            return let_in;
        
    }
    
    
public LoginScreenController() {
		
		
	}


        
}
