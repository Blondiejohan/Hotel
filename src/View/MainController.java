package View;
import java.util.HashMap;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

/**
 * MainController.java
 * Purpose: This class controls
 * 
 * @author Johan during sprint 1
 * @version 1.0
 *
 */
public class MainController extends StackPane{
	
	private HashMap<String, Node> screens = new HashMap<>();
	@FXML HotelEditController edit;
	@FXML HotelOutputController out;
	@FXML HotelOverviewController over;
	
	/**
	 * This method creates a MainController.
	 */
	public MainController(){
		super();
	}
	
	/**
	 * This method adds a screen to the HashMap.
	 * @param name
	 * @param screen
	 */
	public void addScreen(String name, Node screen){
		screens.put(name, screen);
	}
	
	/**
	 * This method gets the stated screen.
	 * @param name
	 * @return String containing name of chosen screen.
	 */
	public Node getScreen(String name){
		return screens.get(name);
	}
	
	/**
	 * This method loads a screen.
	 * @param name
	 * @param resource
	 * @return Boolean that shows if the screen loads or not.
	 */
	public boolean loadScreen(String name, String resource){
		try {
			FXMLLoader myLoader = new FXMLLoader(getClass().getResource(resource));
			Parent loadscreen = (Parent) myLoader.load();
			ControlledScreen myScreenController = ((ControlledScreen) myLoader.getController());
			myScreenController.setScreenParent(this);
			addScreen(name, loadscreen);
			return true;
		}catch (Exception e) {
			System.out.println(e.getMessage()+"Gick inte att ladda");
			return false;
		}
	}
	public boolean setScreen(final String name){
		if (screens.get(name)!= null){
			if (!getChildren().isEmpty()){
				getChildren().remove(0);
				getChildren().add(0, screens.get(name));
			} else {
				getChildren().add(screens.get(name));
			}
			return true;
		}else{
			System.out.println("Screen hasnt been loaded!");
			return false;
		}
	}
	public boolean unloadScreen(String name){
		if (screens.remove(name)== null){
			System.out.println("Screen didnt exist");
			return false;
		}else{ 
			return true;
		}
	}
}