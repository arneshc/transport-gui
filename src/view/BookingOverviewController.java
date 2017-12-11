package view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import ticketer.MainApp;

public class BookingOverviewController {
	/**
	 * Overview elements.
	 */
	@FXML
	private Button backButton;
	
	@FXML
	private Button search;
	
	@FXML
	private ChoiceBox travelMethod;
	
	@FXML
	private ChoiceBox destination;
	
	@FXML
	private Label destResult;
	
	@FXML
	private Label distResult;
	
	@FXML
	private Label costResult;
	
	@FXML
	private Label invalidLabel;
    
    // Reference to the main application.
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public BookingOverviewController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    		//Populate choicebox
    		travelMethod.getItems().add("Plane");
    		travelMethod.getItems().add("Train");
    		
	    //Populate destinations ChoiceBox with list of destinations from database
	    for (int i = 0; i < MainApp.getDestinations().size(); i++) {
	    		destination.getItems().add(MainApp.getDestinations().get(i));
	    }
    }
    
    /**
     * Method to handle button events to change scenes.
     * @param action the action performed
     */
    @FXML
    private void handleButtonAction(ActionEvent action) throws IOException {
    		FXMLLoader loader = new FXMLLoader();
    		Stage currentStage = (Stage) search.getScene().getWindow();
    		Parent root = loader.load(getClass().getResource("BookingOverview.fxml"));
    		if (action.getSource() == search) {
    			//Save and cast choicebox values
    			String choiceValue = (String) travelMethod.getValue();
    			String destinationChoice = (String) destination.getValue();
    			
    			//Check for invalid inputs or empty fields, populate labels based on input
    			if (choiceValue != null && choiceValue.equals("Plane") && destinationChoice != null) {
    				destResult.setText(destinationChoice);
    				for (int i = 0; i < MainApp.getPlaneData().size(); i++) {
    					if (MainApp.getPlaneData().get(i).getDestination().get().equals(destinationChoice)) {
    						distResult.setText("" + MainApp.getPlaneData().get(i).getDistance().getValue());
    						costResult.setText("" + MainApp.getPlaneData().get(i).getTicketCost().getValue());
    					}
    				}
    			} else if (choiceValue != null && choiceValue.equals("Train") && destinationChoice != null) {
    				destResult.setText(destinationChoice);
    				for (int i = 0; i < MainApp.getTrainData().size(); i++) {
    					if (MainApp.getTrainData().get(i).getDestination().get().equals(destinationChoice)) {
    						distResult.setText("" + MainApp.getTrainData().get(i).getDistance().getValue());
    						costResult.setText("" + MainApp.getTrainData().get(i).getTicketCost().getValue());
    					}
    				}
    			} else {
    				invalidLabel.setText("Something went wrong.");
    			}
    		} else {
    			//Reference to button's stage
    			currentStage = (Stage) backButton.getScene().getWindow();
    			
    			//Load document to switch to
    			root = loader.load(getClass().getResource("TicketerOverview.fxml"));
    			
    			//Create new scene with root and stage
        		Scene scene = new Scene(root);
        		currentStage.setScene(scene);
        		currentStage.show();
    		}
    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

    }
}
