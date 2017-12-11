package view;

import javafx.fxml.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import ticketer.MainApp;

import java.io.IOException;

public class TicketerOverviewController {
	/**
	 * Overview elements
	 */
    @FXML
    private Button bookButton;
    
    @FXML
    private Button addStation;
    
    @FXML
    private Button changeRates;
    
    @FXML
    private Button changeDB;

    // Reference to the main application.
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public TicketerOverviewController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }
    
    /**
     * Method to handle button events to change scenes.
     * @param action the action performed
     */
    @FXML
    private void handleButtonAction(ActionEvent action) throws IOException {
    		Stage currentStage;
    		Parent root;
    		FXMLLoader loader = new FXMLLoader();
    		if (action.getSource() == bookButton) {
    			//Book Ticket button
    			//Reference to button's stage
    			currentStage = (Stage) bookButton.getScene().getWindow();
    			
    			//Load document to switch to
    			root = loader.load(getClass().getResource("BookingOverview.fxml"));
    		} else if (action.getSource() == addStation) {
    			//Change Station button
    			currentStage = (Stage) addStation.getScene().getWindow();
    			root = loader.load(getClass().getResource("AddStationOverview.fxml"));
    		} else if (action.getSource() == changeRates) {
    			//Change rates button
    			currentStage = (Stage) changeRates.getScene().getWindow();
    			root = loader.load(getClass().getResource("RateOverview.fxml"));
    		} else {
    			//Modify Database button
    			currentStage = (Stage) changeDB.getScene().getWindow();
    			root = loader.load(getClass().getResource("DBOverview.fxml"));
    		}
    		
    		//Create new scene with root and stage
    		Scene scene = new Scene(root);
    		currentStage.setScene(scene);
    		currentStage.show();
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