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
    
    @FXML
    private void handleRegisterAction(ActionEvent event) throws IOException {
    	Parent register_page_parent = FXMLLoader.load(getClass().getResource("FXMLRegisterPage.fxml"));
    	Scene register_page_scene = new Scene(register_page_parent);
    	Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	app_stage.setScene(register_page_scene);
    	app_stage.show();
    }
    
    @FXML
    private void handleBackAction(ActionEvent event){
    	main.setScreen(App.HotelOutputID);
    	invalid_label.setText("");
    }
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        
        
//            System.out.println("DO IT");

            
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
    
    private boolean isValidCredentials()
    {
        boolean let_in = false;
//        System.out.println( "SELECT * FROM Users WHERE USERNAME= " + "'" + username_box.getText() + "'" 
//            + " AND PASSWORD= " + "'" + password_box.getText() + "'" );
    
        Connection c = null;
        Statement stmt = null;
        try {
            c = DriverManager.getConnection("jdbc:sqlite:test.sqlite");
            c.setAutoCommit(false);
            
            System.out.println("Login connected to Database");
            stmt = c.createStatement();
            
            ResultSet rs = stmt.executeQuery( "SELECT * FROM Users WHERE USERNAME= " + "'" + username_box.getText() + "'" 
            + " AND PASSWORD= " + "'" + password_box.getText() + "'");
            
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
